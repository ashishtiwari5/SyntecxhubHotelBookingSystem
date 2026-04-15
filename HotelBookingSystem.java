import java.util.*;

class Room {
    int roomNumber;
    String occupantName;
    boolean isOccupied;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.occupantName = "None";
    }
}

public class HotelBookingSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize 10 rooms
        for (int i = 101; i <= 110; i++) {
            rooms.add(new Room(i));
        }

        while (true) {
            System.out.println("\n--- Syntecxhub Hotel Management System---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void viewRooms() {
        System.out.println("\nRoom Status:");
        for (Room r : rooms) {
            String status = r.isOccupied ? "[Occupied by " + r.occupantName + "]" : "[Available]";
            System.out.println("Room " + r.roomNumber + ": " + status);
        }
    }

    private static void bookRoom() {
        System.out.print("Enter Room Number (101-110): ");
        int rNum = scanner.nextInt();
        scanner.nextLine();

        Room room = findRoom(rNum);
        if (room != null && !room.isOccupied) {
            System.out.print("Enter Guest Name: ");
            room.occupantName = scanner.nextLine();
            room.isOccupied = true;
            System.out.println("Room booked successfully!");
        } else {
            System.out.println("Room is either invalid or already occupied.");
        }
    }

    private static void cancelBooking() {
        System.out.print("Enter Room Number to cancel: ");
        int rNum = scanner.nextInt();

        Room room = findRoom(rNum);
        if (room != null && room.isOccupied) {
            room.isOccupied = false;
            room.occupantName = "None";
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Room is already empty or invalid.");
        }
    }

    private static Room findRoom(int num) {
        return rooms.stream().filter(r -> r.roomNumber == num).findFirst().orElse(null);
    }
}