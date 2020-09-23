import java.awt.*;
import java.applet.*;
import java.util.Random;

public class CookieClicker extends Applet
{
   ////////////////////////Instance Variables///////////////////
   //tickets is used here, and counts for number of tickets
   int tickets = 0;
   //grannys can be bought and they help you with cookie production
   int grannys = 0;
   
   //initilizer, which sets the size of our screen
   public void init()
   {
      setSize(1425,800);
   }
   
   //Update method, which displays how many grannys you have and how many tickets youve made
   public void update(Graphics g)
   {
      paint(g);
      g.drawString("Tickets: " + tickets, 300, 500);
      g.drawString("Grannys: " + grannys,300,550); 
   }
 
   public void paint(Graphics g)
   {
      //base
      g.setColor(Color.white);
      g.fillRect(0,0,1425,800);
      
      //random color background
      Random r = new Random();
      Random gr = new Random();
      Random b = new Random();
      int red = r.nextInt(255);
      int green = gr.nextInt(255);
      int blue = b.nextInt(255);
      int trans = b.nextInt(255);
      Color col = new Color(red, green, blue);
      g.setColor(col);
      g.fillRect(0, 0, 1425, 800);
      //draw cookie 
      drawCookie(g);
      //draw boxes for buttons
      g.setColor(Color.black);
      g.fillRect(1100, 100, 140, 50);
      g.fillRect(1100,160,140,50);
      g.fillRect(10,10,180,80);
      //draw game name
      g.setFont(new Font("", Font.BOLD, 100));
      g.drawString("Cookie Clicker", 300, 150);
      g.setFont(new Font("", Font.BOLD, 20));     
      g.setColor(Color.white);
      //draw box names
      g.drawString("Transcend", 1110, 130);
      g.drawString("Buy Granny", 1110,190);
      g.drawString("Exit", 50,50);
   }
   
   //Mouse listener which checks where you click
   public boolean mouseDown(Event e, int x, int y)
   {
      //This is where cookie s
      //Is pressed it give you tickets corresponding to number of grannyys
      if((450 < x && x < 950) && (250 < y && y < 750))
      {
         tickets = tickets + grannys+1;
         Ticket.addTickets(grannys+1);
         repaint();
      }
      
      //This is the reset button
      //It resest your tickets if you at a certain amount
      //But square your grannys
      if((1100 < x && x < 1240) && (100 < y && y < 150))
      {
         if (tickets>(grannys+1)*200)
         {
            tickets = 0;
            Ticket.addTickets(-tickets);
            grannys = (int)Math.pow(grannys,2);
         }
         
      }
      
      //The granny buy button
      //When bought, it reduces tickets by 100 and
      //increase by 1
      
      if((1100<x && x<1240) && (y>160 && y<210))
      {
         //protection for negative
         if (tickets>=100)
         {
            grannys++;
            tickets = tickets-100;
            Ticket.addTickets(-100);
         }  
      }
      
      //Finnaly we have an escape button, which if pressed sends you back to lobby.
      if(x<200 && y<100)
      {
         Lobby3Reaction l = new Lobby3Reaction();
         l.init();
         l.setBounds(new Rectangle(0,0,190,90));   
         add(l,null);
      
      }
      
      return true;
   }
   
   //Graphics for our cookie
   public void drawCookie(Graphics g)
   {
      Color tan = new Color(222,184,135);
      Color brown = new Color(51,25,0);
      g.setColor(tan);
      g.fillOval(450,250,500,500);
      g.setColor(brown);
      g.fillOval(550,650,50,50);
      g.fillOval(475,450,50,50);
      g.fillOval(750,650,50,50);
      g.fillOval(650,300,50,50);
      g.fillOval(800,500,50,50);
      g.fillOval(600,500,50,50);
      g.fillOval(800,350,50,50);
   }
}