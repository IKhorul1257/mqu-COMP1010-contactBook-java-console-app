public class Contact {
    public Name fullName;
    public PhoneNumber phone;
    public Email emailAddress;

    /**
     * Contstructor for Contact Class
     * 
     * @param fullName
     * @param phone
     * @param emailAddress
     */
    public Contact(Name fullName, PhoneNumber phone, Email emailAddress) {
        this.fullName = fullName;
        this.phone = phone;
        this.emailAddress = emailAddress;
    }

    // Checks if Contact object is equivalent to other Contact objects
    @Override
    public boolean equals(Object obj) {
        // Checks if objects are the same instance or same class
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        // Check equality of all relevant attributes (fullName, phone, emailAddress)
        Contact other = (Contact) obj;
        return fullName.equals(other.fullName)
                && phone.equals(other.phone)
                && emailAddress.equals(other.emailAddress);
    }

    // Converts the Contact object to a formatted string
    @Override
    public String toString() {
        return "Name: " + fullName + " | Phone: " + phone + " | Email: " + emailAddress;
    }
}
