import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.lang.Math;

public class Block2 extends Applet
{
   /////////////////////////Instance Variables/////////////////////
   //Cordinates for block
   double ycoord;
   double xcoord;
   //move values
   double ymove;
   boolean up;
   boolean down;
   //Sensitivty, so we can chage sensitivity
   double sensitive = .5;
   
   //////////////////////////Constructors/////////////////////////
   public Block2()
   {
      //Sets Values for all instance variables
      up = false;
      down = false;
      ycoord = 200.0;
      ymove = 0.0;
      xcoord = 1375;
      double sensitive = .5;        
   }
   
   ///////////////////////Graphics Methods////////////////////
   public void drawBlock(Graphics g)
   {
      g.setColor(Color.white);
      g.fillRect((int)xcoord,(int)ycoord,20,100);
   }
   
   //Moves block
   public void moveBlock()
   { 
      if(ycoord > 0 && ycoord < 700)
      {
         if(up)
            ymove-=getSensitive();
         else if(down)
            ymove+=getSensitive();
      }
      if(ycoord <= 0)//If the block goes below 0, the y coordinate is reset to 5 and it doesn't move up
      {
         ymove = 0;
         ycoord = 5;
      }
      if(ycoord >= 700)//If the block goes above 700, the y coordinate is reset to 695 and it doesn't move down
      {
         ymove = 0;
         ycoord = 695;
      }  
      ycoord+=ymove;
      setyVal((int)ycoord);
   }   
   
   ////////////////////////Setter Methods////////////////////////////
   public void setyVal(int y)
   {
      ycoord = y;
   }
   
   public void setUp(boolean input)
   {
      up = input;
   }
   
   public void setDown(boolean input)
   {
      down = input;
   }
   
   public void setSensitive(double a)
   {
      sensitive = a;
   }
   
   //////////////////////////Accessor Methods///////////////////////
   public int yVal()
   {
      return (int)ycoord;
   }
   
   public double getSensitive()
   {
      return sensitive;
   }
}