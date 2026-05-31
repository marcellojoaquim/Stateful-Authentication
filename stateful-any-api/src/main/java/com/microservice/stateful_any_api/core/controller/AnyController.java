package com.microservice.stateful_any_api.core.controller;

import com.microservice.stateful_any_api.core.dto.AnyResponse;
import com.microservice.stateful_any_api.core.service.AnyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/resource")
public class AnyController {

    private final AnyService anyService;

    @GetMapping
    public AnyResponse getRerource(@RequestHeader String accessToken) {
        return anyService.getData(accessToken);
    }
}
