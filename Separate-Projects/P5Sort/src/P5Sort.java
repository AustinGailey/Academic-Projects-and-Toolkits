/*
    Project Written By: Austin Gailey
    Date: 2/28/2018
    Input: .txt files of Strings or Integers
    Output: P5Output.txt

    Description:  This program implements several sorting methods
                  and reports the times of the sort.

 */

//Import libraries
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

public class P5Sort {
    //Initialize variable
    static JFileChooser chooser;
    static File inputString;
    static File inputInteger;
    static File output;
    static Scanner stringData;
    static Scanner integerData;

    static ArrayList<String> arrayString = new ArrayList<>();
    static ArrayList<Integer> arrayInteger = new ArrayList<>();

    static long cpuStartTime;
    static long cpuDifference;
    static long wallClockStart;
    static long wallClockDifference;

    /*
    Runs the program
     */
    public static void main(String[] args) {

        input();
        loadData();
        saveData("", false);
        bubbleSort();
        selectionSort();
        javaSort();


    }

    /*
    Prompts user to select input files
     */
    public static void input() {
        System.out.println("Enter the text file.");
        chooser = new JFileChooser(".");
        int status = chooser.showOpenDialog(null);
        if (status != JFileChooser.APPROVE_OPTION) {

            System.out.println("No inputFile chosen - Program ending\r\n");
            System.exit(0);

        }
        inputString = chooser.getSelectedFile();
        System.out.println("Opening >>" + inputString.getName());
        try {

            stringData = new Scanner(inputString);
            stringData.useDelimiter("\n\r");

        } catch (IOException ioe) {
            System.out.println("Error Reading File.");
            System.exit(0);
        }
        System.out.println("Enter the number file.");
        chooser = new JFileChooser(".");
        status = chooser.showOpenDialog(null);
        if (status != JFileChooser.APPROVE_OPTION) {

            System.out.println("No inputFile chosen - Program ending\r\n");
            System.exit(0);

        }
        inputInteger = chooser.getSelectedFile();
        System.out.println("Opening >>" + inputInteger.getName());
        try {

            integerData = new Scanner(inputInteger);
            integerData.useDelimiter("\n\r");

        } catch (IOException ioe) {
            System.out.println("Error Reading File.");
            System.exit(0);
        }
    }//End Input

    /*
    Loads the input data into the ArrayLists
     */
    public static void loadData() {
        String stringValue;
        Integer intValue;
        do {
            while (stringData.hasNextLine()) {
                stringValue = stringData.nextLine();
                //System.out.println("Value read is " + stringValue);   //For debugging
                arrayString.add(stringValue);
            }
        } while (stringData.hasNextLine());
        do {
            while (integerData.hasNextLine()) {
                intValue = Integer.parseInt(integerData.nextLine());
                //System.out.println("Value read is " + intValue);      //For debugging
                arrayInteger.add(intValue);
            }
        } while (integerData.hasNextLine());
    }//End loadData

    /*
    Sorts ArrayLists using BubbleSort
     */
    public static void bubbleSort() {
        ArrayList<String> bubbleStringArray = new ArrayList(arrayString);
        ArrayList<Integer> bubbleIntegerArray = new ArrayList(arrayInteger);
        int n = bubbleStringArray.size();
        saveData("\r\nNumber of words:   " + n,true);
        saveData("Number of numbers: " + bubbleIntegerArray.size() + "\r\n",true);
        cpuStartTime = System.nanoTime();
        wallClockStart = System.currentTimeMillis();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (bubbleStringArray.get(j).compareTo(bubbleStringArray.get(j + 1)) > 0) {
                    // swap values
                    Collections.swap(bubbleStringArray, j, j + 1);
                }
            }
        }
        cpuDifference = System.nanoTime() - cpuStartTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        saveData(String.format("Bubble sort String cpu   time: %-20d",cpuDifference),true);
        saveData(String.format("Bubble sort String clock time: %-10d",wallClockDifference),true);

//      for(String s: bubbleStringArray){     //For debugging
//          System.out.println(s);
//      }
//      for(String s: arrayString){          //For debugging
//          System.out.println(s);
//      }

        n = bubbleIntegerArray.size();
        cpuStartTime = System.nanoTime();
        wallClockStart = System.currentTimeMillis();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (bubbleIntegerArray.get(j) > (bubbleIntegerArray.get(j + 1))) {
                    // swap values
                    Collections.swap(bubbleIntegerArray, j, j + 1);
                }
            }
        }
        cpuDifference = System.nanoTime() - cpuStartTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        saveData(String.format("Bubble sort Integer cpu  time: %-20d",cpuDifference),true);
        saveData(String.format("Bubble sort Integer wall time: %-10d\r\n",wallClockDifference),true);
