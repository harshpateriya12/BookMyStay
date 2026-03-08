package com.app;

import java.util.Scanner;

import com.service.InventoryService;
import com.service.searchService;
import com.service.BookingQueueService;
import com.service.AllocationService;

public class BookMyStay {

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();
        searchService searchService = new searchService(inventoryService);
        AllocationService allocationService = new AllocationService();

        BookingQueueService bookingService =
                new BookingQueueService(inventoryService, allocationService);

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nHotel Management System");
            System.out.println("1. Add Room Type");
            System.out.println("2. Update Room Count");
            System.out.println("3. Update Room Price");
            System.out.println("4. View Inventory");
            System.out.println("5. Search Available Rooms");
            System.out.println("6. Add Booking Request");
            System.out.println("7. Process Booking");
            System.out.println("8. View Booking Queue");
            System.out.println("9. View Allocated Rooms");
            System.out.println("10. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter Room Type:");
                    String type = sc.nextLine();

                    System.out.println("Enter Count:");
                    int count = sc.nextInt();

                    System.out.println("Enter Price:");
                    double price = sc.nextDouble();

                    inventoryService.addRoomType(type, count, price);
                    break;

                case 2:
                    System.out.println("Enter Room Type:");
                    type = sc.nextLine();

                    System.out.println("Enter New Count:");
                    count = sc.nextInt();

                    inventoryService.updateRoomCount(type, count);
                    break;

                case 3:
                    System.out.println("Enter Room Type:");
                    type = sc.nextLine();

                    System.out.println("Enter New Price:");
                    price = sc.nextDouble();

                    inventoryService.updateRoomPrice(type, price);
                    break;

                case 4:
                    inventoryService.showAvailability();
                    break;

                case 5:
                    searchService.searchAvailableRooms();
                    break;

                case 6:
                    System.out.println("Enter Guest Name:");
                    String guest = sc.nextLine();

                    System.out.println("Enter Room Type:");
                    type = sc.nextLine();

                    bookingService.addBookingRequest(guest, type);
                    break;

                case 7:
                    bookingService.processBooking();
                    break;

                case 8:
                    bookingService.viewQueue();
                    break;

                case 9:
                    allocationService.viewAllocations();
                    break;

                case 10:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 10);

        sc.close();
    }
}