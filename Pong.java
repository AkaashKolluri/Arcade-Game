 import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

public class Pong extends Applet implements Runnable, KeyListener
{
   /////////////////////Instance Varibales////////////////
   Thread thread;
   Block block;
   Block2 block2;
   Ball ball;
   boolean start = true;//Allows the program to run  
   int score = 0;
   volatile boolean bExit;
   
   ////////////////////Initializes Class/////////////////
   public void init()
   {
      this.requestFocus();
      addKeyListener(this);
      resize(1425,800);
      block = new Block();
      ball = new Ball();
      block2= new Block2();      
      thread = new Thread(this);//A thread is created so that the ball and the block can move at the same time
      thread.start();

   }
   
   //Draws the Ball and Block or end screen
   public void paint(Graphics g)
   {
      g.setColor(Color.black.darker().darker());
      g.fillRect(0,0,1425,800);
      g.setColor(Color.white);
      g.fillRect(1425/2-5,0,10,800);
      g.setFont(new Font("",Font.PLAIN,20));
      g.setColor(Color.white);
      //If the ball goes past the right side of the screen or the left side, the game is over. Draws endgame screen
      if(ball.xVal()< 0 || ball.xVal()> 1425)
      {
         g.setFont(new Font("", Font.BOLD,100));
         g.setColor(Color.red);
         g.drawString("GAME OVER",400,400);
         g.setFont(new Font("", Font.PLAIN, 50));
         g.setColor(Color.white);
         g.drawString("Hit ESC to go back",500,600);
         int player = 0;
         if(ball.xVal()< 0)//If the ball goes out in the left, player 2 wins
            player = 2;
         if(ball.xVal()>1425)//If the ball goes out in the right, player 1 wins
            player = 1;
         g.drawString("Player " +player+ " Won!",500,650);         
      }
      else //If the game hasn't ended, draw the ball and both blocks
      {
         block.drawBlock(g);
         ball.drawBall(g); 
         block2.drawBlock(g);
      }
   }
   
   //Stops the buffering
   public void update(Graphics g)
   {
      paint(g);
   }
   
   ////////////////////Method of the Runnable Interface//////////////
   public void run()
   {
      while(ball.xVal()>= 0)//As long as the x value of the ball isn't 0, the game runs
      {
         if(getStart() == true)
         {
            //Now we check if the blocks move while moving the ball
            block.moveBlock();
            block2.moveBlock();
            
            ball.moveBall();
            hitScan();
         
            repaint();
            //Creates the animation of the ball moving as well as the blocks moving
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
   }
   
   //////////////////Mathods of the KeyListener Interface/////////////////
   public void keyPressed(KeyEvent e)
   {
      if(e.getKeyCode() == e.VK_W) //If W is pressed, block moves up
      {  
         block.setUp(true);
         setStart(true);
      }
      if(e.getKeyCode() == e.VK_S) //If S is pressed, block moves down
      {
         block.setDown(true);
         setStart(true);
      }
      if(e.getKeyCode() == e.VK_UP) //If Up is pressed, block2 moves up
      {
         block2.setUp(true);
         this.setStart(true);
      }
      if(e.getKeyCode() == e.VK_DOWN) //If Down is pressed, blockw moves down
      {
         block2.setDown(true);
         this.setStart(true);
      }
      if(e.getKeyCode() == e.VK_R) //If R is pressed, the game restarts
      {
         Pong p = new Pong();
         p.setBounds(new Rectangle(0,0,190,90));
         p.init();
         add(p,null);       
      }
      if(e.getKeyCode() == e.VK_ESCAPE) //If ESC is pressed, the lobby reappears
      {
         Lobby2Reaction reset = new Lobby2Reaction();
         reset.setBounds(new Rectangle(0,0,190,190));
         reset.init();
         add(reset,null);
      }  
      if(e.getKeyCode() == e.VK_D) //If D is pressed, block sensitivity increases      
      {  
         if (block.getSensitive()<1 && block.getSensitive()>0)
         block.setSensitive(block.getSensitive()+.1);
      }
      if(e.getKeyCode() == e.VK_A) //If A is pressed, block sensitivity decreases
      {
         if (block.getSensitive()<1 && block.getSensitive()>.11)
         block.setSensitive(block.getSensitive()-.1);
      }
      if(e.getKeyCode() == e.VK_RIGHT) //If Right is pressed, block2 sensitivity increases
      {  
         if (block2.getSensitive()<1 && block2.getSensitive()>0)
         block2.setSensitive(block2.getSensitive()+.1);
      }
      if(e.getKeyCode() == e.VK_LEFT) //If Left is pressed, block2 sensitivity decreases
      {
         if (block2.getSensitive()<1 && block2.getSensitive()>.11)
         block2.setSensitive(block2.getSensitive()-.1);
      }      
   }
   
   //When a key is released, the block stops moving
   public void keyReleased(KeyEvent e)
   {
      if(e.getKeyCode() == e.VK_W)
      {
         block.setUp(false);
         this.setStart(true);
      }
      if(e.getKeyCode() == e.VK_S)
      {
         block.setDown(false);
         this.setStart(true);
      }
      if(e.getKeyCode() == e.VK_UP)
      {
         block2.setUp(false);
         this.setStart(true);
      }
      if(e.getKeyCode() == e.VK_DOWN)
      {
         block2.setDown(false);
         this.setStart(true);
      }
     
   }
   public void keyTyped(KeyEvent e){}

   //Checks if the ball hits the block and if so changes the direction of the ball's velocity
   public void hitScan()
   {
      boolean output = false;
      if(ball.ballX<=70)//If ball hits block1, ballX move negates
         if(ball.ballY>=block.yVal()-15 && ball.ballY<=block.yVal()+100-15)
         {
            ball.ballMoveX = -ball.ballMoveX;
         }
      if(ball.ballX>=1355)//If ball hits block2, ballX move negates
         if(ball.ballY>=block2.yVal()-15 && ball.ballY<=block2.yVal()+100-15)
         {
            ball.ballMoveX = -ball.ballMoveX;
         }
      if(ball.ballY<=0||ball.ballY>=800) // If ball hits the top and bottom of the applet, the y velocity negates
      {
         ball.ballMoveY = -ball.ballMoveY;
      }
   }
      
   //////////////////////////Accessor Methods/////////////////////
   public boolean getStart()
   {
      return start;
   }
   
   /////////////////////////Modifier Methods//////////////////////
   public void setStart(boolean q)
   {
      start = q;
   }
}