package com.example.advent.of.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class day1 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day1\\input.txt");
        List<Integer> dephts = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                dephts.add(Integer.parseInt(st));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numberOfTimesWhenDepthIncreased = 0;
        int previousWindowValue = dephts.stream().limit(3).reduce(0, Integer::sum);
        for (int i = 3; i < dephts.size(); i++) {
            if (previousWindowValue < (previousWindowValue + dephts.get(i) - dephts.get(i - 3))) {
                numberOfTimesWhenDepthIncreased++;
            }
        }

        System.out.println(numberOfTimesWhenDepthIncreased);
    }
}
