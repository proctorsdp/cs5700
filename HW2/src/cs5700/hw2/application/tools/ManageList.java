package cs5700.hw2.application.tools;

import cs5700.hw2.application.subjects.Athlete;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageList {
    private static ManageList instance = null;
    private CalculateInfo calc;

    private ManageList() {
        this.calc = CalculateInfo.getInstance();
    }

    public static ManageList getInstance() {
        if (instance == null) {
            instance = new ManageList();
        }
        return instance;
    }



    public void sortByPace(ArrayList<Athlete> list) {
        int maxIndex = 0;
        double maxPace;
        double newPace;
        Athlete[] sortedList = new Athlete[list.size()];

        for (int i = sortedList.length-1; i >= 0; i--) {
            maxPace = 0;
            for (Athlete a : list) {
                newPace = calc.getTotalSeconds(calc.getPace(a));
                if (newPace > maxPace) {
                    maxPace = newPace;
                    maxIndex = list.indexOf(a);
                }
            }
            sortedList[i] = list.get(maxIndex);
            list.remove(maxIndex);
        }

        list.addAll(Arrays.asList(sortedList));
    }


    public String listToString(ArrayList<Athlete> list) {
        StringBuilder leaderBoard = new StringBuilder();
        leaderBoard.append("===========================================\n");
        leaderBoard.append(titleBlock());
        for (Athlete a : list) {
            leaderBoard.append(indexToString(a));
        }
        leaderBoard.append("===========================================\n");
        return leaderBoard.toString();
    }

    public String indexToString(Athlete a) {
        long[] time = a.isFinished() ? calc.getElapsedTime(a) : calc.getPace(a);
        double dist = calc.metersToMiles(a.getLocationOnCourse());

        String athleteInfo = String.format("# %-3d  %-7.7s, %-5.5s ", a.getBibNumber(), a.getLastName(), a.getFirstName());

        if (a.isDidNotStart() || a.isQuitRace()) {
            athleteInfo += String.format("%21s\n", "DID NOT COMPLETE");
        } else {
            athleteInfo += a.isFinished() ? String.format("%10s ", "FINISHED") : String.format("%10.2f ", dist);
            athleteInfo += String.format("%4d:%02d:%02d\n", time[0], time[1], time[2]);
        }

        return athleteInfo;
    }

    public String titleBlock() {
        String title = String.format("%-6s %-14s %10s %10s\n", "ID", "Name", "Distance", "Pace");
        title += "-------------------------------------------\n";
        return title;
    }
}
