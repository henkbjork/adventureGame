package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Room r1 = new Room("You are in a basement, from here you can go ", new String[]{"N", "E"});
        Room r2 = new Room("Your are in the boiler room, from here you can go", new String[]{"S", "E"});
        Room r3 = new Room("Your are in a bathroom, from here you can go ", new String[]{"S", "W", "E"});
        Room r4 = new Room("You are in a laundry room, from here you can go ", new String[]{"W", "N", "E"});
        Room r5 = new Room("You are in a hallway, from here you can go, ", new String[]{"S", "W"});
        Room r6 = new Room("You are in a closet, from here you can go ", new String[]{"N", "W"});

        r1.addItem("whip");
        r1.addItem("med-kit");
        r2.addItem("chair");
        r2.addItem("sword");
        r3.addItem("bathtub");
        r3.addItem("soap");
        r4.addItem("hanger");
        r4.addItem("pants");
        r5.addItem("jacket");
        r5.addItem("shoes");

        r2.addTrap(new Trap("mouse trap", 10));
        r3.addTrap(new Trap("bomb", 60));
        r4.addTrap(new Trap("spike", 20));
        r5.addTrap(new Trap("skateboard", 10));

        r6.addEnemy(new Enemy("The closet monster", 10));

        Room[][] map = {
                {r2, r3, r5},
                {r1, r4, r6}
        };

        Player p1 = new Player();
        boolean quit = false;
        startTheme();


        while(!quit) {
            Room room = map[p1.getPosY()][p1.getPosX()];
            if(room.getDescription().contains("closet")) {
                if(p1.playerItem()) {
                    String fight = fightMonster();
                    if(fight.equals("dead")) {
                        deadEndTheme();
                        return;
                    } else if(fight.equals("alive")) {
                        aliveEndTheme();
                        return;
                    }
                } else {
                    findMonsterNoWeapon();
                }
            }
            System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
            System.out.println(room);
            int life = p1.getLife();
            if(room.getTrap() != null) {
                life = p1.getLife() - room.getTrap().getDamage();
                p1.setLife(life);
                room.deleteTrap();
                if(life <= 0) {
                    System.out.println("\n\nYou are dead. \n\n\t\t GAME OVER");
                    return;
                }
            }
            System.out.println("> Your life level is: " + life);
            System.out.println("**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**\t\t**");
            System.out.println("\nWhat do you want to do? (enter 6 for available options)");
            String choice = scanner.next().toLowerCase();

            switch (choice) {
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
                    p1.playerItem();
                    break;
                case "5":
                    System.out.println(room.printRoomItems());
                    break;
                case "6":
                    printMenu();
                    break;
                case "quit":
                    System.out.println("\n\n\t\tGame is shutting down, see you next time!");
                    quit = true;
                    break;
                default:
                    System.out.println("Your choice is not available.");
                    break;
            }
        }
    }

    private static void findMonsterNoWeapon() {
        for(int i=0; i<20; i++) {
            System.out.println("\n");
        }

        System.out.println("> You have found the monster in the closet, \n");
        System.out.println("> You will need a weapon, you cant kill the monster with your bare hands\n");
        System.out.println("> Go, find a weapon and come back!");

        for(int i=0; i<10; i++) {
            System.out.println("\n");
        }

        pauseTime();

    }

    private static String fightMonster() {
        for(int i=0; i<20; i++) {
            System.out.println("\n");
        }
        System.out.println("> You have found the monster in the closet, \n" +
                            " lets see if you have what it takes to kill him!");
        promptEnterKey();
        System.out.println("> Grab a weapon, (any item will do), and take a swing");
        System.out.println("> You need to be fast, hit the \"ENTER\" key to swing at the monster.");

        long startTime = System.nanoTime();
        for(int i=0; i<20; i++) {
            hitMonster();
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Timeelapsed:" + timeElapsed);

        if(timeElapsed > 3000000000L) {
            return "dead";
        } else {
            return "alive";
        }
    }

    private static void hitMonster() {
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void promptEnterKey(){
        System.out.println("> Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("Possible options \n" +
                "- Enter 1 if you want to go to another room. \n" +
                "- Enter 2 if you want to pick up an item. \n" +
                "- Enter 3 if you want to drop an item. \n" +
                "- Enter 4 to see players item. \n" +
                "- Enter 5 to see current room items. \n" +
                "- enter 6 to print available options. \n" +
                "- Enter quit if you wish to quit the game.\n");
    }

    private static void pauseTime() {
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void startTheme() {
        System.out.println("\n\t Welcome...");
        pauseTime();
        System.out.println("\n\t\t\t\t\t to the ADVENTURE...");
        pauseTime();
        System.out.println("\n\t\t OF YOUR LIFE...");
        pauseTime();
        System.out.println("\n\n\t\t Game objectives: \nFind the closet and kill the monster to save the day.\n\n");
        System.out.println("\n");
        pauseTime();
    }

    private static void aliveEndTheme() {
        pauseTime();
        System.out.println("**************************************************");
        System.out.println("> You have killed the monster and saved the day.");
        System.out.println("**************************************************");
        System.out.println("\t\tCONGRATULATIONS!!!!");
        System.out.println("**************************************************");
    }

    private static void deadEndTheme() {
        pauseTime();
        System.out.println("*********************************************");
        System.out.println("> You are to slow and stiff \n");
        System.out.println("> You have been killed by the monster.");
        System.out.println("*********************************************");
        System.out.println("\t\t\tGAME OVER!!!!");
        System.out.println("*********************************************");
    }
}

