import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

//RBall is used by RaquetRunner to create our ball
public class RBall extends Applet
{
   //instance variables
   //These indicate the balls cordinates
   double xVal;
   double yVal;
   //These indicate the direction the ball is moving
   double moveX;
   double moveY;
  
   //Constructor to define rBal when it is instanciated
   public RBall()
   {
     	xVal = (Math.random()*750)+10;
     	yVal = 25;
     	moveX = 3;
     	moveY = 3;
   }
   
   //Graphics method, to draw our ball based on it cordinates
   public void drawBall(Graphics g)
   {
     	g.setColor(Color.white);
     	g.fillOval((int)xVal,(int)yVal,25,25);
      setX((int)xVal);
      setY((int)yVal);
   }
   
   //When move ball is called, it makes ball move in a given direction 
   public void moveBall()
   {
     	xVal += moveX; //setX((int)xVal);
     	yVal += moveY; //setY((int)yVal);
   }
   
   //Modifier/Accesors
   //The do exactly what they are described to do
   public void setX(int x)
   {
      xVal = x;
   }
   public void setY(int y)
   {
  	   yVal = y;
   }
   public int ballX()
   {
  	   return (int)xVal;
   }
   public int ballY()
   {
  	   return (int)yVal;
   }
}
