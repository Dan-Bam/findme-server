package com.project.findme.domain.lost.presentation.dto.request;

import com.project.findme.domain.lost.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLostRequest {

    @NotBlank(message = "제목이 입력되지 않았습니다")
    private String title;

    @NotBlank(message = "설명이 입력되지 않았습니다")
    private String description;

    @NotNull(message = "카테고리가 입력되지 않았습니다")
    private Category category;

    @NotNull(message = "태그가 입력되지 않았습니다")
    private List<String> tags;

    @NotNull(message = "안심거래가 선택되지 않았습니다")
    private boolean safeTransaction;

    @NotBlank(message = "장소가 선택되지 않았습니다")
    private String place;

    @NotBlank(message = "위도 값이 입력되지 않았습니다")
    private String latitude;

    @NotBlank(message = "경도 값이 입력되지 않았습니다")
    private String longitude;

}
