import java.util.*;

abstract class Seat {
    private String seatNumber;
    Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    String getSeatNumber() {
        return seatNumber;
    }
    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String seatNumber) {
        super(seatNumber);
    }
    double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    PremiumSeat(String seatNumber) {
        super(seatNumber);
    }
    double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }
    double getPrice() {
        return 400;
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

class Show {
    private String time;
    private boolean started;
    private Set<String> bookedSeats;
    Show(String time) {
        this.time = time;
        this.started = false;
        bookedSeats = new HashSet<>();
    }
    boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getSeatNumber());
    }
    boolean bookSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            if (!isAvailable(seat)) {
                System.out.println("Seat " + seat.getSeatNumber() + " is already booked for this show.");
                return false;
            }
        }
        for (Seat seat : seats) {
            bookedSeats.add(seat.getSeatNumber());
        }
        return true;
    }
    void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.remove(seat.getSeatNumber());
        }
    }
    void startShow() {
        started = true;
    }
    boolean hasStarted() {
        return started;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;
    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.cancelled = false;
    }
    void confirm() {
        if (seats.size() > 6) {
            System.out.println("Cannot book more than 6 seats.");
            return;
        }
        if (show.bookSeats(seats)) {
            System.out.print("Booking confirmed for " + customer.getName() + ": ");
            for (int i = 0; i < seats.size(); i++) {
                System.out.print(seats.get(i).getSeatNumber());
                if (i < seats.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.printf(". Total: ₹%.2f%n", getTotal());
        }
    }
    double getTotal() {
        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }
        return total;
    }
    void cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel booking after the show has started.");
            return;
        }
        if (!cancelled) {
            show.releaseSeats(seats);
            cancelled = true;
            System.out.println(customer.getName() + "'s booking cancelled.");
            System.out.print("Seats ");
            for (int i = 0; i < seats.size(); i++) {
                System.out.print(seats.get(i).getSeatNumber());
                if (i < seats.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" released.");
        }
    }
}
public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show("7 PM");
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");
        List<Seat> ashaSeats = Arrays.asList(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );
        Booking ashaBooking = new Booking(asha, show, ashaSeats);
        ashaBooking.confirm();
        List<Seat> raviA2 = Arrays.asList(
                new RegularSeat("A2")
        );
        Booking raviAttempt = new Booking(ravi, show, raviA2);
        raviAttempt.confirm();
        List<Seat> raviSeats = Arrays.asList(
                new ReclinerSeat("R1")
        );
        Booking raviBooking = new Booking(ravi, show, raviSeats);
        raviBooking.confirm();
        ashaBooking.cancel();
        List<Seat> nehaSeats = Arrays.asList(
                new RegularSeat("A2")
        );
        Booking nehaBooking = new Booking(neha, show, nehaSeats);
        nehaBooking.confirm();
    }
}
