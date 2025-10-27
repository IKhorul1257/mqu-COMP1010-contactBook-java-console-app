import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ContactManagerTest {

    private ContactManager manager;

    @BeforeEach
    void setUp() {
        manager = new ContactManager(); // create fresh manager instance before each test
    }

    @Test
    void testAddContact() {
        manager.addContact("Ifti", "Islam", "0401234567", "ifti.islam@gmail.com");
        assertEquals(1, manager.contacts.size()); // Checks if contacts arraylist size increases after adding every
                                                  // contact object
        assertTrue(manager.nameMatch("Ifti", "Islam"));
    }

    /*
     * Tests that duplicate contacts are not added to the list
     * Adding the same contact twice should result in only one entry
     */
    @Test
    void testDuplicateCheckAdd() {
        Contact contact = new Contact(new Name("Mohammad", "Hasnat"),
                new PhoneNumber("0407000001"),
                new Email("m.hasnat@gmail.com"));

        manager.duplicateCheckAdd(contact);
        manager.duplicateCheckAdd(contact); // attempt to add duplicate

        assertEquals(1, manager.contacts.size(), "Duplicate contact should not be added again");
    }

    /*
     * Tests that all fields of a contact can be edited
     * Verifies that the edited contact can be found with new name
     * and contains the updated phone and email information
     */
    @Test
    void testEditContact() {
        manager.addContact("Wasiq", "Ahmed", "0407000001", "wasiq.ahmed@gmail.com");

        manager.editContact("Wasiq", "Ahmed",
                "Wasif", "Rahman",
                "0408000002", "wasif.rahman@gmail.com");

        Contact edited = manager.searchContact("Wasif", "Rahman");
        assertNotNull(edited);
        assertEquals("0408 000 002", edited.phone.number); // phone is formatted with spaces
        assertEquals("wasif.rahman@gmail.com", edited.emailAddress.emailAddress);
    }

    @Test
    void testDeleteContact() {
        manager.addContact("Farhan", "Siddique", "0406000001", "farhan.siddique@yahoo.com");
        assertTrue(manager.nameMatch("Farhan", "Siddique"));

        manager.deleteContact("Farhan", "Siddique");
        assertFalse(manager.nameMatch("Farhan", "Siddique"), "Contact should be deleted");
    }

    /*
     * Tests the recursive search functionality
     * Verifies that the recursive method can find a contact
     * in the middle of the list starting from index 0
     */
    @Test
    void testSearchRecursive() {
        manager.addContact("Samiul", "Hasan", "0409000001", "samiul.hasan@gmail.com");
        manager.addContact("Arif", "Hossain", "0409000002", "arif.hossain@yahoo.com");

        Contact found = manager.searchRecursive("Arif", "Hossain", 0);
        assertNotNull(found);
        assertEquals("Arif", found.fullName.firstName);
        assertEquals("Hossain", found.fullName.lastName);
    }

    // boundary case: searching for a contact that doesn't exist should return null
    @Test
    void testSearchContactReturnsNullIfNotFound() {
        manager.addContact("Rafi", "Uddin", "0401000001", "rafi.uddin@gmail.com");
        Contact result = manager.searchContact("Nadia", "Rahman");
        assertNull(result, "Search should return null if contact not found");
    }

    /*
     * Tests alphabetical sorting by first name
     * Adds contacts in non-alphabetical order (Y, A, F)
     * and verifies they are sorted correctly (A, F, Y)
     */
    @Test
    void testGetSortedByFirstName() {
        manager.addContact("Yasir", "Khan", "0401000001", "yasir.khan@gmail.com");
        manager.addContact("Abir", "Hasan", "0401000002", "abir.hasan@gmail.com");
        manager.addContact("Farhan", "Siddique", "0401000003", "farhan.siddique@gmail.com");

        ArrayList<Contact> sorted = manager.getSortedByFirstName(manager.contacts);

        assertEquals("Abir", sorted.get(0).fullName.firstName);
        assertEquals("Farhan", sorted.get(1).fullName.firstName);
        assertEquals("Yasir", sorted.get(2).fullName.firstName);
    }

    // Checks if provided name matches with the existing contacts and returns true
    // or false
    @Test
    void testNameMatch() {
        manager.addContact("Tariq", "Aziz", "0402000003", "tariq.aziz@gmail.com");
        assertTrue(manager.nameMatch("Tariq", "Aziz"));
        assertFalse(manager.nameMatch("Sara", "Rahim"));
    }

    /*
     * Tests the string comparison logic used in sorting
     * Covers three cases: greater than, less than, and equal strings
     */
    @Test
    void testIsGreaterComparisonLogic() {
        assertTrue(ContactManager.isGreater("Zahid", "Arif"), "Zahid > Arif should be true");
        assertFalse(ContactManager.isGreater("Arif", "Zahid"), "Arif < Zahid should be false");
        assertFalse(ContactManager.isGreater("Arif", "Arif"), "Equal names should return false");
    }
}