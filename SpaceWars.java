import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Scanner;
import java.util.Random;
import java.awt.event.KeyEvent;

public class SpaceWars extends Applet
{
   //////////////////////Instance Variables////////////////////////
   static int pointx1 = 700;
   static int pointx2 = 730;
   static int pointx3 = 670;
   static int AlienX = 0;
   static int AlienY = 0;
   static int score = 0;
   static boolean shoot = false;
   static boolean[][] createBricks = new boolean[3][13];
   static boolean once = true;
   
   //////////////////////Modifier Methods////////////////////////
   //Draws the Background
   public static void drawBackground(Graphics g)
   {
      g.setColor(Color.black);
      g.fillRect(0, 0, 1365, 850);
      
      //Generates the stars in the background
      Random randx = new Random(12345);
      Random randy = new Random(12345);
      for(int x = 0; x < 140; x++)
      {
         g.setColor(Color.white);
         g.fillOval(randx.nextInt(1365), randy.nextInt(850), 5, 5);
      }
      
      //Sets the Font to Serif, Bolded Font, and 100 size font
      g.setFont(new Font("Serif", Font.BOLD, 100));
      //Creates a Random Color
      g.setColor(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
      g.drawString("Welcome to Spacewar!", 200, 400); 
      //Sets the Font to Serif, Bolded Font, and 24 size font
      g.setFont(new Font("Serif", Font.BOLD, 24));
      g.setColor(Color.white);
      g.drawString( "To move left and right, use the Arrow Keys.", 0, 550);
      g.drawString("To shoot, Press Space!", 0, 600);
      g.drawString("Score: " + score, 1000, 600);      
   }
   
   //Creates the aliens
   public static void drawBrickslvl1(Graphics g)
   {
      //Sets the boolean multi-dimensional array
      for(AlienX = 0; AlienX < 13 && once; AlienX++)
         for(AlienY = 0; AlienY < 3; AlienY++)
            if(AlienY == 0 || AlienY == 1)
               createBricks[AlienY][AlienX] = true; 
      once = false;        
      //If the value of the multi-dimensional array is true, it draws an alien spaceship
      g.setColor(Color.green);
      for(AlienX = 0; AlienX < 13 ; AlienX++)
         for(AlienY = 0; AlienY < 3 ; AlienY++)
            if(createBricks[AlienY][AlienX])
               drawAlienShip(g);
   }
   
   private static void drawBrickslvl2(Graphics g)
   {
      //Sets the boolean multi-dimensional array
      for(AlienX = 0; AlienX < 13 && once; AlienX++)
         for(AlienY = 0; AlienY < 3; AlienY++)
            if((int)(Math.round(Math.random())) == 1)
               createBricks[AlienY][AlienX] = true; 
      once = false;        
      //If the value of the multi-dimensional array is true, it draws and alien spaceship
      g.setColor(Color.green);
      for(AlienX = 0; AlienX < 13 ; AlienX++)
         for(AlienY = 0; AlienY < 3 ; AlienY++)
            if(createBricks[AlienY][AlienX])
               drawAlienShip(g);
   }
   
   //Draws the alien spaceship
   public static void drawAlienShip(Graphics g)
   {
      Polygon alien = new Polygon();
      alien.addPoint(10+AlienX*100, 10 + AlienY*100);
      alien.addPoint((AlienX+1)*100, 10 + AlienY*100);
      alien.addPoint((AlienX + 1)*100, (AlienY+1)*100);
      alien.addPoint(80+AlienX*100, 100 + AlienY*100);
      alien.addPoint(80+AlienX*100, 80+AlienY*100);
      alien.addPoint(30+AlienX*100, 80+AlienY*100);
      alien.addPoint(30+AlienX*100, 100 + AlienY*100);
      alien.addPoint(10+AlienX*100, 100 + AlienY*100);
      g.setColor(Color.red);
      g.fillPolygon(alien);
      g.setColor(Color.black);
      g.fillRect(20 + AlienX*100, 30 + AlienY*100, 30, 30);
      g.fillRect(60 + AlienX*100, 30 + AlienY*100, 30, 30);
      
   }
   
   //Draws the Spaceship
   public static void drawSpaceship(Graphics g)
   {
      //The spaceship is a triangle
      Polygon ship = new Polygon();
      ship.addPoint(pointx1, 639);
      ship.addPoint(pointx1+10, 656);
      ship.addPoint(pointx1+10, 648);
      ship.addPoint(pointx1+20, 648);
      ship.addPoint(pointx1+20, 673);
      ship.addPoint(pointx2, 690);
      ship.addPoint(pointx3, 690);
      ship.addPoint(pointx1-10, 656);
      ship.addPoint(pointx1-10, 648);
      ship.addPoint(pointx1-20, 648);
      ship.addPoint(pointx1-20, 673);      
      g.setColor(Color.blue);
      g.fillPolygon(ship);
         
   }
   
   //Shoots the circle
   public static void shoot(Graphics g)
   {
      //Creates the Animation of the bullet moving
      try{
         for(int x = 660; x > 0 && shoot; x -= 30)
         {
            g.setColor(Color.white);
            g.fillOval(pointx1-5, x, 10, 10);
            Thread.sleep(50);
            g.setColor(Color.white);
            g.fillOval(pointx1-5, x, 10, 10);
            Thread.sleep(1);
         }
      }
      catch(InterruptedException ex)
      {
         Thread.currentThread().interrupt();
      } 
      
      //Changes the brick that the bullet hits to false, so it isn't drawn again 
      boolean oncebreak = true;
      for(int x = 0; x < 13 && shoot; x++)
      {
         for(int y = 2; y >= 0 && shoot; y--)
         {
            if(pointx1 > (10 + 100*x) && pointx1 < (100 + 100*x) && oncebreak && createBricks[y][x])
            {
               createBricks[y][x] = false;  
               oncebreak = false;    
               score++; 
               Ticket.addTickets(10);         
            }
         }  
      }
      
      if(score == 26)//If the score is 26, it moves onto the next level
      {
         for(int x = 0; x < 13; x++)
            for(int y = 0; y < 3; y++)
               createBricks[y][x] = false;
         once = true;
         drawBrickslvl2(g);
      }
      
      else
         drawBrickslvl1(g);
      
      //Stops the bullet from shooting forever                   
      shoot = false;   
   }
}