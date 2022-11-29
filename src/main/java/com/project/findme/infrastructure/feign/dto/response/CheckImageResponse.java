package com.project.findme.infrastructure.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckImageResponse {

    private List<Integer> lostId = new ArrayList<>();

}
