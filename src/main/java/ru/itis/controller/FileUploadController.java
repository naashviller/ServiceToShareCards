package ru.itis.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.form.MyUploadForm;
import ru.itis.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private ImageService imageService;


    @GetMapping("/upload")
    public String getUploadPage(Model model) {
        model.addAttribute("uploadForm", new MyUploadForm());

        return "upload";
    }

    @PostMapping("/upload")
    public String postUploadPage(HttpServletRequest request, Model model,
                                 @ModelAttribute("uploadForm") MyUploadForm form) {
        return imageService.doUpload(request, model, form);
    }


    @RequestMapping(value = "/image/{name}", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response, @PathVariable String name) throws IOException {

        File imageFile = new File("C:\\Project\\upload\\" + name);


        InputStream inputStream = new FileInputStream(imageFile);

        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();

    }

    


}
