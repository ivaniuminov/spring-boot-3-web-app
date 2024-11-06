package com.iuminov.springboottest.webapp.service;

import com.iuminov.springboottest.webapp.domain.Video;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class VideoService {

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
}
