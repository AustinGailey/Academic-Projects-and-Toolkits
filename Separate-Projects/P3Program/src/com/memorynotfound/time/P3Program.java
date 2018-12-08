package com.memorynotfound.time;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class P3Program {

    static long  startTime = System.nanoTime();
    static long difference = System.nanoTime() - startTime;
    static int     randomNumber;
    static float randomFloat;

    static double     durationInSeconds;
    static long          wallClockStart;
    static long     wallClockDifference;

    static int arraySize = 100000;

    static int[] intArray = new int[arraySize];
    static float[] floatArray = new float[arraySize];

    static ArrayList<Integer> intArrayList = new ArrayList<Integer>(arraySize);
    static ArrayList<Float> floatArrayList = new ArrayList<Float>(arraySize);

    static String report = "Austin Gailey - Project 3 \r\n \r\n";
    static File theFile;


    public static void main(String[] args){
        saveData("Output", false);

        for(int i=0;i<5;i++){
            String runNumber = "Run # " + (i+1);
            saveData(runNumber, true);


            fill();
            increment();
        }
    }

    public static void fill(){  //changing to int stuff
        String fillString = "Fill the list: Number of elements: " + arraySize;
        saveData(fillString, true);
        //Fill all of the int arrays

        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i = 0;i< intArray.length;i++){
            randomNumber();
            intArray[i] = randomNumber;
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String intArrayReport = String.format("int array - CPU time%12.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockIntArrayReport = String.format("int array - wall clock%10.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i = 0;i<arraySize;i++){
            randomNumber();
            intArrayList.add(randomNumber);
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String intArrayListReport = String.format("int ArrayList - CPU time%8.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockIntArrayListReport = String.format("int ArrayList - wall clock%6.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";


        saveData(wallClockIntArrayReport, true);
        saveData(wallClockIntArrayListReport, true);
        saveData("\r\n",true);
        saveData(intArrayReport, true);
        saveData(intArrayListReport, true);
        saveData("\r\n",true);


        //Fill all of the float arrays
        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i = 0;i<floatArray.length;i++){
            randomNumber();
            floatArray[i] = randomFloat;
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String floatArrayReport = String.format("float array - CPU time%10.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockFloatArrayReport = String.format("float array - wall clock%8.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i = 0;i<arraySize;i++){
            randomNumber();
            floatArrayList.add(randomFloat);
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String floatArrayListReport = String.format("float ArrayList - CPU time%6.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockFloatArrayListReport = String.format("float ArrayList - wall clock%4.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        saveData(wallClockFloatArrayReport, true);
        saveData(wallClockFloatArrayListReport, true);
        saveData("\r\n",true);
        saveData(floatArrayReport, true);
        saveData(floatArrayListReport, true);
        saveData("\r\n",true);
    }



    public static void increment(){
        String incrementString = "Increment elements in the list:";
        saveData(incrementString, true);
        //Increment int arrays
        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i: intArray){
            i++;
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String intArrayReport = String.format("int array - CPU time%12.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockIntArrayReport = String.format("int array - wall clock%10.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i: intArrayList){
            Integer f = intArrayList.get(i);
            f++;
            intArrayList.set(i,f);
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String intArrayListReport = String.format("int ArrayList - CPU time%8.12s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockIntArrayListReport = String.format("int ArrayList - wall clock%6.12s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        saveData(wallClockIntArrayReport, true);
        saveData(wallClockIntArrayListReport, true);
        saveData("\r\n",true);
        saveData(intArrayReport, true);
        saveData(intArrayListReport, true);
        saveData("\r\n",true);


        //Increment float arrays
        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(float i: floatArray){
            i++;
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String floatArrayReport = String.format("float array - CPU time%10.12s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockFloatArrayReport = String.format("float array - wall clock%8.9s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        wallClockStart = System.currentTimeMillis();
        startTime = System.nanoTime();
        for(int i = 0;i<floatArrayList.size();i++){
            Float f = floatArrayList.get(i);
            f++;
            floatArrayList.set(i,f);
        }
        difference = System.nanoTime() - startTime;
        wallClockDifference = System.currentTimeMillis() - wallClockStart;
        durationInSeconds = durationToSeconds(difference, true);
        String floatArrayListReport = String.format("float ArrayList - CPU time%6.13s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";
        durationInSeconds = durationToSeconds(wallClockDifference, false);
        String wallClockFloatArrayListReport = String.format("float ArrayList - wall clock%4.10s:"," ") + String.format("%2s%3.8f"," ",durationInSeconds) + " seconds";

        saveData(wallClockFloatArrayReport, true);
        saveData(wallClockFloatArrayListReport, true);
        saveData("\r\n",true);
        saveData(floatArrayReport, true);
        saveData(floatArrayListReport, true);
        saveData("\r\n",true);
        saveData("\r\n",true);

    }


    public static void randomNumber(){
        Random rand = new Random();
        randomNumber = rand.nextInt(9999);
        randomFloat = rand.nextFloat() * 9999;
    }

    public static double durationToSeconds(long l, boolean b){
        if(b){
            double durationInSeconds = (double)l / 1000000000.0;
            return durationInSeconds;
        }else{
            durationInSeconds = (double)l / 1000;
            return durationInSeconds;
        }
    }

    public static void saveData(String s, boolean b) {
        if(b == false){
            JFileChooser chooser = new JFileChooser(".");
                int status = chooser.showOpenDialog(null);
                if(status != JFileChooser.APPROVE_OPTION) {
                    System.out.println("No file chosen - failed to save");
                }else {
                    theFile = chooser.getSelectedFile();
                    System.out.println("Saving to >> " + theFile.getName());
                try {
                    PrintWriter out = new PrintWriter(new FileWriter(theFile, false));
                    out.write(report);

                    //Closes file
                    out.close();
                } catch (IOException ioe) {
                }
            }
        }else{
                try {
                    PrintWriter out = new PrintWriter(new FileWriter(theFile, true));
                    out.write(s);
                    out.write("\r\n");

                    //Closes file
                    out.close();
                } catch (IOException ioe) {
                }
        }
    }
}//End P3
