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
                
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");


                LocalDate checkinDate = LocalDate.parse(checkin, fmt);
                LocalDate checkoutDate = LocalDate.parse(checkout, fmt);

                if (checkinDate.isAfter(checkoutDate) || checkoutDate.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Error in Reservation: Check-out date must be after check-in date.");
                }
                
                Reservation r = new Reservation(roomNumber, checkinDate, checkoutDate);
                reservations.add(r);

                System.out.println(r.toString());

                System.out.println("Enter data to update the reservation: ");

                System.out.print("Check-in date: (dd/MM/yyyy)");
                String checkin2 = sc.nextLine();

                System.out.print("Check-out date: (dd/MM/yyyy)");
                String checkout2 = sc.nextLine();
                
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate checkinDate2 = LocalDate.parse(checkin2, fmt2);
                LocalDate checkoutDate2 = LocalDate.parse(checkout2, fmt2);
                

                if (checkinDate2.isAfter(checkoutDate2) || checkoutDate2.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Error in Reservation: Check-out date must be after check-in date.");
                }
                
                r.updateReservation(checkinDate2, checkoutDate2);
                
                reservations.add(r);
                
                System.out.println(r.toString());
                
                counter++;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }
}
