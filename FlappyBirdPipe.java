import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

//This class create object Pipes, which we can move and draw
public class FlappyBirdPipe
{
   //Defining global variables
   private int pipeX;
   private int pipeY;
   boolean start = false;
   private int score =0;
 
   //Based on int parmeter, it constructs a pipe at a specfic location. Param is used as we need three differnt pipes
   public FlappyBirdPipe(int x)
   {
      pipeX = 400+(x*250);
      pipeY = (int)(Math.random()*500)+260;
   }
   
   //Given pipe cordinates(relative) it draws in the pipe
   //pipeY is the cordinate of the bottom of the gap in between pipes
   //PipeX is constantly changing to move the pipe
   //Using this, we can draw a top pipe, and a bottom pipe
   public void drawPipe(Graphics g)
   {
      //Drawing pipe shadows
      g.setColor(Color.green.darker().darker());
      g.fillRect(pipeX,pipeY,40,1000);
      g.fillRect(pipeX-5,pipeY-10,40,10);//
      g.fillRect(pipeX,0, 40, pipeY-180);
      g.fillRect(pipeX-5,pipeY-180-10,40,10);//
      
      //Drawing pipe
      g.setColor(Color.green.darker());
      g.fillRect(pipeX+10,pipeY,30,1000);
      g.fillRect(pipeX+5,pipeY-10,40,10);//
      g.fillRect(pipeX+10,0,30, pipeY-180);
      g.fillRect(pipeX+5,pipeY-180-10,40,10);//
   
   }
   
   //Move pipe method is constantly moving the pipe location
   //Additionnaly, when it gets to the left of the screen, it is reset to a num whole location
   //and to the right side
   //When a pipe is reset we adad a score as that would mean our bird made it through
   //And we play a sound when this happens
   //Finnaly, we use get boolean start to make sure that the pipes dont start moving
   //Until the user is ready
   public void movePipe()
   {
      if(getStart())
      {
         if (pipeX>0)
         {
            pipeX = pipeX - 6;
         }
         else
         {
            pipeX = pipeX+1400;
            pipeY = (int)(Math.random()*600)+100;
            score++;
            PlaySound.point();
         }
      }
   }
   
   //Modifer/Accesor methods, they do exactly what they are named as
   public void setPipeX(int a)
   {
      pipeX = a;
   }
   public void setPipeY(int a)
   {
      pipeY = a;
   }
   public int getPipeX()
   {
      return pipeX;
   }
   public int getPipeY()
   {
      return pipeY;
   }
   public int getScore()
   {
      return score;
   }
   public void setStart(boolean a)
   {
      start = a;
   }
   
   public boolean getStart()
   {
      return start;
   }   
}
 
   