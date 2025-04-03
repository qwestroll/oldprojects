/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ıtısbelongstobahcivan;

import java.util.Scanner;

/**
 * Mini math quiz game with timer, nothing fancy.
 * @author qwest
 */
public class project2Bahcivan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String request = "";
        boolean LvL2 = false;
        startingMenu();
        request = scanner.next().toLowerCase();
        while (!"q".equals(request)) {
            int timer = 0;
            int timeUp = 90; //90
            double timePoint1 = 0;
            double timePoint2 = 0;

            int strikeCounter = 0;
            int trueCounter = 0;
            int questionCounter = 0;

            do {
                timePoint1 = System.currentTimeMillis(); //zaman ı method da çalıştiramama problemi
                qNums(questionCounter);
                int question = questionOperating(LvL2);
                int answer = scanner.nextInt(); //nextInt den sonra line atma problemi
                timePoint2 = System.currentTimeMillis();
                timer += ((timePoint2 - timePoint1) / 1000);

                if (question == answer) {
                    System.out.print("Correct ");
                    strikeCounter += 1;
                    trueCounter += 1;
                    questionCounter += 1;
                    if (fiver(trueCounter)) {
                        System.out.print("You got extra 5 secs.");
                    }
                } else {
                    System.out.print("Wrong ");
                    strikeCounter = 0;
                    questionCounter += 1;
                }
                if (fiver(strikeCounter)) {
                    timeUp += 5;
                }
                System.out.println("");
                starPattern(strikeCounter);
            } while (timer < timeUp);

            System.out.println("Time is up!");
            System.out.println("Correct Answers: " + trueCounter);
            System.out.println("Total Questions: " + questionCounter);
            System.out.println("Total Time: " + timeUp + " secs");
            System.out.println("");
            //menu
            request = menu(trueCounter, LvL2);
            if (request.equals("yes") && trueCounter >= 10) {
                LvL2 = true;
            }
        }
        System.out.println("Game Over!");              
    }

    public static String operators() {
        String operators = "/*-+";
        int interval = (int) (Math.random() * 4);
        return "" + operators.charAt(interval);
    }

    public static int questionOperating(boolean LvL2) {
        int number1 = 1 + (int) (Math.random() * 100);//1-100
        int number2 = 1 + (int) (Math.random() * 100);
        int number3 = 1 + (int) (Math.random() * 100);
        String operator = operators();
        String operator2 = operators();
        int result = 0;
        if (LvL2) {
            result = questionResult2(number1, number2, number3, operator, operator2);
            questionTyper2(number1, number2, number3, operator, operator2);
        } else {
            result = questionResult(number1, number2, operator);
            questionTyper(number1, number2, operator);
        }
        return result;
    }

    public static void questionTyper(int number1, int number2, String operator) {//sonucun farklı cıkmasının sebebi operators() random gönderiyor.

        switch (operator) {
            case "/":
                System.out.print(number1 + " / " + number2 + " = ");
                break;
            case "*":
                System.out.print(number1 + " * " + number2 + " = ");
                break;
            case "-":
                System.out.print(number1 + " - " + number2 + " = ");
                break;
            case "+":
                System.out.print(number1 + " + " + number2 + " = ");
                break;
            default:
                break;
        }
    }

    public static void questionTyper2(int number1, int number2, int number3, String operator, String operator2) {
        String question = "";
        switch (operator) {
            case "/":
                question += number1 + " / " + number2;
                break;
            case "*":
                question += number1 + " * " + number2;
                break;
            case "-":
                question += number1 + " - " + number2;
                break;
            case "+":
                question += number1 + " + " + number2;
                break;
            default:
                break;
        }
        switch (operator2) {
            case "/":
                question += " / " + number3 + " = ";
                break;
            case "*":
                question += " * " + number3 + " = ";
                break;
            case "-":
                question += " - " + number3 + " = ";
                break;
            case "+":
                question += " + " + number3 + " = ";
                break;
            default:
                break;
        }
        System.out.print(question);
    }

    public static int questionResult(int number1, int number2, String operator) {
        int result = 0;
        switch (operator) {
            case "/":
                result = number1 / number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "+":
                result = number1 + number2;
                break;
            default:
                break;
        }
        return result;
    }

    public static int questionResult2(int number1, int number2, int number3, String operator, String operator2) {
        int result = 0;

        switch (operator) {
            case "/":
                result = number1 / number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "+":
                result = number1 + number2;
                break;
            default:
                break;
        }
        switch (operator2) {
            case "/":
                if ("+".equals(operator)) {               //.equals yerine == kullanınca çalışmıyor.??
                    result = number1 + number2 / number3;
                } else if ("-".equals(operator)) {
                    result = number1 - number2 / number3;
                } else {
                    result = result / number3;
                }
                break;
            case "*":
                if ("+".equals(operator)) {
                    result = number1 + number2 * number3;
                } else if ("-".equals(operator)) {
                    result = number1 - number2 * number3;
                } else {
                    result = result * number3;
                }
                break;
            case "-":
                result = result - number3;
                break;
            case "+":
                result = result + number3;
                break;
            default:
                break;
        }
        return result;
    }

    public static void qNums(int questionCounter) {
        System.out.print("Q" + (questionCounter + 1) + "\t");
    }

    public static boolean fiver(int strikeCounter) {
        if (strikeCounter == 0) {
            return false;
        }
        return strikeCounter % 5 == 0;//recommended by IDE
    }

    public static String menu(int trueCounter, boolean LvL2) {
        Scanner scanner = new Scanner(System.in);
        String request = "";
        if (trueCounter >= 10) {
            if (!LvL2) {
                System.out.println("If you want to pass to the advanced mode type 'Yes'");
            }
            System.out.println("Press q to quit...");
            System.out.println("Press any key to try again except q...");
            request = scanner.next().toLowerCase();
            return request;
        } else {
            if (!LvL2) {
                System.out.println("You cannot pass to next level!");
            }
            System.out.println("Press q to quit...");
            System.out.println("Press any key to try again except q...");
            request = scanner.next().toLowerCase();
            return request;
        }
    }

    public static void startingMenu() {
        System.out.println("Welcome to Arithmetic's Exercise Program");
        System.out.println("—————————————————————");
        System.out.println("You have 90 seconds to answer as many questions as possible.");
        System.out.println("Use java arithmetic precedence rules to find answers!");
        System.out.println("Press 'q' to quit or any key to start…");
    }

    public static void starPattern(int strikeCounter) {
        if (strikeCounter % 3 == 0) {
            if (strikeCounter > 44) {
                strikeCounter /= 4;
            }
            int a = strikeCounter;
            String str = "";
            starPatternTop(strikeCounter);
            System.out.println("");
            for (int i = 0; i < strikeCounter; i++) {
                str += " ";
                for (int j = 0; j < strikeCounter; j++) {

                    System.out.print(str);
                    for (int k = 0; k < a; k++) {
                        System.out.print("*");
                        System.out.print(" ");
                    }
                    System.out.print(str);
                }
                a = a - 1;
                System.out.println("");
            }
        }
    }

    public static void starPatternTop(int strikeCounter) {
        int count = 0;
        String str = " ";
        String message = "";
        while (count < strikeCounter) {
            for (int i = 0; i < strikeCounter - 2; i++) {
                str += "  ";
            }
            if (count == 0 || count == (strikeCounter - 1)) {

                for (int j = 0; j < strikeCounter; j++) {
                    System.out.print(" ");
                    System.out.print("*");
                }
            }
            if (strikeCounter / 2 == count) {
                switch (strikeCounter) {
                    case 3:
                        message = "good";
                        break;
                    case 6:
                        message = "very good";
                        break;
                    case 9:
                        message = "great!";
                        break;
                    case 12:
                        message = "excellent!";
                        break;
                    case 15:
                        message = "perfect!";
                        break;
                    case 18:
                        message = "Math Math!";
                        break;
                    case 21:
                        message = "Math man!";
                        break;
                    case 24:
                        message = "Math expert!";
                        break;
                    default:
                        if (strikeCounter> 24) {
                            message = "yo bro you broke the Math!";
                            break;
                        } else {
                            message = "we ar' tryin' somethin' here!";
                            break;
                        }
                }
                if (!(message.length() % 2 == 0)) {
                    message += " ";
                }
                str = str.substring(message.length() / 2);
                if (strikeCounter % 2 == 0) {
                    for (int i = 0; i < strikeCounter - 2; i += 2) {
                        str = str.substring(2);//her çift sayıda 2 kayıyor kaymayı engellemek için
                    }
                }
                System.out.print(str);
                System.out.print(message);
                System.out.print(str);
            }
            count++;
        }
    }
}
