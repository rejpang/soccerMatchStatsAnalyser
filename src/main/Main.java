package main;

import main.controllers.SoccerGameController;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        SoccerGameController controller = new SoccerGameController(input);
        controller.init();
        controller.calculateTeamPercentages();
        controller.logOutput();
    }
}

