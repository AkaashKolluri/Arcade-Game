import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class ChaseMouse extends Applet implements KeyListener, Runnable
{
   //////////////////////////Instance Variables//////////////////////////
   int movesUsed = 0;
   Mouse mouse;
   Person person;
   Thread thread;
   boolean go = true;
   
   //////////////////////////Modifier Methods////////////////////////////
   //This method initialize the applet and KeyListener as well as instantiating the mouse, person, and thread objects
   public void init()
   {
      setSize(1425,800);
      this.requestFocus();
      addKeyListener(this);
      mouse = new Mouse();
      person = new Person();
      thread = new Thread(this);
      thread.start();
   }
   
   //The paint method draws the mouse and person
   public void paint(Graphics g)
   {     
      g.setColor(Color.white);
      g.fillRect(0,0,10000,10000);
      g.setColor(Color.black);
      mouse.drawMouse(g); 
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
      //Since there are 2 ways to end the game, there must be 2 if statements
      if(!go && mouse.mouseX == person.personX) //This if statement runs if the user catches the mouse
      {
         g.setColor(Color.red);
         g.fillRect(0, 0, 1425, 800);
         g.setFont(new Font("", Font.BOLD, 100));
         g.setColor(Color.white);
         g.drawString("You took a total of " + movesUsed + " moves", 0, 300);
         g.drawString("You earned " + (1000 - movesUsed)/10 + " tickets!", 100, 400);
         
         g.setFont(new Font("", Font.PLAIN, 30));
         g.drawString("Press ESC to leave", 200, 500);
         g.drawString("Press R to replay", 700, 500);
         
      }
      else if(!go) //This if statement runs if the mouse leaves the screen before the user catches it
      {
         g.setColor(Color.red);
         g.fillRect(0, 0, 1425, 800);
         g.setColor(Color.white);
         g.drawString("Uh-Oh! The mouse escaped!", 200, 200);
         g.drawString("You earned " + movesUsed/10 + " tickets!", 100, 400);
         g.setFont(new Font("", Font.PLAIN, 30));
         g.drawString("Press ESC to leave", 200, 500);
         g.drawString("Press R to replay", 700, 500);
      }
   }
   
   ///////////////////////////Runnable Interface Methods//////////////////////////
   public void run()
   {
      //While the game is running, the code will constantly run all of the methods that recquire moving
      while(go)
      {
         mouse.moveMouse();
         person.moveRight();       
         person.moveLeft();
         person.moveUp();
         person.moveDown();
         if(mouse.mouseX == person.personX || (mouse.mouseX < 0 || mouse.mouseX > 1400 || mouse.mouseY < 0 || mouse.mouseY > 725)) //The game goes false, when the x and y coordinates of the mouse are equal to the person
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
      
      //If the user presses the left arrow key, the mouse moves randomly and the person moves left
      if(e.getKeyCode() == e.VK_LEFT && person.personX > 0)
      {
         mouse.mouseMove = true;
         person.movePersonLeft = true;
         movesUsed++;
         repaint();
      }
      
      //If the user presses the up arrow key, the mouse moves randomly and the person moves up
      if(e.getKeyCode() == e.VK_UP && person.personY > 0)
      {
         mouse.mouseMove = true;
         person.movePersonUp = true;
         movesUsed++;
         repaint();
      }
      
      //If the user presses the down arrow key, the mouse moves randomly and the person moves down
      if(e.getKeyCode() == e.VK_DOWN && person.personY < 725)
      {
         mouse.mouseMove = true;
         person.movePersonDown = true;
         movesUsed++;
         repaint();
      }
      
      //If the user presses ESC, the applet returns to the lobby
      if(e.getKeyCode() == e.VK_ESCAPE)
      {  
         if(!go)
            Ticket.addTickets((1000 - movesUsed)/10);
         LobbyReaction l = new LobbyReaction();
         l.init();
         l.setBounds(new Rectangle(0,0,1425,800));   
         add(l,null);
      }
      
      //If the user presses R, the program re-runs
      if(e.getKeyCode() == e.VK_R)
      {
         if(!go)
            Ticket.addTickets((1000 - movesUsed)/10);
         ChaseMouse l = new ChaseMouse();
         l.init();
         l.setBounds(new Rectangle(0,0,190,90));   
         add(l,null);
      }
   }
   public void keyTyped(KeyEvent e){}
   public void keyReleased(KeyEvent e){}
}