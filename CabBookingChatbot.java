import java.util.Scanner;
import java.util.Random;

public class CabBookingChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        while (true) {

            // Main menu
            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter 1 or 2.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            if (choice == 1) {

                // Select cab type
                System.out.println("\n===== CHOOSE CAB TYPE =====");
                System.out.println("1. Auto - Rs.12/km");
                System.out.println("2. Sedan - Rs.18/km");
                System.out.println("3. SUV - Rs.25/km");
                System.out.print("Enter cab choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid cab choice!");
                    sc.next();
                    continue;
                }

                int cabChoice = sc.nextInt();

                String cabType;
                int rate;

                if (cabChoice == 1) {
                    cabType = "Auto";
                    rate = 12;
                } else if (cabChoice == 2) {
                    cabType = "Sedan";
                    rate = 18;
                } else if (cabChoice == 3) {
                    cabType = "SUV";
                    rate = 25;
                } else {
                    System.out.println("Invalid cab choice!");
                    continue;
                }

                // Select source
                System.out.println("\n===== CHOOSE SOURCE =====");
                System.out.println("1. Pune Station");
                System.out.println("2. Swargate");
                System.out.println("3. Hinjawadi");
                System.out.print("Enter source choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid source choice!");
                    sc.next();
                    continue;
                }

                int sourceChoice = sc.nextInt();

                String source;

                if (sourceChoice == 1) {
                    source = "Pune Station";
                } else if (sourceChoice == 2) {
                    source = "Swargate";
                } else if (sourceChoice == 3) {
                    source = "Hinjawadi";
                } else {
                    System.out.println("Invalid source choice!");
                    continue;
                }

                // Select destination
                System.out.println("\n===== CHOOSE DESTINATION =====");
                System.out.println("1. Shivajinagar");
                System.out.println("2. Kothrud");
                System.out.println("3. Wakad");
                System.out.print("Enter destination choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid destination choice!");
                    sc.next();
                    continue;
                }

                int destinationChoice = sc.nextInt();

                String destination;
                int distance;

                if (destinationChoice == 1) {

                    destination = "Shivajinagar";

                    if (sourceChoice == 1) {
                        distance = 5;
                    } else if (sourceChoice == 2) {
                        distance = 7;
                    } else {
                        distance = 15;
                    }

                } else if (destinationChoice == 2) {

                    destination = "Kothrud";

                    if (sourceChoice == 1) {
                        distance = 8;
                    } else if (sourceChoice == 2) {
                        distance = 6;
                    } else {
                        distance = 18;
                    }

                } else if (destinationChoice == 3) {

                    destination = "Wakad";

                    if (sourceChoice == 1) {
                        distance = 12;
                    } else if (sourceChoice == 2) {
                        distance = 14;
                    } else {
                        distance = 10;
                    }

                } else {
                    System.out.println("Invalid destination choice!");
                    continue;
                }

                // Calculate fare
                int fare = rate * distance;

                // Display ride details(choices)
                System.out.println("\n===== RIDE DETAILS =====");
                System.out.println("Cab Type: " + cabType);
                System.out.println("Source: " + source);
                System.out.println("Destination: " + destination);
                System.out.println("Distance: " + distance + " km");
                System.out.println("Rate: Rs." + rate + "/km");
                System.out.println("Total Fare: Rs." + fare);

                // Confirm or cancel before sharing OTP (Choices)
                System.out.println("\n===== BOOKING CONFIRMATION =====");
                System.out.println("1. Confirm & Share OTP");
                System.out.println("2. Cancel Ride");
                System.out.print("Enter your choice: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid confirmation choice!");
                    sc.next();
                    continue;
                }

                int confirmChoice = sc.nextInt();

                if (confirmChoice == 1) {

                    // Generate a four-digit OTP for temprory vverification of ride
                    int otp = 1000 + random.nextInt(9000);

                    System.out.println("\nYour OTP has been shared: " + otp);
                    System.out.println("Please enter the OTP to verify your booking.");
                    System.out.print("Enter OTP: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid OTP input!");
                        sc.next();
                        System.out.println("Booking not verified.");
                        continue;
                    }

                    int enteredOtp = sc.nextInt();

                    // Verify OTP using random class generator
                    if (enteredOtp == otp) {

                        System.out.println("\nOTP verified successfully!");
                        System.out.println("Booking Confirmed!");
                        System.out.println("Cab Type: " + cabType);
                        System.out.println("Source: " + source);
                        System.out.println("Destination: " + destination);
                        System.out.println("Total Fare: Rs." + fare);
                        System.out.println(
                            "Cancellation is not allowed after OTP sharing."
                        );

                    } else {

                        System.out.println("\nIncorrect OTP!");
                        System.out.println("Booking not verified.");
                    }

                } else if (confirmChoice == 2) {

                    System.out.println("\nRide cancelled successfully.");
                    System.out.println("No OTP has been shared.");

                } else {

                    System.out.println("Invalid confirmation choice!");
                    System.out.println("Returning to main menu.");
                }

            } else if (choice == 2) {

                System.out.println("\nThank you for using Cab Booking Chatbot. Visit Again.... Goodbye!");
                break;

            } else {

                System.out.println("Invalid choice! Please select 1 or 2.");
            }
        }

        sc.close();
    }
}
