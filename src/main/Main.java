package main;

import main.controllers.SoccerGameController;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Raw data is from \"src/main/view/csv/inputCsvFile.csv\" \n");
        System.out.println("Please Enter specific time format (mm:ss) like \"00:11\" or \"2:21\"");

        //input your time eg. 00:11 or 2:21
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        SoccerGameController controller = new SoccerGameController(input);

        //initialize the main lists were data will be manipulated
        controller.init();

        //calculate percentages of each team
        controller.calculateTeamPercentages();

        //logged output
        controller.logOutput();

        System.out.println("Also see the output in csv file to this directory \"src/main/view/csv/outputCsvFile.csv\"");
    }
}

