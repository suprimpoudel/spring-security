package com.np.suprimpoudel.controller;

import com.np.suprimpoudel.dto.Base;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    @GetMapping("/test-me")
    public Base<String> testMe() {
        return new Base<>("Hello World");
    }
}

