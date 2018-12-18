package ru.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.form.MyUploadForm;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @GetMapping("/upload")
    public String getUploadPage(Model model) {
        model.addAttribute("uploadForm", new MyUploadForm());

        return "upload";
    }

    @PostMapping("/upload")
    public String postUploadPage(HttpServletRequest request, Model model, @ModelAttribute("uploadForm") MyUploadForm form) {
        return this.doUpload(request, model, form);
    }

    private String doUpload(HttpServletRequest request, Model model, MyUploadForm form) {

        String description = form.getDescription();
        String uploadPath = "C:\\Users\\Павлова Анастасия\\Documents\\upload";

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile[] images = form.getImage();
        List<File> uploadedFiles = new ArrayList<File>();

        for (MultipartFile image : images) {

            String name = image.getOriginalFilename();

            if (name != null && name.length() > 0) {
                try {
                    File serverFile = new File(uploadDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(image.getBytes());
                    stream.close();
                    uploadedFiles.add(serverFile);
                } catch (Exception e) {

                }


            }

        }
        model.addAttribute("description", description);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "list";

    }

}
