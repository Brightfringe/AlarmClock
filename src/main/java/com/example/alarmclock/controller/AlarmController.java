package com.example.alarmclock.controller;

import com.example.alarmclock.model.Alarm;
import com.example.alarmclock.service.AlarmService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alarms")
public class AlarmController {

    private final AlarmService alarmService;

    public AlarmController(AlarmService alarmService){
        this.alarmService = alarmService;
        System.out.println("alarmcontroller initialized");
    }
   //lists of all alarms
    @GetMapping
    public String index(Model model) {
        model.addAttribute("alarms",alarmService.getAllAlarms());
        return "index";
    }

    // to add new alarm

    @PostMapping("/add")
    public String addAlarm(@RequestParam String time,
                           @RequestParam String label,
                           @RequestParam(required = false) String active) {

        boolean isActive = (active != null && active.equals("on"));
        Alarm alarm = new Alarm(time, label, isActive);
        alarmService.addAlarm(alarm);
        return "redirect:/";
    }

    //to delete an alarm

    @PostMapping("/delete/{id}")
    public String deleteAlarm(@PathVariable int id){
        alarmService.deleteAlarm(id);
        return "redirect:/alarms";
    }

    //to toggle alarm active/inactive

    @PostMapping("/toggle/{id}")
    public String toggleAlarm(@PathVariable int id) {
        alarmService.toggleAlarm(id);
        return "redirect:/alarms";
    }

}
