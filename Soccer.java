import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

public class Soccer extends Applet implements Runnable, KeyListener
{
   //Declaring our global variables
   //We declare a thread as it is needed for runnabe
   Thread thread;
   //we also declare our block and soccer ball objects
   Block b1;
   SoccerBall b2;
   boolean start = false;
   int score = 0;
   volatile boolean bExit;
   
   //Initializes the applet
   public void init()
   {
      //Initializes the global variables
      this.requestFocus();
      addKeyListener(this);
      resize(1425,800);
      b1 = new Block();
      b2 = new SoccerBall();      
      thread = new Thread(this);
      thread.start();
   }
   
   //Paint method
   public void paint(Graphics g)
   {
      g.setColor(Color.green.darker());
      g.fillRect(0,0,1425,800);
      g.setColor(Color.black);
      g.setFont(new Font("",Font.PLAIN,20));
      g.drawString("Score: " +score,650,100);
      g.setColor(Color.black);
      if(b2.xVal()< 0)//If the ball goes past the goalie into the goal, the game ends
      {
         g.setFont(new Font("", Font.BOLD,100));
         g.setColor(Color.red);
         g.drawString("GAME OVER",400,400);
         g.setFont(new Font("", Font.PLAIN, 50));
         g.setColor(Color.white);
         g.drawString("Hit ESC to go back",500,600);
         g.drawString("Tickets Earned: " +score*10,500,650);
         Ticket.addTickets(score*10);         
      }
      else//Otherwise it draws the ball and block
      {
         b1.drawBlock(g);
         b2.drawSoccerBall(g); 
      }
   }
   
   //Update stops applet from flickering
   public void update(Graphics g)
   {
      paint(g);
   }
   
   //Method in the runnable Interface
   public void run()
   {
      while(b2.xVal()>= 0)//While the ball doesn't do past the goalie
      {
        if (start)//It constantly checks for the keyListener of the block and moves the soccer ball
        {
            b1.moveBlock();
            b2.moveSoccerBall();
            hitScan();
        }         
        repaint();
            
        try //Creates the animation of the ball moving
        {
           Thread.sleep(10);
        }
        catch(InterruptedException e)
        {
              System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
             
        }
         
      }
     
   }
   
   //Key Listener Interface
   public void keyPressed(KeyEvent e)
   {
      if(e.getKeyCode() == e.VK_UP)//If user presses up, the goalie goes up
      {  
         b1.setUp(true);
         start =true;
      }
      if(e.getKeyCode() == e.VK_DOWN)//If user presses down, the goalie goes down
      {
         b1.setDown(true);
         start = true;
      }
      if(e.getKeyCode() == e.VK_R)//If user presses R, the game restarts
      {
         Soccer p = new Soccer();
         p.setBounds(new Rectangle(0,0,190,90));
         p.init();
         add(p,null); 
         
      }
      if(e.getKeyCode() == e.VK_ESCAPE)//If user presses ESC, the lobby reappears
      {
         LobbyReaction reset = new LobbyReaction();
         reset.setBounds(new Rectangle(0,0,190,190));
         reset.init();
         add(reset,null);
      }  
      if(e.getKeyCode() == e.VK_RIGHT)//If user presses up, the goalie increases sensitivity
      {  
         if (b1.getSensitive()<1 && b1.getSensitive()>0)
            b1.setSensitive(b1.getSensitive()+.1);
      }
      if(e.getKeyCode() == e.VK_LEFT)//If user presses left, the goalie decreases sensitivity 
      {
         if (b1.getSensitive()<1 && b1.getSensitive()>.11)
            b1.setSensitive(b1.getSensitive()-.1);
      }     
   }
   
   //Same thing as key pressed, but once a key is released, the goalie stops moving
   public void keyReleased(KeyEvent e)
   {
      if(e.getKeyCode() == e.VK_UP)
      {
         b1.setUp(false);
         start = true;
      }
      if(e.getKeyCode() == e.VK_DOWN)
      {
         b1.setDown(false);
         start = true;
         start = true;
      }   
   }
   
   public void keyTyped(KeyEvent e){}
   
   //Hit scan checks if the ball hits the goalie, if it does then it resets the ball
   public void hitScan()
   {
      boolean output = false;
      if(b2.ballX<=70)
         if(b2.ballY>=b1.yVal()-15 && b2.ballY<=b1.yVal()+100-15)
         {
            b2.setX(1375);
            b2.setY((Math.random()*500)+50);
            score++;
            b2.ballMove -=1;
            PlaySound.point();
         }
   }
   
   //////////////////Get Methods///////////////////
   public boolean getStart()
   {
      return start;
   }
}