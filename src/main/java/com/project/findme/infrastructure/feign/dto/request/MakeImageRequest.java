package com.project.findme.infrastructure.feign.dto.request;

import com.project.findme.domain.lost.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class MakeImageRequest {

    private Long lostId;

    private Category category;

    private List<String> tags;

}
