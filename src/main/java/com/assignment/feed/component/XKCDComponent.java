package com.assignment.feed.component;

import com.assignment.feed.dto.Feed;
import com.assignment.feed.dto.XKCDItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class XKCDComponent {

    @Bean
    public List<Feed> getDataFromXKCD() {
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<XKCDItem> items = new ArrayList<>();
        log.info("XKCD items are being parsed ...");
        XKCDItem latest = restTemplate.getForObject("https://xkcd.com/info.0.json", XKCDItem.class);
        items.add(latest);
        int lastItem = Integer.parseInt(latest.getNum());
        for (int i = 1; i < 10; i++) {
            items.add(restTemplate.getForObject("https://xkcd.com/{id}/info.0.json", XKCDItem.class, lastItem - 10 + i));
        }
        log.info("XKCD items have been parsed.");
        return items.stream().map(XKCDComponent::outBound).collect(Collectors.toList());
    }

    private static Feed outBound(XKCDItem item) {
        try {
            return Feed.builder().title(item.getTitle()).
                    pictureURL(new URL(item.getImg())).
                    publishingDate(LocalDateTime.of(Integer.parseInt(item.getYear()), Integer.parseInt(item.getMonth()), Integer.parseInt(item.getDay()), 0, 0)).
                    description(item.getAlt()).
                    browserView(new URL(String.format("https://xkcd.com/%s", item.getNum()))).
                    build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
