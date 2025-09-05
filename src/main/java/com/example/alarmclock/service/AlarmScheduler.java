package com.example.alarmclock.service;

import  com.example.alarmclock.model.Alarm;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class AlarmScheduler {

    private final AlarmService alarmService;

    public AlarmScheduler(AlarmService alarmService) {
        this.alarmService = alarmService;
    }
    @Scheduled(cron = "0 * * * * *")
    public  void checkAlarms() {
        List<Alarm> alarms = alarmService.getAllAlarms();
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);

        for (Alarm alarm : alarms){
            if(alarm.isActive() && alarm.getTime().equals(now.toString())){
                System.out.println("Alarm Ringingg!!!!" + alarm.getLabel());
            }
        }
    }
}
