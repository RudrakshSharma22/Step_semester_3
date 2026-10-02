import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private String roomNumber;
    Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    String getRoomNumber() {
        return roomNumber;
    }
    abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    StandardRoom(String roomNumber) {
        super(roomNumber);
    }
    double calculatePrice(long days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }
    double calculatePrice(long days) {
        return days * 180;
    }
}

class Suite extends Room {
    Suite(String roomNumber) {
        super(roomNumber);
    }
    double calculatePrice(long days) {
        return days * 300;
    }
}

class Customer {
    private String name;
    Customer(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean cancelled;
    Reservation(Customer customer, Room room, LocalDate startDate,
                LocalDate endDate, LocalDate cancellationDeadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        cancelled = false;
    }
    boolean overlaps(LocalDate start, LocalDate end) {
        return !cancelled && start.isBefore(endDate) && end.isAfter(startDate);
    }
    double getPrice() {
        long days = endDate.toEpochDay() - startDate.toEpochDay();
        return room.calculatePrice(days);
    }
    void cancel(LocalDate currentDate) {
        if (currentDate.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
            return;
        }

        if (!cancelled) {
            cancelled = true;
            System.out.println("Reservation for " + customer.getName() +
                    ", " + room.getClass().getSimpleName() + " " +
                    room.getRoomNumber() + " (" + startDate + "-" +
                    endDate + ") cancelled successfully.");
        }
    }
    boolean isCancelled() {
        return cancelled;
    }
}

class Hotel {
    private List<Reservation> reservations;
    Hotel() {
        reservations = new ArrayList<>();
    }
    boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom() == room &&
                    reservation.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }
    void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    void checkAvailability(Room room, LocalDate start, LocalDate end) {
        if (isAvailable(room, start, end)) {
            System.out.println(room.getClass().getSimpleName() + " " +
                    room.getRoomNumber() + " is available from " +
                    start + " to " + end + ".");
        } else {
            System.out.println(room.getClass().getSimpleName() + " " +
                    room.getRoomNumber() + " is not available from " +
                    start + " to " + end + ".");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Room standard101 = new StandardRoom("101");
        Room deluxe201 = new DeluxeRoom("201");
        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");
        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);
        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);
        hotel.checkAvailability(standard101, jan1, jan5);
        Reservation reservationA = new Reservation(
                customerA, standard101, jan1, jan5, LocalDate.of(2025, 12, 30)
        );
        if (hotel.isAvailable(standard101, jan1, jan5)) {
            hotel.addReservation(reservationA);
            System.out.printf("Reservation confirmed for Customer A, Standard Room 101 (%s-%s). Price: $%.2f%n",
                    jan1, jan5, reservationA.getPrice());
        }
        hotel.checkAvailability(standard101, jan3, jan7);
        reservationA.cancel(LocalDate.of(2025, 12, 29));
        LocalDate feb10 = LocalDate.of(2026, 2, 10);
        LocalDate feb12 = LocalDate.of(2026, 2, 12);
        Reservation reservationC = new Reservation(
                customerC, deluxe201, feb10, feb12, LocalDate.of(2026, 2, 8)
        );
        if (hotel.isAvailable(deluxe201, feb10, feb12)) {
            hotel.addReservation(reservationC);
            System.out.printf("Reservation confirmed for Customer C, Deluxe Room 201 (%s-%s). Price: $%.2f%n",
                    feb10, feb12, reservationC.getPrice());
        }
    }
}
