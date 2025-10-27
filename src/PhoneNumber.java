public class PhoneNumber {
    public String number;

    /**
     * Constructor for PhoneNumber object
     * 
     * @param number
     */
    public PhoneNumber(String number) {
        // Check if phone number is valid before creating object
        if (number == null || !isValidNum(number)) {
            System.out.println("Warning: Invalid phone number");
            this.number = "0000000000"; // If phonenumber not valid input default value "0000000000"
        } else {
            this.number = formatPhone(number); // Input the formatted number
        }
    }

    // Helper Functions

    // Returns true if the phone number is valid otherwise false
    public static boolean isValidNum(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }
        // Checks if number contains forbidden characters (other than the allowed ones)
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (!Character.isDigit(c) && c != '+' && c == '-' && c == ' ' && c == '(' && c == ')') {
                return false;
            }
        }
        return formatPhone(number).length() >= 10; // Ensures standard length for all phone numbers
    }

    // Formats the user input to a standard phone number format such as "0000 000
    // 000(AU)"
    public static String formatPhone(String rawNumber) {
        if (rawNumber == null || rawNumber.isEmpty()) {
            return "";
        }

        // Remove all non-digit characters
        String digits = rawNumber.replaceAll("\\D", "");

        // Handle country code variations (+61 or 61)
        if (digits.startsWith("61")) {
            digits = "0" + digits.substring(2); // convert +61 or 61 → 0
        }

        // Ensure it starts with '0' and has 10 digits total
        if (!digits.startsWith("0")) {
            digits = "0" + digits;
        }
        if (digits.length() > 10) {
            digits = digits.substring(0, 10); // truncate extra digits
        }

        // Format as 0412 345 678
        if (digits.length() == 10) {
            return digits.substring(0, 4) + " " + digits.substring(4, 7) + " " + digits.substring(7);
        }

        // If invalid length, just return digits
        return digits;
    }

    // Checks if PhoneNumber object is equivalent to other PhoneNumber objects
    @Override
    public boolean equals(Object obj) {
        // Checks if the objects are of same instance or same class
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        // Checks for equality of relevant attributes (number)
        PhoneNumber other = (PhoneNumber) obj;
        return number.equals(other.number);
    }

    // Converts the PhoneNumber object into String
    @Override
    public String toString() {
        return number;
    }
}
