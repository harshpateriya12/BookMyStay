package com.app;

import java.util.Scanner;
import com.service.InventoryService;

public class UCOneBookMyStay {

    public static void main(String[] args) {

        InventoryService service = new InventoryService();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nRoom Inventory Management");
            System.out.println("1. Add Room Type");
            System.out.println("2. Update Room Count");
            System.out.println("3. Update Room Price");
            System.out.println("4. View Inventory");
            System.out.println("5. Exit");

            choice = sc.nextInt();
            sc.nextLine();
            //using switch cases for better time complexity
            switch (choice) {

                case 1:
                    System.out.println("Enter Room Type:");
                    String type = sc.nextLine();

                    System.out.println("Enter Room Count:");
                    int count = sc.nextInt();

                    System.out.println("Enter Room Price:");
                    double price = sc.nextDouble();

                    service.addRoomType(type, count, price);
                    break;

                case 2:
                    System.out.println("Enter Room Type:");
                    type = sc.nextLine();

                    System.out.println("Enter New Count:");
                    count = sc.nextInt();

                    service.updateRoomCount(type, count);
                    break;

                case 3:
                    System.out.println("Enter Room Type:");
                    type = sc.nextLine();

                    System.out.println("Enter New Price:");
                    price = sc.nextDouble();

                    service.updateRoomPrice(type, price);
                    break;

                case 4:
                    service.showInventory();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}