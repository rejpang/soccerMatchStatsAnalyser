package main.utils;

import main.model.StatData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static main.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class SoccerGameUtilsTest {

    @Test
    void convertToSeconds() {

        SoccerGameUtils soccerGameUtils = new SoccerGameUtils();
        int output = soccerGameUtils.convertToSeconds("00:11");
        int output2 = soccerGameUtils.convertToSeconds("2:21");

        assertEquals(11, output);
        assertEquals(141, output2);
    }

    @Test
    void computePercentage() {

        SoccerGameUtils soccerGameUtils = new SoccerGameUtils();
        int output = soccerGameUtils.computePercentage(31,50);
        int output2 = soccerGameUtils.computePercentage(1,10);

        assertEquals(62, output);
        assertEquals(10, output2);

    }

    @Test
    void countEvent() {

        List<StatData> statDataList = new ArrayList<>();

        for (int x = 0; x < 5; x++){
            StatData statData = new StatData();
            statData.setEvent(SHOT);
            statData.setTeam(TEAM_A);
            statDataList.add(statData);
        }

        for (int x = 0; x < 6; x++){
            StatData statData = new StatData();
            statData.setEvent(SHOT);
            statData.setTeam(TEAM_B);
            statDataList.add(statData);
        }

        for (int x = 0; x < 5; x++){
            StatData statData = new StatData();
            statData.setEvent(SCORE);
            statData.setTeam(TEAM_A);
            statDataList.add(statData);
        }

        for (int x = 0; x < 6; x++){
            StatData statData = new StatData();
            statData.setEvent(SCORE);
            statData.setTeam(TEAM_B);
            statDataList.add(statData);
        }

        SoccerGameUtils soccerGameUtils = new SoccerGameUtils();
        int output = soccerGameUtils.countEvent(SHOT, TEAM_A, statDataList);
        int output1 = soccerGameUtils.countEvent(SHOT, TEAM_B, statDataList);
        int output3 = soccerGameUtils.countEvent(SCORE, TEAM_A, statDataList);
        int output4 = soccerGameUtils.countEvent(SCORE, TEAM_B, statDataList);

        assertEquals(5, output);
        assertEquals(6, output1);
        assertEquals(5, output3);
        assertEquals(6, output4);
    }
}