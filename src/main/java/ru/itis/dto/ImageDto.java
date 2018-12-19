package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.model.Image;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
    private Long id;
    private String name;
    private String extension;
    private String directory;
    private Integer size;

    public static ImageDto from(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .name(image.getName())
                .build();
    }

    public static List<ImageDto> from(List<Image> images) {
        return images.stream().map(ImageDto::from).collect(Collectors.toList());
    }


}
