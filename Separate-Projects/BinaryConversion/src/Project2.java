//This program was written by:
// ___________________________________________________________
//|  / \                                                       |
//| /   \                        ____                          |
//||_____|            |         |               |         \   /|
//||     |        __ _|_ o  __  |   ___  __   o |    ___   \ / |
//||     | |   | |__  |  | |  | |    |  |  |  | |   /___\   |  |
//||     | |___|  __| |  | |  | |____|  |__|_ | |__ \___    |  |
//|____________________________________________________________|
import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;


/*
**Project 2 - CS 2 - Section 3**
This is a binary to decimal converter.  It looks at
8 digit strings, verifies that they contain the proper
characters and returns the decimal value.
Input can be read from a file or entered manually.
 */
public class Project2 {

    static JFileChooser chooser;
    static Scanner data;
    static File theFile;
    static Scanner kb = new Scanner(System.in);
    static ArrayList<String> fileList = new ArrayList<String>();
    static String report = "Austin Gailey - Project 2 \r\n";


    /*
    Main Method - runs program commands
     */
    public static void main(String[] args) {

        enterCommands();

    }




    /*
    Uses user input to call methods
    */
    public static void enterCommands() {
        //prompts available commands
        promptCommands();
        //selects command and runs methods
        switch(kb.nextLine()) {

            case "s":
                selectFile();
                enterCommands();
                break;

            case "b":
                manualEntry();
                enterCommands();
                break;

            case "q":
                System.out.println("Qutiing the program");
                System.exit(0);
                break;

            case "save":
                System.out.println("Saving the report");
                saveData();
                enterCommands();
                break;


            default:
                System.out.println("Invalid input, try again");
                enterCommands();
                break;
        }
    }//End enterCommands

    //prompts the user with the available commands
    public static void promptCommands() {
        System.out.println("Welcome to The Binary Converter 2000");
        System.out.println("Select a command to convert binary to decimal.");
        System.out.println("\r\nEnter one of the following commands:");
        System.out.println("b    - Manually enter binary number");
        System.out.println("s    - Select a file to convert to decimal");
        System.out.println("save - Save the output to a report");
        System.out.println("q    - Quit the Program");
    }//End promptCommands

    /*
    Prompts the user to select a file to open.
     */
    public static void selectFile(){
        chooser = new JFileChooser(".");
        int status = chooser.showOpenDialog(null);
        if(status != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file chosen - program ending");
            System.exit(0);
        }
        theFile = chooser.getSelectedFile();
        System.out.println("Opening " + theFile.getName());
        try {
            data = new Scanner(theFile);
            data.useDelimiter("");
            readInTheData();
        } catch (IOException ioe) {
            System.out.println("Invalid file.  Please try again.");
            enterCommands();
        }
    }//End selectFile

    /*
    Reads the data and gives an output to the console.
     */
    public static void readInTheData() {
        String value;
        //ArrayList<String> fileList = new ArrayList<String>();
        do {
            while (data.hasNextLine()) {
                value = data.nextLine();
                //System.out.println("Value read is " + value);
                fileList.add(value);
            }
        } while (data.hasNextLine());
        convertToDecimal(fileList);
    }//End readInTheData

    //Saves the new data to a selected file----This can be the same
    public static void saveData() {           //as the input file
        File theFile;
        chooser = new JFileChooser(".");
        int status = chooser.showOpenDialog(null);
        if(status != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file chosen - failed to save");
        }else{
            theFile = chooser.getSelectedFile();
            System.out.println("Saving to >> " + theFile.getName());
            try {
                PrintWriter out = new PrintWriter(theFile);
                out.print(report);

                //Closes file
                out.close();
            } catch (IOException ioe) {
            }
        }
    }//End saveData

    /*
    Prompts user to input an 8 digit string.
     */
    public static void manualEntry(){
        System.out.println("Enter an eight digit binary number:");
        String entry = kb.nextLine();
        ArrayList<String> manualList = new ArrayList<String>(1);
        manualList.add(entry);
        convertToDecimal(manualList);
    }//End ManualEntry

    /*
    Converts binary string data to decimal form.

    Parameters: ArrayList<String>
     */
    public static void convertToDecimal(ArrayList<String> in){
        for(String n : in){
            boolean valid = validateString(n);
            if(valid == true){
                int length = 8;
                int total = 0;
                for(int i=0; i<8;i++){
                    if(n.charAt(i) == '1'){
                        total += Math.pow(2, length-1);
                    }
                    length--;
                }
                System.out.println("Decimal Value: " + total + "\r\n");
                report+= "\r\n" + "Decimal Value: " + total + "\r\n";
            }
        }
    }//End convertToDecimal

    /*
    Ensures string is a valid input.

    Parameters: String
     */
    public static boolean validateString(String n){
        boolean valid = false;
        int length = n.length();
        System.out.println(n);
        report+= "\r\n" + n;
        if(length != 8){
            System.out.println("Status: Invalid\r\n");
            report+= "\r\n" + "Status: Invalid\r\n";
            return false;
        }
        for(int i=0;i<length;i++){
            char testChar = n.charAt(i);
            if(testChar == '0' || testChar == '1'){
                valid = true;
            }else{
                System.out.println("Status: Invalid\r\n");
                report+= "\r\n" + "Status: Invalid\r\n";
                return false;
            }
        }
        if(valid == false){
            System.out.println("Status: Invalid\r\n");
            report+= "\r\n" + "Status: Invalid\r\n";
        }else{
            System.out.println("Status: Valid");
            report+= "\r\n" + "Status: Valid";
        }
        return valid;
    }//End validateString
}
