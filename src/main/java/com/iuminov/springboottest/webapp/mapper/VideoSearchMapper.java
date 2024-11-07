package com.iuminov.springboottest.webapp.mapper;

import com.iuminov.springboottest.webapp.domain.VideoSearch;
import com.iuminov.springboottest.webapp.entity.VideoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoSearchMapper {
    VideoEntity videoSearchToEntity(VideoSearch search);
    VideoSearch videoEntityToSearch(VideoEntity entity);
}
