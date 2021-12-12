package com.example.advent.of.code.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day12\\input.txt");

        List<String> inputs = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                inputs.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String, Cave> caves = new HashMap<>();

        //Create cave system
        for (String line : inputs) {
            int delimiterPos = line.indexOf('-');
            String firstCaveName = line.substring(0, delimiterPos);
            String secondCaveName = line.substring(delimiterPos + 1);
            if (!caves.containsKey(firstCaveName)) {
                caves.put(firstCaveName, new Cave(firstCaveName));
            }
            if (!caves.containsKey(secondCaveName)) {
                caves.put(secondCaveName, new Cave(secondCaveName));
            }
            Cave firstCave = caves.get(firstCaveName);
            Cave secondCave = caves.get(secondCaveName);
            firstCave.addConnection(secondCave);
            secondCave.addConnection(firstCave);
        }

        List<Cave> startingPath = new ArrayList<>();
        startingPath.add(caves.get(START));
        int resultPart1 = goThroughCaves(startingPath, true);

        System.out.println(resultPart1);

        int resultPart2 = goThroughCaves(startingPath, false);

        System.out.println(resultPart2);
    }

    private static int goThroughCaves(List<Cave> currentPath, boolean wasAnySmallCaveVisitedTwice) {
        int pathsThrough = 0;
        for (Cave connectedCave : currentPath.get(currentPath.size() - 1).getConnections()) {
            if (!connectedCave.getName().equals(START)) {
                if (connectedCave.getName().equals(END)) {
                    ++pathsThrough;
                } else if (connectedCave.isBigCave()
                        || currentPath.stream().noneMatch(cave -> cave.equals(connectedCave))
                ) {
                    List<Cave> newPath = new ArrayList<>(currentPath);
                    newPath.add(connectedCave);
                    pathsThrough += goThroughCaves(newPath, wasAnySmallCaveVisitedTwice);
                } else if (!wasAnySmallCaveVisitedTwice) {
                    List<Cave> newPath = new ArrayList<>(currentPath);
                    newPath.add(connectedCave);
                    pathsThrough += goThroughCaves(newPath, true);
                }
            }
        }
        return pathsThrough;
    }
}
