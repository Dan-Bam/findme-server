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

    private String title;

    private String description;

    private String category;

    private List<String> imageUrl;

    private List<String> tags;

    private String place;

    private String latitude;

    private String longitude;

    public static FoundResponse of(Found found, List<String> imageUrl) {
        return FoundResponse.builder()
                .id(found.getId())
                .userId(found.getUser().getId())
                .title(found.getTitle())
                .description(found.getDescription())
                .category(found.getCategory().getName())
                .imageUrl(imageUrl)
                .tags(found.getTags())
                .place(found.getPlace())
                .latitude(found.getLatitude())
                .longitude(found.getLongitude())
                .build();
    }

}
