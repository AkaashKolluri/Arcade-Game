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
import java.util.*;

public class Lobby2Reaction extends Applet 
{  
   //initiallizes global variables 
   int numColor;
   boolean inLobby = true;
   
   //detects when the mouse clicks and determines the outcome based on the location of the click    
   public boolean mouseDown(Event e,int x, int y)
   {
      switch(game1.insideGame(x,y))
      {
         case "  PONG": numColor = 1; break;
         case "Racquet": numColor = 2; break;
         case "Space": numColor = 3; break;
         case "Back": numColor = 4; break;
         case "Next": numColor = 5; break;
      }  
      repaint();
      return true;   
   }
   //sets the applet size 
   public void init()
   {
      setSize(1425,800);
   }
      
   //draws the backround by calling the lobby class
   public void paint(Graphics g)
   {
      resize(1425,800);
      Lobby2.drawBackground(g);
      g.setColor(Color.white);
      
      switch(numColor)
      {
         //these are going to change and call the game classes
         case 1:
            inLobby=false;
            Pong p = new Pong();
            p.setBounds(new Rectangle(0,0,190,90));
            p.init();
            add(p,null);  
            break;
         case 2:
            inLobby=false;
            RaquetBallRunner s = new RaquetBallRunner();
            s.setBounds(new Rectangle(0,0,190,90));
            s.init();
            add(s,null); 
            break;
         case 3: 
            inLobby=false;
            MouseTitle t = new MouseTitle();
            t.setBounds(new Rectangle(0,0,190,90));
            t.init();
            add(t,null);
            break;
         case 4:
            LobbyReaction l = new LobbyReaction();
            l.setBounds(new Rectangle(0,0,190,90));
            l.init();
            add(l,null); 
            break;
         case 5:
            inLobby= false;
            Lobby3Reaction y = new Lobby3Reaction();
            y.setBounds(new Rectangle(0,0,190,90));
            y.init();
            add(y,null);
            break;
       }      
   }  
}

class game1
{
   //this takes the mouse click location and returns a value based on where it clickes
   public static String insideGame(int x, int y)
   {
      String gameName = "";
      if (x>=175 && x<=375 && y>=275 && y<=425)
         gameName = "  PONG"; 
      else if((x>=475 && x<=675 && y>=275 && y<=425))
         gameName = "Racquet";
      else if(x>=775 && x<=975 && y>=275 && y<=425)
         gameName = "Space";
      else if(x>=50 && x<=150 && y>=25 && y<=75)
         gameName = "Back";
      else if(x>=950+300 && x<=1010+300 && y>=40 && y <=80)
         gameName = "Next";
      return gameName;     
   }
}