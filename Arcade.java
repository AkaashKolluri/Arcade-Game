import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.InputStream;

public class Arcade extends Applet implements Runnable, KeyListener
{
   //initiate global variables and objects
   int blink;
   int a;
   boolean inArcade = true;
   Thread thread;
   Image arcade;
   
   //starts the keylistener, sets the applet size, initiates the objects and imports the images
   public void init()
   {
      blink = 0;
      resize(1425,800);
      thread = new Thread(this);
      this.requestFocus();
      addKeyListener(this);
      arcade = getImage(getDocumentBase(), "arcade1.jpg");
      thread.start();
   }
   //this runs the animation of the blinking lights
   public void run()
   {
      while(inArcade)
      {
         blink++;
         
         repaint();
         //half second timer for turning on and off
         try
         {
            Thread.sleep(500);
         }
         catch(InterruptedException e)
         {
            System.out.println("reeeeee");
         }
      }
   }
   //draws the graphics of the program
   public void paint(Graphics g)
   {
      //Drawing Background
      g.drawImage(arcade,0,0,1425,800, this);
      g.setColor(Color.blue.darker());
      g.fillRect(520,300,400,200);
      Color glow = new Color(0,0,255,40);
      g.setColor(glow);
      g.fillRect(510,290,420,220);
      g.setColor(Color.black);
      g.fillRect(530,310,380,180);
      //Drawing welcomeSign
      g.setFont(new Font("Bodoni Mt Black", Font.PLAIN,35));
      g.setColor(Color.white);
      g.drawString("         Welcome",540,400);
      g.drawString("Press Space to Begin", 540,450);
      //draws the lights and determines whether or not they are on or off
      for(int x = 0; x<=1425; x+=20)
      {
         int r1;
         int g1;
         int b1;
         int a1 = 255;
         if(blink%2 == 0)
         {
            r1 = 255;
            g1 = 0;
            b1 = 0;
         } 
         else
         {
            r1 = 211;
            g1 = 211;
            b1 = 211;
         }
            
         Color on = new Color(r1,b1,g1,a1);
         g.setColor(on);
         g.fillOval(x,0,10,10);
         g.fillOval(x,775,10,10);
      }
      for(int y = 0; y<=775; y+=20)
      {
         int r1;
         int g1;
         int b1;
         int a1 = 255;
         if(blink%2 == 0)
         {
            r1 = 255;
            g1 = 0;
            b1 = 0;
         } 
         else
         {
            r1 = 211;
            g1 = 211;
            b1 = 211;
         }
         g.fillOval(0,y,10,10);
         g.fillOval(1415,y,10,10);
      }  
   }
  
   //updates the image
   public void update(Graphics g)
   {
      paint(g);
   }
   //key listenener methods
   public void keyPressed(KeyEvent e)
   {
      //if the space bar is pressed, it will send the user to the lobby
      if(e.getKeyCode() == e.VK_SPACE)
      {
         LobbyReaction l = new LobbyReaction();
         l.init();
         l.setBounds(new Rectangle(0,0,190,190));
         add(l,null);
      }
   }
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e){}
}
   
