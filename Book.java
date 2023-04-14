import java.util.Date;


public class Book extends LibraryCollection {
    private int ISBN;

    Book() {
        this.title = "";
        this.ISBN = 0;
        this.isBorrowed = false;
        this.section = Section.SCIENCES;
        this.price = 17.5;
    }
    Book(String title, int ISBN, Section section, float price) {
        this.title = title;
        this.ISBN = ISBN;
        this.isBorrowed = false;
        this.section = section;
        this.price = price;
    }
    public void borrowItem(Library library, Member member) {
        this.isBorrowed = true;
        this.dateRented = new Date();
        this.memberInformation = member.toString();
        this.starttimer(library.ondutyLibrarian, member);
    }
    public void returnItem() {
        this.isBorrowed = false;
        this.stoptimer();
    }
}
