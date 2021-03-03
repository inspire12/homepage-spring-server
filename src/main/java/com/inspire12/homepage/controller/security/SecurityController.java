package com.inspire12.homepage.controller.security;


import com.inspire12.homepage.aspect.MethodAllow;
import com.inspire12.homepage.common.DefaultValue;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.domain.service.UserDomainService;
import com.inspire12.homepage.message.request.AuthenticationRequest;
import com.inspire12.homepage.message.request.EmailRequest;
import com.inspire12.homepage.message.request.SignupRequest;
import com.inspire12.homepage.message.response.CommonResponse;
import com.inspire12.homepage.security.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final AuthProvider authProvider;

    private final UserDomainService userDomainService;
//    private final EmailService emailService;

//    private final RedisTemplate<String, String> redisTemplate; // TODO

    @MethodAllow(allow = MethodAllow.UserRole.GUEST)
    @PostMapping(value = "/valid-email")
    @ResponseBody
    public ResponseEntity<String> registerUser(@Valid @RequestBody final EmailRequest requestBody, RedirectAttributes redirectAttributes) throws InvalidKeyException, NoSuchAlgorithmException {
        String email = requestBody.getEmail();
//TODO
        //String token = emailService.getCertifyTokenByMail(email);
        return ResponseEntity.ok().build();
    }

    @MethodAllow(allow = MethodAllow.UserRole.GUEST)
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<CommonResponse<String>> registerUser(@RequestBody SignupRequest requestBody, RedirectAttributes redirectAttributes) throws InvalidKeyException, NoSuchAlgorithmException {
        String username = requestBody.getUsername();
        String password = requestBody.getPassword();
        String email = requestBody.getEmail();
        int studentId = Integer.parseInt(requestBody.getStudentId());
        String realName = requestBody.getRealname();

        String encryptedPassword = authProvider.encrypt(username, password);
        AppUser user = AppUser.create(username, email, encryptedPassword, studentId, realName);

        try {
            // 중복 체크 추가
            if (userDomainService.isExistUser(user)) {
                return ResponseEntity.unprocessableEntity().body(new CommonResponse<>(2, "중복 체크"));
            }
            userDomainService.saveUser(user);
            return ResponseEntity.ok().body(new CommonResponse<>(1, "성공"));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new CommonResponse<>(3, "실패"));
        }
    }

    @MethodAllow(allow = MethodAllow.UserRole.GUEST)
    @GetMapping("/login")
    public String getLoginView(Model model) {
        return "auth/login";
    }

    @MethodAllow(allow = MethodAllow.UserRole.GUEST)
    @PostMapping(value = "/login")
    public String doLogin(HttpSession session, @ModelAttribute AuthenticationRequest authenticationRequest, RedirectAttributes redirectAttributes) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        AppUser user = userDomainService.findByUsername(username).orElseGet(DefaultValue::defaultUser);
        user.setLastAccessAt(LocalDateTime.now());
        userDomainService.saveUser(user);

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

        userDomainService.setNewPassword(username, encryptedPassword);
        return ResponseEntity.ok().build();
    }

//    @MethodAllow(allow = MethodAllow.UserRole.GUEST)
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(status.toString()));
//        model.addAttribute("code", status.toString());
//
//        if (status.toString().equals("401")) {
//            model.addAttribute("msg", "회원을 위한 공간입니다. 가입한 후 사용해주세요~");
//        } else {
//            model.addAttribute("msg", httpStatus.getReasonPhrase());
//        }
//        model.addAttribute("timestamp", LocalDateTime.now());
//
//        if (httpStatus.equals(HttpStatus.FORBIDDEN)) {
//            return "auth/login";
//        }
//        return getErrorPath();
//    }

//    @Override
//    public String getErrorPath() {
//        return "auth/error";
//    }
}
