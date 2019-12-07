package com.inspire12.homepage.controller.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @RequestMapping(value = "/signup", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String registerUser(@RequestParam Map<String, String> requestBody,
                               Model model, RedirectAttributes redirectAttributes) throws InvalidKeyException, NoSuchAlgorithmException {
        String name = requestBody.get("username");
        String password = requestBody.get("password");
        String email = requestBody.get("email");
        String encryptedPassword = authProvider.encrypt(name, password);
        User user = User.create(name, email, encryptedPassword);
        try {
            // 중복 체크 추가
            if (userDetailService.isExistUser(user)) {
                model.addAttribute("name", "signup");
                model.addAttribute("status", "fail");
                return "redirect:signup";
            }
            userDetailService.saveUser(user);
            model.addAttribute("status", "signup");
            model.addAttribute("name", "index");
            return "redirect:index";
        } catch (Exception e) {
            model.addAttribute("status", "fail");
        }
        model.addAttribute("name", "signup");
        return "redirect:signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public RedirectView login(
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

//        new AuthenticationToken(user.getName(), user.getAuthorities(), session.getId());
        RedirectView redirectView = new RedirectView("index", true);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "index");
        objectNode.put("status", "login");
        redirectAttributes.addFlashAttribute(objectNode);
        return redirectView;
    }

    @RequestMapping("/error")
//    @ExceptionHandler(Throwable.class)
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        logger.error("Exception during execution of SpringSecurity application", throwable);
//        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
//        model.addAttribute("errorMessage", errorMessage);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        model.addAttribute("code", status.toString());
        model.addAttribute("msg", httpStatus.getReasonPhrase());
        model.addAttribute("timestamp", LocalDateTime.now());

        return getErrorPath();
    }

    @Override
    public String getErrorPath() {
        return "error/404";
    }
}
