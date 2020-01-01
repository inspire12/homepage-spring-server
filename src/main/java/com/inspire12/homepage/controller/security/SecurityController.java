package com.inspire12.homepage.controller.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inspire12.homepage.interceptor.UserLevel;
import com.inspire12.homepage.model.entity.AuthenticationToken;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.security.AuthProvider;
import com.inspire12.homepage.security.UserDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class SecurityController implements ErrorController {
    @Autowired
    AuthProvider authProvider;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserDetailService userDetailService;

    Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ObjectNode> registerUser(@RequestBody Map<String, String> requestBody, RedirectAttributes redirectAttributes) throws InvalidKeyException, NoSuchAlgorithmException {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String email = requestBody.get("email");
        String studentId = requestBody.get("student_id");
        String realName = requestBody.get("realname");

        String encryptedPassword = authProvider.encrypt(username, password);
        User user = User.create(username, email, encryptedPassword);
        ObjectNode response = objectMapper.createObjectNode();
        try {
            // 중복 체크 추가
            if (userDetailService.isExistUser(user)) {
                response.put("name", "signup");
                response.put("status", "fail");
                response.put("status_detail", "duplicated_id");
                return ResponseEntity.unprocessableEntity().body(response);
            }
            userDetailService.saveUser(user);
            response.put("status", "signup");
            response.put("name", "index");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("status", "fail");
        }
        response.put("name", "signup");
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "auth/login";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String login(
            @RequestParam Map<String, String> authenticationRequest,
            HttpSession session, RedirectAttributes redirectAttributes
    ) {
        String username = authenticationRequest.get("username");
        String password = authenticationRequest.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        User user = userDetailService.readUser(username);
        userDetailService.setLastLoginedAt(username);

        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("name", "index");
        redirectAttributes.addFlashAttribute("status", "login");
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/index";
    }



    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @RequestMapping("/error")
    @ExceptionHandler(Throwable.class)
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        logger.error("Exception during execution of SpringSecurity application", throwable);
//        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
//        model.addAttribute("errorMessage", errorMessage);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        model.addAttribute("code", status.toString());

        if (status.toString().equals("401")) {
            model.addAttribute("msg", "회원을 위한 공간입니다. 가입한 후 사용해주세요~");
        } else {
            model.addAttribute("msg", httpStatus.getReasonPhrase());
        }
        model.addAttribute("timestamp", LocalDateTime.now());

        if (httpStatus.equals(HttpStatus.FORBIDDEN)) {
            return "auth/login";
        }
        return getErrorPath();
    }

    @Override
    public String getErrorPath() {

        return "error/404";
    }
}
