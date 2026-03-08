package com.app;

import java.util.Scanner;

import com.service.InventoryService;
import com.service.searchService;
import com.service.BookingQueueService;
import com.service.AllocationService;
import com.service.AddOnServiceManager;
import com.service.BookingHistoryService;

import com.model.Service;

public class BookMyStay {

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();

        searchService searchService =
                new searchService(inventoryService);

        AllocationService allocationService =
                new AllocationService();

        BookingHistoryService historyService =
                new BookingHistoryService();

        BookingQueueService bookingService =
                new BookingQueueService(
                        inventoryService,
                        allocationService,
                        historyService
                );

        AddOnServiceManager serviceManager =
                new AddOnServiceManager();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nHotel Management System");

            System.out.println("1 Add Room Type");
            System.out.println("2 Update Room Count");
            System.out.println("3 Update Room Price");
            System.out.println("4 View Inventory");

            System.out.println("5 Search Available Rooms");

            System.out.println("6 Add Booking Request");
            System.out.println("7 Process Booking");
            System.out.println("8 View Booking Queue");

            System.out.println("9 View Allocated Rooms");

            System.out.println("10 Add Service to Reservation");
            System.out.println("11 View Reservation Services");

            System.out.println("12 View Booking History");
            System.out.println("13 Cancel Reservation");

            System.out.println("14 Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("Enter Room Type:");
                    String type = sc.nextLine();

                    System.out.println("Enter Room Count:");
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

                    System.out.println("Enter Reservation ID:");
                    String resId = sc.nextLine();

                    System.out.println("Select Service");

                    System.out.println("1 Breakfast (500)");
                    System.out.println("2 Spa (1500)");
                    System.out.println("3 Airport Pickup (800)");

                    int sChoice = sc.nextInt();
                    sc.nextLine();

                    Service service = null;

                    switch (sChoice) {

                        case 1:
                            service =
                                    new Service("Breakfast", 500);
                            break;

                        case 2:
                            service =
                                    new Service("Spa", 1500);
                            break;

                        case 3:
                            service =
                                    new Service("Airport Pickup", 800);
                            break;
                    }

                    serviceManager.addService(resId, service);

                    break;

                case 11:

                    System.out.println("Enter Reservation ID:");
                    resId = sc.nextLine();

                    serviceManager.viewServices(resId);

                    break;

                case 12:

                    historyService.viewBookingHistory();

                    break;

                case 13:

                    System.out.println("Enter Reservation ID:");
                    resId = sc.nextLine();

                    historyService.cancelReservation(resId);

                    break;

                case 14:

                    System.out.println("Exiting System");

                    break;

                default:

                    System.out.println("Invalid choice");
            }

        } while (choice != 14);

        sc.close();
    }
}