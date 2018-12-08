import javax.imageio.*;
import java.awt.image.*;
import java.awt.Image;
import java.io.*;

/**
*	ColorImage is responsible for pulling
*   the image apart into it's RGB and alpha components.
*	<h2>Sample use</h2>
*	<h3>Creating a ColorImage from a BufferedImage</h3>.
*	<pre>
*	{@code
*	ColorImage ci = new ColorImage(img);
*	}
*	</pre>
*	<h3>Getting and manipulating the image's red and green planes.</h3>
*	<pre>
*	{@code
*	short[][] red = ci.getRed();
*	short[][] green = ci.getGreen();
*	for (int r=0; r<red.length; r++) {
*		for (int c=0; c < red[100].length; c++) {
*			red[r/2][c/2] = red[r][c];
*			green[r/2][c/2] = green[r][c];
*		}
*	}
*	}
*	</pre>
*	<h3>Creating a new ColorImage and saving it to a file.</h3>
*	<pre>
*	{@code
*	ColorImage two = new ColorImage(red, green, ci.getGreen(), ci.getAlpha());
*	File f = new File("picTwo.jpeg");
*	try {
*		two.save(f);
*	} catch (IOException e) {
*		System.out.printf("File save failed\n");
*	}
*	}
*	</pre>
*
*	@author Aaron Gordon
*/
public class ColorImage {
	int [] pix;	//holds the pixels from the image 
	short [][] red, green, blue, alpha;
	int pixwidth, pixheight;

	/**
	*	constructs a ColorImage object from an Image
	*
	*	@param img the image
	*
	*/
	public ColorImage(Image img) {
		if (img==null) return;
		pixwidth  = img.getWidth(null);
		pixheight = img.getHeight(null);
		System.out.printf("width is %d, height is %d\n\n",pixwidth,pixheight);
		PixelGrabber  grab =
						new PixelGrabber(img,0, 0, pixwidth, pixheight, true); //forceRGB=true
		try {
			grab.grabPixels();
		} catch(Exception e) {
			System.err.println("ColorImage:constructor: pixel grabbing failed!!");
			return;
		}
		pix		= (int []) grab.getPixels();
		alpha	= new short[pixheight][pixwidth];
		red		= new short[pixheight][pixwidth];
		green	= new short[pixheight][pixwidth];
		blue	= new short[pixheight][pixwidth];
		int spot = 0;		//index into pix
		for (int r = 0;  r< pixheight; r++) {
			for (int c = 0; c < pixwidth; c++) {
				int num = pix[spot++];
				blue[r][c]	 = (short)(num & 255);
				num = num >> 8;
				green[r][c]	 = (short)(num & 255);
				num = num >> 8;
				red[r][c]	 = (short)(num & 255);
				num = num >> 8;
				alpha[r][c]	 = (short)(num & 255);
			}//for c
		}//for r
		//show(red);
	}//constructor

	public ColorImage(short[][]rd, short[][]g, short[][]b, short[][]al) {
		pixheight = rd.length;
		pixwidth  = rd[0].length;
		red		= rd.clone();		//new short[pixheight][pixwidth];
		green	= g.clone();		//new short[pixheight][pixwidth];
		blue	= b.clone();		//new short[pixheight][pixwidth];
		alpha	= al.clone();		//new short[pixheight][pixwidth];
		pix		= new int[pixwidth * pixheight];
		int pixel;		//holds RGBA for 1 pixel
		int spot = 0;
		for (int r = 0;  r< pixheight; r++) {
			for (int c = 0; c < pixwidth; c++) {
				pixel = alpha[r][c];
				pixel = pixel << 8;
				pixel += red[r][c];
				pixel = pixel << 8;
				pixel += green[r][c];
				pixel = pixel << 8;
				pixel += blue[r][c];
				pix[spot++] = pixel;
			}//for c
		}//for r
		new PictureViewer(this.getAsBufferedImage());
	}//constructor

	public BufferedImage getAsBufferedImage() {
		BufferedImage pict = new BufferedImage(pixwidth, pixheight, BufferedImage.TYPE_INT_RGB);
		pict.setRGB(0, 0, pixwidth, pixheight, pix, 0, pixwidth);
		return pict;
	}

	/**
	* getRed returns the red value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@return the red value between 0 and 255
	*/
	public short getRed(int r, int c) {
		return red[r][c];
	}

	/**
	* getGreen returns the green value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@return the green value between 0 and 255
	*/
	public short getGreen(int r, int c) {
		return green[r][c];
	}

	/**
	* getBlue returns the blue value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@return the blue value between 0 and 255
	*/
	public short getBlue(int r, int c) {
		return blue[r][c];
	}

	/**
	* getAlpha returns the alpha value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@return the alpha value between 0 and 255
	*/
	public short getAlpha(int r, int c) {
		return alpha[r][c];
	}

	/**
	* set the red value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@param val the value to set
	*/
	public void setRed(int r, int c, short val) {
		red[r][c] = val;
	}

	/**
	* set the green value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@param val the value to set
	*/
	public void setGreen(int r, int c, short val) {
		green[r][c] = val;
	}

	/**
	* set the blue value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@param val the value to set
	*/
	public void setBlue(int r, int c, short val) {
		blue[r][c] = val;
	}

	/**
	* set the alpha value between 0 and 255 for
	* the pixel at the specifc row and column.
	*
	*	@param r the row
	*	@param c the column
	*	@param val the value to set
	*/
	public void setAlpha(int r, int c, short val) {
		alpha[r][c] = val;
	}

	/**
	* get all of the red values between 0 and 255 for
	* the pixels in the image
	*
	*	@return the 2D array of values
	*/
	public short[][] getRed() {
		return red.clone();
	}//getRed
	
	/**
	* get all of the green values between 0 and 255 for
	* the pixels in the image
	*
	*	@return the 2D array of values
	*/
	public short[][] getGreen() {
		return green.clone();
	}//getGreen

	/**
	* get all of the blue values between 0 and 255 for
	* the pixels in the image
	*
	*	@return the 2D array of values
	*/
	public short[][] getBlue() {
		return blue.clone();
	}//getBlue

	/**
	* get all of the alpha values between 0 and 255 for
	* the pixels in the image
	*
	*	@return the 2D array of values
	*/
	public short[][] getAlpha() {
		return alpha.clone();
	}//getAlpha

	/**
	*	saves the file as a jpeg to disk
	*
	*	@param thefile The file to save to.
	*/
	public void save(File thefile) throws IOException {
		if (!ImageIO.write(this.getAsBufferedImage(),"jpeg",thefile))
					System.err.println("Couldn't write file - save failed");
	}//save


}//class
