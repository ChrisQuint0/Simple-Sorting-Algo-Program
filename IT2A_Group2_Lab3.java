
/**
 * A console based program demonstrating simple sorting algorithms such as
 * Bubble, Select and Insert. A laboratory exercise for our data structures and
 * algorithms course.
 * 
 * Group2
 * Authors: Goto, Ipei B.(Leader)
 * Quinto, Christopher A. (Members)
 * Talato, Joshua P.
 * 
 * Laboratory Exercise 3
 * Date: September 24, 2024
 */

import java.util.Scanner;
import java.nio.channels.Pipe.SourceChannel;
import java.text.spi.NumberFormatProvider;
import java.util.InputMismatchException;

public class IT2A_Group2_Lab3 {

  static int[] currentArray;
  static boolean arrayExists;
  static int emptySlots;
  static int[] currentArrayTemp;
  static Scanner sc = new Scanner(System.in);
  static Utils utils = new Utils();
  static Ascii asc = new Ascii();

  public static void main(String[] args) {
    int choice = 0;
    int choiceAgain = 0;

    utils.clearScreen();

    asc.displayProgramHeading();
    System.out.println("                         By: Goto, Quinto, and Talato");
    System.out.println("\n                                   Loading...");

    utils.sleep(3000);

    utils.clearScreen();

    do {
      createArray();
      insertElements(currentArray);

      do {

        displayMenu();
        boolean validInputChoice = false;

        while (!validInputChoice) {
          try {
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

          } catch (InputMismatchException e) {

            sc.next();

            utils.clearScreen();

            asc.displayErrorAscii();

            System.out.println("\nInvalid input. Please enter an integer.");

            utils.pause();

            utils.clearScreen();

            displayMenu();

            continue;
          }

          if (choice < 0 || choice > 4) {
            System.out.println("Invalid input! Please enter a value between 0 and 4.");

            continue;
          } else {
            validInputChoice = true;
          }
        }
        executeChoice(choice);
      } while (choice != 4);

    } while (choiceAgain != -99);

  }

  public static void displayMenu() {
    asc.displayProgramHeading();

    System.out.println("                                  MENU\n");
    System.out.println("                         [ 1 ]   Bubble Sort ");
    System.out.println("                         [ 2 ]   Selection Sort ");
    System.out.println("                         [ 3 ]   Insertion Sort");
    System.out.println("                         [ 4 ]   Exit ");

  }

  public static void executeChoice(int choice) {
    switch (choice) {
      case 1:
        // Bubble Sort
        System.out.println("Bubble Sort - In development");
        break;
      case 2:
        // Selection Sort
        System.out.println("Selection Sort - In development");
        break;
      case 3:
        // Insertion Sort
        System.out.println("Insertion Sort - In development");
        break;
    }
  }

  public static void createArray() {

    int size = 0;
    boolean validInput = false;

    while (!validInput) {
      try {
        asc.displayCreateAscii();
        System.out.println("\nPlease create the array to be sorted with the simple sorting algorithms.\n");
        System.out.print("Enter array size: ");
        size = sc.nextInt();

        sc.nextLine();

      } catch (InputMismatchException e) {
        sc.next();

        utils.clearScreen();

        asc.displayErrorAscii();

        System.out.println("\nInvalid input! Please enter an integer between 5 and 15 for the array size.\n");

        utils.pause();

        utils.clearScreen();

        continue;
      }

      if (size < 5 || size > 15) {
        utils.clearScreen();

        asc.displayErrorAscii();

        System.out.println("\nInvalid size. The array size must be between 5 and 15.\n");

        utils.pause();

        utils.clearScreen();

        continue;
      } else {
        validInput = true;
      }
    }

    currentArray = new int[size];
    System.out.println("\nYou successfully created an array with the size of " + size + "\n");
    utils.pause();

    utils.clearScreen();
  }

  public static void insertElements(int[] currentArray) {
    boolean validInput = false;

    while (!validInput) {
      asc.displayInsertAscii();

      System.out.print("\nEnter " + currentArray.length + " Array Elements: ");

      String input = sc.nextLine();

      String[] inputArray = input.trim().split("\\s+");

      if (inputArray.length < currentArray.length) {
        utils.clearScreen();

        asc.displayErrorAscii();

        System.out.println("\nYou entered too few numbers, Please enter exactly " + currentArray.length + " numbers\n");

        utils.pause();

        utils.clearScreen();

        continue;
      } else if (inputArray.length > currentArray.length) {
        utils.clearScreen();

        asc.displayErrorAscii();

        System.out
            .println("\nYou entered too many numbers, Please enter exactly " + currentArray.length + " numbers\n");

        utils.pause();

        utils.clearScreen();

        continue;
      } else {

        try {
          for (int i = 0; i < currentArray.length; i++) {
            currentArray[i] = Integer.parseInt(inputArray[i].trim());
          }
        } catch (NumberFormatException n) {
          utils.clearScreen();

          asc.displayErrorAscii();

          System.out.println("\nInvalid Input. Please enter valid elements for all integers.");

          utils.pause();

          utils.clearScreen();

          continue;
        }

        validInput = true;

        utils.clearScreen();
      }

    }
  }
}

class Utils {
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void sleep(int milliSeconds) {
    try {
      Thread.sleep(milliSeconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void pause() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Press any key to continue...");

    sc.nextLine();

  }
}

class Ascii {
  public void displayProgramHeading() {
    System.out.println("         __ _                 _        __            _   _  ");
    System.out.println("        / _(_)_ __ ___  _ __ | | ___  / _\\ ___  _ __| |_(_)_ __   __ _ ");
    System.out.println("        \\ \\| | '_ ` _ \\| '_ \\| |/ _ \\ \\ \\ / _ \\| '__| __| | '_ \\ / _` | ");
    System.out.println("        _\\ \\ | | | | | | |_) | |  __/ _\\ \\ (_) | |  | |_| | | | | (_| | ");
    System.out.println("        \\__/ |_| |_| |_| .__/|_|\\___| \\__/\\___/|_|   \\__|_|_| |_|\\__, | ");
    System.out.println("                       |_|                                       |___/ ");

  }

  public void displayCreateAscii() {
    System.out.println("   ___               _  ");
    System.out.println("  / ___ __ ___  __ _| |_ ___ ");
    System.out.println(" / / | '__/ _ \\/ _` | __/ _ \\ ");
    System.out.println("/ /__| | |  __| (_| | ||  __/ ");
    System.out.println("\\____|_|  \\___|\\__,_|\\__\\___| ");

  }

  public void displayErrorAscii() {
    System.out.println("   __                     _  ");
    System.out.println("  /___ __ _ __ ___  _ __ / \\ ");
    System.out.println(" /_\\| '__| '__/ _ \\| '__/  / ");
    System.out.println("//__| |  | | | (_) | | /\\_/  ");
    System.out.println("\\__/|_|  |_|  \\___/|_| \\/   ");

  }

  public void displayInsertAscii() {
    System.out.println("_____                     _  ");
    System.out.println("\\_   \\_ __  ___  ___ _ __| |_ ");
    System.out.println(" / /\\| '_ \\/ __|/ _ | '__| __| ");
    System.out.println("/\\/ /_ | | | \\__ |  __| |  | |_  ");
    System.out.println("\\____/ |_| |_|___/\\___|_|   \\__| ");
  }
}
