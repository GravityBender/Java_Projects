package com.bookmanagement;

import java.util.*;
import java.util.HashMap;

public class Main {

    private String bookName;
    private String authorName;
    private int isbnNumber;
    private String choice;

    public Scanner scanner = new Scanner(System.in);

    public void insertBookData(HashMap<Integer, Book> insertisbn, HashMap<String, Book> insertBook, Book book) {

        while (true) {
            System.out.println("Enter the book name: ");
            bookName = scanner.nextLine();
            System.out.println("Enter the author name: ");
            authorName = scanner.nextLine();
            System.out.println("Enter the isbn number: ");
            isbnNumber = Integer.parseInt(scanner.nextLine());
            book = new Book(bookName, authorName);
            insertisbn.put(isbnNumber, book);
            insertBook.put(bookName, book);

            System.out.println("Do you want to continue to enter data?(y/n)");
            choice = scanner.nextLine();

            if (!(choice.equals("y") || choice.equals("Y"))) {
                break;
            }
        }
    }

    public void getBookData(HashMap<Integer, Book> insertisbn, HashMap<String, Book> insertBook, Book book) {
        while (true) {
            System.out.println("Enter the isbn number of the book to be searched(enter 0 if not known)");
            isbnNumber = Integer.parseInt(scanner.nextLine());

            if (!(isbnNumber == 0)) {
                if (insertisbn.containsKey(isbnNumber)) {
                    System.out.println(insertisbn.get(isbnNumber));
                } else {
                    System.out.println("Book not present");
                }
            } else {
                System.out.println("Enter the book name");
                bookName = scanner.nextLine();
                if (insertBook.containsKey(bookName)) {
                    System.out.println(insertBook.get(bookName));
                } else {
                    System.out.println("Book not present");
                }
            }

            System.out.println("Do you want to search the book?(y/n)");
            choice = scanner.nextLine();

            if (choice.equals("y") || choice.equals("Y")) {
                continue;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {

        Main main = new Main();
        int ch;
        Book book = new Book();
        HashMap<Integer, Book> insertisbn = new HashMap<Integer, Book>();
        HashMap<String, Book> insertBook = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose one of the following actions: ");
            System.out.println("Enter 1 to insert the book data");
            System.out.println("Enter 2 to search a book");
            System.out.println("Enter 3 to exit the interface");
            ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1:
                    main.insertBookData(insertisbn, insertBook, book);
                    break;

                case 2:
                    main.getBookData(insertisbn, insertBook, book);
                    break;

                default:
                    break;
            }
            if (ch == 3) {
                break;
            }
        }
    }
}
