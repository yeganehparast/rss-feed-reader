package com.assignment.feed.component;


import com.assignment.feed.dto.Feed;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class PDLComponent {

    @Bean
    public List<Feed> getDataFromPDL() {
        try {
            String url = "http://feeds.feedburner.com/PoorlyDrawnLines";
            List<Feed> feeds = new ArrayList<>();
            try (XmlReader reader = new XmlReader(new URL(url))) {
                SyndFeed feed = new SyndFeedInput().build(reader);
                log.info("PoorlyDrawingLines are being parsed ...");
                for (SyndEntry entry : feed.getEntries()) {
                    Feed toDto = Feed.builder().
                            browserView(new URL(entry.getLink())).
                            description(entry.getDescription().getValue()).
                            publishingDate(convertDateToLocalDateTime(entry.getPublishedDate())).
                            pictureURL(getImageUrl(entry.getContents())).
                            title(entry.getTitle())
                            .build();
                    feeds.add(toDto);
                }
                log.info("PoorlyDrawingLines have been parsed.");
                feeds.sort(Comparator.comparing(Feed::getPublishingDate).reversed());
                return feeds;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private LocalDateTime convertDateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private URL getImageUrl(List<SyndContent> contents) throws MalformedURLException {
        Document parse = Jsoup.parse(contents.stream().filter(f -> f != null && f.getType().equals("html")).map(SyndContent::getValue).findFirst().orElse(""));
        return new URL(parse.getAllElements().select("img[loading=\"lazy\"]").attr("src"));
    }
}
