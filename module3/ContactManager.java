import java.util.*;

public class ContactManager {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = new HashMap<>();

        // Step 4: add contacts here
        contacts.put("Ram Eati", new Contact("Ram Eati", "+1 508 508 0508"));
        contacts.put("Garrett Bontempo", new Contact("Garrett Bontempo", "+1 412 343 5343"));
        contacts.put("Danielle Stroe", new Contact("Danielle Stroe", "+1 412 434 3433"));
        contacts.put("Brady Bennett", new Contact("Brady Bennett", "+1 412 232 4332"));
        contacts.put("Louis Grant", new Contact("Louis Grant", "+1 617 555 0105"));

        // Step 5: look up a contact by name
        String findByName = "Ram Eati";
        Contact found = contacts.get(findByName);
        if (found == null) {
            System.out.println("Lookup Result for "+findByName+" contact not found.");
        } else {
            System.out.println("Lookup Result for "+findByName);
            System.out.println(found+" contact found.");
        }

        // Test with a name that does not exist
        String missingName = "Anthony Deg";
        Contact missing = contacts.get(missingName);
        if (missing == null) {
            System.out.println("Lookup Result for "+missingName+" contact not found.");
        } else {
            //Not needed based on input data but in case.
            System.out.println(missing);
        }

        // Step 6: print sorted list
        printContacts(contacts);

        // Step 7: remove a contact
        String nameToRemove = "Ram Eati";
        removeContact(contacts, nameToRemove);

        // Try removing a name that doesn't exist, to test the not-found case
        removeContact(contacts, "Ram Eati");
        addContact(contacts, "Ada Lovelace", "+1 999 555 0000");
        // Step 8: print sorted list after removing the contact.

        printContacts(contacts);
    }
    public static void printContacts(HashMap<String, Contact> contacts){
        ArrayList<Contact> updatedsortedlist = new ArrayList<>(contacts.values());
        updatedsortedlist.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("\n");
        System.out.println("=== Contacts ===");
        for (Contact c : updatedsortedlist) {
            System.out.println(c);
        }
        System.out.println("=== Contacts end ===");
        System.out.println("\n");
    }
    public static void addContact(HashMap<String, Contact> contacts, String name, String phone) {
        if (contacts.containsKey(name)) {
            System.out.println("Cannot add - contact already exists: " + name);
        } else {
            contacts.put(name, new Contact(name, phone));
            System.out.println("Added: " + name + " | " + phone);
        }
    }

    public static void removeContact(HashMap<String, Contact> contacts, String name) {
        Contact removed = contacts.remove(name);
        if (removed == null) {
            System.out.println("Cannot remove - contact not found: " + name);
        } else {
            System.out.println("Removed: " + removed);
        }
    }
}
