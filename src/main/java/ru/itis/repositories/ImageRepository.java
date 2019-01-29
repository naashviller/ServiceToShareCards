package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageById(Long id);

    Image findImageByName(String name);


}
