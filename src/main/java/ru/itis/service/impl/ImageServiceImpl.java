package ru.itis.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.ImageDto;
import ru.itis.form.ImageForm;
import ru.itis.form.MyUploadForm;
import ru.itis.model.Image;
import ru.itis.repositories.ImageRepository;
import ru.itis.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<ImageDto> getAllImages() {
        return ImageDto.from(imageRepository.findAll());
    }

    @Override
    public Image saveImage(ImageForm imageForm) {
        Image image = Image.builder()
                .name(imageForm.getName())
                .extension(imageForm.getExtension())
                .directory(imageForm.getDirectory())
                .size(imageForm.getSize())
                .build();
        imageRepository.save(image);
        return image;
    }

    public String doUpload(HttpServletRequest request, Model model, MyUploadForm form) {

        String description = form.getDescription();
        String uploadPath = "C:\\Users\\Павлова Анастасия\\Documents\\upload";

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        MultipartFile[] images = form.getImage();
        List<File> uploadedFiles = new ArrayList<File>();
        File serverFile = null;

        for (MultipartFile image : images) {

            String name = image.getOriginalFilename();

            if (name != null && name.length() > 0) {
                try {
                    serverFile = new File(uploadDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(image.getBytes());
                    stream.close();
                    uploadedFiles.add(serverFile);
                } catch (Exception e) {

                }
                String extension = FilenameUtils.getExtension(name);
                Long size = (serverFile.length())/1024;
//                String displaySize = FileUtils.byteCountToDisplaySize(size);


                ImageForm currentImage = new ImageForm();
                currentImage.setName(name);
                currentImage.setExtension(extension);
                currentImage.setDirectory(uploadPath);
                currentImage.setSize(Long.valueOf(size));

                imageService.saveImage(currentImage);
            }

        }
        model.addAttribute("description", description);
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "list";

    }


}
