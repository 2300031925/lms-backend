package com.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String instructor;

    private String duration;

    private String level;

    private Integer lessons;

    private String thumbnail;

    private String video;

    private String document;

    // Getters and setters below
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Integer getLessons() { return lessons; }
    public void setLessons(Integer lessons) { this.lessons = lessons; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }

    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }
}
