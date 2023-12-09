package com.example.ap_proj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Streams {
    public int readScore() throws IOException {
        int score=0;
        try{
            Scanner scanner= new Scanner(new File("score.txt"));
            if (scanner.hasNextLine()) {
                score = Integer.parseInt(scanner.nextLine().trim());
            }
            scanner.close();
        }catch(Exception e) {
            System.out.println(e);
        }
        return score;
    }

    public void writeScore(int score) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("score.txt"))) {
            writer.println(score);
        }
    }

    public int readCherry() throws IOException {
        int cherry=0;
        try{
            Scanner scanner= new Scanner(new File("cherry.txt"));
            if (scanner.hasNextLine()) {
                cherry = Integer.parseInt(scanner.nextLine().trim());
            }
            scanner.close();
        }catch(Exception e) {
            System.out.println(e);
        }

        return cherry;
    }

    public void writeCherry(int cherry) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("cherry.txt"))) {
            writer.println(cherry);
        }
    }
}
