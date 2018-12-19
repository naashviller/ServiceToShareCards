package ru.itis.service;

import org.springframework.ui.Model;
import ru.itis.dto.ImageDto;
import ru.itis.form.ImageForm;
import ru.itis.form.MyUploadForm;
import ru.itis.model.Image;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ImageService {
    List<ImageDto> getAllImages();

    Image saveImage(ImageForm imageForm);
    String doUpload(HttpServletRequest request, Model model, MyUploadForm form);

}
