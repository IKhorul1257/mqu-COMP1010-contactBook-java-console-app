public class Email {
    public String emailAddress;

    /**
     * Constructor for Email Class
     * 
     * @param emailAddress
     */
    public Email(String emailAddress) {
        // Checks if email is valid before creating the object
        if (emailAddress == null || !isValidEmail(emailAddress)) {
            System.out.println("Warning: Invalid email address");
            this.emailAddress = "Invalid Email"; // If email invalid inputs default value "Invalid Email"
        }

        else {
            this.emailAddress = emailAddress.toLowerCase(); // Ensures all lowercase letters for email
        }
    }

    // Helper Function: Checks if the provided email is valid
    public static boolean isValidEmail(String input) {
        // If email is null or too short
        if (input == null || input.length() < 5) {
            return false;
        }

        // Must have '@' and '.' after '@'
        int at = input.indexOf('@');
        int dot = input.lastIndexOf('.');
        if (at <= 0 || dot <= at)
            return false;

        // Check for forbidden characters
        String forbidden = " (),:;<>[]{}\"'\\|!";
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (forbidden.indexOf(ch) != -1)
                return false;
        }

        return true;
    }

    // Checks if Email object is equivalent to other Email objects
    @Override
    public boolean equals(Object obj) {
        // Check if objects are of same instance or same class
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        // Checks equality of relevant attributes (emailAddress)
        Email other = (Email) obj;
        return emailAddress.equalsIgnoreCase(other.emailAddress);
    }

    // Converts the Email object into a String
    @Override
    public String toString() {
        return emailAddress;
    }
}
