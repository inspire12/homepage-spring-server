package com.inspire12.homepage.controller.template;

import com.inspire12.homepage.message.ArticleMsg;
import com.inspire12.homepage.service.board.ArticleService;
import com.inspire12.homepage.service.outline.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;

@Controller
@EnableWebMvc
public class ViewController {
    @Autowired
    HeaderService headerService;

    @Autowired
    ArticleService articleService;

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
    public String getAboutView(){
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
        List<ArticleMsg> articles = articleService.showArticleMsgsWithCount(articleCount);
        model.addAttribute("articles", articles);
        model.addAttribute("name", "board");
        return "board";
    }

    @GetMapping("/article")
    public String getSingleBlogView(@RequestParam(defaultValue = "1") int id, Model model){
        model.addAttribute("article", articleService.showArticleMsg(id));
        model.addAttribute("name", "article");
        return "article";
    }
}
