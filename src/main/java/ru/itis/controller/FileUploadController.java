package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.form.MyUploadForm;
import ru.itis.service.ImageService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/upload")
    public String getUploadPage(Model model) {
        model.addAttribute("uploadForm", new MyUploadForm());

        return "upload";
    }

    @PostMapping("/upload")
    public String postUploadPage(HttpServletRequest request, Model model, @ModelAttribute("uploadForm") MyUploadForm form) {
        return imageService.doUpload(request, model, form);
    }


}
