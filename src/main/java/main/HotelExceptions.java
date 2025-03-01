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

                if (checkinDate.isAfter(checkoutDate)) {
                    throw new IllegalArgumentException("Error in Reservation: Check-out date must be after check-in date.");
                }

                reservations.add(new Reservation(roomNumber, checkinDate, checkoutDate));

                System.out.println("Reservation: Room: " + reservations.get(counter).getRoomNumber() + ", check-in: "
                        + reservations.get(counter).getCheckin().format(fmt) + ", check-out: " + reservations.get(counter).getCheckout().format(fmt)
                        + " " + reservations.get(counter).duration(checkinDate, checkoutDate) + " nights.");
                System.out.println();

                System.out.println("Enter data to update the reservation: ");

                System.out.print("Check-in date: (dd/MM/yyyy)");
                String checkin2 = sc.nextLine();

                System.out.print("Check-out date: (dd/MM/yyyy)");
                String checkout2 = sc.nextLine();

                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate checkinDate2 = LocalDate.parse(checkin2, fmt2);
                LocalDate checkoutDate2 = LocalDate.parse(checkout2, fmt2);
                
                if (checkinDate2.isAfter(checkoutDate2)) {
                    throw new IllegalArgumentException("Error in Reservation: Check-out date must be after check-in date.");
                }

                reservations.remove(counter);

                reservations.add(new Reservation(roomNumber, checkinDate2, checkoutDate2));

                System.out.println("Reservation: Room: " + reservations.get(counter).getRoomNumber() + ", check-in: "
                        + reservations.get(counter).getCheckin().format(fmt2) + ", check-out: " + reservations.get(counter).getCheckout().format(fmt2)
                        + " " + reservations.get(counter).duration(checkinDate2, checkoutDate2) + " nights.");
                System.out.println();
                

                counter++;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }
}
