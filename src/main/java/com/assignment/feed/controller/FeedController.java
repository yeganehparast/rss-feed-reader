package com.assignment.feed.controller;

import com.assignment.feed.component.PDLComponent;
import com.assignment.feed.component.XKCDComponent;
import com.assignment.feed.dto.Feed;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping(path = "/v1/comic")
public class FeedController {
    private PDLComponent pdlComponent;
    private XKCDComponent xkcdComponent;

    @Autowired
    private FeedController(PDLComponent pdlComponent, XKCDComponent xkcdComponent) {
        this.pdlComponent = pdlComponent;
        this.xkcdComponent = xkcdComponent;
    }

    @ApiOperation(value = "get list of comic items from feeds and combine them")
    @GetMapping(path = "/latest")
    public ResponseEntity<List<Feed>> getLatest() {
        try {
            List<Feed> dataFromPDL = pdlComponent.getDataFromPDL();
            List<Feed> dataFromXKCD = xkcdComponent.getDataFromXKCD();
            List<Feed> result = new ArrayList<>();
            result.addAll(dataFromPDL);
            result.addAll(dataFromXKCD);
            result.sort(Comparator.comparing(Feed::getPublishingDate).reversed());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

