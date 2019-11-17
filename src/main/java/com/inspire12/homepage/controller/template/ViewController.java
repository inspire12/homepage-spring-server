package com.inspire12.homepage.controller.template;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ViewController {

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
    public String getBoardView(@RequestParam(defaultValue = "all") String type, Model model){
        // board 종류
        model.addAttribute("name", "board");
        return "board";
        //data를 html에 연결해주는 객체를 model이라고 한다.
    }


    @GetMapping("/single-blog")
    public String getSingleBlogView(){
        return "single-blog";
    }




}
