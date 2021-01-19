package com.inspire12.homepage.controller.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire12.homepage.aspect.UserLevel;
import com.inspire12.homepage.common.DefaultValue;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.message.request.AuthenticationRequest;
import com.inspire12.homepage.message.request.EmailRequest;
import com.inspire12.homepage.message.request.SignupRequest;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.security.AuthProvider;
import com.inspire12.homepage.security.UserDetailService;
import com.inspire12.homepage.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SecurityController implements ErrorController {

    private final AuthProvider authProvider;

    @Autowired
    ObjectMapper objectMapper;

    private final UserDetailService userDetailService;

    private final EmailService emailService;

//    private final RedisTemplate<String, String> redisTemplate; // TODO

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @PostMapping(value = "/valid-email")
    @ResponseBody
    public ResponseEntity<String> registerUser(@Valid @RequestBody final EmailRequest requestBody, RedirectAttributes redirectAttributes) throws InvalidKeyException, NoSuchAlgorithmException {
        String email = requestBody.getEmail();
        String token = emailService.getCertifyTokenByMail(email);
        return ResponseEntity.ok().body(token);
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<CommonResponse<String>> registerUser(@RequestBody SignupRequest requestBody, RedirectAttributes redirectAttributes) throws InvalidKeyException, NoSuchAlgorithmException {
        String username = requestBody.getUsername();
        String password = requestBody.getPassword();
        String email = requestBody.getEmail();
        Integer studentId = Integer.parseInt(requestBody.getStudentId());
        String realName = requestBody.getRealname();

        String encryptedPassword = authProvider.encrypt(username, password);
        AppUser user = AppUser.create(username, email, encryptedPassword, studentId, realName);

        try {
            // 중복 체크 추가
            if (userDetailService.isExistUser(user)) {
                return ResponseEntity.unprocessableEntity().body(new CommonResponse<>(2, "중복 체크"));
            }
            userDetailService.saveUser(user);
            return ResponseEntity.ok().body(new CommonResponse<>(1, "성공"));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new CommonResponse<>(3, "실패"));
        }
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginView(Model model) {
        return "auth/login";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @PostMapping(value = "/login")
    public String doLogin(HttpSession session, @RequestParam AuthenticationRequest authenticationRequest, RedirectAttributes redirectAttributes

    ) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        AppUser user = userDetailService.findByUsername(username).orElseGet(() -> DefaultValue.defaultUser());
        userDetailService.setLastLoginAt(username);

        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("name", "index");
        redirectAttributes.addFlashAttribute("status", "login");
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/index";
    }

    @PostMapping("/password")
    public ResponseEntity<CommonResponse<String>> setPassword(HttpSession session, RedirectAttributes redirectAttributes,
                                      @RequestParam Map<String, String> authenticationRequest) throws Exception {
        String username = (String) authenticationRequest.get("username");
        String password = (String) authenticationRequest.get("password");
        String newPassword = (String) authenticationRequest.get("new_password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authProvider.authenticate(token);
        // TODO
        if (!authentication.isAuthenticated()) {
            throw new Exception();
        }
        String encryptedPassword = authProvider.encrypt(username, newPassword);
        if (userDetailService.setNewPassword(username, encryptedPassword) != 1) {
            throw new Exception();
        }
        return ResponseEntity.ok().build();
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        logger.error("Exception during execution of SpringSecurity application", throwable);
//        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
//        model.addAttribute("errorMessage", errorMessage);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));
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
        return "auth/error";
    }
}
