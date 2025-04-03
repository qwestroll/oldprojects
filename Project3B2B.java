/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3b2b;

import java.util.Scanner;

/**
 *
 * @author qwest
 */
public class Project3B2B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of compAlien population: ");
        int numberOfAlien = scanner.nextInt();
        int[] alienCounter = new int[1];//0 starts from
        int[] IDCounter = new int[1];//1 starts from
        char[] DNA = new char[128];
        double[] aliens = new double[numberOfAlien];
        int gender;
        int health;
        int ID;
        double alien;
        int dCounts = 0;
        int deadCounts;
        int option;
        System.out.println("Simulating compAlien species…");
        System.out.println("——————————————————————————");

        for (int i = 0; i < numberOfAlien; i++) {//for
            gender = DNAGenerator(DNA);//1 male , 2 female
            health = health(DNA);
            ID = IDGenerator(IDCounter);

            alien = compAlien(gender, health, ID);

            alienCommunity(alien, aliens, alienCounter);
            //System.out.println(aliens[i]);

        }
        System.out.println("——————————————————————————");
        System.out.println("compAlien population is generated!");
        boolean quit = false;

        while (!quit) {
            deadCounts = 0;
            for (int i = 0; i < aliens.length; i++) {
                alien = aliens[i];
                if ((int) (alien * 100) % 100 > 18) {
                    deadCounts++;
                  
                }
            }
            if (IDCounter[0] == deadCounts) {
                option = 9;
            
                System.out.println("All aliens are dead.");
                System.out.println("Game Over!");
            } else if (IDCounter[0] - 1 == deadCounts) {
                option = 9;
               
                System.out.println("One alien left all alone!");
                System.out.println("Game Over!");
            } else {
                setOptions();
                option = scanner.nextInt();
            }
            boolean controlAtOptions = false;
            while (!controlAtOptions) {

                switch (option) {
                    case 1:
                        controlAtOptions = true;
                        aliens = matingCompAliens(aliens, DNA, IDCounter);

                        break;
                    case 2:
                        controlAtOptions = true;
                        aliens = matingRandom(aliens, DNA, IDCounter);

                        break;
                    case 3:
                        controlAtOptions = true;

                        dCounts = statistics(aliens, numberOfAlien, IDCounter, dCounts);
                        numberOfAlien = IDCounter[0];
                        break;
                    case 4:
                        controlAtOptions = true;
                        plague(aliens);
                        break;
                    case 5:
                        controlAtOptions = true;
                        russianRoulette(aliens, IDCounter);
                        break;
                    case 9:
                        controlAtOptions = true;
                        quit = true;
                        break;
                    default:
                        option = scanner.nextInt();
                        break;
                }
            }
        }

      
    }

    public static void setOptions() {
        System.out.println("Choose an option: ");/////////////burda kaldık aslında burayı method da yazabilirsin.
        System.out.println("(1)Mate two compAliens");
        System.out.println("(2)Randomly mate a set of compAliens");
        System.out.println("(3)Show statistics");
        System.out.println("(4)Plague Simulator");
        System.out.println("(5)Russian Roulette");
        System.out.println("(9)Quit");
        System.out.print("Enter an option: ");

    }

    public static void alienCommunity(double alien, double[] aliens, int[] alienCounter) {// deneme yapıyorum signature u voide çevirmeyi unutma (health max denemesi)==47?? ID=2147484 i found a bug lol its not a bug its a limit
        aliens[alienCounter[0]] = alien;//bu id2147484 den sonra health 47 dönüyor! çok garip double sınırından dolayı oluyor muhtemelen teoride max health 42 ama pratikte max health 17

        int health = (int) (alien * 100) % 100;
        int ID = (int) (alien / 10);
        int gender = (int) (alien % 10);

        String girlsBoys = "";
        if (gender == 1) {
            girlsBoys += "Male";
        } else {
            girlsBoys += "Female";
        }

        System.out.println("ID: " + ID + ", " + girlsBoys + ", Health: " + health);

        //System.out.println(ID);
        //System.out.println(health);
        // System.out.println(gender);
        alienCounter[0]++;

    }

    public static double compAlien(int gender, double health, int ID) {//DNA i tutmuyorum  //ID //gender //health     /well
        double sum = 0;
        sum += health / 100;

        sum += gender;
        sum += ID * 10;
        return sum;
    }

    public static int DNAGenerator(char[] DNA) {

        for (int i = 0; i < DNA.length; i++) {
            int determiner = (int) (Math.random() * 3);
            char letter;
            if (determiner == 0) {
                letter = 'C';
            } else if (determiner == 1) {
                letter = 'S';
            } else {
                letter = 'E';
            }
            DNA[i] = letter;
        }
        int gender;
        if (DNA[127] == 'S') {
            gender = 1;//male
        } else if (DNA[127] == 'C') {
            gender = 2;//female
        } else {
            gender = 3;
        }

        return gender;
    }

    public static int health(char[] DNA) {
        int counter = 0;
        for (int i = 0; i < DNA.length - 2; i++) {
            if (DNA[i] == 'C' && DNA[i + 1] == 'S' && DNA[i + 2] == 'E') {
                counter++;
            }
        }

        //System.out.println(counter);
        return counter;
    }

    public static int IDGenerator(int[] countKeeper) {
        countKeeper[0]++;
        int ID = countKeeper[0];

        return ID;
    }

    public static double[] matingRandom(double[] arr, char[] DNA, int[] IDCounter) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of compAlien pairs to reproduce: ");
        int numberOfPairs = scanner.nextInt();
        int count = 0;
        for (int i = 0; i < numberOfPairs; i++) {
            boolean loopWhile = true;
            int ID1;
            int ID2;

            double alien1;
            double alien2;

            int gender1;
            int gender2;

            do {
                ID1 = (int) (Math.random() * IDCounter[0] + 1);
                alien1 = arr[ID1 - 1];
                gender1 = (int) (alien1 % 10);

                ID2 = (int) (Math.random() * IDCounter[0] + 1);
                alien2 = arr[ID2 - 1];
                gender2 = (int) (alien2 % 10);

                if (ID1 != ID2 && gender1 != gender2 && (gender1 == 1 || gender2 == 1)) {
                    loopWhile = false;
                }
                if ((alien1 * 100) % 100 > 17 || (alien2 * 100) % 100 > 17) {
                    loopWhile = true;

                }
            } while (loopWhile);////////////

            String genderX;
            String genderY;

            if (gender1 == 1) {
                genderX = "Male";
            } else {
                genderX = "Female";
            }
            if (gender2 == 1) {
                genderY = "Male";
            } else {
                genderY = "Female";
            }

            System.out.print("compAlien " + ID1 + "(" + genderX + ")" + " and " + ID2 + "(" + genderY + ")" + " Mate: ");
            if (reproduceCalculater(alien1, alien2)) {
                double[] arr2 = new double[arr.length + 1];
                for (int j = 0; j < arr.length; j++) {
                    arr2[j] = arr[j];

                }
                arr = arr2;

                int gender = DNAGenerator(DNA);//1 male , 2 female
                int health = health(DNA);
                int ID = IDGenerator(IDCounter);

                double alien = compAlien(gender, health, ID);
                arr[arr.length - 1] = alien;
                count++;

            }

        }

        int a = 1;
        for (int i = 0; i < count; i++) {
            double alien = arr[arr.length - a];
            int health = (int) (alien * 100) % 100;
            int ID = (int) (alien / 10);
            int gender = (int) (alien % 10);

            String girlsBoys = "";
            if (gender == 1) {
                girlsBoys += "Male";
            } else {
                girlsBoys += "Female";
            }

            System.out.println("ID: " + ID + ", " + girlsBoys + ", Health: " + health);

            a++;
        }

        return arr;
    }

    public static double[] matingCompAliens(double[] arr, char[] DNA, int[] IDCounter) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID of first compAlien: ");
        int ID1 = scanner.nextInt();
        System.out.print("Enter ID of second compAlien: ");
        int ID2 = scanner.nextInt();

        boolean loopWhile = true;

        double alien1 = arr[ID1 - 1];
        double alien2 = arr[ID2 - 1];
        while (loopWhile) {/////////////////
            if ((int) (alien1 * 100) % 100 < 18 && (int) (alien2 * 100) % 100 < 18) {/////////////////

                int gender1 = (int) (alien1 % 10);
                int gender2 = (int) (alien2 % 10);
                String genderX;
                String genderY;
                boolean IsReproduce = false;

                while (loopWhile) {
                    if (gender1 == 1) {
                        genderX = "Male";
                    } else {
                        genderX = "Female";
                    }
                    if (gender2 == 1) {
                        genderY = "Male";
                    } else {
                        genderY = "Female";
                    }

                    if (gender1 != gender2 && (gender1 == 1 || gender2 == 1)) {

                        System.out.print("compAlien " + ID1 + "(" + genderX + ")" + " and " + ID2 + "(" + genderY + ")" + " Mate: ");
                        IsReproduce = reproduceCalculater(alien1, alien2);
                        if (IsReproduce) {
                            double[] arr2 = new double[arr.length + 1];
                            for (int i = 0; i < 10; i++) {
                                arr2[i] = arr[i];
                            }
                            arr = arr2;

                            int gender = DNAGenerator(DNA);//1 male , 2 female
                            int health = health(DNA);
                            int ID = IDGenerator(IDCounter);

                            double alien = compAlien(gender, health, ID);
                            arr[arr.length - 1] = alien;

                        }

                        loopWhile = false;

                    } else {
                        System.out.println("compAlien " + ID1 + "(" + genderX + ")" + " and " + ID2 + "(" + genderY + ")" + " Mate: Invalid");
                        loopWhile = true;
                        System.out.print("Enter ID of first compAlien: ");
                        ID1 = scanner.nextInt();
                        System.out.print("Enter ID of second compAlien: ");
                        ID2 = scanner.nextInt();
                        alien1 = arr[ID1 - 1];
                        alien2 = arr[ID2 - 1];

                        gender1 = (int) (alien1 % 10);
                        gender2 = (int) (alien2 % 10);
                    }
                }
                if (gender1 != gender2 && (gender1 == 1 || gender2 == 1) && IsReproduce) {
                    double alien = arr[arr.length - 1];
                    int health = (int) (alien * 100) % 100;
                    int ID = (int) (alien / 10);
                    int gender = (int) (alien % 10);

                    String girlsBoys = "";
                    if (gender == 1) {
                        girlsBoys += "Male";
                    } else {
                        girlsBoys += "Female";
                    }

                    System.out.println("ID: " + ID + ", " + girlsBoys + ", Health: " + health);
                }
            }
            if ((int) (alien1 * 100) % 100 > 18) {
                System.out.println(ID1 + " is dead.");
                System.out.print("Enter ID of first compAlien: ");
                ID1 = scanner.nextInt();
            }
            if ((int) (alien2 * 100) % 100 > 18) {////////////
                System.out.println(ID2 + " is dead.");
                System.out.print("Enter ID of second compAlien: ");
                ID2 = scanner.nextInt();
            }
        }

        return arr;
    }

    public static boolean reproduceCalculater(double alien1, double alien2) {
        int health1 = (int) (alien1 * 100) % 100;
        int health2 = (int) (alien2 * 100) % 100;

        double reproduceRate = (health1 + health2);
        double reproduce = (int) (Math.random() * 35);//N =34

        double percentage = (int) (reproduceRate / 0.34 * 10) / 10.0;   //1.7 = %5
        System.out.print("Offspring chance " + percentage + "%.");

        if (reproduce <= reproduceRate) {
            System.out.println("They have 1 offspring!");
            return true;
        }
        System.out.println("Not this time!");
        return false;
    }

    public static int statistics(double[] aliens, int numberOfAlien, int[] IDCounter, int dCounts) {

        Scanner scanner = new Scanner(System.in);
        double healthTotal = 0;
        int male = 0;
        int female = 0;
        double alien;
        int health;
        int gender;
        int deadCount = 0;

        for (int i = 0; i < aliens.length; i++) {
            alien = aliens[i];
            if ((int) (alien * 100) % 100 < 18) {///////////////

                gender = (int) (alien % 10);
                if (gender == 1) {
                    male++;

                } else {
                    female++;
                }

            } else {
                deadCount++;
            }
        }

        //int healthStatistic=healthTotal/aliens.length;
        int total = male + female;
        if (total == 0) {
            total = -1;
        }

        double percentageNumber = 100.0 / total;
        double malePercentage = ((int) (percentageNumber * male * 10)) / 10.0;
        double femalePercentage = ((int) (percentageNumber * female * 10)) / 10.0;
        System.out.println("FEMALE population = " + femalePercentage + "%");
        System.out.println("MALE population = " + malePercentage + "%");

        System.out.print("Enter an health threshold [between 1 and 17]: ");
        int healthLimit = scanner.nextInt();
        int selected = 0;

        int numberOfNewMembers = IDCounter[0] - numberOfAlien;
        for (int i = 0; i < aliens.length; i++) {
            alien = aliens[i];
            if ((int) (alien * 100) % 100 < 18) {///////////
                health = (int) (alien * 100) % 100;

                healthTotal += health;
                if (healthLimit <= health) {
                    selected++;
                }
            }
        }
        double selectedPercentage = (int) (percentageNumber * selected * 10) / 10.0;
        System.out.println(selectedPercentage + "% of compAlien population have a health of " + healthLimit + " or higher");
        double healthAverage = ((int) (healthTotal / total * 10)) / 10.0;
        System.out.println("Health average of compAliens colony is " + healthAverage);
        deadCount = deadCount - dCounts;
        if (numberOfNewMembers > 0) {
            if (numberOfNewMembers < 2) {
                System.out.println(numberOfNewMembers + " new member of colony.");
            } else {
                System.out.println(numberOfNewMembers + " new members of colony.");
            }

        } else if (deadCount > 0) {
            if (deadCount < 2) {
                System.out.println(deadCount + " member of colony died.");
            } else {
                System.out.println(deadCount + " members of colony died.");
            }
        }
        deadCount = deadCount + dCounts;
        return deadCount;
    }

    public static void plague(double[] aliens) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Welcome to plague simulator.");
        System.out.println("----------------------------------");
        System.out.println("(1)Weak virus");
        System.out.println("(2)Killer virus");
        System.out.println("(3)Destroyer virus");
        boolean loopWhile = true;
        double deathPossibility = 0;
        while (loopWhile) {
            System.out.print("Choose the virus: ");
            int answer = scanner.nextInt();

            switch (answer) {
                case 1:
                    deathPossibility = 80;
                    loopWhile = false;
                    break;
                case 2:
                    deathPossibility = 250;
                    loopWhile = false;
                    break;
                case 3:
                    deathPossibility = 600;
                    loopWhile = false;
                    break;
                default:
                    break;
            }
        }

        double alien;
        int health;
        int ID;
        int gender;
        int counter = 0;

        for (int i = 0; i < aliens.length; i++) {
            alien = aliens[i];
            health = (int) (alien * 100) % 100;
            ID = (int) (alien / 10);
            gender = (int) (alien % 10);
            if (health > 17) {
            } else {
                int genderImmunity;
                switch (gender) {
                    case 1:
                        genderImmunity = 6;
                        break;
                    case 2:
                        genderImmunity = 17;
                        break;
                    default:
                        genderImmunity = 2;

                        break;
                }
                double healthImmunity = health * 0.5;

                String girlsBoys = "";
                if (gender == 1) {
                    girlsBoys += "Male";
                } else {
                    girlsBoys += "Female";
                }
                deathPossibility = deathPossibility - genderImmunity - healthImmunity;
                int death = (int) (Math.random() * 20);
                if (death <= deathPossibility) {
                    alien = (int) (alien);
                    alien += 0.9;
                    System.out.println("ID: " + ID + ", " + girlsBoys + ", Health: " + "dead!");
                } else {
                    counter++;
                }

            }
        }
        System.out.println(counter + " aliens survived.");

    }

    public static void russianRoulette(double[] aliens, int[] IDCounter) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Welcome to Russian Roulette.");
        System.out.println("----------------------------------");
        System.out.println("(1) To choose first alien by yourself");
        System.out.println("(2) To choose aliens with shuffling");

        double alien1 = 0;
        double alien2 = 0;
        int answer = 0;
        while (answer != 1 && answer != 2) {
            answer = scanner.nextInt();
        }

        int ID1 = 0;
        int ID2 = 0;
        if (answer == 1) {
            System.out.print("Enter first ID: ");
            ID1 = scanner.nextInt();
            alien1 = aliens[ID1 - 1];
            while ((int) (alien1 * 100) % 100 > 18) {
                System.out.print("Enter another ID please:");
                ID1 = scanner.nextInt();
                alien1 = aliens[ID1 - 1];
            }
            System.out.print("Enter second ID: ");
            ID2 = scanner.nextInt();
            alien2 = aliens[ID2 - 1];
            while (ID1 == ID2 || (int) (alien2 * 100) % 100 > 18) {
                System.out.print("Enter another ID please:");
                ID2 = scanner.nextInt();
                alien2 = aliens[ID2 - 1];
            }
        } else if (answer == 2) {

            while (ID1 == ID2 || (((int) (alien1 * 100) % 100 > 18) || ((int) (alien2 * 100) % 100 > 18))) {
                ID1 = (int) (Math.random() * IDCounter[0] + 1);
                alien1 = aliens[ID1 - 1];
                ID2 = (int) (Math.random() * IDCounter[0] + 1);
                alien2 = aliens[ID2 - 1];

            }
            System.out.println("ID: " + ID1);
            System.out.println("ID: " + ID2);
        }
        int gun = -1;
        int chance = 6;
        boolean shot = false;

        int tailsOrHeads = (int) Math.random() * 2; //tails first one starts

        String str = "";
        if (tailsOrHeads == 0) {
            str = "Tails";
        } else {
            str = "Heads";
        }
        System.out.println("A coin flipped: " + str);

        while (!shot) {
            if (tailsOrHeads % 2 == 0) {
                gun = (int) (Math.random() * chance);
                System.out.println(ID1 + " took the gun and shot!");
                if (gun == 0) {
                    shot = true;
                    System.out.println("BANG!");
                    aliens[ID2 - 1] = (int) (alien2);
                    aliens[ID2 - 1] += 0.9;
                    System.out.println(ID2 + " is died. ;(");
                } else {
                    System.out.println(ID1 + " missed!");
                    System.out.println("1/" + (chance - 1) + " chance");
                }

                
            } else {
                gun = (int) (Math.random() * chance);
                System.out.println(ID2 + " he has his chance and shot!");
                if (gun == 0) {
                    shot = true;
                    System.out.println("BANG!");
                    aliens[ID1 - 1] = (int) (alien1);
                    aliens[ID1 - 1] += 0.9;
                    System.out.println(ID1 + " is died. ;(");
                } else {
                    System.out.println(ID2 + " missed!");
                    System.out.println("1/" + (chance - 1) + " chance");
                }
               
            }
            
                tailsOrHeads++;
                chance--;

        }

    }
}
