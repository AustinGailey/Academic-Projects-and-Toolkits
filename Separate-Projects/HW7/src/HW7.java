/*
*   HW7
*   Program Written By: Austin Gailey
*
*   This program will build a binary search tree
*   and return some timing data to an output file
*   selected by the user.
*
*   Input:  A number file and a search query file.
*   Output: Timing data on various trees and searches.
 */


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class HW7 {

    //Initialize variables
    static JFileChooser     chooser;
    static File             input;
    static File             query;
    static File             output;

    static Scanner          data;

    static ArrayList<Integer> inputList = new ArrayList<>();
    static ArrayList<Integer> inputListSorted = new ArrayList<>();
    static ArrayList<Integer> queryList = new ArrayList<>();

    static long             timerStart;
    static long             elapsedTime;
    static double           elapsedSeconds;

    static BT               binaryST        = new BT();
    static BT               binarySTSorted  = new BT();

    static AVLTree          avlTree         = new AVLTree();
    static AVLTree          avlTreeSorted   = new AVLTree();



    public static void main(String[] args){

        input();
        loadInput();
        query();
        loadQuery();
        saveData("HW 7 - Austin Gailey",false);
        saveData("How many leaf nodes are in the binary tree? It depends on the data",true);
        saveData("How many leaf nodes are in the AVL tree?    2^h",true);
        makeBST();
        makeAVL();
        searchBST();
        searchAVL();

    }//End main


    /*
        Makes the binary search tree.
     */
    public static void makeBST(){
        startTimer();
        for(Integer i: inputList){
            binaryST.insert(i);
        }
        stopTimer();
        saveData(String.format("Time to create BST in seconds:        %20f",elapsedSeconds),true);

        startTimer();
        for(Integer i: inputListSorted){
            binarySTSorted.insert(i);
        }
        stopTimer();
        saveData(String.format("Time to create sorted BST in seconds: %20f",elapsedSeconds),true);

    }//End makeBST

    /*
        Makes the AVL tree.
     */
    public static void makeAVL(){
        startTimer();
        for(Integer i: inputList){
            avlTree.insert(i);
        }
        stopTimer();
        saveData(String.format("Time to create AVL in seconds:        %20f",elapsedSeconds),true);

        startTimer();
        for(Integer i: inputListSorted){
            avlTreeSorted.insert(i);
        }
        stopTimer();
        saveData(String.format("Time to create sorted AVL in seconds: %20f",elapsedSeconds),true);

    }//End makeAVL

    /*
        Searches the binary search tree.
     */
    public static void searchBST(){
        Integer searchDepth = 0;
        saveData("BST Search Results",true);
        saveData("Number     Found  Depth",true);
        startTimer();
        for(Integer i: queryList){
            if(binaryST.search(i)){
                searchDepth = binaryST.getSearchDepth();
                saveData(String.format("%10d true  %7d",i,searchDepth),true);
                binaryST.clearSearchDepth();
            }else{
                saveData(String.format("%10d false ",i),true);
                binaryST.clearSearchDepth();
            }
        }
        stopTimer();
        saveData(String.format("Time to search BST in seconds:        %20f",elapsedSeconds),true);

    }//End searchBST

    /*
        Searches the AVL tree.
     */
    public static void searchAVL(){
        Integer searchDepth = 0;
        saveData("AVL Search Results",true);
        saveData("Number     Found  Depth",true);
        startTimer();
        for(Integer i: queryList){
            if(avlTree.search(i)){
                searchDepth = avlTree.getSearchDepth();
                saveData(String.format("%10d true  %7d",i,searchDepth),true);
                avlTree.clearSearchDepth();
            }else{
                saveData(String.format("%10d false ",i),true);
                avlTree.clearSearchDepth();
            }
        }
        stopTimer();
        saveData(String.format("Time to search sorted AVL in seconds: %20f",elapsedSeconds),true);

    }//End searchAVL

    //Sets starting point for timer
    public static void startTimer(){
        timerStart = System.nanoTime();
    }//End startTimer

    //Subtracts start time from end time, then converts elapsed time to seconds
    public static void stopTimer(){
        elapsedTime = System.nanoTime() - timerStart;
        elapsedSeconds = (double)elapsedTime / 1000000000.0;
    }//End stopTimer

    /*
        Allows user to choose input file.
     */
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
    }//End input

    /*
        Allows user to choose query file.
     */
    public static void query(){
        System.out.println("Enter a query number file.");
        chooser = new JFileChooser(".");
        int status = chooser.showOpenDialog(null);
        if(status != JFileChooser.APPROVE_OPTION){

            System.out.println("No input file chosen - Program ending\r\n");
            System.exit(0);
        }
        query = chooser.getSelectedFile();
        System.out.println("Opening >> " + query.getName());
        try {
            data = new Scanner(query);
            data.useDelimiter("\n");
        }catch(IOException ioe){
            System.out.println("Error Reading File.");
            System.exit(0);
        }
    }//End input

    /*
        Loads input data into program.
     */
    public static void loadInput(){
        Integer intValue;
        do {
            while(data.hasNextLine()){
                try{    //To ensure input is all valid integers
                    intValue = Integer.parseInt(data.nextLine());
                    inputList.add(intValue);
                }catch(Exception e){
                    System.exit(0);
                }
            }
        }while(data.hasNextLine());
        inputListSorted = inputList;
        Collections.sort(inputListSorted);
    }//End load

    /*
        Loads query data into program.
     */
    public static void loadQuery() {
        Integer intValue;
        do {
            while (data.hasNextLine()) {
                try {    //To ensure input is all valid integers
                    intValue = Integer.parseInt(data.nextLine());
                    queryList.add(intValue);
                } catch (Exception e) {
                    System.out.println("Error reading file - Program ending");
                    System.exit(0);
                }
            }
        } while (data.hasNextLine());
    }//End loadQuery

    /*
        Saves data to a user selected output file.
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

}//End HW7

/*
 *  Various classes imported to be used with this
 *  homework assignment.
 */

