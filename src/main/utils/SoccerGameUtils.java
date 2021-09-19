package main.utils;

import main.model.StatData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SoccerGameUtils {

    public int convertToSeconds(String time) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
            Date date = sdf.parse(time);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND);
    }

    public int computePercentage(double possessedTime, int inputInSeconds){
        return (int) Math.round((possessedTime / inputInSeconds) * 100);
    }

    public int countEvent(String event, String team, List<StatData> involvedData){
        int count = 0;
        for(StatData statData : involvedData){
            if(event.equals(statData.getEvent()) && team.equals(statData.getTeam())){
                count++;
            }
        }
        return count;
    }

    public String composeMessage(String input, double possessedA, double possessedB){
        return "Given the time stamp "+input+", Team A possessed "+(int) Math.round(possessedA)+" seconds, Team B possessed "+(int) Math.round(possessedB)+" second. The output will be:";
    }

    public String composeCsvOutput(String input, String team, int prc, int shot, int score){
        return input + ", "+team+", " + prc + "%, " + shot + ", " + score;
    }

    public boolean isLastIndex(List<StatData> list, int index) {
        return index == list.size() - 1;
    }

}
