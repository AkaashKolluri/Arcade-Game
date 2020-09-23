import java.awt.*;
import java.applet.*;
public class InsanePerson
{
   //global variables
   //Location
   int personX;
   int personY;
   //movement
   boolean movePersonRight = false;
   boolean movePersonLeft = false;
   boolean movePersonUp = false;
   boolean movePersonDown = false;
   boolean erase = false;
   
   //Instanicates person
   public InsanePerson()
   {
      personX = (int)(Math.random()*57)*25;
      personY = (int)(Math.random()*30)*25;
   }
  
  //Graphics method which calls the person 
   public void drawPerson(Graphics g)
   {
      g.setColor(Color.black);
      g.fillRect(personX, personY, 8, 8);//Draws Head
      g.drawLine(personX+4, personY+8, personX+4, personY+18);//Draws body
      g.drawLine(personX, personY+25, personX+4, personY+18);
      g.drawLine(personX+4, personY+18, personX+8, personY + 25);
      g.drawLine(personX, personY + 12, personX + 8, personY + 12);
   }
   
   //erases the old person when called
   public void erasePerson(Graphics g)
   {
      if(erase)
      {
         g.setColor(Color.white);
         g.fillRect(personX, personY, 25, 25);
      }
      erase = false;
   }
   
   //Move methods, which are defined when the person moves
   //Coresponding to the direction, it moves it that direction
   public void moveRight()
   {
      if(movePersonRight)
         personX += 25;
      movePersonRight = false;
   }
   
   public void moveLeft()
   {
      if(movePersonLeft)
         personX -= 25;
      movePersonLeft = false;
   }
   public void moveUp()
   {
      if(movePersonUp)
         personY -= 25;
      movePersonUp = false;
   }
   
   public void moveDown()
   {
      if(movePersonDown)
         personY += 25;
      movePersonDown = false;
   }  
}