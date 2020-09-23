import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.lang.Math;

public class Ball 
{
   //////////////////////////Instance Variables/////////////////////
   double ballY = (int)(Math.random()*500)+50;//Creates a random Y value for the ball
   double ballX;
   double ballMoveX;
   double ballMoveY;
   Block b1;
   Pong pong;
        
   /////////////////////////////Constructors////////////////////////
   public Ball()
   {
      //Set values for all instance variables of ball
      ballY = (Math.random()*700)+50;
      this.setY(ballY);
      ballX = 1375;
      ballMoveX = -3;
      ballMoveY = (Math.random()*10)+1;
      b1 = new Block();
      pong = new Pong();
   }
    
   //////////////////////////Mutator Methods////////////////////////
   //Draws the ball which is red
   public void drawBall(Graphics g)
   {
      g.setColor(Color.red);
      g.fillOval((int)ballX,(int)ballY,30,30);
   }
   
   //If a key is pressed, the moveBall method
   public void moveBall()
   {
      ballX+=ballMoveX/1.2;
      ballY+=ballMoveY/1.2;
   }
   
   ////////////////////Accessor Methods//////////////////
   public int xVal()
   {
      return (int)ballX;  
   }
   
   public int yVal()
   {
      return (int)ballY;
   }
   
   ////////////////////Setter Methods/////////////////
   public void setY(double y)
   {
      ballY = (y);
   }
   
   public void setX(double y)
   {
      ballX = (y);
   }
}