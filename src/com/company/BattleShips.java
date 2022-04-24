package com.company;

import java.util.Scanner;

public class BattleShips {
    String sea[][] = new String[10][10];
    String compsea[][] = new String[10][10];
    int x, y;

    public static int playerShips = 5;
    public static int computerShips = 5;

    public void CreateSea() {
        //First part of ocean map
        System.out.print("  ");
        for (int p = 0; p < 10; p++)
            System.out.print(p);
        System.out.println();

        //middle part of ocean map

        for (int x = 0; x < 10; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < 10; y++) {
                sea[x][y] = " ";
                System.out.print(" ");

            }
            System.out.print("|" + x);
            System.out.println();
        }

        //Last part of Ocean Map
        System.out.print("  ");
        for (int p = 0; p < 10; p++)
            System.out.print(p);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("****Welcome to BattleShips Game****");
        System.out.println("Right now,the sea is empty");
        BattleShips obj = new BattleShips();
        obj.CreateSea();
        obj.deployPlayerShip();
        //obj.showPlayerShip();
        obj.deployComputerShips();
        do {
            obj.playerturn();
            obj.computerturn();
            obj.showPlayerShip();

            System.out.println();
            System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
            System.out.println();
        } while (playerShips != 0 && computerShips != 0);
        obj.gameOver();

    }

    public void deployPlayerShip() {
        System.out.println("Deploy your ships");
        Scanner input = new Scanner(System.in);
        //Deploying five ships for battle
        for (int p = 0; p < playerShips; ) {
            System.out.println("Enter X for your" + " " + p + " " + "ship: ");
            int x = input.nextInt();
            System.out.println("Enter Y for your" + " " + p + " " + "ship: ");
            int y = input.nextInt();
            if ((x >= 0 && x < 10) && (y >= 0 && y < 10) && (sea[x][y] == " ")) {
                sea[x][y] = "@";
                p++;
            } else if ((x >= 0 && x < 10) && (y >= 0 && y < 10) && (sea[x][y] == "@")) {
                System.out.println("you cannot place two or more ships on same location");
            } else if ((x < 0 || x >= 10) || (y < 0 || y >= 10)) {
                System.out.println("You can't place ships outside the" + 10 + "by" + 10 + "sea");
            }
        }

        showPlayerShip();

    }

    public void showPlayerShip() {
        //First part
        System.out.print("  ");
        for (int p = 0; p < 10; p++) {
            System.out.print(p);
        }
        System.out.println();
        //middle part
        for (int x = 0; x < 10; x++) {
            System.out.print(x + "|");
            for (int y = 0; y < 10; y++) {
                System.out.print(sea[x][y]);
            }
            System.out.print("|" + x);
            System.out.println();
        }
        //last part
        System.out.print("  ");
        for (int p = 0; p < 10; p++) {
            System.out.print(p);
        }
        System.out.println();
    }

    public void deployComputerShips() {
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer

        for (int p = 0; p < computerShips; ) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);

            if ((x >= 0 && x < 10) && (y >= 0 && y < 10) && (sea[x][y] == " ")) {
                compsea[x][y] = "c";
                System.out.println((p + 1) + ".   Ship DEPLOYED");
                p++;
            } else if ((x >= 0 && x < 10) && (y >= 0 && y < 10) && (sea[x][y] == "@")) {
                System.out.println("player's ship is deployed on these co-ordinates");
            } else if ((x < 0 || x >= 10) || (y < 0 || y >= 10)) {
                System.out.println("invalid location: Kindly put co-ordinates inside 10 by 10 grid");
            }

        }
        showPlayerShip();
    }

    public void playerturn() {

        System.out.println("\nYour Turn");
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();

            if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) {

                if (sea[x][y] == "c") {
                    System.out.println("\"Boom!You sunk the ship!\"");
                    sea[x][y] = "!";

                    --computerShips;
                } else if (sea[x][y] == "@") {
                    System.out.println("Oh no,you sunk your own ships");
                    sea[x][y] = "x";
                    --playerShips;
                } else if (sea[x][y] == " ") {
                    System.out.println("Sorry, you missed");
                    sea[x][y] = "-";
                }
            }
        } while ((x < 0 || x >= 10) || (y < 0 || y >= 10));
    }

    public void computerturn() {
        System.out.println("\ncomputer's turn");
        do {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) ;
            {
                if (sea[x][y] == "@") {
                    System.out.println("The computer sunk one of your ships!");
                    sea[x][y] = "x";
                    --playerShips;

                } else if (sea[x][y] == "x") {
                    System.out.println("The computer sunk one of its own ship");
                    sea[x][y] = "!";
                    --computerShips;
                } else if (sea[x][y] == " ") {
                    System.out.println("Computer missed");
                }
            }
        } while ((x < 0 || x >= 10) || (y < 0 || y >= 10));
    }

    public void gameOver() {
        System.out.println("Your ships: " + playerShips + "|computer ships: " + computerShips);
        if (playerShips > 0 && computerShips <= 0)
            System.out.println("Hooray!You won the battle");
        else
            System.out.println("Sorry,You lost the battle");
        System.out.println();
    }
}
