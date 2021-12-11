package com.example.advent.of.code.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day4\\input.txt");

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

        String[] calledNumbers = inputs.get(0).split(",");

        List<Bingo> bingoCards = new ArrayList<>();
        int cardsCounter = 0;

        for (int i = 2; i < inputs.size(); i++) {
            bingoCards.add(new Bingo());
            bingoCards.get(cardsCounter)
                    .insertRow(inputs.get(i++), 0)
                    .insertRow(inputs.get(i++), 1)
                    .insertRow(inputs.get(i++), 2)
                    .insertRow(inputs.get(i++), 3)
                    .insertRow(inputs.get(i++), 4);
            cardsCounter++;
        }

        int resultPart1 = 0;
        int resultPart2 = 0;
        int numberOfWinners = 0;

        for (String calledNumber1 : calledNumbers) {
            int calledNumber = Integer.parseInt(calledNumber1);
            for (Bingo card : bingoCards) {
                if (!card.isBingo() && card.checkNumber(calledNumber)) {
                    numberOfWinners++;
                    if (numberOfWinners == 1) {
                        resultPart1 = card.sumOfUnmarked() * calledNumber;
                    }
                    if (numberOfWinners == 100) {
                        resultPart2 = card.sumOfUnmarked() * calledNumber;
                    }
                }
            }
        }


        System.out.println(resultPart1);
        System.out.println(resultPart2);


    }
}
