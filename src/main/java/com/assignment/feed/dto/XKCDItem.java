package com.assignment.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XKCDItem {
    private String month;
    private String num;
    private String ink;
    private String year;
    private String news;
    private String safeTitle;
    private String transcript;
    private String alt;
    private String img;
    private String title;
    private String day;
}
