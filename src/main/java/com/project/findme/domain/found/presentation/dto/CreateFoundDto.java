package com.project.findme.domain.found.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoundDto {

    private String title;

    private String description;

    private String place;


}
