import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
*	DisPanel is a JPanel that displays 
*	an Image;
*/
public class DisPanel extends JPanel {

	private Image img;	//holds Image to display

	/**
	*	sets the Image
	*
	*	@param im The Image to set
	*/
	public void setImage(Image im) {
		img = im;
	}

	public void paintComponent(Graphics g) {
		if (img != null) g.drawImage(img, 0, 0, this);
	}//paint

}//class


//TODO blah blah blah