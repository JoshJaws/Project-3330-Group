import java.util.*;
import librarypackage.SSN;


public class Member extends Person {
    protected Hashtable<LibraryCollection, Integer> materials;
    protected int [] dayCount;
    protected double moneyOwed;
    protected int ID;

    Member() {
        super();
        materials = new Hashtable<>();
        dayCount = null;
        moneyOwed = 0;
    }
    Member(String name, String address, Date dob, String email, SSN ssn) {
        super(name, address, dob, email, ssn);
        materials = new Hashtable<>();
        dayCount = null;
        moneyOwed = 0;
        this.ID =this.toString().hashCode();
    }
    public void borrowItem(Library library, LibraryCollection item) {
        if (materials.size() == 5) {
            System.out.println("Sorry, we cannot let you borrow more than 5 items. Please return another item if you would like to borrow more!");
            }
        if (item.isBorrowed == false) {
            if (materials == null) {
                materials = new Hashtable<>();
            }
            materials.put(item, materials.size()+1);
            library.ondutyLibrarian.fetchItem(item);
            item.borrowItem(library, this);
            return;
        }
        System.out.println("Item is already being borrowed!");
    }
    public void returnItem(LibraryCollection item) {
        materials.remove(item);
        item.returnItem();
    }
}
