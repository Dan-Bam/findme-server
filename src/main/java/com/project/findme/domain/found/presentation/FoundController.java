package com.project.findme.domain.found.presentation;

import com.project.findme.domain.found.presentation.dto.request.CreateFoundRequest;
import com.project.findme.domain.found.presentation.dto.request.UpdateFoundRequest;
import com.project.findme.domain.found.presentation.dto.response.FoundResponse;
import com.project.findme.domain.found.service.FoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("found")
@RequiredArgsConstructor
public class FoundController {

    private final FoundService foundService;

    @PostMapping
    public ResponseEntity<Void> createFound(
            @RequestPart("foundDto") @Valid CreateFoundRequest createFoundRequest,
            @RequestPart(required = false) MultipartFile file
    ) {
        foundService.createFound(createFoundRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{foundId}")
    public ResponseEntity<Void> updateFound(
            @PathVariable Long foundId,
            @RequestPart("foundDto") @Valid UpdateFoundRequest updateFoundRequest,
            @RequestPart(required = false) MultipartFile file
    ) {
        foundService.updateFound(foundId, updateFoundRequest, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{foundId}")
    public ResponseEntity<Void> deleteFound(@PathVariable Long foundId) {
        foundService.deleteFound(foundId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{foundId}")
    public ResponseEntity<FoundResponse> findFoundById(@PathVariable Long foundId) {
        return new ResponseEntity<>(foundService.findById(foundId), HttpStatus.OK);
    }

}
