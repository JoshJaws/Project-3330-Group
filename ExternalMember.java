import java.util.*;
import librarypackage.SSN;


public class ExternalMember extends Member {
    
    ExternalMember() {
        super();
    }
    ExternalMember(String name, String address, Date dob, String email, SSN ssn) {
        super(name, address, dob, email, ssn);
    }
    @Override
    public void borrowItem(Library library, String name) {
        if (materials.size() == 1) {
            System.out.println("Sorry, we cannot let you borrow more than 1 item. Please return another item if you would like to borrow more!");
        }
        LibraryCollection item = library.getItem(null, name);
        if (item.isBorrowed == false) {
            materials.put(item.title.hashCode(), item);
            library.ondutyLibrarian.removeItemFromCollection(item);
            item.borrowItem(library, this);
            return;
        }
        System.out.println("Item is already being borrowed!");
    }
    @Override
    public void borrowItem(Library library, Section section, String name) {
        if (materials.size() == 1) {
            System.out.println("Sorry, we cannot let you borrow more than 1 item. Please return another item if you would like to borrow more!");
        }
        LibraryCollection item = library.getItem(section, name);
        if (item.isBorrowed == false) {
            if (materials == null) {
                materials = new Hashtable<>();
            }
            materials.put(item.title.hashCode(), item);
            library.ondutyLibrarian.removeItemFromCollection(item);
            item.borrowItem(library, this);
            return;
        }
        System.out.println("Item is already being borrowed!");
    }
}
