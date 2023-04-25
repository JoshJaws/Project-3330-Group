import java.util.*;
import librarypackage.SSN;

/*
 * Members can borrow up to 5 items from a library. When they borrow an item, a timer is set and they will be fined
 * $1 for every day after 2 weeks borrowed, and the price of the item if they do not return the item within 1 month.
 * Members can be Students or Professors, or they will be an external member if they are neither.
 */
public class Member extends Person {
    protected Hashtable<Integer, LibraryCollection> materials; // keyed by item.title.hashCode() so it can be found with
                                                               // only the title
    protected double moneyOwed;
    protected int ID;

    Member() {
        super();
        materials = new Hashtable<>();
        moneyOwed = 0;
        this.ID = this.toString().hashCode();
    }

    Member(String name, String address, Date dob, String email, SSN ssn) {
        super(name, address, dob, email, ssn);
        materials = new Hashtable<>();
        moneyOwed = 0;
        this.setID();
    }

    public void setID() {
        this.ID = this.toString().hashCode();
    }

    public int getID() {
        return this.ID;
    }

    public void borrowItem(Library library, LibraryCollection item) {
        if (materials.size() == 5) {
            System.out.println(
                    "Sorry, we cannot let you borrow more than 5 items. Please return another item if you would like to borrow more!");
            return;
        }
        if (item.isBorrowed == false) {
            materials.put(item.title.hashCode(), item);
            library.ondutyLibrarian.removeItemFromCollection(item);
            item.borrowItem(library, this);
            return;
        }
        System.out.println("Item is already being borrowed!");
    }

    public void borrowItem(Library library, String name) {
        if (materials.size() == 5) {
            System.out.println(
                    "Sorry, we cannot let you borrow more than 5 items. Please return another item if you would like to borrow more!");
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

    public void borrowItem(Library library, Section section, String name) {
        if (materials.size() == 5) {
            System.out.println(
                    "Sorry, we cannot let you borrow more than 5 items. Please return another item if you would like to borrow more!");
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

    public void returnItem(LibraryCollection item) {
        materials.remove(item.title.hashCode());
        item.returnItem();
    }
}
