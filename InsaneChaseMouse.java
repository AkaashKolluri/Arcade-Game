import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class InsaneChaseMouse extends Applet implements KeyListener, Runnable
{
   //////////////////////////Instance Variables//////////////////////////
   int movesUsed = 0;
   InsaneMouse mouse;
   InsanePerson person;;
   Thread thread;
   boolean go = true;
   
   //////////////////////////Modifier Methods////////////////////////////
   //This method initialize the applet and KeyListener as well as instantiating the mouse, person, and thread objects
   public void init()
   {
      setSize(1425,800);
      this.requestFocus();
      addKeyListener(this);
      mouse = new InsaneMouse();
      person = new InsanePerson();
      thread = new Thread(this);
      thread.start();
   }
   
   //The paint method draws the mouse and person
   public void paint(Graphics g)
   {
      g.setColor(Color.white);
      g.fillRect(0,0,10000,10000);
      g.setColor(Color.black);
      mouse.drawInsaneMouse(g); 
      endGame(g); 
      person.drawPerson(g);
      endGame(g);         
   }
   
   //The update method is used to stop the flickering of the person and the mouse
   public void update(Graphics g)
   {
      paint(g);
   }
   
   //This method runs when the game ends or go is equal to false
   public void endGame(Graphics g)
   {
      if(!go)  //This if statement runs if the user catches the mouse
      {
         g.setColor(Color.red);
         g.fillRect(0, 0, 1425, 800);
         g.setFont(new Font("", Font.BOLD, 100));
         g.setColor(Color.white);
         g.drawString("You took a total of " + movesUsed + " moves", 0, 300);
         g.drawString("You earned " + (1000 - movesUsed)/10 + " tickets!", 100, 400);
         g.setFont(new Font("", Font.PLAIN, 30));
         g.drawString("Press ESC to leave", 200, 500);
      }
   }
   
   ///////////////////////////Runnable Interface Methods//////////////////////////
   public void run()
   {
      //While the game is running, the code will constantly run all of the methods that recquire moving
      while(go)
      {
         mouse.moveInsaneMouse();
         person.moveRight();       
         person.moveLeft();
         person.moveUp();
         person.moveDown();
         if((mouse.mouseX == person.personX || Math.abs(mouse.mouseX - person.personX) == 1)
             && (mouse.mouseY == person.personY || Math.abs(mouse.mouseX - person.personX) == 1))
            go = false;   
         repaint();
         
         //The try catch is used to make it so that the person and mouse don't travel instantly
         try
         {
            Thread.sleep(100);
         }
         catch(InterruptedException e)
         {
            System.out.println("REEEEEEEEEEEE");
         }
      }
   }
   
   ///////////////////////////////Key Listener Methods/////////////////////////////////
   public void keyPressed(KeyEvent e)
   {
      //If the user presses the right arrow key, the mouse moves randomly and the person moves right
      if(e.getKeyCode() == e.VK_RIGHT && person.personX < 1400)
      {
         mouse.mouseMove = true;
         person.movePersonRight = true;
         movesUsed++;
         repaint();
      }
      
      //If the left arrow key is pressed, the person moves left and the mouse moves randomly again
      if(e.getKeyCode() == e.VK_LEFT && person.personX > 0)
      {
         mouse.mouseMove = true;
         person.movePersonLeft = true;
         movesUsed++;
         repaint();
      }
      
      //Same as above but the person moves up
      if(e.getKeyCode() == e.VK_UP && person.personY > 0)
      {
         mouse.mouseMove = true;
         person.movePersonUp = true;
         movesUsed++;
         repaint();
      }
      
      //same as above but the person moves down
      if(e.getKeyCode() == e.VK_DOWN && person.personY < 725)
      {
         mouse.mouseMove = true;
         person.movePersonDown = true;
         movesUsed++;
         repaint();
      }
      
      //if the escape key is pressed, the user is returned to lobby2 from which he came
      if(e.getKeyCode() == e.VK_ESCAPE)
      {
         //add tickets if game is over and ESC is preseed
         if(!go)
            Ticket.addTickets((1000 - movesUsed)/10);
         Lobby2Reaction l = new Lobby2Reaction();
         l.init();
         l.setBounds(new Rectangle(0,0,190,90));   
         add(l,null);
      }
      
      //If R is pressed then it resets the games
      if(e.getKeyCode() == e.VK_R)
      {  
         //add tickets if game is over and R is pressed
         if(!go)
            Ticket.addTickets((1000 - movesUsed)/10);
         InsaneChaseMouse s = new InsaneChaseMouse();
         s.setBounds(new Rectangle(0,0,190,90));
         s.init();
         add(s,null);
      }
   }
   
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e){}
}