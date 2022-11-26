package com.project.findme.domain.lost.presentation.dto.response;

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

    private String category;

    private List<String> imageUrls;

    private List<String> tags;

    private Boolean isSafe;

    private String place;

    private String latitude;

    private String longitude;

}
