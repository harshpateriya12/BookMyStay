package com.service;

import java.util.*;

import com.model.Service;

public class AddOnServiceManager {

    private Map<String, List<Service>> reservationServices = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());

        reservationServices.get(reservationId).add(service);

        System.out.println("Service added to reservation " + reservationId);
    }

    // View services for reservation
    public void viewServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId);

        double total = 0;

        for (Service s : services) {
            System.out.println(s);
            total += s.getPrice();
        }

        System.out.println("Total Service Cost: ₹" + total);
    }
}