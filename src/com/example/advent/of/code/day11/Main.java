package com.example.advent.of.code.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int SIZE = 10;

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day11\\input.txt");

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


        int[][] startingOctopusEnergyLevels = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                startingOctopusEnergyLevels[i][j] = Integer.parseInt(inputs.get(i).substring(j, j + 1));
            }
        }

        long resultPart1 = 0;
        boolean didAllOctopusesFlash = false;
        long resultPart2 = 0;

        int[][] prevDayEnergyLevels = startingOctopusEnergyLevels;
        for (int i = 0; i < 100; i++) {
            int[][] currentDayEnergyLevels = prevDayEnergyLevels;
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    //step part 1
                    currentDayEnergyLevels[j][k]++;
                    //step part 2
                    if (currentDayEnergyLevels[j][k] == 10) {
                        flashOctopus(currentDayEnergyLevels, j, k);
                    }
                }
            }
            //step part 3
            int flashCounter= 0;
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (currentDayEnergyLevels[j][k] > 9) {
                        flashCounter++;
                        resultPart1++;
                        currentDayEnergyLevels[j][k] = 0;
                    }
                }
            }
            if(flashCounter==SIZE*SIZE) {
                didAllOctopusesFlash = true;
                resultPart2 = i+1;
            }
            prevDayEnergyLevels = currentDayEnergyLevels;
        }

        System.out.println(resultPart1);

        if(!didAllOctopusesFlash) {
            int i=100;
            while (!didAllOctopusesFlash) {
                int[][] currentDayEnergyLevels = prevDayEnergyLevels;
                for (int j = 0; j < SIZE; j++) {
                    for (int k = 0; k < SIZE; k++) {
                        //step part 1
                        currentDayEnergyLevels[j][k]++;
                        //step part 2
                        if (currentDayEnergyLevels[j][k] == 10) {
                            flashOctopus(currentDayEnergyLevels, j, k);
                        }
                    }
                }
                //step part 3
                int flashCounter= 0;
                for (int j = 0; j < SIZE; j++) {
                    for (int k = 0; k < SIZE; k++) {
                        if (currentDayEnergyLevels[j][k] > 9) {
                            flashCounter++;
                            currentDayEnergyLevels[j][k] = 0;
                        }
                    }
                }
                if(flashCounter==SIZE*SIZE) {
                    didAllOctopusesFlash = true;
                    resultPart2 = i+1;
                }
                prevDayEnergyLevels = currentDayEnergyLevels;
                i++;
            }
        }
        System.out.println(resultPart2);
    }

    private static void flashOctopus(int[][] octopusEnergyLevels, int x, int y) {
        boolean topEdge = x == 0;
        boolean bottomEdge = x == SIZE - 1;
        boolean leftEdge = y == 0;
        boolean rightEdge = y == SIZE - 1;
        if (!topEdge) {
            if (++octopusEnergyLevels[x - 1][y] == 10) {
                flashOctopus(octopusEnergyLevels, x - 1, y);
            }
            if (!leftEdge) {
                if (++octopusEnergyLevels[x - 1][y - 1] == 10) {
                    flashOctopus(octopusEnergyLevels, x - 1, y - 1);
                }
            }
            if (!rightEdge) {
                if (++octopusEnergyLevels[x - 1][y + 1] == 10) {
                    flashOctopus(octopusEnergyLevels, x - 1, y + 1);
                }
            }
        }
        if (!leftEdge) {
            if (++octopusEnergyLevels[x][y - 1] == 10) {
                flashOctopus(octopusEnergyLevels, x, y - 1);
            }
        }
        if (!rightEdge) {
            if (++octopusEnergyLevels[x][y + 1] == 10) {
                flashOctopus(octopusEnergyLevels, x, y + 1);
            }
        }
        if (!bottomEdge) {
            if (++octopusEnergyLevels[x + 1][y] == 10) {
                flashOctopus(octopusEnergyLevels, x + 1, y);
            }
            if (!leftEdge) {
                if (++octopusEnergyLevels[x + 1][y - 1] == 10) {
                    flashOctopus(octopusEnergyLevels, x + 1, y - 1);
                }
            }
            if (!rightEdge) {
                if (++octopusEnergyLevels[x + 1][y + 1] == 10) {
                    flashOctopus(octopusEnergyLevels, x + 1, y + 1);
                }
            }
        }
    }
}
