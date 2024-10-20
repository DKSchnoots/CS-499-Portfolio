package contactservice;

import java.util.List;

public class ContactService {
    private BinaryTree contacts;

    public ContactService() {
        // Initialize the contacts binary tree
        contacts = new BinaryTree();
    }

    public void addContact(Contact contact) {
        // Add the contact to the contacts binary tree
        contacts.addContact(contact);
    }

    public void deleteContact(String contactId) {
        // Remove the contact from the contacts binary tree based on the contactId
        contacts.deleteContact(contactId);
    }

    public void updateContact(Contact updatedContact) {
        // Update the contact in the contacts binary tree by deleting and re-adding
        deleteContact(updatedContact.getContactId());
        addContact(updatedContact);
    }

    public Contact getContact(String contactId) {
        // Retrieve the contact from the contacts binary tree based on the contactId
        return contacts.getContact(contactId);
    }

    // New search methods
    public List<Contact> searchContactsByFirstName(String firstName) {
        return contacts.searchContactsByFirstName(firstName);
    }

    public List<Contact> searchContactsByLastName(String lastName) {
        return contacts.searchContactsByLastName(lastName);
    }

    public List<Contact> searchContactsByPhone(String phone) {
        return contacts.searchContactsByPhone(phone);
    }

    public List<Contact> searchContactsByAddress(String address) {
        return contacts.searchContactsByAddress(address);
    }
}

