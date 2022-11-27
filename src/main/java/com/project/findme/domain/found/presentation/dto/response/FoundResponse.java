package com.project.findme.domain.found.presentation.dto.response;

import com.project.findme.domain.found.domain.Found;
import com.project.findme.domain.lost.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class FoundResponse {

    private Long id;

    private String userId;

    private Boolean isMine;

    private String title;

    private String description;

    private String category;

    private List<String> imageUrl;

    private List<String> tags;

    private Boolean isSafe;

    private String place;

    private String latitude;

    private String longitude;

    public static FoundResponse of(Found found, List<String> imageUrl, Boolean isMine) {
        return FoundResponse.builder()
                .id(found.getId())
                .userId(found.getUser().getId())
                .isMine(isMine)
                .title(found.getTitle())
                .description(found.getDescription())
                .category(found.getCategory().getName())
                .imageUrl(imageUrl)
                .tags(found.getTags())
                .isSafe(found.isSafe())
                .place(found.getPlace())
                .latitude(found.getLatitude())
                .longitude(found.getLongitude())
                .build();
    }

}
