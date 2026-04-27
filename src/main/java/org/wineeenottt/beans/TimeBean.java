package org.wineeenottt.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named("timeBean")
@ViewScoped
public class TimeBean implements Serializable {
    private String currentTime;
    private String currentDate;
    private Long serverTimestamp;

    @PostConstruct
    public void init() {
        update();
    }

    public void update() {
        LocalDateTime now = LocalDateTime.now();
        currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        currentDate = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        serverTimestamp = Instant.now().toEpochMilli();

    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }
    public Long getServerTimestamp() {
        return serverTimestamp;
    }
}

