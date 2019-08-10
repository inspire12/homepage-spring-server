package com.inspire12.homepage.controller.template;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String introduce() {return "introduce";}

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }


    @GetMapping("/gallery")
    public String gallary(){
        return "gallery";
    }


    @GetMapping("/single-blog")
    public String singleBlog(){
        return "single-blog";
    }




}
