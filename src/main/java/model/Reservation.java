/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author samue
 */
public class Reservation {

    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Reservation() {
    }

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public String toString() {
        return "Reservation: Room: " + roomNumber
                + ", check-in: " + checkin.format(f)
                + ", check-out: " + checkout.format(f)
                + " " + duration(checkin, checkout) + " nights.";
    }

    public void updateReservation(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public void validateReservationDates(String checkin, String checkout) {
        LocalDate checkinDate = LocalDate.parse(checkin, f);
        LocalDate checkoutDate = LocalDate.parse(checkout, f);
        
        if (checkinDate.isAfter(checkoutDate) || checkoutDate.isBefore(LocalDate.now()) || checkinDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Error in Reservation: Check-in date must be today or later, "
                    + "and check-out must be after check-in.");
            
        }
    }

    public long duration(LocalDate checkin, LocalDate checkout) {

        long duration = ChronoUnit.DAYS.between(checkin, checkout);
        return duration;
    }

}
