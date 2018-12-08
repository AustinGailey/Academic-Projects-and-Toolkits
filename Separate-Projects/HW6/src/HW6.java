/*
*
*
*
*
*
*
*
*/


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class HW6 {

static JFileChooser chooser;
static File         input;
static File         output;
static Scanner      data;
static Integer[]        keys = new Integer[10000];
static Integer[]        array1 = new Integer[11000];
static Integer[]        array2 = new Integer[15707];
static Integer[]        array3 = new Integer[17111];
static Integer[]        array4 = new Integer[25111];

static LinkedList<Integer>  arrayList1 = new LinkedList<Integer>();
static LinkedList<Integer>  arrayList2 = new LinkedList<Integer>();
static LinkedList<Integer>  arrayList3 = new LinkedList<Integer>();
static LinkedList<Integer>  arrayList4 = new LinkedList<Integer>();


    public static void main(String[] args){

        input();
        load();
        hashChaining();
        view();
    }


    public static void hashChaining(){
        int hashIndex;
        for(int key: keys){
            hashIndex = key % 1231;

            //indexKey(array1,hashIndex,key);

            //System.out.println("array1: " + array1[hashIndex] + " at key " + key);
            array2[hashIndex] = key;
            array3[hashIndex] = key;
            array4[hashIndex] = key;
        }
    }

    public static void indexChainingKey(Integer[] array,int hashIndex,int key){
        if(array[hashIndex] == null){
            array[hashIndex] = key;
        }else{

        }
    }

    public static void indexLinearKey(Integer[] array,int hashIndex,int key){
        if(array[hashIndex] == null){
            array[hashIndex] = key;
        }else{
            indexLinearKey(array,hashIndex++,key);
        }
    }


    public static void view(){
        for(int value: array1){
            System.out.println("Array 1" + value);
        }
        for(int value: array2){
            System.out.println("Array 2" + value);
        }
        for(int value: array3){
            System.out.println("Array 3" + value);
        }
        for(int value: array4){
            System.out.println("Array 4" + value);
        }
    }

    public static void input(){
        System.out.println("Enter a number file.");
        chooser = new JFileChooser(".");
        int status = chooser.showOpenDialog(null);
        if(status != JFileChooser.APPROVE_OPTION){

            System.out.println("No input file chosen - Program ending\r\n");
            System.exit(0);
        }
        input = chooser.getSelectedFile();
        System.out.println("Opening >> " + input.getName());
        try {
            data = new Scanner(input);
            data.useDelimiter("\n");
        }catch(IOException ioe){
            System.out.println("Error Reading File.");
            System.exit(0);
        }
    }

    public static void load(){
        Integer intValue;
        int i=0;
        do {
            while(data.hasNextLine()){
                intValue = Integer.parseInt(data.nextLine());
                keys[i] = intValue;
                //System.out.println(keys[i] + " index: " + i);
                i++;
            }
        }while(data.hasNextLine());
    }//End load

    public static void save(String s, boolean b){
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
                    out.write("P6Hashing - Austin Gailey \r\n");
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
    }//End save
}//End HW6
