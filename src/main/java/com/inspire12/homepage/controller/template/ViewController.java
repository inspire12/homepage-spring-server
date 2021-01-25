package com.inspire12.homepage.controller.template;

import com.inspire12.homepage.aspect.UserLevel;
import com.inspire12.homepage.common.DefaultValue;
import com.inspire12.homepage.domain.model.AppUser;
import com.inspire12.homepage.dto.message.ArticleMsg;
import com.inspire12.homepage.exception.NotAuthException;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.outline.HeaderService;
import com.inspire12.homepage.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final HeaderService headerService;
    private final ArticleService articleService;
    private final UserService userService;

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping({"/", "/index"})
    public ModelAndView index(HttpSession session, ModelAndView model) {
        AppUser user = (AppUser) session.getAttribute("user");
        model.setViewName("index");
        model.addObject("userInfo", user);
        model.addObject("adminUsers", userService.getAdminUsers());
        model.addObject("name", "index");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/signup")
    public ModelAndView getSignup(HttpSession session, ModelAndView model) {
        model.setViewName("auth/signup");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/profile")
    public ModelAndView getProfile(HttpSession session, ModelAndView model) {
        model.addObject("name", "profiles");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/introduce")
    public String getIntroduceView(HttpSession session, ModelAndView model) {
        return "introduce";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/contact")
    public String getContactView(HttpSession session, ModelAndView model) {
        return "contact";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/about")
    public String getAboutView(HttpSession session, ModelAndView model) {
        model.addObject("adminUsers", userService.getAdminUsers());
        return "about";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/blog")
    public String getBlogView(HttpSession session, ModelAndView model) {
        return "blog";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/gallery")
    public String getGalleryView(HttpSession session, ModelAndView model) {
        return "gallery";
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/board")
    public ModelAndView getBoardView(HttpSession session, ModelAndView model, @RequestParam(defaultValue = "0") Integer type, @RequestParam(defaultValue = "30") int articleCount, @RequestParam(defaultValue = "1") int pageNum) {
        // board 종류
        try {
            List<ArticleMsg> articles = articleService.showArticleMsgsWithCount(type, pageNum, articleCount);
            model.addObject("articles", articles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("name", "board");
        model.setViewName("board");
        return model;
    }


    @GetMapping("/privatepolicy")
    public String getPrivatePolicy(HttpSession session, ModelAndView model) {
        return "auth/privatepolicy";
    }

    @GetMapping("/article")
    public String getSingleBlogView(HttpSession session, ModelAndView model,
                                    @RequestParam(defaultValue = "1") Long id) {
        ArticleMsg article;
        try {
            article = articleService.showArticleMsgById(id, DefaultValue.defaultUser().getId());
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {

            }
        } catch (Exception e) {
            article = new ArticleMsg();
            e.printStackTrace();
        }
        model.addObject("article", article);
        model.addObject("name", "article");
        return "article";
    }

    @UserLevel(allow = UserLevel.UserRole.USER)
    @GetMapping("/writing")
    public ModelAndView getWriteView(HttpSession session, ModelAndView model,
                               @RequestParam(name = "id", defaultValue = "0") Long id) throws NotAuthException {
        if (id != 0){
            ArticleMsg articleMsg = articleService.showArticleMsgById(id, DefaultValue.defaultUser().getId());
//            if (! SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(articleMsg.getAuthor().getUsername())){
//                throw new NotAuthException("작성자만 글을 수정할 수 있습니다.");
//            }
            model.addObject("article", articleMsg);
        }
        model.addObject("name", "write");
        return model;
    }


    @GetMapping("/algorithm")
    public ModelAndView getAlgorithmView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/algorithm");
        return model;
    }

    @GetMapping("/gan-style")
    public ModelAndView getGanView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/gan-styles");
        return model;
    }

    @GetMapping("/ai")
    public ModelAndView getAiView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/ai");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/test")
    public ModelAndView getGifView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/test");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/opensource")
    public ModelAndView getOpensourceView(HttpSession session, ModelAndView model) {
        model.setViewName("opensource");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/kakao")
    public ModelAndView getKakaoLabView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/kakao-login");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/video")
    public ModelAndView getVideoLabView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/video");
        return model;
    }

    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/facebook-login")
    public ModelAndView getFacebookLabView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/facebook-login");
        return model;
    }


    @UserLevel(allow = UserLevel.UserRole.GUEST)
    @GetMapping("/table")
    public ModelAndView getTableView(HttpSession session, ModelAndView model) {
        model.setViewName("lab/table");
        return model;
    }

}
