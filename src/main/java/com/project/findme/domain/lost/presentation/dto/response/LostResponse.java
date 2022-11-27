package com.project.findme.domain.lost.presentation.dto.response;

import com.project.findme.domain.lost.domain.Lost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class LostResponse {

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

    public static LostResponse of(Lost lost, List<String> imageUrl, Boolean isMine) {
        return LostResponse.builder()
                .id(lost.getId())
                .userId(lost.getUser().getId())
                .isMine(isMine)
                .title(lost.getTitle())
                .description(lost.getDescription())
                .category(lost.getCategory().getName())
                .imageUrl(imageUrl)
                .tags(lost.getTags())
                .isSafe(lost.isSafe())
                .place(lost.getPlace())
                .latitude(lost.getLatitude())
                .longitude(lost.getLongitude())
                .build();
    }

}
