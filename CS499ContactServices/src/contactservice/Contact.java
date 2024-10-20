package contactservice;

public class Contact {
    private String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Getter for the contactId field
    public String getContactId() {
        return contactId;
    }

    // Getter for the firstName field
    public String getFirstName() {
        return firstName;
    }

    // Getter for the lastName field
    public String getLastName() {
        return lastName;
    }

    // Getter for the phone field
    public String getPhone() {
        return phone;
    }

    // Getter for the address field
    public String getAddress() {
        return address;
    }
}
