import java.awt.*;
import java.applet.*;
public class Person
{
   ////////////////////////////Instance Variables//////////////////////////////
   int personX;
   int personY;
   boolean movePersonRight = false;
   boolean movePersonLeft = false;
   boolean movePersonUp = false;
   boolean movePersonDown = false;
   boolean erase = false;
   
   /////////////////////////////Constructors///////////////////////////////////
   public Person()
   {
      personX = (int)(Math.random()*57)*25;//Creates a random x value for person
      personY = (int)(Math.random()*30)*25;//Creates a random y value for person
   }
   
   /////////////////////////////Accessor Methods///////////////////////////////
   public int getPersonX()
   {
      return personX;
   }
   
   public int getPersonY()
   {
      return personY;
   }
   
   /////////////////////////////Modifier Methods///////////////////////////////
   public void drawPerson(Graphics g)
   {
      g.setColor(Color.black);
      g.fillRect(personX, personY, 8, 8);//Draws Head
      g.drawLine(personX+4, personY+8, personX+4, personY+18);//Draws body
      g.drawLine(personX, personY+25, personX+4, personY+18);
      g.drawLine(personX+4, personY+18, personX+8, personY + 25);
      g.drawLine(personX, personY + 12, personX + 8, personY + 12);
   }
   
   //If movePersonRight is true, the person moves right
   public void moveRight()
   {
      if(movePersonRight)
         personX += 25;
      movePersonRight = false;
   }
   
   //If movePersonLeft is true, the person moves left
   public void moveLeft()
   {
      if(movePersonLeft)
         personX -= 25;
      movePersonLeft = false;
   }
   
   //If movePersonUp is true, the person moves up
   public void moveUp()
   {
      if(movePersonUp)
         personY -= 25;
      movePersonUp = false;
   }
   
   //If movePersonDown is true, the person moves down
   public void moveDown()
   {
      if(movePersonDown)
         personY += 25;
      movePersonDown = false;
   }
}