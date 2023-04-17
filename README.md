# Java-Project-1
Project 1 for CS3330 Spring 2023


## Code Explanation

The public Library Class has 4 sections to it, the art section, the science section, the law section, and the newspapers section. These sections can contain any of the four items held in the library (Books, Journals, DVDs and Newspapers), and are found and stored by hashing the name of the item. 
There are two types of employees, who each are assigned a library in which they can directly access the sections. The Librarian is responsible for giving memberships, sending email reminders of overdue items, charging for overdue items, and removing items that members have borrowed from the collection. The Technicians job is to sort items into the correct section after an item has been returned from a member. Employees also have an ID number, which is employee.toString().hashCode().
 
There are three types of members, who can each borrow 5 items at a time max, which are also held in a hashtable keyed by title.hashCode (I like to use hashtables so you can find items with only a name, which I feel is more realistic). Professors can supervise multiple students but students can only have one professor as superviser. When a member is not a professor or student, it is an external member who is not affiliated with the University.

Both members and employees are kept in a hashtable found by hashing the name of the person (may be duplicates, but its too small of a chance to warrant a data structure change).

When a member borrows an item, the item is removed from its corresponding section and is marked as borrowed. A timer is start, where at 12 days an email reminder is sent that the item will soon be overdue. At 14 days, another reminder is sent. All days after that until a month after the item is borrwed $1 is charged. After a month, along with the fees the member is charged the price of the item.
