package com.project.findme.infrastructure.feign.dto.request;

import com.project.findme.domain.lost.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class CheckImageRequest {

    private String userImage;

    private Category category;

    private Long foundId;

}
