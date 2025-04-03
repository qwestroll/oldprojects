/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstproject;

import java.util.Scanner;

/**
 *
 * @author qwest
 */
public class FirstProject {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DART GAME!");
        System.out.print("Enter the number of darts to be thrown: ");
        int N = scanner.nextInt();
        String Dart;
        int counter = 1;
        String coordinates;
        String region;
        String statistics;
        double x = 0;
        double y = 0;
        //numberOfHitsPerRegion
        int numberA = 0;
        int numberB = 0;
        int numberC = 0;
        int numberD = 0;
        int numberE = 0;
        int numberF = 0;
        int numberG = 0;
        int numberU = 0;

        String s = "dart";//"s"
        while (counter <= N) {

            x = (int) ((Math.random()) * 102 - 51) / 10.0;//[-5,5]
            y = (int) ((Math.random()) * 102 - 51) / 10.0;

            Dart = "";
            coordinates = "";
            region = "";

            Dart += "Dart " + counter + ": ";
            coordinates += "Coordinates: (" + x + "," + y + ")";
            region += "Region: ";

            if (x > 0 && y < 0 && x < 5 && y > -5) { //area of F
                region += "F";
                numberF++;
            } else if (x > 0 && y > 0 && x < 5 && y < 5) {// area of A and B
                if (x + y < 5) {
                    region += "A";
                    numberA++;
                } else if (x + y > 5) {
                    region += "B";
                    numberB++;
                } else {
                    region += "Undecided";
                    numberU++;
                }
            } else if (x < 0 && y < 0 && x > -5 && y > -5) {//D and E
                if (x < y) {//D
                    region += "D";
                    numberD++;
                } else if (x > y) {
                    region += "E";
                    numberE++;
                } else {
                    region += "Undecided";
                    numberU++;
                }
            } else if (x < 0 && y > 0 && x > -5 && y < 5) {// C and G

                if ((-x - 3) * (-x - 3) + (y - 3) * (y - 3) < 1) {//formula of circle used
                    region += "C";
                    numberC++;
                } else if ((-x - 3) * (-x - 3) + (y - 3) * (y - 3) > 1) {
                    region += "G";
                    numberG++;
                } else {
                    region += "Undecided";
                    numberU++;
                }

            } else {
                region += "Undecided";
                numberU++;
            }
            counter++;

            System.out.println(Dart);
            System.out.println(coordinates);
            System.out.println(region);
            System.out.println("\n");

        }//end of the loop  

        double sum = numberA + numberB + numberC + numberD + numberE + numberF + numberG + numberU;
        double temp = sum / 1000;
        double perA = ((int) (numberA / temp)) / 10.0;//percentage of A
        double perB = ((int) (numberB / temp)) / 10.0;
        double perC = ((int) (numberC / temp)) / 10.0;
        double perD = ((int) (numberD / temp)) / 10.0;
        double perE = ((int) (numberE / temp)) / 10.0;
        double perF = ((int) (numberF / temp)) / 10.0;
        double perG = ((int) (numberG / temp)) / 10.0;
        double perU = ((int) (numberU / temp)) / 10.0;

        statistics = "Region statics: \n";
        if (numberA > 1) {
            s = "darts";
        }
        statistics += "A: " + numberA + " " + s + "(" + perA + "%)\n";
        s = "dart";
        if (numberB > 1) {
            s = "darts";
        }
        statistics += "B: " + numberB + " " + s + "(" + perB + "%)\n";
        s = "dart";
        if (numberC > 1) {
            s = "darts";
        }
        statistics += "C: " + numberC + " " + s + "(" + perC + "%)\n";
        s = "dart";
        if (numberD > 1) {
            s = "darts";
        }
        statistics += "D: " + numberD + " " + s + "(" + perD + "%)\n";
        s = "dart";
        if (numberE > 1) {
            s = "darts";
        }
        statistics += "E: " + numberE + " " + s + "(" + perE + "%)\n";
        s = "dart";
        if (numberF > 1) {
            s = "darts";
        }
        statistics += "F: " + numberF + " " + s + "(" + perF + "%)\n";
        s = "dart";
        if (numberG > 1) {
            s = "darts";
        }
        statistics += "G: " + numberG + " " + s + "(" + perG + "%)\n";
        s = "dart";
        if (numberU > 1) {
            s = "darts";
        }
        statistics += "Undecided: " + numberU + " " + s + "(" + perU + "%)";

        System.out.println(statistics);
    }
}
