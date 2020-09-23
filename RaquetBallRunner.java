import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;
public class RaquetBallRunner extends Applet implements KeyListener, Runnable
{
   //Instance Vairbales
   //We define raquet, to be a object of our raquet class
   Raquet raquet;
   //and ball as an obvject of ball class
   RBall ball;
   //thread must be defined as part of runnable
   Thread thread;
   //and score counts what point you get during a game
   int score = 0;
   //Setting variabe start, which indicates when to move ball
   boolean start = false;
   
   //initizlizer
   public void init()
   {
      //Implementing our key listner
      this.requestFocus();
      addKeyListener(this);
      //Setting the size of our applet
  	   setSize(1425,800);
      //Defining our created objects
      raquet = new Raquet();
      ball = new RBall();
      thread = new Thread(this);
      
      //Start our thread, which in turn runs our run() method
      thread.start();
   }
   
   //paint methods
   public void paint(Graphics g)
   {
      //We draw background
     	g.setColor(Color.black);
     	g.fillRect(15,15,1475-15,800-15);
      g.setColor(Color.white);
      g.setFont(new Font("", Font.PLAIN,25));
      //With the score the user has, and basic instructions
      g.drawString("Score: "+score/100,500,100);
      g.drawString("Use A & D to play",450,125);
      //We set a detector, which see when the ball goes out of bounds
      //If it does, it displays and end game screen
      if(ball.ballY()>719)
      {
         g.setFont(new Font("", Font.BOLD,100));
         g.setColor(Color.red);
         g.drawString("GAME OVER",400,400);
         g.setFont(new Font("", Font.PLAIN, 50));
         g.setColor(Color.white);
         g.drawString("Hit ESC to go back",500,600);
         g.drawString("Tickets Earned: " +score/10,500,650);
         Ticket.addTickets(score/10);    
      }
      //Otherwise, the game goes on, and we redraw our things, and increase the score
      else
      {
     	   raquet.drawRaquet(g);
     	   ball.drawBall(g);
         score++;
      }
   }
   
   //We use update method, which creates a buffer that prevents some lag
   public void update(Graphics g)
   {
      paint(g);
   }
   
   //Run Method
   //This method is what the game moves
   public void run()
   {
      //We use a while loop which makes our game go until this while loop goes false
      //This while loop detects when our ball goes out of bounds
     	while(ball.ballY() <= 720)
     	{  
         //We move our raquet and ball
        	raquet.moveRaquet();
         if(start)
        	ball.moveBall();
         //We check for raquest ball collison and if there is, the ball bounces another direction
         bounce();
        	//We repaint with updates to our objects
         repaint();
         //Finnaly, we wait some time using a Thread.sleep, which makes our animation
        	try
        	{
           	Thread.sleep(10);
        	}
        	catch(InterruptedException e)
        	{
           	System.out.println("");
        	}
     	}
   }
   
   //Key Listener
   public void keyPressed(KeyEvent e)
   {
      //this detects the keys that are pressed if the a key is pressed, the raquet is moved to the left
     	if(e.getKeyCode() == e.VK_A)
        	raquet.setUp(true);
         start = true;
      //if the d key is pressed, it will move the raquet to the right
     	if(e.getKeyCode() == e.VK_D)
        	raquet.setDown(true);
         start = true;
      //if the r key is pressed, it will reset the game
     	if(e.getKeyCode() == e.VK_R)
  	   {
        	RaquetBallRunner r = new RaquetBallRunner();
        	r.setBounds(new Rectangle(0,0,190,90));
        	r.init();
        	add(r,null);
  	   }
      //if the escape key is pressed it will return the user back to the lobby
     	if(e.getKeyCode() == e.VK_ESCAPE)
     	{
        	Lobby2Reaction reset = new Lobby2Reaction();
        	reset.setBounds(new Rectangle(0,0,190,190));
        	reset.init();
        	add(reset,null);
     	} 
   }
   
   public void keyReleased(KeyEvent e)
   {
      //this stops the raquet from continuing to move once the a and d keys are released
     	if(e.getKeyCode() == e.VK_A)
        	raquet.setUp(false);
     	if(e.getKeyCode() == e.VK_D)
        	raquet.setDown(false);
   }
   
   public void keyTyped(KeyEvent e){}
   
   //Methods
   //Bounce method check for collision between the ball and the raquest or all the ball and a wall
   //If it hits either of these, we simply reverse the balls volicty, which makes it "bounce"
   //It also plays a bounce sound
   public void bounce()
   {
     	if(ball.ballY() >= 690)// Ball hits block 
        	if(ball.ballX()>=raquet.raquetX() && ball.ballX()<=raquet.raquetX()+200-12)
         {
            PlaySound.pong();
           	ball.moveY = -ball.moveY;  
         }

      if(ball.ballY()<=0)//Ball hits ceiling
      {
         PlaySound.pong();
         ball.moveY = -ball.moveY;
      }
      
      if(ball.ballX() <= 0 || ball.ballX() >=1425)//Ball hits side of wall 
      {
         PlaySound.pong();
         ball.moveX = -ball.moveX;   
      }
   }
}

