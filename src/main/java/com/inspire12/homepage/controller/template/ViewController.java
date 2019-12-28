package com.inspire12.homepage.controller.template;

import com.inspire12.homepage.exception.NotAuthException;
import com.inspire12.homepage.interceptor.UserLevel;
import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.outline.HeaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@EnableWebMvc
public class ViewController {
    @Autowired
    HeaderService headerService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(ViewController.class);

    private List<User> getAdminUsers() {
        List<User> users = new ArrayList<>();
        List<String> names = Arrays.asList("inspire12", "hygoni", "Sinyoung3016", "MoonDD99", "wilook");
        for (String name : names) {
            try {
                users.add(userRepository.findById(name).get());
            } catch (Exception e) {
                logger.warn("user not found: " + name + e.toString());
            }
        }
        Collections.shuffle(users);
        return users;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("users", getAdminUsers());
        model.addAttribute("name", "index");
        return "index";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/signup")
    public String getSignup(Model model) {
        model.addAttribute("name", "signup");
        return "auth/signup";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/introduce")
    public String getIntroduceView() {
        return "introduce";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/contact")
    public String getContactView() {
        return "contact";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/about")
    public String getAboutView(Model model) {
        model.addAttribute("users", getAdminUsers());
        return "about";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/blog")
    public String getBlogView() {
        return "blog";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/gallery")
    public String getGalleryView() {
        return "gallery";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/board")
    public String getBoardView(@RequestParam(defaultValue = "all") String type, @RequestParam(defaultValue = "30") int articleCount, @RequestParam(defaultValue = "1") int pageNum, Model model) {
        // board 종류
        try {
            List<ArticleMsg> articles = articleService.showArticleMsgsWithCount(type, pageNum, articleCount);
            model.addAttribute("articles", articles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("name", "board");
        return "board";
    }

    @GetMapping("/article")
    public String getSingleBlogView(@RequestParam(defaultValue = "1") int id, Model model) {
        ArticleMsg article;
        try {
            article = articleService.showArticleMsgById(id);
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {

            }
        } catch (Exception e) {
            article = new ArticleMsg();
            e.printStackTrace();
        }
        model.addAttribute("article", article);
        model.addAttribute("name", "article");
        return "article";
    }

//    @UserLevel(allow = UserLevel.UserRole.USER)
    @GetMapping("/writing")
    public String getWriteView(Model model,
                               @RequestParam(name = "id", defaultValue = "0") Integer id) throws NotAuthException {
        if (id != 0){
            ArticleMsg articleMsg = articleService.getArticleMsgById(id);
//            if (! SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(articleMsg.getAuthor().getUsername())){
//                throw new NotAuthException("작성자만 글을 수정할 수 있습니다.");
//            }
            model.addAttribute("article", articleMsg);
        }
        model.addAttribute("name", "write");
        return "writing";
    }


    @GetMapping("/algorithm")
    public String getAlgorithmView() {
        return "lab/algorithm";
    }

    @GetMapping("/gan-style")
    public String getGanView(Model model) {
        return "lab/gan-styles";
    }

    @GetMapping("/ai")
    public String getAiView() {
        return "lab/ai";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/gif")
    public String getGifView() {
        return "lab/gif";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/opensource")
    public String getOpensourceView() {
        return "opensource";
    }

}
