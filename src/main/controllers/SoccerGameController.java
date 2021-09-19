package main.controllers;

import main.model.StatData;
import main.utils.SoccerGameUtils;
import main.view.SoccerGameDisplay;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.constants.Constants.*;

public class SoccerGameController {

    public List<StatData> statDataList = new ArrayList<>();
    public List<StatData> involvedData = new ArrayList<>();

    private final String SPLIT_BY = ",";
    private final String CSV_HEADER = "TimeStamp, Team, Possession, Shot, Score";
    private String input;
    private double possessedA = 0;
    private double possessedB = 0;
    private int percentA = 0;
    private int percentB = 0;
    private int inputInSeconds;
    private int lastIndex;

    private SoccerGameUtils utils = new SoccerGameUtils();

    public SoccerGameController(String input) {
        this.input = input;
    }

    public void init() {
        initStatDataList();
        inputInSeconds = utils.convertToSeconds(input);
        involvedData = getInvolvedData();
        setTimeOfPossession();


        initHighestPossessionPerTeam();
    }

    public void logOutput() {

        String Message = utils.composeMessage(input, possessedA, possessedB);
        String teamALog = utils.composeCsvOutput(input, TEAM_A, percentA, utils.countEvent(SHOT, TEAM_A, involvedData), utils.countEvent(SCORE, TEAM_A, involvedData));
        String teamBLog = utils.composeCsvOutput(input, TEAM_B, percentB, utils.countEvent(SHOT, TEAM_B, involvedData), utils.countEvent(SCORE, TEAM_B, involvedData));

        SoccerGameDisplay soccerGameDisplay = new SoccerGameDisplay();
        soccerGameDisplay.display(Message);
        soccerGameDisplay.display(teamALog);
        soccerGameDisplay.display(teamBLog);

        writeToFile(CSV_HEADER + "\n" + teamALog + "\n" + teamBLog);
    }

    public void writeToFile(String message){
        try (PrintWriter writer = new PrintWriter(new File("src/main/view/csv/outputCsvFile.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append(message);

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calculateTeamPercentages() {
        percentA = utils.computePercentage(Math.abs(possessedA), inputInSeconds);
        percentB = utils.computePercentage(Math.abs(possessedB), inputInSeconds);
    }

    private void initHighestPossessionPerTeam() {
        for (StatData statData : involvedData) {
            if (statData.getTeam().equals(TEAM_B)) {
                possessedB = statData.getTimeOfPossession();
            } else {
                possessedA = statData.getTimeOfPossession();
            }
        }
    }

    private List<StatData> getInvolvedData() {
        List<StatData> countedData = new ArrayList<>();

        for (StatData statData : statDataList) {
            if (inputInSeconds >= statData.getTimeInSeconds() && statData.getTeam() != null) {
                countedData.add(statData);
            }
        }
        return countedData;
    }

    public void initStatDataList() {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/view/csv/inputCsvFile.csv"));
            br.readLine();
            while ((line = br.readLine()) != null) {
                statDataList.add(getStatData(line));
            }
            lastIndex = statDataList.size() - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StatData getStatData(String line) {
        List<String> dataFromCsv = Arrays.asList(line.split(SPLIT_BY));
        StatData statData = new StatData();

        statData.setTime(dataFromCsv.get(0));
        statData.setEvent(dataFromCsv.get(1));

        if (NUM_OF_COLUMNS == dataFromCsv.size()) {
            statData.setTeam(dataFromCsv.get(2));
        }

        statData.setTimeInSeconds(utils.convertToSeconds(statData.getTime()));
        return statData;
    }

    public void setTimeOfPossession() {
        for (int x = 0; x < involvedData.size(); x++) {

            StatData currentData = involvedData.get(x);
            StatData nextData = getNextStatData(x);
            String currentTeam = currentData.getTeam();

            if (!utils.isLastIndex(involvedData, x)) {
                currentData.setTimeOfPossession(nextData.getTimeInSeconds() - getRecentOppositeTeam(x, currentTeam).getTimeOfPossession());
            } else {
                StatData recentOppositeTeam = getRecentOppositeTeam(x, currentTeam);
                int currentTimeInSeconds = currentData.getTimeInSeconds();
                int currentTeamTimePossession = currentTimeInSeconds - recentOppositeTeam.getTimeOfPossession();
                currentData.setTimeOfPossession(currentTeamTimePossession + (inputInSeconds - currentTimeInSeconds));
            }
        }
    }

    private StatData getRecentOppositeTeam(int index, String team){
        for(int x = index; x > 0; x--){
            if(!team.equals(involvedData.get(x).getTeam())){
                return involvedData.get(x);
            }
        }
        return involvedData.get(0);
    }

    private StatData getNextStatData(int x) {
        StatData nextData = null;
        if (!utils.isLastIndex(statDataList, x)) {
            nextData = statDataList.get(x + 1);
        }
        return nextData;
    }
}
