package com.company;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Room r1 = new Room("You are in a basement, from here you can go ", new String[]{"N", "E"});
        Room r2 = new Room("Your are in the boiler room, from here you can go", new String[]{"S", "E"});
        Room r3 = new Room("Your are in a bathroom, from here you can go ", new String[]{"S", "W"});
        Room r4 = new Room("You are in a closet, from here you can go ", new String[]{"N", "W"});

        r1.addItem("whip");
        r1.addItem("stick");
        r2.addItem("chair");
        r2.addItem("sword");
        r3.addItem("bathtub");
        r3.addItem("soap");
        r4.addItem("hanger");
        r4.addItem("pants");


        Room[][] map = {
                {r2, r3},
                {r1, r4}
        };

        Player p1 = new Player();
        System.out.println("\n\t Welcome the ADVENTURE OF YOUR LIFE...");
        System.out.println("\n");
        printMenu();

        while (true) {
            Room room = map[p1.getPosY()][p1.getPosX()];
            System.out.println("\nWhat do you want to do? (enter 6 for available options)");
            String choice = scanner.next();

            switch (choice) {
                case "0":
                    System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
                    System.out.println(room);
                    System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
                    break;
                case "1":
                    String direction = "";
                    while (!room.possibleExit(direction)) {
                        System.out.println("Enter which exit you want to use: ");
                        direction = scanner.next().toUpperCase();
                    }
                    p1.move(direction);
                    break;
                case "2":
                    System.out.println("Enter item to pickup: ");
                    String itemName = scanner.next();
                    if(room.deleteItem(itemName)) {
                        p1.pickUpItem(itemName);
                        break;
                    }
                    System.out.println("Item is not in the room.\n");
                    break;
                case "3":
                    System.out.println("Enter item to drop: ");
                    String item = scanner.next();
                    if(p1.dropItem(item)) {
                        room.addItem(item);
                        break;
                    }
                    System.out.println("You dont have this item.\n");
                    break;
                case"4":
                    System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
                    p1.playerItem();
                    System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
                    break;
                case "5":
                    System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
                    System.out.println(room.printRoomItems());
                    System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
                    break;
                case "6":
                    printMenu();
                    break;
                case "9":
                    System.out.println("Game is shutting down, see you next time!");
                    return;
                default:
                    System.out.println("Your choice is not available.");
                    break;
            }
        }
    }
    private static void printMenu() {
        System.out.println("Possible options \n" +
                "- Enter 0 to see were you are. \n" +
                "- Enter 1 if you want to go to another room. \n" +
                "- Enter 2 if you want to pick up an item. \n" +
                "- Enter 3 if you want to drop an item. \n" +
                "- Enter 4 to see players item. \n" +
                "- Enter 5 to see current room items. \n" +
                "- enter 6 to print available options. \n" +
                "- Enter 9 if you wish to quit the game.\n");
    }
}