//        for(Integer i : bubbleIntegerArray){     //For debugging
//            System.out.println(i);
//        }

    }//End bubbleSort

    /*
    Sorts ArrayLists using SelectionSort
     */
    public static void selectionSort() {
        ArrayList<String> selectionStringArray = new ArrayList(arrayString);
        ArrayList<Integer> selectionIntegerArray = new ArrayList<>(arrayInteger);
        cpuStartTime = System.nanoTime();
        wallClockStart = System.currentTimeMillis();
        for (int i = 0; i < selectionStringArray.size(); i++) {
            // find position of smallest num between (i + 1)th element and last element
            int pos = i;
            for (int j = i; j < selectionStringArray.size(); j++) {
                if (selectionStringArray.get(j).compareTo(selectionStringArray.get(pos)) < 0) {
                    pos = j;
                }
            }
            // Swap min (smallest num) to current position on array
            String min = selectionStringArray.get(pos);
            selectionStringArray.set(pos, selectionStringArray.get(i));
            selectionStringArray.set(i, min);
        }
        cpuDifference = System.nanoTime() - cpuStartTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        saveData(String.format("Selection sort String cpu   time: %-20d",cpuDifference),true);
        saveData(String.format("Selection sort String wall  time: %-10d",wallClockDifference),true);

//        for(String s: selectionStringArray){     //For debugging
//            System.out.println(s);
//        }
        cpuStartTime = System.nanoTime();
        wallClockStart = System.currentTimeMillis();
        for (int i = 0; i < selectionIntegerArray.size(); i++) {
            // find position of smallest num between (i + 1)th element and last element
            int pos = i;
            for (int j = i; j < selectionIntegerArray.size(); j++) {
                if (selectionIntegerArray.get(j) < selectionIntegerArray.get(pos))
                    pos = j;
            }
            // Swap min (smallest num) to current position on array
            int min = selectionIntegerArray.get(pos);
            selectionIntegerArray.set(pos, selectionIntegerArray.get(i));
            selectionIntegerArray.set(i, min);
        }
        cpuDifference = System.nanoTime() - cpuStartTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        saveData(String.format("Selection sort Integer cpu  time: %-20d",cpuDifference),true);
        saveData(String.format("Selection sort Integer wall time: %-10d\r\n",wallClockDifference),true);

//        for(Integer i: selectionIntegerArray){     //For debugging
//            System.out.println(i);
//        }

    }//End selectionSort

    /*
    Sorts ArrayLists using JavaSort
     */
    public static void javaSort() {
        ArrayList<String> javaStringArray = new ArrayList(arrayString);
        ArrayList<Integer> javaIntegerArray = new ArrayList(arrayInteger);

        cpuStartTime = System.nanoTime();
        wallClockStart = System.currentTimeMillis();
        Collections.sort(javaStringArray);
        cpuDifference = System.nanoTime() - cpuStartTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        saveData(String.format("Java sort String cpu   time: %-20d",cpuDifference),true);
        saveData(String.format("Java sort String wall  time: %-10d",wallClockDifference),true);

        cpuStartTime = System.nanoTime();
        wallClockStart = System.currentTimeMillis();
        Collections.sort(javaIntegerArray);
        cpuDifference = System.nanoTime() - cpuStartTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        saveData(String.format("Java sort Integer cpu  time: %-20d",cpuDifference),true);
        saveData(String.format("Java sort Integer wall time: %-10d\r\n",wallClockDifference),true);


//        for(String s: javaStringArray){       //For debugging
//            System.out.println(s);
//        }
//        for(Integer i: javaIntegerArray){     //For debugging
//            System.out.println(i);
//        }
    }//End javaSort

    /*
    Saves data to the P5Output file.
    Parameters: String  s: To save to file
                Boolean b: To select append to file
     */
    public static void saveData(String s, boolean b) {
        if (b == false) {
            System.out.println("Select a file to save output to");
            JFileChooser chooser = new JFileChooser(".");
            int status = chooser.showOpenDialog(null);
            if (status != JFileChooser.APPROVE_OPTION) {
                System.out.println("No file chosen - failed to save");
            } else {
                output = chooser.getSelectedFile();
                System.out.println("Saving to >> " + output.getName());
                try {
                    PrintWriter out = new PrintWriter(new FileWriter(output, false));
                    out.write("P5Sort - Austin Gailey \r\n");
                    //Closes file
                    out.close();
                } catch (IOException ioe) {
                }
            }
        } else {
            try {
                PrintWriter out = new PrintWriter(new FileWriter(output, true));
                out.write(s);
                out.write("\r\n");

                //Closes file
                out.close();
            } catch (IOException ioe) {
            }
        }

    }//End saveData
}//End P5Sort



