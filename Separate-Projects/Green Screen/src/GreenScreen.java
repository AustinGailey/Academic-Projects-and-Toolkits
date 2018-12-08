//This program was written by:
// ____________________________________________________________
//|  / \                                                       |
//| /   \                        ____                          |
//||_____|            |         |               |         \   /|
//||     |        __ _|_ o  __  |   ___  __   o |    ___   \ / |
//||     | |   | |__  |  | |  | |    |  |  |  | |   /___\   |  |
//||     | |___|  __| |  | |  | |____|  |__|_ | |__ \___    |  |
//|____________________________________________________________|
//
//This program will take a green screen photo and a background photo.
//It will then merge the photos by removing the green pixels and replace
//them with the pixels of the background photo.
//The program can then save the new photo to a file.
//
import javax.imageio.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;

public class GreenScreen {
	//Initialize variables
	static Scanner kb = new Scanner(System.in);
	static JFileChooser chooser;
	static ColorImage imgForeground;
	static ColorImage imgBackground;
	static ColorImage imgMerged;
	
	//Main method runs the program
	public static void main(String[] args) {
		
		enterCommands();
	}//main
	
	//Allows user to enter commands.
	public static void enterCommands() {
		//prompt the available commands to the user
		promptCommands();
		//Take user input and execute commands
		switch(kb.nextLine()) {
			//Selects photos
			case "select": 
				selectPhotos();
				enterCommands();
			break;
			//Merges photos
			case "merge":
				mergePhotos();
				enterCommands();
			break;
			//Prints merged image
			case "print":
				printMergedImage(imgMerged);
				enterCommands();
			break;
			//Saves new image
			case "save":
				saveFile();
				enterCommands();
			break;
			//Quits the program
			case "quit":
				System.out.println("Quitting the Program");
				System.exit(0);
			break;
			//Default output
			default:
				System.out.println("Invalid Command - Please Try Again");
				enterCommands();
			break;
		}
	}//enterCommands
	
	//Prints all of the available commands
	public static void promptCommands() {
		System.out.println("Welcome to EZ Green Screen");
		System.out.println("------------------------------");
		System.out.println("Enter a command or 'quit' to quit");
		System.out.println("'select'  - Selects the photos you would like to merge.");
		System.out.println("'merge'   - Merges the two selected photos");
		System.out.println("'print'   - Print the new merged photo");
		System.out.println("'save'    - Save the new photo");
		System.out.println("'quit'    - Quits the program");
	}//promptCommands
	
	//Prints the merged image
	public static void printMergedImage(ColorImage imgMerged) {
		BufferedImage bufferedMergedImage = imgMerged.getAsBufferedImage();
		new PictureViewer(bufferedMergedImage);
	}//printMergedImage
	
	//Replaces green pixels and merges the two photos
	public static void mergePhotos() {
		//initialize imgMerged
		imgMerged = imgForeground;
		//gets Foreground pixel data
		short[][] redFore   = imgForeground.getRed();
		short[][] greenFore = imgForeground.getGreen();
		short[][] blueFore  = imgForeground.getBlue();
		short[][] alphaFore = imgForeground.getAlpha();
		//gets Background pixel data
		short[][] redBack   = imgBackground.getRed();
		short[][] greenBack = imgBackground.getGreen();
		short[][] blueBack  = imgBackground.getBlue();
		short[][] alphaBack = imgBackground.getAlpha();
		//initializes Merged pixel data
		short[][] redMerged   = imgMerged.getRed();
		short[][] greenMerged = imgMerged.getGreen();
		short[][] blueMerged  = imgMerged.getBlue();
		short[][] alphaMerged = imgMerged.getAlpha();
		//iterates through pixel rows
		for(int r=0;r<imgForeground.pixheight;r++) {
			//iterates through pixel columns
			for(int c=0;c<imgForeground.pixwidth;c++) {
				//replaces green pixels
				if(greenFore[r][c]-35 > redFore[r][c] && greenFore[r][c] > blueFore[r][c]) {
					//replaces green with background pixels
					redMerged[r][c]   = redBack[r][c];
					greenMerged[r][c] = greenBack[r][c];
					blueMerged[r][c]  = blueBack[r][c];
					alphaMerged[r][c] = alphaBack[r][c];
				}else{
					//replaces merged pixel with Foreground pixels
					imgMerged.red[r][c]   = redFore[r][c];
					imgMerged.green[r][c] = greenFore[r][c];
					imgMerged.blue[r][c]  = blueFore[r][c];
					imgMerged.alpha[r][c] = alphaFore[r][c];
				}//end if	
			}//end for
		}//end for
		//creates new merged image
		ColorImage imgNew =new ColorImage(redMerged,greenMerged,blueMerged,alphaMerged);
		//saves new image as imgMerged
		imgMerged = imgNew;
	}//mergePhotos
	
	//Saves the new merged image
	public static void saveFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		int code = chooser.showOpenDialog(null);
		if(code != JFileChooser.APPROVE_OPTION) {
			System.out.println("No file chosen, try again");
			enterCommands();
		}
		File f = chooser.getSelectedFile();
		try {
			imgMerged.save(f);
		} catch (IOException ioe) {
			System.out.println("Invalid File - Program Ending");
			System.exit(0);	
		}
	}//saveFile
	
	//Selects the photos to merge
	public static void selectPhotos() {
		System.out.println("Enter the Green Screen photo");
		imgForeground = new ColorImage(getImage());
		System.out.println("Enter the Background Photo");
		imgBackground = new ColorImage(getImage());
	}//selectPhotos
	
	//Gets the BufferedImage
	public static BufferedImage getImage() {
		//Initializes BufferedImage
		BufferedImage img = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		int code = chooser.showOpenDialog(null);
		if(code != JFileChooser.APPROVE_OPTION) {
			System.out.println("No file chosen, program ending");
			System.exit(0);
		}
		File f = chooser.getSelectedFile();
		try {
			img = ImageIO.read(f);
		} catch (IOException ioe) {
			System.out.println("Invalid Image - Program Ending");
			System.exit(0);	
		}
		new PictureViewer(img);
		return img;
	}//getImage
}//GreenScreen
