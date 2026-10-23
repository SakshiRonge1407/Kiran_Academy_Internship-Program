import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstraction
abstract class Passenger {

    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract double getDiscountPercentage();

    public abstract String getPassengerType();
}

// Inheritance
class RegularPassenger extends Passenger {

    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 0.0;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}

// Inheritance: StudentPassenger extends Passenger
class StudentPassenger extends Passenger {

    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double getDiscountPercentage() {
        return 0.10;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}

class Flight {

    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline, String source,
                  String destination, int availableSeats, double baseFare) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getAirline() {
        return airline;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public boolean hasAvailableSeats(int tickets) {
        return tickets > 0 && tickets <= availableSeats;
    }

    public void reserveSeats(int tickets) {
        if (!hasAvailableSeats(tickets)) {
            throw new IllegalArgumentException("Not enough seats available.");
        }

        availableSeats -= tickets;
    }

    public void displayFlight() {
        System.out.printf(
            "%-10s %-15s %-15s %-15s %-10d Rs. %.2f%n",
            flightCode, airline, source, destination,
            availableSeats, baseFare
        );
    }
}

class Booking {

    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int numberOfTickets;
    private double subtotal;
    private double discount;
    private double totalFare;

    public Booking(String bookingId, Flight flight, Passenger passenger,
                   int numberOfTickets) {

        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.numberOfTickets = numberOfTickets;

        this.subtotal = flight.getBaseFare() * numberOfTickets;

        this.discount = subtotal * passenger.getDiscountPercentage();

        this.totalFare = subtotal - discount;
    }

    public void displayBookingSummary() {
        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Booking ID       : " + bookingId);
        System.out.println("Flight Code      : " + flight.getFlightCode());
        System.out.println("Airline          : " + flight.getAirline());
        System.out.println("Route            : " + flight.getSource()
                           + " to " + flight.getDestination());
        System.out.println("Passenger Name   : " + passenger.getName());
        System.out.println("Passenger Age    : " + passenger.getAge());
        System.out.println("Passenger Type   : " + passenger.getPassengerType());
        System.out.println("Number of Tickets: " + numberOfTickets);

        System.out.printf("Base Fare/Ticket : Rs. %.2f%n",
                          flight.getBaseFare());
        System.out.printf("Subtotal         : Rs. %.2f%n", subtotal);
        System.out.printf("Discount (%.0f%%)    : Rs. %.2f%n",
                          passenger.getDiscountPercentage() * 100, discount);
        System.out.printf("Final Amount     : Rs. %.2f%n", totalFare);

        System.out.println("=====================================");
    }
}

class FlightBookingSystem {

    private List<Flight> flights;
    private List<Booking> bookings;
    private Scanner scanner;
    private int nextBookingNumber = 1001;

    public FlightBookingSystem() {

        flights = new ArrayList<>();
        bookings = new ArrayList<>();
        scanner = new Scanner(System.in);

        flights.add(new Flight(
            "PN101", "Air India", "Pune", "Delhi", 20, 4000
        ));

        flights.add(new Flight(
            "MB202", "IndiGo", "Mumbai", "Bengaluru", 25, 3500
        ));

        flights.add(new Flight(
            "PN303", "Akasa Air", "Pune", "Hyderabad", 15, 3000
        ));
    }

    // Displays all available flights
    public void displayFlights() {

        System.out.println("\n================ AVAILABLE FLIGHTS ================");
        System.out.printf("%-10s %-15s %-15s %-15s %-10s %s%n",
                          "Code", "Airline", "Source", "Destination",
                          "Seats", "Base Fare");

        for (Flight flight : flights) {
            flight.displayFlight();
        }

        System.out.println("====================================================");
    }

    public Flight findFlightByCode(String code) {

        for (Flight flight : flights) {
            if (flight.getFlightCode().equalsIgnoreCase(code)) {
                return flight;
            }
        }

        return null;
    }

    // Validates the passenger and ticket details
    private boolean validateBookingDetails(
            Flight flight, String name, int age, int tickets) {

        if (flight == null) {
            System.out.println("Error: Invalid flight code.");
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Passenger name cannot be blank.");
            return false;
        }

        if (age <= 0) {
            System.out.println("Error: Age must be greater than zero.");
            return false;
        }

        if (tickets <= 0) {
            System.out.println("Error: Number of tickets must be greater than zero.");
            return false;
        }

        if (!flight.hasAvailableSeats(tickets)) {
            System.out.println("Booking failed: Not enough seats available.");
            return false;
        }

        return true;
    }

    private int readPositiveInteger(String message) {

        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Please enter a number greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public void bookFlight() {

        System.out.print("\nEnter flight code: ");
        String code = scanner.nextLine().trim();

        Flight flight = findFlightByCode(code);

        if (flight == null) {
            System.out.println("Error: Flight code does not exist.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine().trim();

        int age = readPositiveInteger("Enter passenger age: ");

        System.out.print("Passenger type (Regular/Student): ");
        String passengerType = scanner.nextLine().trim();

        if (!passengerType.equalsIgnoreCase("Regular")
                && !passengerType.equalsIgnoreCase("Student")) {
            System.out.println("Error: Passenger type must be Regular or Student.");
            return;
        }

        int tickets = readPositiveInteger("Enter number of tickets: ");

        if (!validateBookingDetails(flight, name, age, tickets)) {
            return;
        }

        Passenger passenger;

        if (passengerType.equalsIgnoreCase("Student")) {
            passenger = new StudentPassenger(name, age);
        } else {
            passenger = new RegularPassenger(name, age);
        }

        Booking booking = new Booking(
            "BK" + nextBookingNumber,
            flight,
            passenger,
            tickets
        );

        flight.reserveSeats(tickets);
        bookings.add(booking);
        nextBookingNumber++;

        System.out.println("\nBooking confirmed successfully!");
        booking.displayBookingSummary();
    }

    public void viewBooking() {

        if (bookings.isEmpty()) {
            System.out.println("\nNo confirmed bookings available.");
            return;
        }

        bookings.get(bookings.size() - 1).displayBookingSummary();
    }

    public void start() {

        System.out.println("Welcome to Kiran Air Booking!");

        while (true) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");
            System.out.println("===============================");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    displayFlights();
                    break;

                case "2":
                    bookFlight();
                    break;

                case "3":
                    viewBooking();
                    break;

                case "4":
                    System.out.println(
                        "Thank you for using Kiran Air Booking!"
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice. Please select an option from 1 to 4."
                    );
            }
        }
    }
}

public class FlightBookingApp {

    public static void main(String[] args) {

        FlightBookingSystem system = new FlightBookingSystem();

        system.start();
    }
}