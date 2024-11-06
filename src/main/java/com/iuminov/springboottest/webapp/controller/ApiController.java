package com.iuminov.springboottest.webapp.controller;

import com.iuminov.springboottest.webapp.domain.Video;
import com.iuminov.springboottest.webapp.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ApiController {
    private final VideoService videoService;

    @GetMapping("/api/videos")
    public List<Video> all() {
        return videoService.getVideos();
    }

    @PostMapping("/api/videos")
    public Video newVideo(@RequestBody Video newVideo) {
        return videoService.create(newVideo);
    }
}
