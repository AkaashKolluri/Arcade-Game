import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.lang.Math;

public class SoccerBall 
{
   ////////////////Instance Variables///////////////
   double ballY = (int)(Math.random()*500)+50;//Sets the Y value if the ball randomly
   double ballX;
   double ballMove;
   Block block1;
   Pong pong;
        
   /////////////////Constructor///////////////////
   public SoccerBall()
   {
      //Instantiates all the Instance Variables
      ballY = (Math.random()*750)+50;
      this.setY(ballY);
      ballX = 1425;
      ballMove = -3;
      block1 = new Block();
      pong = new Pong();
   }
   
   //Draws a red Soccerball
   public void drawSoccerBall(Graphics g)
   {
      g.setColor(Color.red);
      g.fillOval((int)ballX,(int)ballY,30,30);
   }
   
   //Moves the soccerball
   public void moveSoccerBall()
   {
      ballX+=ballMove;
   }
   
   ////////////////////Get Methods///////////////////
   public int xVal()
   {
      return (int)ballX;  
   }
   
   public int yVal()
   {
      return (int)ballY;
   }
   
   ///////////////////Setter Methods/////////////////
   public void setY(double y)
   {
      ballY = (y);
   }
    public void setX(double y)
   {
      ballX = (y);
   }
}