package com.project.findme.domain.found.presentation.controller;

import com.project.findme.domain.found.presentation.dto.CreateFoundRequest;
import com.project.findme.domain.found.service.FoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("found")
@RequiredArgsConstructor
public class FoundController {

    private final FoundService foundService;

    @PostMapping
    public ResponseEntity<Void> createFound(
            @RequestPart("foundDto") @Valid CreateFoundRequest createFoundRequest,
            @RequestParam List<MultipartFile> files
    ) {
        foundService.createFound(createFoundRequest, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
