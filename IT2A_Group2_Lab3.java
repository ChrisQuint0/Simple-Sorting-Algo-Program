import java.util.InputMismatchException;
import java.util.Scanner;

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
public class IT2A_Group2_Lab3 {
  static int[] currentArray;
  static boolean arrayExists;
  static int emptySlots;
  static int[] currentArrayTemp;

  static Ascii asc = new Ascii();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int choice = -99;

    do {
      displayMenu();

      System.out.print("Enter your choice: ");

      try {
        choice = sc.nextInt();
      } catch (InputMismatchException e) {
        clearScreen();
        sc.next();

        asc.displayErrorAscii();
        System.out.println("Invalid Input. Please Enter an Integer\n");

        pause();
        clearScreen();

        continue;
      }

      if (choice < 0 || choice > 7) {
        clearScreen();

        asc.displayErrorAscii();

        System.out.println("Invalid Input. Please choose from 0-6\n");

        pause();
        clearScreen();

        continue;
      } else {
        executeChoice(choice);
      }

    } while (choice != 0);

    clearScreen();
    asc.displayExitAscii();
    System.out.println("Program exited successfully\n");
    pause();
    clearScreen();

  }

  public static void displayMenu() {
    clearScreen();
    asc.displayProgramHeading();

    System.out.println("""

                            [1] Create an Array

                            [2] Insert Elements

                            [3] Bubble Sort

                            [4] Select Sort

                            [5] Insert Sort

                            [6] Reset Array

                            [7] Display

                            [0] Stop
        """);
  }

  public static void executeChoice(int choice) {
    switch (choice) {
      case 1:
        createArray();
        break;
      case 2:
        insert(currentArray, currentArrayTemp, arrayExists);
        break;
      case 3:
        // bubbleSort();

        // Delete this three lines if you've done the method
        clearScreen();
        System.out.println("In development");
        pause();
        break;
      case 4:
        selectSort(currentArray);
        break;
      case 5:
        // insertSort();

        // Delete these three lines below if you've done the method
        clearScreen();
        System.out.println("In development");
        pause();
        break;
      case 6:
        resetCurrentArray();
        break;
      case 7:
        printArray(currentArray);
        break;
    }
  }

  public static void createArray() {
    if (!arrayExists) {
      clearScreen();

      Scanner sc = new Scanner(System.in);

      int length = 0;

      do {
        asc.displayCreateAscii();

        try {

          System.out.print("Enter the number of elements for your array: ");
          length = sc.nextInt();

        } catch (java.util.InputMismatchException e) {
          sc.next();

          clearScreen();

          asc.displayErrorAscii();

          System.out.println("Invalid Input. Please Enter an Integer\n");

          pause();

          clearScreen();

          continue;
        }

        if (length < 5 || length > 20) {
          System.out.println("\nInvalid input the size must be between 5 to 20");

          System.out.println();
          pause();
          clearScreen();
        }
      } while (length < 5 || length > 20);

      currentArray = new int[length];
      currentArrayTemp = new int[length];

      arrayExists = true;

      System.out.println("\nYou've successfully created an array with a size of " + length + " elements.\n");

      pause();
    } else {
      clearScreen();

      asc.displayErrorAscii();

      System.out.println("An array has already been created. You cannot create another array at this time.\n");

      pause();
    }

    clearScreen();

  }

  public static void insert(int[] array, int[] arrayTemp, boolean arrExists) {
    Scanner sc = new Scanner(System.in);
    // if the array doesn't exist...
    if (!arrExists) {
      clearScreen();

      asc.displayErrorAscii();

      System.out.println("Array doesn't exist! You must create an array first\n");

      pause();
      clearScreen();
      return;
    }

    // Quick check if it's not full
    if (array[array.length - 1] != 0) {

      clearScreen();

      asc.displayErrorAscii();

      System.out.println("Array is full! Cannot Add\n");

      pause();
      clearScreen();
      return;
    }

    int[] tempArray = array.clone(); // Create a copy of the original array
    int insertedNumber;
    boolean duplicated = false;
    emptySlots = detectEmptySlots(tempArray);

    clearScreen();
    asc.displayInsertAscii();

    // While inserting...
    while (emptySlots != 0) {

      duplicated = false;

      System.out
          .print("Enter a number, otherwise enter -99 to stop("
              + (emptySlots + (" slots left) : ")));
      emptySlots--;

      try {
        insertedNumber = sc.nextInt();

        if (insertedNumber == 0) {
          emptySlots++;
          clearScreen();

          asc.displayErrorAscii();

          System.out.println("Cannot Enter 0 or Null\n");

          pause();

          clearScreen();

          asc.displayInsertAscii();

          continue;
        }

        // Stops
        if (insertedNumber == -99) {

          clearScreen();
          break;
        }

        // Searches if it already exists
        for (int i = 0; i < tempArray.length; i++) {
          if (tempArray[i] == insertedNumber) {
            emptySlots++;
            clearScreen();

            asc.displayErrorAscii();

            System.out.println("\nThis number is already in the array!\n\nPick another number!\n");

            duplicated = true;
            pause();
            clearScreen();

            asc.displayInsertAscii();
          }
        }

        if (duplicated == false) {
          // Moves all items to the right 1 index
          for (int i = tempArray.length - 1; i > 0; i--) {
            tempArray[i] = tempArray[i - 1];
          }
          // Puts the new number in front
          tempArray[0] = insertedNumber;

          System.out.println("\n" + insertedNumber + " Successfully Added\n");
        }

      } catch (java.util.InputMismatchException exp) {
        sc.next();

        emptySlots++;

        clearScreen();

        asc.displayErrorAscii();

        System.out.println("Invalid input. Please enter an integer.\n");

        pause();
        clearScreen();

        asc.displayInsertAscii();

        continue;
      }

    }

    for (int i = 0; i < tempArray.length; i++) {
      currentArray[i] = tempArray[i];
    }

    for (int i = 0; i < tempArray.length; i++) {
      currentArrayTemp[i] = tempArray[i];
    }
  }

  public static void selectSort(int[] array) {
    if (!arrayExists) {
      clearScreen();

      asc.displayErrorAscii();

      System.out.println("Array doesn't exist. Create an array first.\n");

      pause();

      clearScreen();

      return;
    }
    System.out.print("ORIGINAL ARRAY: ");
    printArray(array);

    int n = array.length;

    for (int i = 0; i < n - 1; i++) {

      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (array[j] < array[min]) {
          System.out.println(array[j] + " is less than " + array[min] + ", therefore " + array[j]
              + " is now the minimum at index: " + j);
          min = j;

        }
      }

      System.out.println("Swapping " + array[min] + " and " + array[i] + "...");
      int temp = array[min];
      array[min] = array[i];
      array[i] = temp;

      printSwapOrder(i);
      printArray(array);
    }
  }

  public static void printArray(int[] array) {
    if (!arrayExists) {
      clearScreen();

      asc.displayErrorAscii();

      System.out.println("Array doesn't exist. Create an array first.\n");

      pause();

      clearScreen();

      return;
    }

    for (int i : array) {
      System.out.print(i + " ");
    }

    System.out.println();
    System.out.println();

    pause();
  }

  // Organizes the formatting to 1st, 2nd, 3rd, 4th etc. based on the end of the
  // number
  public static void printSwapOrder(int i) {
    i += 1;

    String iString = Integer.toString(i);

    int stringLength = iString.length();

    if (iString.charAt(stringLength - 1) == '1') {
      System.out.print(i + "st Swap: ");
    } else if (iString.charAt(stringLength - 1) == '2') {
      System.out.print(i + "nd Swap: ");
    } else if (iString.charAt(stringLength - 1) == '3') {
      System.out.print(i + "rd Swap: ");
    } else {
      System.out.print(i + "th Swap: ");
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void pause() {
    Scanner sc = new Scanner(System.in);

    System.out.println("Press any key to continue...");

    sc.nextLine();

  }

  public static int detectEmptySlots(int[] array) {
    int emptySlotCount = 0;

    for (int i = 0; i < array.length; i++) {
      if (array[i] == 0) {
        emptySlotCount++;
      }
    }

    return emptySlotCount;
  }

  public static void resetCurrentArray() {
    if (!arrayExists) {
      clearScreen();

      asc.displayErrorAscii();

      System.out.println("Array doesn't exist. Create an array first.\n");

      pause();

      clearScreen();

      return;
    }

    for (int i = 0; i < currentArrayTemp.length; i++) {
      currentArray[i] = currentArrayTemp[i];
    }

    System.out.println("\nArray successfully reset to the original\n");

    pause();

  }

}