package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Reservation;

public class BookingHistoryService {

    private List<Reservation> bookingHistory = new ArrayList<>();

    // add confirmed booking
    public void addReservation(Reservation reservation) {

        bookingHistory.add(reservation);

        System.out.println("Reservation stored in booking history.");
    }

    // view booking history
    public void viewBookingHistory() {

        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\nBooking History:");

        for (Reservation r : bookingHistory) {

            System.out.println(
                    r.getReservationId()
                    + " | "
                    + r.getGuestName()
                    + " | "
                    + r.getRoomType()
            );
        }
    }

    // cancel reservation
    public void cancelReservation(String reservationId) {

        for (Reservation r : bookingHistory) {

            if (r.getReservationId().equals(reservationId)) {

                bookingHistory.remove(r);

                System.out.println("Reservation cancelled.");
                return;
            }
        }

        System.out.println("Reservation not found.");
    }
}