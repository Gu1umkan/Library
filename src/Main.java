import models.Book;
import models.Library;
import models.Reader;
import models.enums.Gender;
import models.enums.Genre;
import models.generator.MyGeneratorId;
import service.BookService;
import service.LibraryService;
import service.ReaderService;
import service.impl.BookServiceImpl;
import service.impl.LibraryServiceImpl;
import service.impl.ReaderServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryServiceImpl();
        BookService bookService = new BookServiceImpl();
        ReaderService readerService = new ReaderServiceImpl();
        while (true) {
            showCommands();
            switch (scanner.nextLine()) {
                case "1" -> {
                    Library library = new Library();
                    library.setId(MyGeneratorId.idForLibrary());
                    System.out.print("Enter name: ");
                    library.setName(scanner.nextLine());
                    System.out.print("Enter address: ");
                    library.setAddress(scanner.nextLine());
                    System.out.println(libraryService.saveLibrary(library));
                }
                case "2" -> System.out.println(libraryService.getAllLibraries());
                case "3" -> {
                    System.out.print("enter id: ");
                    System.out.println(libraryService.getLibraryById(chekLong()));
                }
                case "4" -> {
                    Library library = new Library();
                    System.out.print("Enter Library id:");
                    Long id = chekLong();
                    System.out.print("Enter name: ");
                    library.setName(scanner.nextLine());
                    System.out.print("Enter address: ");
                    library.setAddress(scanner.nextLine());
                    libraryService.updateLibrary(id, library);
                }
                case "5" -> {
                    System.out.print("Enter Library id:");
                    System.out.println(libraryService.deleteLibrary(chekLong()));
                }
                case "6" -> {
                    Book book = new Book();
                    book.setId(MyGeneratorId.idForBook());
                    System.out.print("Enter book name: ");
                    book.setName(scanner.nextLine());
                    System.out.print("Enter author: ");
                    book.setAuthor(scanner.nextLine());
                    Genre[] genres = Genre.values();
                    for (int i = 0; i < genres.length; i++) {
                        System.out.println(i + "." + genres[i]);
                    }
                    System.out.println("Enter genre");
                    try {
                        book.setGenre(genres[new Scanner(System.in).nextInt()]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print("Enter library id:");
                    System.out.println(bookService.saveBook(chekLong(), book));
                }
                case "7" -> {
                    System.out.print("Enter library id: ");
                    System.out.println(bookService.getAllBooks(chekLong()));
                }
                case "8" -> {
                    System.out.print("Enter Library id: ");
                    long id = chekLong();
                    System.out.print("Enter book id: ");
                    System.out.println(bookService.getBookById(id, chekLong()));
                }
                case "9" -> {
                    System.out.print("Enter Library id: ");
                    long id = chekLong();
                    System.out.print("Enter book id: ");
                    System.out.println(bookService.deleteBook(id, chekLong()));
                }
                case "10" -> {
                    System.out.print("Enter library id: ");
                    bookService.clearBooksByLibraryId(chekLong());}
                case "11" -> {
                    Reader reader = new Reader();
                    System.out.print("Enter full name : ");
                    reader.setFullName(chekScanner(scanner.nextLine()));
                    System.out.print("Enter email: ");
                    reader.setEmail(chekScanner(scanner.nextLine()));
                    System.out.print("Enter gender(m or f):");
                    String str = chekScanner(scanner.nextLine());
                    if (str.equalsIgnoreCase("m")) {
                        reader.setGender(Gender.MALE);
                    } else if (str.equalsIgnoreCase("f")) {
                        reader.setGender(Gender.FEMALE);
                    } else System.out.println("Error gender");
                    System.out.print("Enter phoneNumber: ");
                    reader.setPhoneNumber(chekScanner(scanner.nextLine()));
                    reader.setId(MyGeneratorId.idForReader());
                    readerService.saveReader(reader);
                }
                case "12" -> System.out.println(readerService.getAllReaders());
                case "13" -> {
                    System.out.print("Eneter reader id: ");
                    Reader reader = readerService.getReaderById(chekLong());
                    System.out.println(reader == null ? "Mындай id жок" : reader);
                }
                case "14"->{
                    System.out.print("Enter reader id: ");
                    long id = chekLong();
                    Reader reader = new Reader();
                    System.out.println("Enter  new fullname");
                    reader.setFullName(chekScanner(scanner.nextLine()));
                    readerService.updateReader(id,reader);
                }
                case "15" -> {
                    System.out.print("Enter library id: ");
                    long id = chekLong();
                    System.out.print("Enter reader id : ");
                    readerService.assignReaderToLibrary(chekLong(),id);
                }
            }
        }
    }

    public static void showCommands() {
        System.out.println("""
                1.  Save Library
                2.  Get all Libraries
                3.  Get Library by id
                4.  Update Library
                5.  Delete Library
                6.  Save Book
                7.  Get all Books by Library id
                8.  Get Book by id
                9.  Delete Book
                10. Clear books by Library id
                11. Save reader
                12. Get all readers
                13. Get Reader by id
                14. Update Reader
                15. Assign Reader To Library
                0. Exit
                                
                Enter command:
                """);
    }

    public static String chekScanner(String scannerWord) {
        while (true) {
            if (!scannerWord.isBlank()) {
                return scannerWord;
            } else {
                System.err.println(" Бош болуусу мүмкун эмес , маалымат киргизиңиз : ");
                scannerWord = new Scanner(System.in).nextLine();
            }
        }
    }

    public static long chekLong() {
        while (true) {
            try {
                long id = new Scanner(System.in).nextLong();
                return id;
            } catch (InputMismatchException e) {
                System.out.println("long тибинде id киргиз: ");
            }
        }
    }
}