/* Class BTNode */
class BTNode
{
    BTNode left, right;
    int data;

    /* Constructor */
    public BTNode()
    {
        left = null;
        right = null;
        data = 0;
    }
    /* Constructor */
    public BTNode(int n)
    {
        left = null;
        right = null;
        data = n;
    }
    /* Function to set left node */
    public void setLeft(BTNode n)
    {
        left = n;
    }
    /* Function to set right node */
    public void setRight(BTNode n)
    {
        right = n;
    }
    /* Function to get left node */
    public BTNode getLeft()
    {
        return left;
    }
    /* Function to get right node */
    public BTNode getRight()
    {
        return right;
    }
    /* Function to set data to node */
    public void setData(int d)
    {
        data = d;
    }
    /* Function to get data from node */
    public int getData()
    {
        return data;
    }
}//End class BTNode

/* Class BT */
class BT
{
    private BTNode root;
    private Integer searchDepth = 0;

    /* Constructor */
    public BT()
    {
        root = null;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Functions to insert data */
    public void insert(int data)
    {
        root = insert(root, data);
    }
    /* Function to insert data recursively */
    private BTNode insert(BTNode node, int data)
    {
        if (node == null)
            node = new BTNode(data);
        else
        {
            if (node.getRight() == null)
                node.right = insert(node.right, data);
            else
                node.left = insert(node.left, data);
        }
        return node;
    }
    /* Function to count number of nodes */
    public int countNodes()
    {
        return countNodes(root);
    }
    /* Function to count number of nodes recursively */
    private int countNodes(BTNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }
    /* Function to search for an element */
    public boolean search(int val)
    {
        return search(root, val);
    }
    /* Function to search for an element recursively */
    private boolean search(BTNode r, int val) {
        searchDepth++;
        if (r.getData() == val)
            return true;
        if (r.getLeft() != null)
            if (search(r.getLeft(), val))
                return true;
        if (r.getRight() != null)
            if (search(r.getRight(), val))
                return true;
        return false;
    }


    public Integer getSearchDepth(){
        return searchDepth;
    }

    public void clearSearchDepth(){
        searchDepth = 0;
    }

    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(BTNode r)
    {
        if (r != null)
        {
            inorder(r.getLeft());
            System.out.print(r.getData() +" ");
            inorder(r.getRight());
        }
    }

    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(BTNode r)
    {
        if (r != null)
        {
            System.out.print(r.getData() +" ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(BTNode r)
    {
        if (r != null)
        {
            postorder(r.getLeft());
            postorder(r.getRight());
            System.out.print(r.getData() +" ");
        }
    }
}//End class BST

/* Class AVLNode */
class AVLNode
{
    AVLNode left, right;
    int data;
    int height;

    /* Constructor */
    public AVLNode()
    {
        left = null;
        right = null;
        data = 0;
        height = 0;
    }
    /* Constructor */
    public AVLNode(int n)
    {
        left = null;
        right = null;
        data = n;
        height = 0;
    }
}//End class AVLNode

/* Class AVLTree */
class AVLTree
{
    private AVLNode root;
    private Integer searchDepth = 0;

    /* Constructor */
    public AVLTree()
    {
        root = null;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        root = null;
    }
    /* Function to insert data */
    public void insert(int data)
    {
        root = insert(data, root);
    }
    /* Function to get height of node */
    private int height(AVLNode t )
    {
        return t == null ? -1 : t.height;
    }
    /* Function to max of left/right node */
    private int max(int lhs, int rhs)
    {
        return lhs > rhs ? lhs : rhs;
    }
    /* Function to insert data recursively */
    private AVLNode insert(int x, AVLNode t)
    {
        if (t == null)
            t = new AVLNode(x);
        else if (x < t.data)
        {
            t.left = insert( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( x < t.left.data )
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( x > t.data )
        {
            t.right = insert( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( x > t.right.data)
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        else
            ;  // Duplicate; do nothing
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }
    /* Rotate binary tree node with left child */
    private AVLNode rotateWithLeftChild(AVLNode k2)
    {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /* Rotate binary tree node with right child */
    private AVLNode rotateWithRightChild(AVLNode k1)
    {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child */
    private AVLNode doubleWithLeftChild(AVLNode k3)
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child */
    private AVLNode doubleWithRightChild(AVLNode k1)
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(AVLNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(int val)
    {
        return search(root, val);
    }
    private boolean search(AVLNode r, int val){
        searchDepth++;
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.data;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    public Integer getSearchDepth(){
        return searchDepth;
    }

    public void clearSearchDepth(){
        searchDepth = 0;
    }


    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(AVLNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.data +" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root);
    }
    private void preorder(AVLNode r)
    {
        if (r != null)
        {
            System.out.print(r.data +" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root);
    }
    private void postorder(AVLNode r)
    {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data +" ");
        }
    }
}//End class AVLTree




