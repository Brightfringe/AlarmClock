package com.example.alarmclock.model;

public class Alarm {

    private static int count = 0;
    private final int id;
    private String time;
    private String label;
    private boolean active;

    public Alarm(String time, String label, boolean active) {
        this.id = count++;
        this.time = time;
        this.label = label;
        this.active = active;
    }


    public int getId() { return id; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
