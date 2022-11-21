package com.project.findme.domain.lost.presentation.dto.response;

import com.project.findme.domain.image.entity.LostImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class LostResponse {

    private Long id;

    private String title;

    private String description;

    private List<String> lostImages;

    private String category;

    private List<String> tags;

    private String place;

    private String latitude;

    private String longitude;

}
