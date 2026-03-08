package com.service;

import java.util.LinkedList;
import java.util.Queue;

import com.model.Reservation;

public class BookingQueueService {

    private Queue<Reservation> bookingQueue = new LinkedList<>();

    private InventoryService inventoryService;
    private AllocationService allocationService;

    public BookingQueueService(InventoryService inventoryService,
                               AllocationService allocationService) {

        this.inventoryService = inventoryService;
        this.allocationService = allocationService;
    }

    // Add booking request
    public void addBookingRequest(String guestName, String roomType) {

        Reservation reservation = new Reservation(guestName, roomType);

        bookingQueue.add(reservation);

        System.out.println("Booking request added to queue.");
    }

    // Process booking
    public void processBooking() {

        if (bookingQueue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }

        Reservation reservation = bookingQueue.poll();

        String guest = reservation.getGuestName();
        String roomType = reservation.getRoomType();

        int availableRooms =
                inventoryService.getRoomInventory().getOrDefault(roomType, 0);

        if (availableRooms > 0) {

            String roomId = allocationService.allocateRoom(roomType);

            inventoryService.updateRoomCount(roomType, availableRooms - 1);

            System.out.println("\nBooking Confirmed!");
            System.out.println("Guest: " + guest);
            System.out.println("Room Type: " + roomType);
            System.out.println("Allocated Room ID: " + roomId);
        }
        else {
            System.out.println("Room not available for " + guest);
        }
    }

    // View queue
    public void viewQueue() {

        if (bookingQueue.isEmpty()) {
            System.out.println("Booking queue empty.");
            return;
        }

        System.out.println("\nPending Booking Requests:");

        for (Reservation r : bookingQueue) {
            System.out.println(r.getGuestName() + " -> " + r.getRoomType());
        }
    }
}