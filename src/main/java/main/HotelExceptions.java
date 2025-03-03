/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import model.Reservation;

/**
 *
 * @author samue
 */
public class HotelExceptions {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        ArrayList<Reservation> reservations = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Reservation r = new Reservation();

        Scanner sc = new Scanner(System.in);

        int counter = 0;

        while (counter < 10) {

            try {

                System.out.print("Room number: ");
                Integer roomNumber = sc.nextInt();
                sc.nextLine();

                System.out.print("Check-in date: (dd/MM/yyyy) ");
                String checkin = sc.nextLine();

                System.out.print("Check-out date: (dd/MM/yyyy)");
                String checkout = sc.nextLine();

                LocalDate checkinDate = LocalDate.parse(checkin, fmt);
                LocalDate checkoutDate = LocalDate.parse(checkout, fmt);

                r.validateReservationDates(checkin, checkout);

                Reservation reservation = new Reservation(roomNumber, checkinDate, checkoutDate);
                reservations.add(reservation);

                System.out.println(reservation.toString());

                System.out.println("Enter data to update the reservation: ");

                System.out.print("Check-in date: (dd/MM/yyyy)");
                String checkin2 = sc.nextLine();

                System.out.print("Check-out date: (dd/MM/yyyy)");
                String checkout2 = sc.nextLine();

                LocalDate checkinDate2 = LocalDate.parse(checkin2, fmt);
                LocalDate checkoutDate2 = LocalDate.parse(checkout2, fmt);

                r.validateReservationDates(checkin2, checkout2);

                reservation.updateReservation(checkinDate2, checkoutDate2);

                System.out.println(reservation.toString());

                counter++;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }
}
