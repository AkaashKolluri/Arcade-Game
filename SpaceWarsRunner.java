import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.KeyListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SpaceWarsRunner extends Applet implements KeyListener, Runnable
{
   ///////////////////////Instance Variables////////////////////////    
   Image dbImage;
   Graphics dbg;
   int secondsPassed = 0;
   boolean run = true;
   boolean go = true;
   Timer myTimer = new Timer();
   Thread thread;
   
   ///////////////////////Modifier Methods//////////////////////////
   //Creates a timer
   TimerTask task = new TimerTask()
   {
      public void run()
      {
         secondsPassed++;
      }
   };
   
   public void start()
   {
      myTimer.scheduleAtFixedRate(task, 100, 1000);
   }
   
   //Creates the Initial applet
   public void init()
   {
       this.requestFocus();
       addKeyListener(this);
       setSize(1425,800);
       thread = new Thread(this);
       thread.start();
   }
   
   /////////////////////////Runnable Methods////////////////////////
   //Tells the game how long it should run
   public void run()
   {
      while(go)
         if(secondsPassed == 30)
            go = false;     
   }

   //////////////////////////Drawing Methods///////////////////////
   //Stops animation from flickering
   public void update(Graphics g)
   {
      dbImage = createImage(1425,800);
      dbg = dbImage.getGraphics();
      paint(dbg);
      g.drawImage(dbImage, 0, 0, this);
   }
   
   //Draws all components of the SpaceWars class  
   public void paint(Graphics g)
   {
      SpaceWars.drawBackground(g); 
      SpaceWars.drawBrickslvl1(g);
      SpaceWars.drawSpaceship(g);
      SpaceWars.shoot(g);
      g.setColor(Color.white);
      g.drawString("" + secondsPassed, 300, 300);   
      if(secondsPassed >= 30)
         run = false; 
      thread = new Thread(this);       
   }
      
   /////////////////////////KeyListener Interface Methods//////////////////////
   public void keyTyped( KeyEvent e ) { }
      
   public void keyReleased( KeyEvent e ) { }
   
   public void keyPressed( KeyEvent e ) 
   {        
      //If the User presses the right key, the ship moves to the right
      if( e.getKeyCode() == e.VK_RIGHT && SpaceWars.pointx2 < 1425 && run)
      {
         SpaceWars.pointx1 += 10;
         SpaceWars.pointx2 += 10;
         SpaceWars.pointx3 += 10;
         repaint();
      }
      
      //If the User presses the left key, the ship moves to the left
      if( e.getKeyCode() == e.VK_LEFT && SpaceWars.pointx3 > 0 && run)
      {
         SpaceWars.pointx1 -= 10;
         SpaceWars.pointx2 -= 10;
         SpaceWars.pointx3 -= 10;
         repaint();
      }
      
      //If the user presses the space bar, the shoot method is turned on and the ship shoots
      if(e.getKeyCode() == e.VK_SPACE && run)
      {
         SpaceWars.shoot = true;
         PlaySound.laser();
         repaint();
      }
      
      //If user presses ESC, it goes back to the lobby
      if(e.getKeyCode() == e.VK_ESCAPE)
      {
         try
         {
            Thread.sleep(100);
            LobbyReaction goBack = new LobbyReaction();
            goBack.init();
            goBack.setBounds(new Rectangle(0,0,190,90));
            add(goBack,null); 
         }
         catch(InterruptedException c)
         {
            c.printStackTrace(); 
         }
      }             
   } 
}
