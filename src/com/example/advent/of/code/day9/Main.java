package com.example.advent.of.code.day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day9\\input.txt");

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

        int resultPart1 = 0;
        long resultPart2 = 0;
        int width = inputs.get(0).length();
        int[][] higherNeighboursCount = new int[inputs.size()][width];

        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < width; j++) {
                if(i==0) {
                    higherNeighboursCount[i][j]++;
                }
                if(j==0) {
                    higherNeighboursCount[i][j]++;
                }
                int currentPoint = Integer.parseInt(inputs.get(i).substring(j,j+1));
                if(i==inputs.size()-1) {
                    higherNeighboursCount[i][j]++;
                } else {
                    int nextPoint = Integer.parseInt(inputs.get(i+1).substring(j,j+1));
                    if(currentPoint < nextPoint) {
                        higherNeighboursCount[i][j]++;
                    } else if (currentPoint > nextPoint) {
                        higherNeighboursCount[i+1][j]++;
                    }
                }
                if(j==width-1) {
                    higherNeighboursCount[i][j]++;
                } else {
                    int nextPoint = Integer.parseInt(inputs.get(i).substring(j+1,j+2));
                    if(currentPoint < nextPoint) {
                        higherNeighboursCount[i][j]++;
                    } else if (currentPoint > nextPoint) {
                        higherNeighboursCount[i][j+1]++;
                    }
                }
                if (higherNeighboursCount[i][j]==4) {
                    resultPart1+=(currentPoint+1);
                }
            }
        }

        System.out.println(resultPart1);
        System.out.println(resultPart2);
    }
}
