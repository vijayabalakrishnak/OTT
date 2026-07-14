package com.training.ott.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to OTT Platform";
    }

    @GetMapping("/movies")
    public String movies() {
        return "Movie List";
    }

    @GetMapping("/series")
    public String series() {
        return "Series List";
    }
}
