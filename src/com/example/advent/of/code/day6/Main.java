package com.example.advent.of.code.day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day6\\input.txt");

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


        int[] startingFishTimers = Arrays.stream(inputs.get(0).split(",")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Long> fishesPerTimer = new HashMap<>();
        for (int initialTimer : startingFishTimers) {
            fishesPerTimer.merge(initialTimer, (long) 1, (integer, integer2) -> integer + integer2);
        }

        for (int i = 0; i < 256; i++) {
            Map<Integer, Long> newFishesPerTimer = new HashMap<>();
            for (int timer : fishesPerTimer.keySet()) {
                if (timer == 0) {
                    newFishesPerTimer.merge(6, fishesPerTimer.get(timer), (integer, integer2) -> integer + integer2);
                    newFishesPerTimer.merge(8, fishesPerTimer.get(timer), (integer, integer2) -> integer + integer2);
                } else {
                    newFishesPerTimer.merge(timer - 1, fishesPerTimer.get(timer), (integer, integer2) -> integer + integer2);
                }
            }
            fishesPerTimer = newFishesPerTimer;
        }

        long result = fishesPerTimer.values().stream().reduce((long) 0, Long::sum);

        System.out.println(result);
    }
}
