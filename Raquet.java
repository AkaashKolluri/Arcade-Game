import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

//Here we define our Raquet
public class Raquet extends Applet
{
   //Location of raquet
   double xcoord;
   double move;
   //bolean detecting direction
   boolean goUp;
   boolean goDown;
   
   //defining raquet
   public Raquet()
   {
      xcoord = 700;
      move = 0;
      goUp = false;
      goDown = false;
   }
   
   //drawing raquet
   public void drawRaquet(Graphics g)
   {
  	   g.setColor(Color.white);
  	   g.fillRect((int)xcoord,700,200,25);
  	
   }
   
   //Method which moves raquet
   public void moveRaquet()
   {
      //Checks if still on screen
  	   if(xcoord>0 && xcoord<1225)
     	{
         //If on screen, it move up or down depending on what it is doing
        	if(goUp)
           	move -= 0.3;
        	if(goDown)
           	move +=0.3;       	
     	}
      //If not move is back on screen
     	else if(xcoord<=0)
      {
         xcoord = 5;
        	move = 0;
      }
      //Also moves back on screen
      else if(xcoord>=1225)
      {
         xcoord = 1220;
         move = 0;
      }  	
     	xcoord+=move;
     	setXVal((int)xcoord);
   }
   
   //Simple accesor/modifier method which do exactly what they say they do
   public int raquetX()
   {
  	   return (int)xcoord;
   }
   public void setXVal(int x)
   {
  	   xcoord = x;
   }
   public void setUp(boolean input)
   {
  	   goUp = input;
   }
   public void setDown(boolean input)
   {
  	   goDown = input;
   }
}
