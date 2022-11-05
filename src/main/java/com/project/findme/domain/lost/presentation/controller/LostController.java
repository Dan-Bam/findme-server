package com.project.findme.domain.lost.presentation.controller;

import com.project.findme.domain.lost.presentation.dto.CreateLostRequest;
import com.project.findme.domain.lost.presentation.dto.LostResponseDto;
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
            @Valid @RequestPart(name = "lostDto") CreateLostRequest createLostRequest,
            @RequestPart List<MultipartFile> files) {
        lostService.createLost(createLostRequest, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{lostId}")
    public ResponseEntity<LostResponseDto> findByLostId(@PathVariable("lostId") Long lostId) {
        LostResponseDto responseDto = lostService.findById(lostId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<LostResponseDto>> findLostAll() {
        List<LostResponseDto> responseDto = lostService.findAll();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<LostResponseDto>> findByCategory(@RequestParam("category") String category) {
        List<LostResponseDto> responseDto = lostService.findByCategory(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
