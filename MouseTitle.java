import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MouseTitle extends Applet implements KeyListener
{
   public void init()
   {
      this.requestFocus();
      this.addKeyListener(this);
      this.resize(1425,800); 
   }

   public void paint(Graphics g)
   {
     resize(1425,800);
     g.setColor(Color.black);
     g.fillRect(0,0,1425,800);
     g.setColor(Color.DARK_GRAY);
     g.fillRect(350-10,300+10,600,200);
     g.setColor(Color.LIGHT_GRAY);
     g.fillRect(350,300,600,200);
     g.setColor(Color.black);
     g.setFont(new Font("Courier", Font.BOLD, 36));
     g.drawString("Hit 1 for Normal Mode" ,400,350);
     g.drawString("Hit 9 for Insane Mode",400,450);      
   }
   public void keyTyped( KeyEvent e ) 
   {
                                                         //Nothing happens here, as we do not need keyTyped, but this is part of keyListner
   }
      
   public void keyReleased( KeyEvent e ) 
   {
                                                         //Nothing happens here, as we do not need keyReleased, but this is part of keyListner
   }
      
   public void keyPressed( KeyEvent e )                  //We use the keyPressed part of Key Listener in order to
   {                                                     //Detect when a key is pressed and in turn when to move the snake
      if(e.getKeyCode() == e.VK_ESCAPE)                    //This line of code make it so when you press escape
      {                                                    //It creates a LobbyReation( our main Lobby)
            Lobby2Reaction goBack = new Lobby2Reaction();    //It ends the current snakeGame
            goBack.init();                                 //and puts you back into a reactive lobby
            goBack.setBounds(new Rectangle(0,0,190,90));   
            add(goBack,null);
      }
      
      if(e.getKeyCode() == e.VK_9)                          //This set of code makes it so when you press R
      {                                                     //It creates a new SnakeRunner
            InsaneChaseMouse s2 = new InsaneChaseMouse();              //And resets your game, so you can either
            s2.setBounds(new Rectangle(0,0,190,90));         //Restart during a game, or start a new one when you die
            s2.init();
            add(s2,null); 
      }
      if(e.getKeyCode() == e.VK_1)                          //This set of code makes it so when you press R
      {                                                     //It creates a new SnakeRunner
            ChaseMouse s1 = new ChaseMouse();              //And resets your game, so you can either
            s1.setBounds(new Rectangle(0,0,190,90));         //Restart during a game, or start a new one when you die
            s1.init();
            add(s1,null); 
      }
   }

   
}