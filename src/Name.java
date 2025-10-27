public class Name {
    public String firstName;
    public String lastName;

    /**
     * Constructor for Name class
     * 
     * @param firstName
     * @param lastName
     */
    public Name(String firstName, String lastName) {
        // Capitalize firstname and lastname before creating the object
        this.firstName = capitalize(firstName);
        this.lastName = capitalize(lastName);
    }

    // Helper Function: Capitalizing only the first letter of user provided names
    public static String capitalize(String str) {
        if (str == null || str.isEmpty())
            return str;
        String firstLetter = str.substring(0, 1).toUpperCase(); // Seperates first letter
        String remainingLetter = str.substring(1).toLowerCase();
        return firstLetter + remainingLetter;
    }

    // Checks if Name object is equivalent to other Name objects
    @Override
    public boolean equals(Object obj) {
        // Checks if the objects are of same instance or same class
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        // Checks equality of relevant attributes (firstName, lastName)
        Name other = (Name) obj;
        return firstName.equalsIgnoreCase(other.firstName)
                && lastName.equalsIgnoreCase(other.lastName);
    }

    // Converts the Name object to a formatted String as Full Name
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
