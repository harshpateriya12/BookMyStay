package com.service;

import java.util.LinkedList;
import java.util.Queue;

import com.model.Reservation;

public class BookingQueueService {

    private Queue<Reservation> bookingQueue = new LinkedList<>();

    private InventoryService inventoryService;
    private AllocationService allocationService;
    private BookingHistoryService historyService;

    private int reservationCounter = 100;

    public BookingQueueService(
            InventoryService inventoryService,
            AllocationService allocationService,
            BookingHistoryService historyService) {

        this.inventoryService = inventoryService;
        this.allocationService = allocationService;
        this.historyService = historyService;
    }

    // Generate reservation ID
    private String generateReservationId() {
        reservationCounter++;
        return "RES" + reservationCounter;
    }

    // Add booking request
    public void addBookingRequest(String guestName, String roomType) {

        String reservationId = generateReservationId();

        Reservation reservation =
                new Reservation(reservationId, guestName, roomType);

        bookingQueue.add(reservation);

        System.out.println("Booking request added.");
        System.out.println("Reservation ID: " + reservationId);
    }

    // Process booking request (FIFO)
    public void processBooking() {

        if (bookingQueue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        Reservation reservation = bookingQueue.poll();

        String guest = reservation.getGuestName();
        String roomType = reservation.getRoomType();
        String reservationId = reservation.getReservationId();

        int availableRooms =
                inventoryService.getRoomInventory()
                        .getOrDefault(roomType, 0);

        if (availableRooms > 0) {

            String roomId =
                    allocationService.allocateRoom(roomType);

            inventoryService.updateRoomCount(
                    roomType,
                    availableRooms - 1
            );

            // store in booking history
            historyService.addReservation(reservation);

            System.out.println("\nBooking Confirmed");
            System.out.println("Guest: " + guest);
            System.out.println("Reservation ID: " + reservationId);
            System.out.println("Room Type: " + roomType);
            System.out.println("Allocated Room ID: " + roomId);
        }
        else {
            System.out.println("Room not available for " + guest);
        }
    }

    // View booking queue
    public void viewQueue() {

        if (bookingQueue.isEmpty()) {
            System.out.println("Booking queue empty.");
            return;
        }

        System.out.println("\nPending Booking Requests:");

        for (Reservation r : bookingQueue) {

            System.out.println(
                    r.getReservationId() + " | "
                            + r.getGuestName()
                            + " -> "
                            + r.getRoomType()
            );
        }
    }
}