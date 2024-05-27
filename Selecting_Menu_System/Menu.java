package Selecting_Menu_System;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("Welcome to the Menu");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.print("Please enter your choice (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You selected Option 1");
                    // Your code for Option 1 goes here
                    break;
                case 2:
                    System.out.println("You selected Option 2");
                    // Your code for Option 2 goes here
                    break;
                case 3:
                    System.out.println("You selected Option 3");
                    // Your code for Option 3 goes here
                    break;
                case 4:
                    System.out.println("You selected Option 4");
                    // Your code for Option 4 goes here
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 4.");
            }

            System.out.print("Do you want to quit? (yes/no): ");
            String response = scanner.next();
            quit = response.equalsIgnoreCase("yes");
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
