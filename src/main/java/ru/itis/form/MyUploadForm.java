package ru.itis.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MyUploadForm {
    private String description;
    private MultipartFile[] image;
}
