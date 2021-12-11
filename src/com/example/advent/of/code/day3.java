package com.example.advent.of.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class day3 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day3\\input.txt");

        List<String> inputs = new ArrayList<>();
        int[][] bitOccurrences = new int[12][2];

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                inputs.add(st);
                for (int i = 0; i < st.length(); i++) {
                    bitOccurrences[i][Character.getNumericValue(st.charAt(i))]++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder gammaRateStr = new StringBuilder();
        StringBuilder epsilonRateStr = new StringBuilder();

        for (int[] bit : bitOccurrences
        ) {
            if (bit[0] < bit[1]) {
                gammaRateStr.append("1");
                epsilonRateStr.append("0");
            } else {
                gammaRateStr.append("0");
                epsilonRateStr.append("1");
            }
        }

        int gammaRate = Integer.parseInt(gammaRateStr.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateStr.toString(), 2);

        System.out.println(gammaRate * epsilonRate);

        List<String> oxygenGeneratorRatings = new ArrayList<>(inputs);
        List<String> co2ScrubberRatings = new ArrayList<>(inputs);
        for (int i = 0; i < 12; i++) {
            int finalI = i;

            if (oxygenGeneratorRatings.size() > 1) {
                char mostCommonCharInOxygen = MostCommonCharacterInListAtPos(oxygenGeneratorRatings, i);
                oxygenGeneratorRatings = oxygenGeneratorRatings.stream().filter(s -> s.charAt(finalI) == mostCommonCharInOxygen).collect(Collectors.toList());
            }

            if (co2ScrubberRatings.size() > 1) {
                char leastCommonCharInCO2 = LeastCommonCharacterInListAtPos(co2ScrubberRatings, i);
                co2ScrubberRatings = co2ScrubberRatings.stream().filter(s -> s.charAt(finalI) == leastCommonCharInCO2).collect(Collectors.toList());
            }
        }

        int oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatings.get(0), 2);
        int co2ScrubberRating = Integer.parseInt(co2ScrubberRatings.get(0), 2);

        System.out.println(oxygenGeneratorRating * co2ScrubberRating);
    }

    private static char MostCommonCharacterInListAtPos(List<String> list, int pos) {
        int[] bitOccurrence = new int[2];
        for (String entry : list) {
            if (entry.charAt(pos) == '0') {
                bitOccurrence[0]++;
            } else {
                bitOccurrence[1]++;
            }
        }
        return bitOccurrence[0] > bitOccurrence[1] ? '0' : '1';
    }

    private static char LeastCommonCharacterInListAtPos(List<String> list, int pos) {
        int[] bitOccurrence = new int[2];
        for (String entry : list) {
            if (entry.charAt(pos) == '0') {
                bitOccurrence[0]++;
            } else {
                bitOccurrence[1]++;
            }
        }
        return bitOccurrence[0] > bitOccurrence[1] ? '1' : '0';
    }
}
