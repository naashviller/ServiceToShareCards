package ru.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VideoController {

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String videoView(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);

        return "video";
    }


}
