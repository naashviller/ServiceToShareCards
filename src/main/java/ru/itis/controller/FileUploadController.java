package ru.itis.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.form.MyUploadForm;
import ru.itis.model.Image;
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

        Image im = imageService.findImageByName(name);
        log.error("1");
        File imageFile = new File("C:\\Project\\upload\\" + im.getName());
        log.error("2");
        response.setContentType(im.getContentType());
        log.error("3");
        response.setContentLength(im.getSize().intValue());
        log.error("4");

        try {
            log.error("5");
            InputStream is = new FileInputStream(imageFile);
            log.error("6");
            IOUtils.copy(is, response.getOutputStream());
            log.error("7");
        } catch (IOException e) {
            log.error("Could not show picture " + name, e);
        }


    }


}
