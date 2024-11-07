package com.iuminov.springboottest.webapp.controller;

import com.iuminov.springboottest.webapp.domain.UniversalSearch;
import com.iuminov.springboottest.webapp.domain.Video;
import com.iuminov.springboottest.webapp.domain.VideoSearch;
import com.iuminov.springboottest.webapp.entity.VideoEntity;
import com.iuminov.springboottest.webapp.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private final VideoService videoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", videoService.getVideos());
        return "index";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute Video newVideo) {
        videoService.create(newVideo);
        return "redirect:/";
    }

    @GetMapping("/react")
    public String react() {
        return "react";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch(@ModelAttribute VideoSearch search, Model model) {
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }
}
