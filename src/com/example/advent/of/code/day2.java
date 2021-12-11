package com.example.advent.of.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class day2 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Adam\\IdeaProjects\\advent_of_code\\resources\\day2\\input.txt");

        int horizontalPosition = 0;
        int verticalPosition = 0;
        int aim = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] command = st.split(" ");
                if (command[0].contains("forward")) {
                    horizontalPosition += Integer.parseInt(command[1]);
                    verticalPosition += Integer.parseInt(command[1])*aim;
                }
                if (command[0].contains("down")) {
                    aim += Integer.parseInt(command[1]);
                }
                if (command[0].contains("up")) {
                    aim -= Integer.parseInt(command[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(horizontalPosition*verticalPosition);
    }
}
