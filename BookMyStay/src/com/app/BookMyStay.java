package com.app;

import java.util.Scanner;

import com.service.InventoryService;
import com.service.searchService;

public class BookMyStay {

    public static void main(String[] args) {

        InventoryService service = new InventoryService();
        searchService searchService = new searchService(service);

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nRoom Inventory Management");
            System.out.println("1. Add Room Type");
            System.out.println("2. Update Room Count");
            System.out.println("3. Update Room Price");
            System.out.println("4. View Inventory");
            System.out.println("5. Search Available Rooms (Guest)");
            System.out.println("6. Check Room Availability");
            System.out.println("7. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            // using switch for better readability
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
                    service.showAvailability();
                    break;

                // UC2 Integration
                case 5:
                    searchService.searchAvailableRooms();
                    break;

                case 6:
                    System.out.println("Enter Room Type to Check:");
                    type = sc.nextLine();

                    boolean available = searchService.isRoomAvailable(type);

                    if (available) {
                        System.out.println("Room is available for booking.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 7);

        sc.close();
    }
}