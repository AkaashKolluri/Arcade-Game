import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.TimerTask;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.KeyListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


//This file contains all of our sound files, other files call this
//when the wish to play a sound
//Note when each method is called all files are called lasersound
//and all clips are called laser
//This is because these variables do not matter outside the method
//And thus it was easier to leave them and just copy paste
public class PlaySound
{
   //Munch sound, when snake eats apple
   public static void munch() 
   {
      try
      {
         File lasersound = new File("Munch.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   } 
   
   //Laser sound for space inavders
   public static void laser() 
   {
      try
      {
         File lasersound = new File("Laser.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   }
   
   //Bird wing flap for flappy bird
   public static void birdFlap() 
   {
      try
      {
         File lasersound = new File("BirdFlap.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   }
   
   //Points sound for flappy bird/other files when you get a point
   public static void point()
   {
      
      try
      {
         File lasersound = new File("BirdPoint.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   }
   
   //BirDie is the sounds that plays when the flappybird dies
   public static void birdDie()
   {
      
      try
      {
         File lasersound = new File("BirdDie.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   }

   
   //Plays a ball bouncing sound for pong, and raque

   public static void pong()
   {
      
      try
      {
         File lasersound = new File("Pong.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   }
   
   public static void lobbyMusic()
   {
      
      try
      {
         File lasersound = new File("lobbyMusic.wav");
         AudioInputStream stream = AudioSystem.getAudioInputStream(lasersound);
         Clip laser = AudioSystem.getClip();
         laser.open(stream);
         laser.start();
      } 
      catch(Exception ex) 
      {
         System.out.println("Error with playing sound.");
         ex.printStackTrace();
      }
   }
}