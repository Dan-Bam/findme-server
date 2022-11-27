package com.project.findme.infrastructure.feign.client;

import com.project.findme.infrastructure.feign.dto.request.MakeImageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "makeImageClient", url = "http://3.39.93.60:3000/makeimg")
public interface MakeImageFeign {

    @PostMapping
    void makeImage(MakeImageRequest makeImageRequest);

}
