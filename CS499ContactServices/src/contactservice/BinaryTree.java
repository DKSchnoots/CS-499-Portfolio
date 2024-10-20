package contactservice;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void addContact(Contact contact) {
        root = addRecursive(root, contact);
    }

    private TreeNode addRecursive(TreeNode current, Contact contact) {
        if (current == null) {
            return new TreeNode(contact);
        }

        if (contact.getContactId().compareTo(current.contact.getContactId()) < 0) {
            current.left = addRecursive(current.left, contact);
        } else if (contact.getContactId().compareTo(current.contact.getContactId()) > 0) {
            current.right = addRecursive(current.right, contact);
        } else {
            // contactId already exists
            return current;
        }

        return current;
    }

    public void deleteContact(String contactId) {
        root = deleteRecursive(root, contactId);
    }

    private TreeNode deleteRecursive(TreeNode current, String contactId) {
        if (current == null) {
            return null;
        }

        if (contactId.equals(current.contact.getContactId())) {
            // Node to delete found
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            // Find the smallest contact in the right subtree
            String smallestContactId = findSmallestContactId(current.right);
            // Update the current contact with the smallest contact's details
            Contact smallestContact = getContact(smallestContactId);
            current.contact = new Contact(
                smallestContact.getContactId(),
                smallestContact.getFirstName(),
                smallestContact.getLastName(),
                smallestContact.getPhone(),
                smallestContact.getAddress()
            );
            current.right = deleteRecursive(current.right, smallestContactId);
            return current;
        }
        if (contactId.compareTo(current.contact.getContactId()) < 0) {
            current.left = deleteRecursive(current.left, contactId);
            return current;
        }
        current.right = deleteRecursive(current.right, contactId);
        return current;
    }

    private String findSmallestContactId(TreeNode root) {
        return root.left == null ? root.contact.getContactId() : findSmallestContactId(root.left);
    }

    public Contact getContact(String contactId) {
        return getRecursive(root, contactId);
    }

    private Contact getRecursive(TreeNode current, String contactId) {
        if (current == null) {
            return null;
        }

        if (contactId.equals(current.contact.getContactId())) {
            return current.contact;
        }
        return contactId.compareTo(current.contact.getContactId()) < 0
                ? getRecursive(current.left, contactId)
                : getRecursive(current.right, contactId);
    }

    // New search methods
    public List<Contact> searchContactsByFirstName(String firstName) {
        List<Contact> results = new ArrayList<>();
        searchByFirstName(root, firstName, results);
        return results;
    }

    private void searchByFirstName(TreeNode current, String firstName, List<Contact> results) {
        if (current != null) {
            if (current.contact.getFirstName().equalsIgnoreCase(firstName)) {
                results.add(current.contact);
            }
            searchByFirstName(current.left, firstName, results);
            searchByFirstName(current.right, firstName, results);
        }
    }

    // Similar methods for last name, phone, and address can be added here
    public List<Contact> searchContactsByLastName(String lastName) {
        List<Contact> results = new ArrayList<>();
        searchByLastName(root, lastName, results);
        return results;
    }

    private void searchByLastName(TreeNode current, String lastName, List<Contact> results) {
        if (current != null) {
            if (current.contact.getLastName().equalsIgnoreCase(lastName)) {
                results.add(current.contact);
            }
            searchByLastName(current.left, lastName, results);
            searchByLastName(current.right, lastName, results);
        }
    }

    public List<Contact> searchContactsByPhone(String phone) {
        List<Contact> results = new ArrayList<>();
        searchByPhone(root, phone, results);
        return results;
    }

    private void searchByPhone(TreeNode current, String phone, List<Contact> results) {
        if (current != null) {
            if (current.contact.getPhone().equals(phone)) {
                results.add(current.contact);
            }
            searchByPhone(current.left, phone, results);
            searchByPhone(current.right, phone, results);
        }
    }

    public List<Contact> searchContactsByAddress(String address) {
        List<Contact> results = new ArrayList<>();
        searchByAddress(root, address, results);
        return results;
    }

    private void searchByAddress(TreeNode current, String address, List<Contact> results) {
        if (current != null) {
            if (current.contact.getAddress().equalsIgnoreCase(address)) {
                results.add(current.contact);
            }
            searchByAddress(current.left, address, results);
            searchByAddress(current.right, address, results);
        }
    }
}
