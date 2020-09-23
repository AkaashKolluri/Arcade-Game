import java.awt.*;
import java.applet.*;

public class InsaneMouse
{  
   //We declare our vairable for mouse
   //Cordinates
   int mouseX;
   int mouseY;
   //Booleans that detect if the mouse should be there, and if the mouse should move
   boolean mouseMove = false;
   boolean erase = false;
   
   //Defining cordinate of mouse
   public InsaneMouse()
   {
      mouseX = (int)(Math.random()*57)*25;
      mouseY = (int)(Math.random()*30)*25;
   }
   
   //Drawing insane mouse
   public void drawInsaneMouse(Graphics g)
   {
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(mouseX, mouseY, 25, 25);
      g.setColor(Color.black);
      g.fillRect(mouseX + 5, mouseY + 5, 5, 5);
      g.fillRect(mouseX + 15, mouseY + 5, 5, 5);
   }
   
   //Moving Insane Mouse
   public void moveInsaneMouse()
   {
      //when the mouse is supposed to move it does this
      if(mouseMove)
      {  
         //Random spot set to nearest cordinate     
         int x = ((int)(Math.random()*7)-3);
         int y = ((int)(Math.random()*7)-3);
         //If goes off screeb sebds ut to other side
         mouseX += x*25;
         mouseX  = (mouseX+1400)%1400;
         mouseY += y*25;
         mouseY  = (mouseY+725)%725;
      }
      //and then set mouse back to false
      mouseMove = false; 
   }      
}