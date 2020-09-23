import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.KeyListener;
import java.io.*;
import sun.audio.*;
import java.util.*;

public class LobbyReaction extends Applet 
{
   //initiallizes global variables 
   int numColor;

   //detects when the mouse clicks and determines the outcome based on the location of the click 
   public boolean mouseDown(Event e,int x, int y)
   {
      switch(game.insideGame(x,y))
      {
         case "Soccer": numColor = 1; break;
         case "Snake": numColor = 2; break;
         case "Space": numColor = 3; break;
         case "Next": numColor = 4; break;
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
      Lobby.drawBackground(g);
      g.setColor(Color.white);
      switch(numColor)
      {
         //these are going to change and call the game classes based on the result of the mouse clicker
         case 1:
            Soccer p = new Soccer();
            p.setBounds(new Rectangle(0,0,190,90));
            p.init();
            add(p,null);  
            break;
         case 2:
            SnakeTitle s = new SnakeTitle();
            s.setBounds(new Rectangle(0,0,190,90));
            s.init();
            add(s,null); 
            break;
         case 3: 
            if(arcade.score < 26)
            {
               SpaceWarsRunner t = new SpaceWarsRunner();
               t.setBounds(new Rectangle(0,0,190,90));
               t.init();
               add(t,null);
            }
            break;
         case 4:
            Lobby2Reaction goBack = new Lobby2Reaction();    
            goBack.init();                                 
            goBack.setBounds(new Rectangle(0,0,190,90));   
            add(goBack,null);
            break;
      }
   }
}
//this finds the location of the mouse when it clicks and returns a string value based on that
class game
{
   public static String insideGame(int x, int y)
   {
      String gameName = "";
      if (x>=175 && x<=375 && y>=275 && y<=425)
         gameName = "Soccer"; 
      else if((x>=475 && x<=675 && y>=275 && y<=425))
         gameName = "Snake";
      else if(x>=775 && x<=975 && y>=275 && y<=425)
         gameName = "Space";
      else if(x>=950+300 && x<=1010+300 && y>=40 && y <=80)
         gameName = "Next";
      return gameName;     
   }
}