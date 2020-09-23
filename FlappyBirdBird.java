import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

//This class create object Birds, which we can move and draw
public class FlappyBirdBird
{
   //Defining global variables
   //Bird Y defines height
   private int birdY = 350;
   //We use a boolean start(used in Runner) to make sure the bird doesnt go until the user is ready
   boolean start = false;
   
   
   //Based on int parmeter, it constructs a bird at a specfiic location. Param is used as we need five birds.
   //Given bird cordinates(relative) it draws in the bird
   public void drawBird(Graphics g)
   {
      g.setColor(Color.red);
      g.fillOval(50,birdY,20,20);
      g.setColor(Color.white);
      g.fillOval(60,birdY,10,10);
      g.setColor(Color.black);
      g.fillOval(62,birdY+2,5,5);
      int[] beakX = {65,75,65};
      int[] beakY = {birdY+7,birdY+10,birdY+13};
      g.setColor(Color.yellow);
      g.fillPolygon(beakX,beakY,3);
      g.setColor(Color.LIGHT_GRAY);
      g.fillOval(45,birdY+10,15,10);
   }
   
   //This method makes the bird go down 8 pixels, which makes it fall
   //and during our run, this is run so the bird falls
   //We uses start to make sure the it doesnt start until user is ready
   //start value starts out false, but when space is hit it is changed to tru
   public void fall()
   {
      if(start)
         setBirdY(getBirdY()+8);    
   }
    
    
   //Modifer/Accesor methods, they do exactly what they are named as
   public void setBirdY(int a)
   {
      birdY = a;
   }

   public int getBirdY()
   {
      return birdY;
   }
   
   public void setStart(boolean a)
   {
       start = a;
   }
   
   public boolean getStart()
   {
       return start;
   }
}  
 