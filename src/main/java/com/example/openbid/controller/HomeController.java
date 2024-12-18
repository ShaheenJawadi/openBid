package com.example.openbid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "publicPages/index";
    }


    @GetMapping("/item/{slug}")
    public String item(@PathVariable("slug") String slug, Model model) {
        //Item item = itemService.getItemBySlug(slug)
        model.addAttribute("itemSlug", slug);

            return "publicPages/itemPage";

    }
}
