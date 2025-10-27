import java.io.*;
import java.util.*;

public class ContactManager {
    public ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    // Adds contact to the arraylist of contacts and then updates the saved contacts
    public void addContact(String fName, String lName, String phone, String email) {
        Contact newContact = new Contact(new Name(fName, lName), new PhoneNumber(phone), new Email(email));
        duplicateCheckAdd(newContact);
        System.out.println("Contact added successfully!");
    }

    /*
     * Edit function is called after user chooses from the menu
     * The firstname and lastname is matched
     * If it is found then the existing attributes of the contact object is updated
     * accordingly
     */
    public void editContact(String firstName, String lastName, String newFirstName, String newLastName, String newPhone,
            String newEmail) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.fullName.firstName.equalsIgnoreCase(firstName) && c.fullName.lastName.equalsIgnoreCase(lastName)) {
                /*
                 * If any field is null or empty, retain the existing value
                 * This allows partial updates without overwriting existing data
                 */
                if (newFirstName == null || newFirstName == "") {
                    newFirstName = c.fullName.firstName;
                }

                if (newLastName == null || newLastName == "") {
                    newLastName = c.fullName.lastName;
                }

                if (newPhone == null || newPhone == "") {
                    newPhone = c.phone.number;
                }

                if (newEmail == null || newEmail == "") {
                    newEmail = c.emailAddress.emailAddress;
                }

                c.fullName.firstName = Name.capitalize(newFirstName);
                c.fullName.lastName = Name.capitalize(newLastName);
                if (PhoneNumber.isValidNum(newPhone)) {
                    c.phone.number = PhoneNumber.formatPhone(newPhone);
                }
                if (Email.isValidEmail(newEmail)) {
                    c.emailAddress.emailAddress = newEmail;
                }
            }
        }
    }

    /*
     * Checks through the arraylist of contacts first
     * If the firsname and lastname matches then the contact object is deleted from
     * the arraylist
     * When the arraylist is exported the existing contact is removed
     */
    public void deleteContact(String firstName, String lastName) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.fullName.firstName.equalsIgnoreCase(firstName) && c.fullName.lastName.equalsIgnoreCase(lastName)) {
                contacts.remove(i);
                System.out.println("Contact Removed :(");
                return;
            }
        }
        System.out.println("Contact not found");
    }

    /*
     * Uses this function to print all the saved contacts on the terminal for
     * viewing
     * It also includes an index for all the contacts
     * Also prints out the total number of contacts saved
     */
    public void showSavedContacts(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int idx = 1; // Iterates index number
        int count = 0; // Iterates total count of contacts
        reader.readLine(); // skip header line
        System.out.println("\n===== SAVED CONTACTS =====");
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String name = parts[0].trim();
                String phone = parts[1].trim();
                String email = parts[2].trim();
                System.out.println(idx + ". Name: " + name + " | Phone: " + phone + " | Email: " + email);
                idx++;
                count++;
            }
        }
        reader.close();
        System.out.println("\nTotal Contacts: " + count);
    }

    /*
     * Imports contacts from a CSV file with format: FirstName,LastName,Phone,Email
     * Expects at least 4 fields per line and skips the header row
     */
    public void importFromCSV(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length >= 4) {
                String fName = parts[0].trim();
                String lName = parts[1].trim();
                String phone = parts[2].trim();
                String email = parts[3].trim();

                Contact newContact = new Contact(new Name(fName, lName), new PhoneNumber(phone), new Email(email));
                duplicateCheckAdd(newContact); // Compares the newly created contact object
            }
        }
        reader.close();
    }

    /*
     * Imports contacts from saved file with format: FullName,Phone,Email
     * Splits the full name into first and last name components
     */
    public void importSavedContacts(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(","); // Split the csv columns
            String[] nameParts = parts[0].split(" "); // Split fullname to firstname and lastname
            String fName = nameParts[0];
            String lName = nameParts[1];
            String phone = parts[1];
            String email = parts[2];

            Contact newContact = new Contact(new Name(fName, lName), new PhoneNumber(phone), new Email(email));
            duplicateCheckAdd(newContact);
        }
        reader.close();
    }

    // Uses java file i/o to export the values from arraylist to csv files
    public void exportToCSV(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        writer.write("Name,Phone,Email\n");

        for (Contact c : getSortedByFirstName(contacts)) {
            writer.write(c.fullName + "," + c.phone + "," + c.emailAddress + "\n");
        }
        writer.close();
    }

    /*
     * Returns a new sorted ArrayList by first name using bubble sort
     * Creates a copy to avoid modifying the original list
     */
    public ArrayList<Contact> getSortedByFirstName(ArrayList<Contact> list) {
        ArrayList<Contact> sortedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            sortedList.add(list.get(i));
        }

        /*
         * Bubble sort: compare adjacent elements and swap if out of order
         * After each pass, the largest element bubbles to the end
         */
        for (int i = 0; i < sortedList.size() - 1; i++) {
            for (int j = 0; j < sortedList.size() - i - 1; j++) {
                String firstName1 = sortedList.get(j).fullName.firstName;
                String firstName2 = sortedList.get(j + 1).fullName.firstName;

                // Uses helper function to compare names and order them according to the first
                // name
                if (isGreater(firstName1, firstName2)) {
                    Contact temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }

        return sortedList;
    }

    /*
     * Compares two strings alphabetically (case-insensitive)
     * Returns true if string a comes after string b in alphabetical order
     */
    public static boolean isGreater(String a, String b) {
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            char ch1 = Character.toLowerCase(a.charAt(i));
            char ch2 = Character.toLowerCase(b.charAt(i));
            if (ch1 > ch2)
                return true;
            if (ch1 < ch2)
                return false;
        }
        return a.length() > b.length(); // if all characters match, longer string is greater
    }

    // Helper Function: Adds contact only if it's not already in the list
    public void duplicateCheckAdd(Contact check) {
        if (!contacts.contains(check)) {
            contacts.add(check);
        }
    }

    public Contact searchContact(String firstName, String lastName) {
        return searchRecursive(firstName, lastName, 0);
    }

    /*
     * Recursively searches for a contact by first and last name
     * Base case: index exceeds list size (contact not found)
     * Recursive case: check current contact, then move to next
     */
    public Contact searchRecursive(String firstName, String lastName, int index) {
        if (index >= contacts.size()) {
            return null;
        }

        Contact current = contacts.get(index);

        if (current.fullName.firstName.equalsIgnoreCase(firstName) &&
                current.fullName.lastName.equalsIgnoreCase(lastName)) {
            return current;
        }

        return searchRecursive(firstName, lastName, index + 1);
    }

    // Helper Function: To check if the provided firstname and lastname matches
    public boolean nameMatch(String fName, String lName) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.fullName.firstName.equalsIgnoreCase(fName) && c.fullName.lastName.equalsIgnoreCase(lName)) {
                return true;
            }
        }
        return false;
    }
}