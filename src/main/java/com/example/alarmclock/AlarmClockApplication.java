package com.example.alarmclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlarmClockApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmClockApplication.class, args);
    }
}

