package com.service;

import java.util.LinkedList;
import java.util.Queue;

import com.model.Reservation;

public class BookingQueueService {

    private Queue<Reservation> bookingQueue = new LinkedList<>();
    private InventoryService inventoryService;

    public BookingQueueService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Add booking request
    public void addBookingRequest(String guestName, String roomType) {

        Reservation reservation = new Reservation(guestName, roomType);

        bookingQueue.add(reservation);

        System.out.println("Booking request added to queue.");
    }

    // Process bookings FIFO
    public void processBooking() {

        if (bookingQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        Reservation reservation = bookingQueue.poll();

        String roomType = reservation.getRoomType();
        String guest = reservation.getGuestName();

        int availableRooms = inventoryService.getRoomInventory().getOrDefault(roomType, 0);

        if (availableRooms > 0) {

            inventoryService.updateRoomCount(roomType, availableRooms - 1);

            System.out.println("Booking confirmed for " + guest + " | Room Type: " + roomType);
        } 
        else {
            System.out.println("Room not available for " + guest);
        }
    }

    // View queue
    public void viewQueue() {

        if (bookingQueue.isEmpty()) {
            System.out.println("Booking queue is empty.");
            return;
        }

        System.out.println("\nPending Booking Requests:");

        for (Reservation r : bookingQueue) {
            System.out.println(r.getGuestName() + " -> " + r.getRoomType());
        }
    }
}