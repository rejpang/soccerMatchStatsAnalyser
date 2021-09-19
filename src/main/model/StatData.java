package main.model;

public class StatData {

    private String time;
    private String event;
    private String team;
    private int timeInSeconds;
    private int timeOfPossession;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public int getTimeOfPossession() {
        return timeOfPossession;
    }

    public void setTimeOfPossession(int timeOfPossession) {
        this.timeOfPossession = timeOfPossession;
    }
}
