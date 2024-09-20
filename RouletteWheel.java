import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


/**
 * This class reads in pictures of a roulette wheel with varying ball positions
 * stores pictures in a hashmap
 */
public class RouletteWheel {
    private HashMap<Integer, ImageIcon> endings;
    BufferedImage temp;
    ImageIcon alsoTemp;

    /**
     * Constructor
     * instantiates the hashmap and inserts pictures
     */
    public RouletteWheel(){
        endings = new HashMap<>();

        try {
            temp = ImageIO.read(this.getClass().getResource("Images/0.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(0, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/1.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(1, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/2.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(2, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/3.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(3, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/4.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(4, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/5.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(5, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/6.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(6, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/7.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(7, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/8.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(8, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/9.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(9, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/10.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(10, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/11.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(11, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/12.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(12, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/13.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(13, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/14.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(14, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/15.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(15, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/16.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(16, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/17.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(17, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/18.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(18, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/19.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(19, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/20.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(20, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/21.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(21, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/22.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(22, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/23.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(23, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/24.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(24, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/25.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(25, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/26.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(26, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/27.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(27, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/28.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(28, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/29.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(29, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/30.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(30, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/31.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(31, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/32.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(32, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/33.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(33, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/34.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(34, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/35.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(35, alsoTemp);

            temp = ImageIO.read(this.getClass().getResource("Images/36.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(36, alsoTemp);

            //turns
            temp = ImageIO.read(this.getClass().getResource("Images/StartPosition.png"));
            alsoTemp = new ImageIcon(temp);
            endings.put(37, alsoTemp);


        } catch (IOException e){
            System.out.println("Error");
        }


    }

    /**
     * gets ending pics in a hashmap
     * @return hashmap of pics
     */
    public HashMap getEndings(){
        return endings;
    }

}
