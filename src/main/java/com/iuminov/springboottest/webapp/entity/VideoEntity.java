package com.iuminov.springboottest.webapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class VideoEntity {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    public VideoEntity() {
        this(null, null);
    }
    public VideoEntity(String name, String description) {
        this.id = null;
        this.description = description;
        this.name = name;
    }
}
