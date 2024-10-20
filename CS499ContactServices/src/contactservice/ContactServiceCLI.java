package contactservice;

import java.util.List;
import java.util.Scanner;

public class ContactServiceCLI {
    private static ContactService contactService = new ContactService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nContact Service");
            System.out.println("1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Get Contact");
            System.out.println("5. Search Contacts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    deleteContact(scanner);
                    break;
                case 3:
                    updateContact(scanner);
                    break;
                case 4:
                    getContact(scanner);
                    break;
                case 5:
                    searchContacts(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter Contact ID: ");
        String contactId = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Contact contact = new Contact(contactId, firstName, lastName, phone, address);
        contactService.addContact(contact);
        System.out.println("Contact added successfully.");
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter Contact ID to delete: ");
        String contactId = scanner.nextLine();
        contactService.deleteContact(contactId);
        System.out.println("Contact deleted successfully.");
    }

    private static void updateContact(Scanner scanner) {
        System.out.print("Enter Contact ID to update: ");
        String contactId = scanner.nextLine();
        System.out.print("Enter New First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter New Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter New Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter New Address: ");
        String address = scanner.nextLine();

        Contact updatedContact = new Contact(contactId, firstName, lastName, phone, address);
        contactService.updateContact(updatedContact);
        System.out.println("Contact updated successfully.");
    }

    private static void getContact(Scanner scanner) {
        System.out.print("Enter Contact ID to retrieve: ");
        String contactId = scanner.nextLine();
        Contact contact = contactService.getContact(contactId);

        if (contact != null) {
            System.out.println("Contact Details:");
            System.out.println("ID: " + contact.getContactId());
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
            System.out.println("Phone: " + contact.getPhone());
            System.out.println("Address: " + contact.getAddress());
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void searchContacts(Scanner scanner) {
        System.out.println("Search Contacts by:");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone");
        System.out.println("4. Address");
        System.out.print("Choose an option: ");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (option) {
            case 1:
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();
                List<Contact> firstNameResults = contactService.searchContactsByFirstName(firstName);
                displaySearchResults(firstNameResults);
                break;
            case 2:
                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();
                List<Contact> lastNameResults = contactService.searchContactsByLastName(lastName);
                displaySearchResults(lastNameResults);
                break;
            case 3:
                System.out.print("Enter Phone: ");
                String phone = scanner.nextLine();
                List<Contact> phoneResults = contactService.searchContactsByPhone(phone);
                displaySearchResults(phoneResults);
                break;
            case 4:
                System.out.print("Enter Address: ");
                String address = scanner.nextLine();
                List<Contact> addressResults = contactService.searchContactsByAddress(address);
                displaySearchResults(addressResults);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void displaySearchResults(List<Contact> results) {
        if (results.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : results) {
                System.out.println("ID: " + contact.getContactId() +
                        ", First Name: " + contact.getFirstName() +
                        ", Last Name: " + contact.getLastName() +
                        ", Phone: " + contact.getPhone() +
                        ", Address: " + contact.getAddress());
            }
        }
    }
}
