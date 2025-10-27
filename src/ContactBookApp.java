import java.io.IOException;
import java.util.*;

public class ContactBookApp {
    // Scanner instance is created outside the main function as global variable sc
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        showMenu(); // Continues the showMenu() recursively
        sc.close(); // Scanner is closed only after user exits and showMenu is stopped
    }

    /*
     * Displays the main menu and handles user interaction
     * Recursively calls itself to keep the program running until user exits
     * Loads saved contacts at the start and saves after each modification
     */
    public static void showMenu() throws IOException {
        ContactManager manager = new ContactManager();
        manager.importSavedContacts("savedContacts.csv"); // load existing contacts

        System.out.println("\n===== CONTACT MANAGER =====");
        System.out.println("1. Add Contact");
        System.out.println("2. Edit Contact");
        System.out.println("3. Delete Contact");
        System.out.println("4. Show Saved Contacts");
        System.out.println("5. Import contacts from CSV");
        System.out.println("6. Export contacts to CSV");
        System.out.println("7. Search Contact");
        System.out.println("0. Exit");
        System.out.print("Enter Option: ");

        int choice = sc.nextInt();
        sc.nextLine(); // consume newline character left by nextInt()

        if (choice == 1) {
            System.out.print("First Name: ");
            String fName = sc.nextLine();
            System.out.print("Last Name: ");
            String lName = sc.nextLine();
            System.out.print("Phone Number: ");
            String phone = sc.nextLine();
            System.out.print("Email Address: ");
            String email = sc.nextLine();
            manager.addContact(fName, lName, phone, email);
            manager.exportToCSV("savedContacts.csv"); // save changes to file
        }

        else if (choice == 2) {
            System.out.print("Enter  First Name to edit: ");
            String fName = sc.nextLine();
            System.out.print("Enter  Last Name to edit: ");
            String lName = sc.nextLine();
            if (manager.nameMatch(fName, lName)) {
                /*
                 * Allow partial updates by accepting blank inputs
                 * Blank fields will retain their existing values
                 */
                System.out.print("New First Name (blank to skip): ");
                String newFName = sc.nextLine();
                System.out.print("New Last Name (blank to skip): ");
                String newLName = sc.nextLine();
                System.out.print("New phone (blank to skip): ");
                String newPhone = sc.nextLine();
                System.out.print("New email (blank to skip): ");
                String newEmail = sc.nextLine();
                manager.editContact(fName, lName, newFName, newLName, newPhone, newEmail);
                manager.exportToCSV("savedContacts.csv"); // save changes to file
                System.out.println("\nContact Updated Successfully!");
            } else {
                System.out.println("\nContact not found");
            }
        }

        else if (choice == 3) {
            System.out.print("First Name: ");
            String fName = sc.nextLine();
            System.out.print("Last Name: ");
            String lName = sc.nextLine();
            manager.deleteContact(fName, lName);
            manager.exportToCSV("savedContacts.csv"); // save changes to file
        }

        else if (choice == 4) {
            manager.exportToCSV("savedContacts.csv"); // ensure file is up to date
            manager.showSavedContacts("savedContacts.csv");
        }

        else if (choice == 5) {
            System.out.print("Enter filename to import(eg. import.csv): ");
            String fileName = sc.nextLine();
            manager.importFromCSV(fileName);
            manager.exportToCSV("savedContacts.csv"); // merge imported contacts with saved contacts
            System.out.println("\n Contacts Imported Successfully!");
        }

        else if (choice == 6) {
            // Used to export all the saved contacts to a given csv file for external use
            System.out.print("Enter filename to export(eg. contacts.csv): ");
            String fileName = sc.nextLine();
            manager.exportToCSV(fileName);
        }

        else if (choice == 7) {
            System.out.print("Enter  First Name to search: ");
            String fName = sc.nextLine();
            System.out.print("Enter  Last Name to search: ");
            String lName = sc.nextLine();
            // Checks through the contacts and uses the searchContact function to output the
            // search results
            Contact found = manager.searchContact(fName, lName);
            if (found != null) {
                System.out.println("\nContact found: " + found);
            } else {
                System.out.println("\nContact not found.");
            }
        }

        else if (choice == 0) {
            System.out.println("Exiting Program.. Goodbye!");
            return; // terminate recursion and exit program
        }

        else {
            System.out.println("Invalid choice. Try again.");
        }

        showMenu(); // recursive call to display menu again
    }
}