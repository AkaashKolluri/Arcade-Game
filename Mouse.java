import java.awt.*;
import java.applet.*;

public class Mouse
{
   ////////////////////////////Instance Variables//////////////////////////
   int mouseX;
   int mouseY;
   boolean mouseMove = false;
   boolean erase = false;
   
   ///////////////////////////////Constructors/////////////////////////////
   public Mouse()
   {
      mouseX = (int)(Math.random()*57)*25; //Generates random x coordinate for mouse
      mouseY = (int)(Math.random()*30)*25; //Generates random y coordinate for mouse
   }
   
   ////////////////////////////Accessor Methods///////////////////////////
   public int getMouseX()
   {
      return mouseX;
   }
   
   public int getMouseY()
   {
      return mouseY;
   }
   
   ////////////////////////////Modifier Methods////////////////////////////
   public void drawMouse(Graphics g)
   {
      //Draw Tail
      g.setColor(new Color(255,192,203));
      g.fillRect(mouseX + 25, mouseY + 17, 15, 8);
      g.setColor(Color.white);
      g.fillArc(mouseX + 25, mouseY + 9, 15, 8, 180, 360);
      //Draw Body
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(mouseX, mouseY, 25, 25);
      //Draw Face
      g.setColor(Color.black);
      g.fillRect(mouseX + 5, mouseY + 5, 5, 5);
      g.fillRect(mouseX + 15, mouseY + 5, 5, 5);
      g.fillRect(mouseX + 5, mouseY + 15, 15, 5);
   }
   
   public void moveMouse()
   {
      if(mouseMove) // Each of the if, else if, and else statements each have a 25% chance of running meaning that the direction that the mouse moves in is random
      {  
         if((int)(Math.random()*4) == 3)
            mouseY += 25;
         else if((int)(Math.random()*4) == 2)
            mouseX += 25;
         else if((int)(Math.random()*4) == 1)
            mouseX -= 25;
         else
            mouseY -= 25;
      mouseMove = false; //This makes sure that the mouse doesn't keep moving even while you aren't moving the person
      }     
   }
}