package com.iuminov.springboottest.webapp.service;

import com.iuminov.springboottest.webapp.domain.UniversalSearch;
import com.iuminov.springboottest.webapp.domain.Video;
import com.iuminov.springboottest.webapp.domain.VideoSearch;
import com.iuminov.springboottest.webapp.entity.VideoEntity;
import com.iuminov.springboottest.webapp.mapper.VideoSearchMapper;
import com.iuminov.springboottest.webapp.repository.VideoRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository repository;
    private final VideoSearchMapper videoSearchMapper;

    @Getter
    private List<Video> videos = List.of(
            new Video("Island"),
            new Video("Spring Boot 3, Watch and Learn"),
            new Video("Titanic")
    );

    public Video create(Video newVideo) {
        ArrayList<Video> newVideos = new ArrayList<>(videos);
        newVideos.add(newVideo);
        videos = List.copyOf(newVideos);
        return newVideo;
    }

    public List<VideoEntity> search(VideoSearch videoSearch) {
        if (StringUtils.hasText(videoSearch.name())
                && StringUtils.hasText(videoSearch.description())) {
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(
                            videoSearch.name(), videoSearch.description());
        }

        if (StringUtils.hasText(videoSearch.name())) {
            return repository.findByNameContainsIgnoreCase
                    (videoSearch.name());
        }

        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase
                    (videoSearch.description());
        }

        return Collections.emptyList();
    }

    public List<VideoEntity> search(UniversalSearch search) {
        VideoEntity probe = new VideoEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }

        Example<VideoEntity> example = Example.of(probe, ExampleMatcher.matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example);
    }

    @PostConstruct
    void initDatabase() {
        repository.save(new VideoEntity("Need HELP with your SPRING BOOT 3 App?",
                "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."));
        repository.save(new VideoEntity("Don't do THIS to your own CODE!",
                "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"));
        repository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",
                "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
    }
}
