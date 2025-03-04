/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import model.exceptions.DomainException;

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

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {
        if (!checkout.isAfter(checkin)) {
            throw new DomainException("Error in Reservation: check-out date must be after check-in date");
        }
        
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

    public void validateReservationDates(String checkin, String checkout) throws DomainException {
        LocalDate checkinDate = LocalDate.parse(checkin, f);
        LocalDate checkoutDate = LocalDate.parse(checkout, f);
        
        if (checkoutDate.isBefore(LocalDate.now()) || checkinDate.isBefore(LocalDate.now())) {
            throw new DomainException("Error in Reservation: reservation dates for updates must be future dates");
        }
        
        if (!checkoutDate.isAfter(checkinDate)) {
            throw new DomainException("Error in Reservation: check-out date must be after check-in date");
        }
    }

    public long duration(LocalDate checkin, LocalDate checkout) {

        long duration = ChronoUnit.DAYS.between(checkin, checkout);
        return duration;
    }

}
