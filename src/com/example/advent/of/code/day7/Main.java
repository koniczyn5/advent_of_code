package com.example.advent.of.code.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.commons.math3.stat.descriptive.rank.Median;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day7\\input.txt");

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


        double[] crabPositions = Arrays.stream(inputs.get(0).split(",")).mapToDouble(Double::parseDouble).toArray();

        Median median = new Median();

        int medianValueFloored = (int) Math.floor(median.evaluate(crabPositions));

        int resultPart1 = (int) Arrays.stream(crabPositions).reduce(0, (left, right) -> left + Math.abs(right - medianValueFloored));

        System.out.println(resultPart1);

        int averageValueFloored = (int) Math.floor(Arrays.stream(crabPositions).average().orElse(0));

        long resultPart2 = (long) Arrays.stream(crabPositions).reduce(0, (total, currentPosition) -> {
            double distance = Math.abs(currentPosition - averageValueFloored);
            double fuelCost = (1+distance)*distance/2;
            return total + fuelCost;
        });

        System.out.println(resultPart2);
    }
}
