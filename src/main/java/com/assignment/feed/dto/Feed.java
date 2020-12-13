package com.assignment.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    private String description;
    private String title;
    private URL browserView;
    private URL pictureURL;
    private LocalDateTime publishingDate;
}
