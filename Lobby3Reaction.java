import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.KeyListener;

import java.util.*;

public class Lobby3Reaction extends Applet 
{
   //initiallizes global variables
   int numColor;
   SpaceWars arcade = new SpaceWars();
   
   //detects when the mouse clicks and determines the outcome based on the location of the click   
   public boolean mouseDown(Event e,int x, int y)
   {
      switch(game2.insideGame(x,y))
      {
         case "Flap": numColor = 1; break;
         case "Cookie": numColor = 2; break;
         case "Back": numColor = 4; break;
      }  
      repaint();
      return true;   
   }
   //sets the applet size 
   public void init()
   {
      setSize(1425,800);
   }
   //draws background by calling the lobby class
   public void paint(Graphics g)
   {
      resize(1425,800);
      Lobby3.drawBackground(g);
      g.setColor(Color.white);
      switch(numColor)
      {
         //these are going to change and call the game classes
         case 1:
            FlappyBirdRunner p = new FlappyBirdRunner();
            p.setBounds(new Rectangle(0,0,190,90));
            p.init();
            add(p,null);  
            break;
         case 2:
            CookieClicker s = new CookieClicker();
            s.setBounds(new Rectangle(0,0,190,90));
            s.init();
            add(s,null); 
            break;
         case 3: 
            CookieClicker t = new CookieClicker();
            t.setBounds(new Rectangle(0,0,190,90));
            t.init();
            add(t,null);
            break;
         case 4:
            Lobby2Reaction l = new Lobby2Reaction();
            l.setBounds(new Rectangle(0,0,190,90));
            l.init();
            add(l,null); 
            break;
         case 5:
            break;
      }
   }
}

class game2
{
   //this takes the mouse click location and returns a value based on where it clickes
   public static String insideGame(int x, int y)
   {
      String gameName = "";
      if (x>=175 && x<=375 && y>=275 && y<=425)
         gameName = "Flap"; 
      else if((x>=475 && x<=675 && y>=275 && y<=425))
         gameName = "Cookie";
      else if(x>=775 && x<=975 && y>=275 && y<=425)
         gameName = "Candy";
      else if(x>=50 && x<=150 && y>=25 && y<=75)
         gameName = "Back";
      return gameName;     
   }
}