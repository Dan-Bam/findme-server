package com.project.findme.domain.lost.presentation.dto;

import com.project.findme.domain.image.entity.LostImage;
import com.project.findme.domain.lost.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class LostResponseDto {

    private Long id;

    private String title;

    private String description;

    private String place;

    private List<LostImage> lostImages;

    private String category;

    private List<String> tags;

    private boolean safeTransaction;

}
