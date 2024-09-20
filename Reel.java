import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

/**
 * @author Chris
 * Represents the individual Reels of a slot machine
 */
public class Reel {
    private int currentFaceInt=0;
    private HashMap<Integer, ReelFaces> faces = new HashMap<Integer, ReelFaces>();
    private HashMap<Integer, ImageIcon> facePictures = new HashMap<Integer, ImageIcon>();

    /**
     * fills two HashMaps with values necessary for function.
     * The first HashMap maps integers to ReelFaces
     * The second HashMap maps integers to images to display
     * They line up so any given int gives a matching result in both HashMaps
     * @throws IOException when the images are not present
     */
    public void Populate() throws IOException { //puts values into the hashmaps so they're not all NULLs
        //faces maps integers to ReelFaces enumerations
        //facePictures maps integers to pngs for the reel GUI
        //their ordering matches
        faces.put(0, ReelFaces.barBlue);
        BufferedImage wPic = ImageIO.read(this.getClass().getResource("Images/0_BAR_BLUE.png"));
        facePictures.put(0, new ImageIcon(wPic));

        faces.put(1,ReelFaces.sevenWhite);
        wPic = ImageIO.read(this.getClass().getResource("Images/1_SEVEN_WHITE.png"));
        facePictures.put(1, new ImageIcon(wPic));

        faces.put(2,ReelFaces.cherry);
        wPic = ImageIO.read(this.getClass().getResource("Images/2_CHERRY.png"));
        facePictures.put(2, new ImageIcon(wPic));

        faces.put(3,ReelFaces.barRed);
        wPic = ImageIO.read(this.getClass().getResource("Images/3_BAR_RED.png"));
        facePictures.put(3, new ImageIcon(wPic));

        faces.put(4,ReelFaces.sevenBlue);
        wPic = ImageIO.read(this.getClass().getResource("Images/4_SEVEN_BLUE.png"));
        facePictures.put(4, new ImageIcon(wPic));

        faces.put(5,ReelFaces.barWhite);
        wPic = ImageIO.read(this.getClass().getResource("Images/5_BAR_WHITE.png"));
        facePictures.put(5, new ImageIcon(wPic));

        faces.put(6,ReelFaces.sevenRed);
        wPic = ImageIO.read(this.getClass().getResource("Images/6_SEVEN_RED.png"));
        facePictures.put(6, new ImageIcon(wPic));
        //System.out.println("successfully populated");
    }

    /**
     * Spins the Reel, advancing a random number of times.
     * Also calls another method to update the visible Reel image in the GUI.
     * @param currentReel The visible Reel so it can be updated
     */
    public void Spin(JLabel currentReel){ //advances the currentFace of the Reel a random number of times
        int randomNumber = ThreadLocalRandom.current().nextInt(14, 28); //gives a number between 14 & 28
        //randomNumber = 1;  //uncomment and use this line when testing display
        //System.out.println(randomNumber);
        for(; randomNumber>0;randomNumber--){ //counts down the randomNumber, advancing one face at a time till 0
            //do an "Advance" method in here that updates the image to show it spinning
            currentFaceInt++;
            if(currentFaceInt==7){
                currentFaceInt=0;
            }
            //put the advance method down here
            Advance(currentReel, currentFaceInt);
            //put something to delay it right here, so there's a moment to observe the "animation"
            //don't use sleep. Either figure out a better way or leave it as is.
        }
    }

    /**
     * Updates the visible Reel to match the current position.
     * @param reelImage the JLabel that holds the image
     * @param currentFaceInt the integer for the HashMap to match an image to
     */
    public void Advance(JLabel reelImage, int currentFaceInt) {
        reelImage.setIcon(facePictures.get(currentFaceInt));

        //tried it with a switch, but it had a problem, would consistently display BarBlue as SevenWhite
        //while troubleshooting I realized that I could do it better with a single line.
        //I'm keeping this here so I can try to figure out what was wrong at a later date.
        /*
        switch (currentFaceInt){
            case 0:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(0));
            case 1:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(1));
                break;
            case 2:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(2));
                break;
            case 3:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(3));
                break;
            case 4:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(4));
                break;
            case 5:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(5));
                break;
            case 6:
                //System.out.println("currentFaceInt = " + currentFaceInt);
                foo.setIcon(facePictures.get(6));
                break;
            default:
                //System.out.println("Something went wrong with the Reel.Advance() method.");
                break;
        } */
    }

    /**
     * Returns the current position as an integer
     * @return integer paired to the current position
     */
    public ReelFaces getCurrentFace(){ //returns the currentFace
        return faces.get(currentFaceInt);
    }

    //these two are used for determining if diagonals were made
    /**
     * Gets the previous face.
     * @return The Face 1 above(previous) to the current position
     */
    public ReelFaces getUpFace(){ //returns the face directly above currentFace
        int i = currentFaceInt-1;
        if (i== -1){
            i=6;
        }
        return faces.get(i);
    }

    /**
     * Gets the next face.
     * @return The Face 1 below(next) to the current position
     */
    public ReelFaces getDownFace(){ //returns the face directly below currentFace
        int i = currentFaceInt+1;
        if (i== 7){
            i=0;
        }
        return faces.get(i);
    }
}
