package com.inspire12.homepage.controller.template;

import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.model.entity.User;
import com.inspire12.homepage.repository.UserRepository;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.outline.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

    @GetMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("name", "index");
        return "index";
    }

    @GetMapping("/introduce")
    public String getIntroduceView() {return "introduce";}

    @GetMapping("/contact")
    public String getContactView(){
        return "contact";
    }

    @GetMapping("/about")
    public String getAboutView(Model model){
        List<User> users = userRepository.findAll();
        Collections.shuffle(users);
        model.addAttribute("users", users);
        return "about";
    }

    @GetMapping("/blog")
    public String getBlogView(){
        return "blog";
    }


    @GetMapping("/gallery")
    public String getGallaryView(){
        return "gallery";
    }

    @GetMapping("/board")
    public String getBoardView(@RequestParam(defaultValue = "all") String type, @RequestParam(defaultValue = "10") int articleCount, Model model){
        // board 종류
        try {
            List<ArticleMsg> articles = articleService.showArticleMsgsWithCount(articleCount);
            model.addAttribute("articles", articles);
        }catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("name", "board");
        return "board";
    }

    @GetMapping("/article")
    public String getSingleBlogView(@RequestParam(defaultValue = "1") int id, Model model){
        ArticleMsg article;
        try {
            article = articleService.showArticleMsgById(id);
        }catch (Exception e){
            article = new ArticleMsg();
        }
        model.addAttribute("article", article);
        model.addAttribute("name", "article");
        model.addAttribute("name", "article");
        return "article";
    }

    @GetMapping("/writing")
    public String getWriteView(Model model){

        model.addAttribute("name", "write");
        return "writing";
    }
}
