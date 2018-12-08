import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.tools.Tool;


public class GreenScreenMain extends JFrame{

    public static void main(String[] args){

        new GreenScreenMain();
    }

    public GreenScreenMain(){

        this.setSize(500,500);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);

        this.setLocation(xPos, yPos);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Green Screen");

        JPanel thePanel = new JPanel();

        JLabel label1 = new JLabel("Tell me something");

        label1.setHorizontalTextPosition(SwingConstants.LEFT);

        label1.setVerticalTextPosition(SwingConstants.CENTER);

        thePanel.add(label1);

        this.add(thePanel);

        this.setVisible(true);
    }
}
