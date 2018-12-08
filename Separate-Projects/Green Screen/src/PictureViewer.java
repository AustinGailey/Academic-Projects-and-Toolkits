import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

/**
* PictureViewer creates a GUI for viewing pictures
*	<h2>Sample use</h2>
*	<h3>Reading in and displaying a picture</h3>.
*	<pre>
*	{@code
*	BufferedImage img;
*	JFileChooser choose = new JFileChooser();
*	choose.setCurrentDirectory(new File("."));
*	int code = choose.showOpenDialog(frame);
*	if (code != JFileChooser.APPROVE_OPTION) return false;
*	File f = choose.getSelectedFile();
*	try {
*		img = ImageIO.read(f);		//read in the file
*	} catch (IOException e) {
*	}
*	new PictureViewer(img, "A Picture");	//display the picture with a title of "A Picture"
*	}
*	</pre>
*
* @author Aaron Gordon
*/
public class PictureViewer  {
	private static final int OFFSET =  50;	//x and y offset for next window
	private static final int X1		=  50;	//initial value for windows X coord
	private static final int Y1		= 100;	//initial value for windows Y coord
	private static final int XMAX	= 301;	//maximum value for windows X coord
	private static int xLocation =  X1;		//X coord of NW corner of window
	private static int yLocation =  Y1;		//Y coord of NW corner of window
	//===============================================
	public final int WIDTH  = 650;
	public final int HEIGHT = 500;
	private int width;
	private int height;

	private JFrame frame;			//window on screen
	private BufferedImage img;		//image read in from a file
	private DisPanel center;		//where to display the image
	private String title;			//Holds string for window's titlebar

	/**
	*	@param pict The image to display
	*	@param title The title for the window
	*/
	public PictureViewer(BufferedImage pict, String title) {
		this.title = title;
		frame = new JFrame(this.title);
		frame.setLocation(xLocation, yLocation);
		xLocation += OFFSET;		//next window won't be exactly on top of this one
		yLocation += OFFSET;
		if (xLocation > XMAX) {
			xLocation = X1;
			yLocation = Y1;
		}

		img		= pict;
		center	= new DisPanel();
		width 	= img.getWidth();
		height	= img.getHeight() + 20;
		frame.setSize(width, height);
		center.setImage(img);
		frame.add(center, BorderLayout.CENTER);
		frame.repaint();
		frame.setVisible(true);
	}

	/**
	*	@param pict The image to display
	*/
	public PictureViewer(BufferedImage pict) {
		this(pict, "Image");
	}

}
