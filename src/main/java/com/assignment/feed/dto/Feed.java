package com.assignment.feed.dto;

import lombok.Builder;
import lombok.Getter;

import java.net.URL;
import java.time.LocalDateTime;

@Builder
@Getter
public class Feed {
    private String description;
    private String title;
    private URL browserView;
    private URL pictureURL;
    private LocalDateTime publishingDate;
}
