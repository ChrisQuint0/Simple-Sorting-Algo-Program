
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

            System.out.println("\nInvalid input. Please enter an integer.\n");

            utils.pause();

            utils.clearScreen();

            displayMenu();

            continue;
          }

          if (choice < 1 || choice > 4) {
            utils.clearScreen();

            asc.displayErrorAscii();

            System.out.println("\nInvalid input! Please enter a value between 1 and 4.\n");

            utils.pause();

            utils.clearScreen();

            continue;
          } else {
            validInputChoice = true;
          }
        }
        executeChoice(choice);
      } while (choice != 4);

      String again = "";
      utils.clearScreen();

      while (true) {
        try {
          asc.displayContinueAscii();

          System.out.print("\nTry Again (Y/N)?: ");
          again = sc.next();

        } catch (InputMismatchException e) {
          sc.next();

          utils.clearScreen();

          asc.displayErrorAscii();

          System.out.println("\nInvalid Input. Please Enter Y/N\n");

          utils.pause();

          utils.clearScreen();

          continue;
        }

        if (again.length() > 1) {

          utils.clearScreen();

          asc.displayErrorAscii();

          System.out.println("Invalid Input. Please Enter Y/N\n");

          utils.pause();

          sc.nextLine();

          utils.clearScreen();

          continue;
        }

        char userInput = again.toLowerCase().charAt(0);

        if (userInput == 'y') {
          choiceAgain = 0;

          utils.clearScreen();

          break;
        } else if (userInput == 'n') {
          choiceAgain = -99;

          utils.clearScreen();

          asc.displayQuitAscii();

          System.out.println("\nQuit Successful\n");

          utils.pause();
          utils.clearScreen();
          break;
        } else {

          utils.clearScreen();

          sc.nextLine();

          asc.displayErrorAscii();

          System.out.println("\nInvalid Input. Please Enter Y/N\n");

          utils.pause();

          utils.clearScreen();

          continue;
        }
      }

    } while (choiceAgain != -99);

  }

  public static void displayMenu() {
    utils.clearScreen();

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
        bubbleSort(currentArray);
        break;
      case 2:
        selectSort(currentArray);
        break;
      case 3:
        insertionSort(currentArray);
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

        System.out.println("\nYour input is either incorrect or contains too few numbers, Please enter exactly "
            + currentArray.length + " numbers\n");

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

          System.out.println("\nInvalid Input. Please enter valid integers for all elements.\n");

          utils.pause();

          utils.clearScreen();

          continue;
        }

        validInput = true;

        for (int i = 0; i < currentArray.length; i++) {
          for (int j = i + 1; j < currentArray.length; j++) {
            if (currentArray[i] == currentArray[j]) {
              utils.clearScreen();

              asc.displayErrorAscii();

              System.out.println("\nInvalid Input. Duplicate elements detected.\n");

              utils.pause();

              utils.clearScreen();
              validInput = false;
              break;
            } else if (currentArray[i] == 0) {
              utils.clearScreen();

              asc.displayErrorAscii();

              System.out.println("\nInvalid Input. 0 or Null element/s detected.\n");

              utils.pause();

              utils.clearScreen();
              validInput = false;
              break;
            }
          }

          if (!validInput) {
            break;
          }
        }

        utils.clearScreen();
      }

    }
  }

  // Bubble Sort - Josh
  // For better understanding refer to the video sent by Quinto in the GC
  public static void bubbleSort(int[] array) {
    utils.clearScreen();
    // Clone the array so that the original will not be affected
    int[] tempArray = array.clone();

    utils.clearScreen();
    asc.displayBubbleAscii();

    // Print the array before sorting
    System.out.print("\nGiven Array Elements: ");
    printArray9d(tempArray);

    int n = tempArray.length;

    boolean isSwapped = false; // Declare a boolean for the bubble boolean flag used for efficiency

    for (int i = 0; i < n - 1; i++) { // Up to the second to the last element only
      isSwapped = false;

      for (int j = 0; j < n - 1 - i; j++) { // Up to second to the last element minus the number of the elements that
                                            // was already sorted
        if (tempArray[j] > tempArray[j + 1]) {
          int temp = tempArray[j];
          tempArray[j] = tempArray[j + 1];
          tempArray[j + 1] = temp;

          isSwapped = true;
        }
      }

      if (isSwapped == false) {
        break;
      }

      System.out.print("\n" + (i + 1) + ". "); // Prints the current iteration of the array
      printArray(tempArray);
    }

    // Prints the final sorted array
    System.out.println();
    System.out.print("The Sorted Array Elements: ");
    printArray9d(tempArray);

    utils.pause();
    utils.clearScreen();

  }

  // Select Sort - Quinto
  public static void selectSort(int[] array) {
    utils.clearScreen();

    asc.displaySelectionAscii();

    System.out.print("\nGiven Array Elements: ");
    printArray9d(array);
    System.out.println();

    int[] tempArray = array.clone();

    int n = tempArray.length;

    for (int i = 0; i < n - 1; i++) { // The pointer i will up to the second to the last element
      int min = i; // The min pointer will be at the position i

      for (int j = i + 1; j < n; j++) { // Pointer j will start at i + 1 index and will iterate until the last element
        if (tempArray[j] < tempArray[min]) {
          min = j;
        }
      }

      int temp = tempArray[min];
      tempArray[min] = tempArray[i];
      tempArray[i] = temp;

      System.out.print(i + 1 + ". "); // Prints the current iteration after swapping
      printArray(tempArray);
      System.out.println();
    }

    System.out.print("\nThe Sorted Array Elements: ");
    printArray9d(tempArray);
    System.out.println();

    utils.pause();
    utils.clearScreen();
  }

  // Insertion Sort-Ipei

  public static void insertionSort(int[] currentArray) {
    utils.clearScreen();

    asc.displayInsertionAscii();

    int[] array = currentArray.clone();
    // No need to check if array exists.
    // Because the user wouldn't be able to sort if they haven't created the array
    // in the first place.
    int array_length = array.length;

    // 9 / 28 : Show Given Array Elements
    System.err.print("\n\nGiven Array Elements: ");
    printArray9d(array);

    // i starts from the 2nd number in the array, i.e the 1st index.
    for (int i = 1; i < array_length; ++i) {
      int key = array[i];
      // second pointer. In the first loop, it points to the 0th index.
      int j = i - 1;

      // while j is non-negative, and the number it's pointing at is
      // greater than the key value
      while (j >= 0 && array[j] > key) {
        // move the value to the right.
        array[j + 1] = array[j];
        // And decrement.
        j -= 1;
      }
      // When j becomes -1, it means that the current key's value is the lowest,
      // therefore becoming array[0] = key;

      // But when the current key's value is the greast amongst the already sorted
      // values (the left side), it "skips" it. Putting the greatest value in its
      // place.

      // and when the current key is not the lowest but is less than the value before
      // it moves the greater value in its index (array[j+1]). and moving the key to
      // the left.

      array[j + 1] = key;
      // Display changes each itereation
      printArray(array, i);
      System.out.println();
    }

    // 9 / 28: Show Sorted Array Elements
    System.out.print("\nThe Sorted Array Elements: ");
    printArray9d(array);

    utils.pause();
    utils.clearScreen();
  }

  public static void printArray(int[] array) {
    for (int element : array) {
      System.out.printf("%9d", element);
    }

    System.out.println();
  }

  // PrintArray - Ipei Version
  public static void printArray(int[] array, int iteration) {

    System.out.print(iteration + ".  ");
    for (int j = 0; j < array.length; j++) {
      System.err.printf("%9d", array[j]);
    }
    System.err.println("");
  }

  public static void printArray9d(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.printf("%-9d", array[i]);
    }
    System.out.println("\n");
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
    System.out.println("  _____                     _  ");
    System.out.println("  \\_   \\_ __  ___  ___ _ __| |_ ");
    System.out.println("   / /\\| '_ \\/ __|/ _ | '__| __| ");
    System.out.println("/\\/ /_ | | | \\__ |  __| |  | |_  ");
    System.out.println("\\____/ |_| |_|___/\\___|_|   \\__| ");
  }

  public void displaySelectionAscii() {
    System.out.println(" __      _           _   _               __            _   ");
    System.out.println("/ _\\ ___| | ___  ___| |_(_) ___  _ __   / _\\ ___  _ __| |_ ");
    System.out.println("\\ \\ / _ \\ |/ _ \\/ __| __| |/ _ \\| '_ \\  \\ \\ / _ \\| '__| __|");
    System.out.println("_\\ \\  __/ |  __/ (__| |_| | (_) | | | | _\\ \\ (_) | |  | |_ ");
    System.out.println("\\__/\\___|_|\\___|\\___|\\__|_|\\___/|_| |_| \\__/\\___/|_|   \\__|");
    System.out.println("                                                           ");
  }

  public void displayBubbleAscii() {
    System.out.println("   ___       _     _     _        __            _   ");
    System.out.println("  / __\\_   _| |__ | |__ | | ___  / _\\ ___  _ __| |_ ");
    System.out.println(" /__\\// | | | '_ \\| '_ \\| |/ _ \\ \\ \\ / _ \\| '__| __|");
    System.out.println("/ \\/  \\ |_| | |_) | |_) | |  __/ _\\ \\ (_) | |  | |_ ");
    System.out.println("\\_____/\\__,_|_.__/|_.__/|_|\\___| \\__/\\___/|_|   \\__|");
    System.out.println("                                                    ");
  }

  public void displayInsertionAscii() {
    System.out.println("  _____                     _   _               __            _   ");
    System.out.println("  \\_   \\_ __  ___  ___ _ __| |_(_) ___  _ __   / _\\ ___  _ __| |_ ");
    System.out.println("   / /\\/ '_ \\/ __|/ _ \\ '__| __| |/ _ \\| '_ \\  \\ \\ / _ \\| '__| __|");
    System.out.println("/\\/ /_ | | | \\__ \\  __/ |  | |_| | (_) | | | | _\\ \\ (_) | |  | |_ ");
    System.out.println("\\____/ |_| |_|___/\\___|_|   \\__|_|\\___/|_| |_| \\__/\\___/|_|   \\__|");
    System.out.println("                                                                  ");
  }

  public void displayContinueAscii() {
    System.out.println("   ___            _   _                  ___ ");
    System.out.println("  / __\\___  _ __ | |_(_)_ __  _   _  ___/ _ \\");
    System.out.println(" / /  / _ \\| '_ \\| __| | '_ \\| | | |/ _ \\// /");
    System.out.println("/ /__| (_) | | | | |_| | | | | |_| |  __/ \\/ ");
    System.out.println("\\____/\\___/|_| |_|\\__|_|_| |_|\\__,_|\\___| () ");
    System.out.println("                                             ");
  }

  public void displayQuitAscii() {
    System.out.println("   __      _ _   ");
    System.out.println("  /__\\_  _(_) |_ ");
    System.out.println(" /_\\ \\ \\/ / | __|");
    System.out.println("//__  >  <| | |_ ");
    System.out.println("\\__/ /_/\\_\\_|\\__|");
    System.out.println("                 ");
  }
}
