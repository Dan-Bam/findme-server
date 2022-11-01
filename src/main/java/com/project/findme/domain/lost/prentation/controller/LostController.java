package com.project.findme.domain.lost.prentation.controller;

import com.project.findme.domain.lost.prentation.dto.CreateLostRequest;
import com.project.findme.domain.lost.service.LostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("lost")
@RequiredArgsConstructor
public class LostController {

    private final LostService lostService;

    @PostMapping("")
    public ResponseEntity<Void> createLost(
            @Valid @RequestPart CreateLostRequest createLostRequest,
            @RequestPart List<MultipartFile> multipartFiles) {
        lostService.createLost(createLostRequest, multipartFiles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
