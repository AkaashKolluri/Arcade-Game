import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;

public class FlappyBirdRunner extends Applet implements KeyListener, Runnable
{  
   //Becuase this is runnable, we have to define a thread to make it run
   Thread myThread;                                     
   //boolean start make it so nothing happens until space is pressed, when space is pressed it is change to false
   boolean start = false;
   //Defining our pipes(More info in FlappyBirdPipe class)
   FlappyBirdPipe pipe1 = new FlappyBirdPipe(0);     
   FlappyBirdPipe pipe2 = new FlappyBirdPipe(2);    
   FlappyBirdPipe pipe3 = new FlappyBirdPipe(4);   
   //Defining our bird(More info in FlappyBirdBird class)
   FlappyBirdBird bird = new FlappyBirdBird(); 
   //Flaps is used to help with the bird flying(more info when used)   
   int flaps = 0;
   //And score is used to detect whenever the bird makes it throuhg a pipe and to add a point to the score
   int score = 0;
      
   //Init is the initilzer methods, these initiate our file   
   public void init()
   {
       //Defining Key Listner, and thread for runnable
       this.requestFocus();                                  
       this.addKeyListener(this);
       //resizing to make the right size screne pop up right away
       this.resize(1425,800);  
       //Using our thread, we intiatiate our runnable                            
       myThread = new Thread(this);                        
       myThread.start();             
                      
   }
   
   public void paint(Graphics g)
   {  
      //Drawing Background
      drawBackground(g);      
      g.setFont(new Font("",Font.PLAIN,36));
      
      //Drawing Pipes
      pipe1.drawPipe(g);
      pipe2.drawPipe(g);
      pipe3.drawPipe(g);
      //If collision is not true, then no collision has happend and the game goes on
      if(!collision())
      {
         //drawing flappy bird
         bird.drawBird(g);
         g.drawString("Score: "+(pipe1.getScore()+pipe2.getScore()+pipe3.getScore()),25,25);
      }
      //Otherwise, if a collison did happen it shows end game screen
      else
      {
         //draws game over screen
         g.setColor(Color.red);
         g.setFont(new Font("Courier",Font.PLAIN,100));
         g.drawString("GAME OVER",350,350);
         g.setColor(Color.black);
         g.setFont(new Font("", Font.PLAIN,42));
         g.drawString("Tickets Earned: "+ (pipe1.getScore()+pipe2.getScore()+pipe3.getScore())*10,470,400);
         Ticket.addTickets((pipe1.getScore()+pipe2.getScore()+pipe3.getScore())*10);
      }
   }
   
   //Our run method which keeps the game moving
   public void run()  
   {
      //The game will only run while the bird hasnt died
      //The bird dies when a collison happens
      //So we have it run until collision goes true
      //With a while loop under the condiiton !collision
      while(!collision())
      {
         //we move our pipes across the screen
         pipe1.movePipe();
         pipe2.movePipe();
         pipe3.movePipe();
         //We check flaps values
         //If it is 0 the bird falls
         if (flaps == 0)
            bird.fall(); 
         //Otherwise, the bird moves upward
         else
         {
            bird.setBirdY(bird.getBirdY()-flaps);
            flaps--;
         }  
         //given the updates we repaint
         repaint();
         //Then wait 10 milisecond before going again to create an animation
          try                                          
          {                                             
              Thread.sleep(10);                          
          }                                            
          catch(InterruptedException e)                 
          {                                             
              e.printStackTrace();                       
          }
       }                                            
    }
    
    
    //The following lines are our keyListners, to make the game interactive
   public void keyTyped( KeyEvent e ) 
   {
        //Nothing happens here, as we do not need keyTyped, but this is part of keyListner
   }
      
   public void keyReleased( KeyEvent e ) 
   {
        //Nothing happens here, as we do not need keyReleased, but this is part of keyListner
   }
      
   //If space is pressed, it sets all start values true, to make the game run
   //In addition it plays the flap sound
   //And it sets flaps to 16, which when red in the run method, makes the bird go upward instead of down    
   public void keyPressed( KeyEvent e )                 
   {                                                     
      if(e.getKeyCode() == e.VK_SPACE)                  
      {                                                   
         pipe1.setStart(true);
         pipe2.setStart(true);
         pipe3.setStart(true);  
         bird.setStart(true);          
         flaps = 16;   
         PlaySound.birdFlap();             
      }  
      
      //If R is pressed in resets the Flappy Bird game
      if(e.getKeyCode() == e.VK_R )                  
      {                                                  
         FlappyBirdRunner newGame = new FlappyBirdRunner();              
         newGame.setBounds(new Rectangle(0,0,190,90));         
         newGame.init();
         add(newGame,null);                    
      } 
      
      //If escape is pressed, it sends you back to Lobby3 
      if(e.getKeyCode() == e.VK_ESCAPE )                  
      {                                                  
         Lobby3Reaction goBack = new Lobby3Reaction();    
         goBack.init();                                
         goBack.setBounds(new Rectangle(0,0,190,90));   
         add(goBack,null);                             
      }  
   }   
   
   //Method which defines a collison between bird and pipe,
   //It checks the birds cordinates with all the pipes in order to define a collison
   public boolean collision()
   {
      //We have a massive if loop that check all the locations of the bird vs all 3 pipes
      if(  pipe1.getPipeX() > 50 && pipe1.getPipeX()<70 && (bird.getBirdY()<pipe1.getPipeY()-180||bird.getBirdY()>pipe1.getPipeY()-20)
      || pipe2.getPipeX() > 50 && pipe2.getPipeX()<70 && (bird.getBirdY()<pipe2.getPipeY()-180||bird.getBirdY()>pipe2.getPipeY()-20)
      || pipe3.getPipeX() > 50 && pipe3.getPipeX()<70 && (bird.getBirdY()<pipe3.getPipeY()-180||bird.getBirdY()>pipe3.getPipeY()-20))
      {
         //If a collison happens we play the death sound, and return true for collison, which ends the game
         PlaySound.birdDie();
         return true;
      }
      return false;
   }
   
   //Finnaly we have our graphics method which draws everything for us
   public static void drawBackground(Graphics g)
   {
      Color sky = new Color(153,255,255);
      g.setColor(sky);
      g.fillRect(0,0,1425,800);
      g.setColor(Color.white);
      g.fillOval(250,150,200,100);
      g.fillOval(200,200,200,100);
      g.fillOval(300,200,200,100);
      
      g.setColor(Color.yellow);
      g.fillOval(1000,150,100,100);
      
      g.setColor(Color.green.darker().darker().darker());
      g.fillRect(0,700,1425,100);
   }
}