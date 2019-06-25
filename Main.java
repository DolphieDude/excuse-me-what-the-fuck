package com.ACherevatyi;

import java.util.*;
public class Main {
    public static Random rnd = new Random();
    public static Scanner input = new Scanner(System.in);
    public static int returner;
    public static int ones = 0, doubles = 0, triples = 0, quad = 0; //counters of each ship type
    public static Player player1 = new Player("Player 1");
    public static Player player2 = new Player("Player 2");
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE SEA WAR!");
        System.out.println("PLAYER 1");
        fillField(player1);
        System.out.println("Your final field is like:");
        player1.drawField();
        System.out.println("PLAYER 2");
        fillField(player2);
        System.out.println("Your final field is like:");
        player2.drawField();
        System.out.println("GAME IS STARTED!");
        int randomPlayer = rnd.nextInt(2);
        Player plr = player1;
        Player enemy = player2;
        switch(randomPlayer) { // coin flip
            case 0:
                System.out.println("FIRST TURN IS FOR PLAYER 1");
                break;
            case 1:
                plr = player2;
                enemy = player1;
                System.out.println("FIRST TURN IS FOR PLAYER 2");
                break;
        }
        Player swap;
        int row = 0, col = 0;
        boolean isInput; //to catch exception
        do { //game loop
            returner = 0; // this time returner is to catch miss or hit
            System.out.println(plr.name);
            do {
                try {
                    isInput = false;
                    System.out.print("Enter row to shoot: ");
                    row = input.nextInt();
                    System.out.print("Enter column to shoot: ");
                    col = input.nextInt();
                }
                catch (Exception e) {
                    isInput = true;
                }
            } while (isInput);
            plr.shoot(enemy, row, col);
            if (returner != 1) {
                swap = plr;
                plr = enemy;
                enemy = swap;
            }
        } while(player1.quantity != 0 && player2.quantity != 0);
        winCheck(player1, player2);
        System.out.println("Thank you for playing <3");
    }
    private static void fillField(Player plr) {
        int i = 0;
        int shipSize = 0, col, row;
        String dir;
        do {
            returner = 0;
            try {
                System.out.print("Enter type of your ship(1-4): ");
                shipSize = input.nextInt();
                System.out.print("Enter column of first block: ");
                col = input.nextInt();
                System.out.print("Enter row of first block: ");
                row = input.nextInt();
                System.out.print("Enter direction of ship(up, down, right, left): ");
                dir = input.next();
                plr.ships[i] = new Ship(shipSize, row, col, dir, plr);
                i++;
            }
            catch (Exception e) {
                returner = -1;
            }
            if (returner == -1) {
                i--;
                System.out.println("Something went wrong!. Retry, please");
                continue;
            }
            else if (shipSize == 1) {
                ones++;
            }
            else if (shipSize == 2) {
                doubles++;
            }
            else if (shipSize == 3) {
                triples++;
            }
            else if (shipSize == 4){
                quad++;
            }
            else {
                i--;
                System.out.println("Something went wrong!. Retry, please");
                continue;
            }
            System.out.println("Ship was created succesfully!");
        } while (ones < 4 || doubles < 3 || triples < 2 || quad < 1);
        ones = 0;
        doubles = 0;
        triples = 0;
        quad = 0;
    }
    private static void winCheck (Player plr1, Player plr2) {
        if (plr1.quantity == 0) {
            System.out.println(plr1.name + " WON!");
        }
        else {
            System.out.println(plr2.name + " WON!");
        }
        System.out.println("CONGRATULATIONS!");
    }
}
