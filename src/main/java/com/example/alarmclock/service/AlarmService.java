package com.example.alarmclock.service;

import com.example.alarmclock.model.Alarm;
import org.springframework.stereotype.Service;

import  java.util.ArrayList;
import java.util.List;

@Service
public class AlarmService {
    private final List<Alarm> alarms = new ArrayList<>();

    public List<Alarm> getAllAlarms(){
        return alarms;
    }

    public void addAlarm(Alarm alarm){
        alarms.add(alarm);
    }

    public void toggleAlarm(int id) {
        for (Alarm alarm : alarms) {
            if (alarm.getId() == id) {
                alarm.setActive(!alarm.isActive());
                break;
            }
        }
    }

    public void deleteAlarm(int id) {
        alarms.removeIf(alarm -> alarm.getId() == id);
    }

    }
