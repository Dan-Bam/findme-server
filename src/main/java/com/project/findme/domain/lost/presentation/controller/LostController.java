package com.project.findme.domain.lost.presentation.controller;

import com.project.findme.domain.lost.presentation.dto.request.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.request.UpdateLostRequest;
import com.project.findme.domain.lost.presentation.dto.response.LostResponse;
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

    @PostMapping
    public ResponseEntity<Void> createLost(
            @Valid @RequestPart(name = "lostDto") CreateLostRequest createLostRequest,
            @RequestPart List<MultipartFile> files
    ) {
        lostService.createLost(createLostRequest, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{lostId}")
    public ResponseEntity<Void> updateLost(
            @PathVariable Long lostId,
            @Valid @RequestPart(name = "lostDto") UpdateLostRequest updateLostRequest,
            @RequestPart List<MultipartFile> files
    ) {
        lostService.updateLost(lostId, updateLostRequest, files);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{lostId}")
    public ResponseEntity<Void> deleteLost(@PathVariable Long lostId) {
        lostService.deleteLost(lostId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{lostId}")
    public ResponseEntity<LostResponse> findByLostId(@PathVariable("lostId") Long lostId) {
        return new ResponseEntity<>(lostService.findById(lostId), HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<LostResponse>> findLostAll() {
        return new ResponseEntity<>(lostService.findAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LostResponse>> findByCategoryAndPlace(
            @RequestParam("category") String category,
            @RequestParam("place") String place) {
        return new ResponseEntity<>(lostService.findByCategoryAndPlace(category, place), HttpStatus.OK);
    }
}
