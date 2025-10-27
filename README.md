# Contact Management System

## Project Overview

The Contact Management System is a Java-based console application designed to help users organize, manage, and maintain personal or professional contact information effectively. It provides essential functionalities such as adding, editing, deleting, searching, and listing contacts. The system utilizes simple data validation and CSV file integration, making contact management straightforward and flexible without requiring external dependencies.

The application is ideal for users who prefer a lightweight, local tool for managing contacts from the command line, ensuring data privacy and ease of use.

## Features

### Contact CRUD Operations:

- Add new contacts specifying first name, last name, phone number, and email address.
- Edit contact details selectively without requiring full re-entry of all fields.
- Delete contacts reliably by name search.

### Search Capability:

- Search contacts by first and last name with case-insensitive matching to quickly locate details.

### Contact Listing:

- Display all saved contacts with clear formatting, including indexed ordering and total count.

### CSV Import/Export Functionality:

- Import contacts from CSV files with proper formatting (first name, last name, phone number, email). The imported CSV file should contain 4 columns: First Name, Last Name, Phone, and Email. Upon import, the application processes these entries and exports them to memory as Full Name, Phone, and Email in the main CSV file, with contacts automatically sorted by name.
- Export contacts into a CSV file that can be opened in spreadsheet applications like Excel or Google Sheets. This features simple, clean data output to facilitate backups and sharing.

### Data Validation and Error Handling:

- Phone numbers and emails are validated before saving. Invalid emails default to "Invalid Email", and phone numbers to a generic placeholder.
- Users receive warnings for invalid data entries to maintain data quality.

### Duplicate Prevention:

- The system prevents adding duplicate contacts by checking names, phone numbers, and emails.

### Persistent Data Storage:

- Contact data is saved and loaded using CSV files to maintain state across sessions.

### User-friendly Menu Interface:

- Menu-driven interface with numbered options for easy navigation and command selection.

## Architecture and Design

The project consists of several core Java classes organized for modularity and easy maintenance.

**ContactBookApp (Main Program):**
Handles application launch, command loop, and menu-driven user interaction.

**ContactManager:**
Manages contact list operations including add, edit, delete, search, import, export, and validation tasks.

**Contact Class:**
Represents individual contact entries, encapsulating fields for name, phone, and email.

**Supporting Classes (Name, PhoneNumber, Email):**
Encapsulate individual contact attributes along with validation logic, ensuring separation of concerns and clearer code organization.

This layered class structure promotes maintainability and potential extensibility for future enhancements such as GUI integration or networking features.

## Installation and Setup

1. Ensure you have Java SE 11 or higher installed on your machine.
2. Download or clone the project files to your local environment.
3. Compile the Java files using the command:
   ```bash
   javac *.java
   ```
4. Run the main application with:
   ```bash
   java ContactBookApp
   ```

## Usage Instructions

When you start the application, you will see a menu with options to manage your contacts:

```
CONTACT MANAGER
1. Add Contact
2. Edit Contact
3. Delete Contact
4. Show Saved Contacts
5. Import contacts from CSV
6. Export contacts to CSV
7. Search Contact
0. Exit
Enter Option:
```

Input the number corresponding to the desired action.

Follow the prompts to enter contact details or file paths as required.

The program will provide feedback after each operation.

## Sample Output

### Adding a Contact

```
Enter Option: 1
Enter First Name: Arisha
Enter Last Name: Rahman
Enter Phone Number: 0412345678
Enter Email Address: arisha.rahman@example.com
Contact added successfully!
```

### Viewing All Contacts

```
Enter Option: 4
SAVED CONTACTS
1. Name: Arisha Rahman | Phone: 0412 345 678 | Email: arisha.rahman@example.com
Total Contacts: 1
```

### Searching for a Contact

```
Enter Option: 7
Enter First Name to search: Arisha
Enter Last Name to search: Rahman
Contact found:
Name: Arisha Rahman | Phone: 0412 345 678 | Email: arisha.rahman@example.com
```

### Exporting Contacts to CSV

```
Enter Option: 6
Export successful! Contacts saved to savedContacts.csv
```

The saved CSV file format:

| First Name | Last Name | Phone         | Email                        |
|------------|-----------|---------------|------------------------------|
| Arisha     | Rahman    | 0412 345 678  | arisha.rahman@example.com    |

## Data Validation Details

**Phone Number Validation:**
Accepts numeric strings formatted as local phone numbers. Invalid entries are replaced with "0000 000 000" and a warning is issued.

**Email Validation:**
Checks for standard email format (presence of "@" and a domain). Invalid emails are replaced by "Invalid Email" with user feedback.

## Extensibility and Future Work

The current code base's modular design allows easy implementation of additional features such as:

- Grouping contacts by categories or tags
- Sorting contacts by other attributes (e.g., last name or email)
- Bulk operations (import/delete multiple contacts)
- GUI or web interface for enhanced usability
- Encryption for contact data privacy

## Troubleshooting

- Ensure all inputs are correctly formatted (e.g., phone numbers without spaces or letters).
- Verify file paths when importing/exporting CSVs.
- Check that Java runtime environment is correctly installed and version 11 or above.

## License and Contributions

This project is open for modification and improvement. Contributions in the form of bug reports, feature requests, or pull requests are warmly welcomed.

## Author

Iftakharul Islam

BSc. IT(AI Major), Macquarie University