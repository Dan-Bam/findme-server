package com.project.findme.infrastructure.feign.client;

import com.project.findme.infrastructure.feign.dto.request.CheckImageRequest;
import com.project.findme.infrastructure.feign.dto.request.MakeImageRequest;
import com.project.findme.infrastructure.feign.dto.response.CheckImageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "makeImageClient", url = "http://3.39.93.60:3000")
public interface ImageFeignClient {

    @PostMapping("/makeimg")
    void makeImage(MakeImageRequest makeImageRequest);

    @PostMapping("/checkimg")
    CheckImageResponse checkImage(CheckImageRequest checkImageRequest);

}
