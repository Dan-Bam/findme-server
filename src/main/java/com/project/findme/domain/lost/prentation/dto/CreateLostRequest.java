package com.project.findme.domain.lost.prentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLostRequest {

    private String title;

    private String description;

    private String place;

    private List<String> tags;

    private boolean safeTransaction;

}
