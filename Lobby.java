import java.applet.*;
import java.awt.*;
import java.io.InputStream;

public class Lobby
{
   public static void drawBackground(Graphics g)
   {
      //background image
      //floor
      Color floor = new Color(51,0,102);
      g.setColor(floor);
      g.fillRect(0,0,2000,2000);
            
      //floor pattern
      Color floorPat = new Color(102,102,255);
      for(int floopv = 0; floopv<=2000; floopv+=100)
      {
         g.setColor(floorPat);
         g.drawLine(floopv,500,floopv-600,800);
         g.drawLine(0,500+floopv/2,1425,500+floopv/2);
         for(int floopc = 0; floopc<=2000; floopc+=100)
            g.fillOval(floopc-10,500+floopv/2-5,20,10);
      }
      
      //wall
      Color beige = new Color(220,233,202);
      g.setColor(beige);
      g.fillRect(0,0,2000,500);
      
      //light frame
      Color grey = new Color(200,200,200);
      g.setColor(grey);
      Polygon light = new Polygon();
      light.addPoint(550,0);
      light.addPoint(650,0);
      light.addPoint(700,50);
      light.addPoint(500,50);
      g.fillPolygon(light);
      
      //light bulb
      int r = (int)(Math.random()*255);
      int gr = (int)(Math.random()*255);
      int b = (int)(Math.random()*255);
      Color random = new Color(r,gr,b);
      g.setColor(random);
      g.fillArc(562,25,75,50,180,180);
      
      //clock
      g.setColor(Color.DARK_GRAY);
      g.fillRect(805,20,160,60);
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(800,25,160,60);
      g.setColor(Color.black);
      g.fillRect(805,30,150,50);
      g.setColor(Color.red);
      g.setFont(new Font("Courier",Font.PLAIN,43));
      g.drawString("11:39",815,70);
            
      //arcade games
      Color top = new Color(0,0,0);
      int x = 0;
      for(int loop = 0; loop<3; loop++)
      {
         //arcade top
         g.setColor(Color.LIGHT_GRAY);   
         int[] polyX = {200+x,400+x,375+x,175+x}; 
         int[] polyY = {200,200,225,225};
         g.fillPolygon(polyX,polyY,4);
         
         //arcade side
         g.setColor(Color.DARK_GRAY);
         int[] poly2x = {375+x,400+x,400+x,350+x,350+x,375+x};
         int[] poly2y = {225,200,500,525,475,425};
         g.fillPolygon(poly2x,poly2y,6);
         
         //arcade front(title displayed)
         g.setColor(Color.red);
         g.fillRect(175+x,225,200,50);
         g.setColor(Color.black);
         g.fillRect(175+7+x,225+7,200-14,50-14);
         
         //arcade screen
         //Color screen = new Color(0,128,255);
         g.setColor(Color.white);
         g.fillRect(175+x,275,200,150);
         g.setColor(Color.black);
         g.fillRect(175+10+x,275+10,180,130);
         
         g.drawLine(175+x,275,185+x,285);
         g.drawLine(375+x,275,365+x,285);
         g.drawLine(175+x,425,185+x,415);
         g.drawLine(375+x,425,365+x,415);
         
         //controls panel
         g.setColor(Color.LIGHT_GRAY);
         int[] poly3x = {175+x,375+x,350+x,150+x};
         int[] poly3y = {425,425,475,475};
         g.fillPolygon(poly3x,poly3y,4);
         
         //arcade front(bottom)
         g.setColor(Color.white);
         g.fillRect(150+x,475,200,50);
         
         //coin slot
         g.setColor(Color.LIGHT_GRAY.darker().darker().darker());
         g.fillRect(325+x,485,8,20);
         g.setColor(Color.black);
         g.fillRect(328+x,488,2,14);
         g.setFont(new Font("Bodoni Mt Black",Font.PLAIN,12));
         g.drawString("25¢",295+x,498);
         
         //yellow button
         g.setColor(Color.yellow);
         g.fillOval(195+x,430,15,15);
         g.setColor(Color.yellow.darker().darker());
         g.setFont(new Font("", Font.PLAIN,10));
         g.drawString("Y", 199+x,441);
         
         //red button
         g.setColor(Color.red);
         g.fillOval(195+15+x,430+15,15,15);
         g.setColor(Color.red.darker().darker());
         g.drawString("B",215+x,456);
         
         //green button
         g.setColor(Color.green);
         g.fillOval(195+x,460,15,15);
         g.setColor(Color.green.darker().darker());
         g.drawString("A",199+x,471);
         
         //blue button
         Color xColor = new Color(55,119,255);         
         g.setColor(xColor);
         g.fillOval(195-15+x,445,15,15);
         g.setColor(Color.blue.darker().darker());
         g.drawString("X",185+x,456);
         
         //controller
         //base
         g.setColor(Color.black);
         g.fillOval(195+100+x,445,40,15);
         
         //stick
         g.setColor(Color.white);
         g.fillOval(311+x,430,8,25);
         Color whiteShade = new Color(210,210,210);
         g.setColor(whiteShade);
         g.fillOval(311+x,430,4,25);
         
         //ball on the control stick
         Color redShade =  new Color(102,0,0);
         g.setColor(redShade);
         g.fillOval(307+x,422,16,16);
         g.setColor(Color.red);
         g.fillOval(312+x,422,11,16);
         
         //titles and images displayed on the screens
         Color lime = new Color(0,255,0);
         g.setColor(lime);
                  
         if(loop == 0)
         {
            //draws the Soccer Arcade machine
            g.setFont(new Font("Bodoni Mt Black", Font.PLAIN,36));
            g.drawString("SOCCER",175+15+5+x,265-2);
            g.setFont(new Font("Courier", Font.PLAIN,20));
            g.setColor(lime);
            g.drawString("CLICK TO PLAY",185+12+x,285+125);
            g.setColor(Color.white);
            g.fillRect(185+20+x,285+25,5,25);
            g.setColor(Color.red);
            g.fillOval(205+50+20+x,315,10,10);
         }
         else if(loop == 1)
         {
            //g.fillRect(175+10+x,275+10,180,130);
            //screen coords 185+x,285,185+180+x,285+130
            //draws the Snake Arcade Machine
            g.setFont(new Font("Forte", Font.PLAIN,36));
            g.drawString("SNAKE",175+25+20+x,263);
            g.setFont(new Font("Courier", Font.PLAIN,20));
            g.setColor(lime);
            g.drawString("CLICK TO PLAY",185+12+x,285+125);
            g.fillRect(250+x,285+25,10,40);
            g.fillRect(260+x,285+25+30,10,40);
            g.setColor(Color.red);
            g.fillOval(250+50+x,285+25,10,10);
            g.setColor(Color.black);
            g.fillOval(252+x,285+25+2,4,4);
         }
         else if(loop ==2)
         {
            //draws the SpaceWars arcade machine
            g.setFont(new Font("Pristina", Font.BOLD,31));
            g.drawString("SPACEWAR",180+x,260);
            g.setFont(new Font("Courier", Font.PLAIN,20));
            g.setColor(lime);
            g.drawString("CLICK TO PLAY",185+12+x,285+125);
            g.setColor(Color.blue);
            int[] spacex = {185+75+x,185+35+50+x,185+50+45+x};
            int[] spacey = {285+105,285+105-10,285+105};
            g.fillPolygon(spacex,spacey,3);
            g.setColor(Color.white);
            for(int loop1 = 0; loop1 <= 50; loop1 += 10)
               g.fillOval(185+32+50+x,285+105-20-loop1,6,6);
            g.setColor(Color.red);
            g.fillRect(185+35+50-10+x,285+105-20-70,20,10);
            g.setColor(Color.black);
            g.fillRect(185+35+50-10+3+x,285+105-90+2,3,3);
            g.fillRect(185+35+50+3+x,285+105-90+2,3,3);
         }
         else if(loop == 3)
         {
            //draws the Pong Game machine
            g.setFont(new Font("Courier", Font.BOLD,31));
            g.drawString("    PONG",180+x,260);
            g.setFont(new Font("Courier", Font.PLAIN,20));
            g.setColor(lime);
            g.drawString("CLICK TO PLAY",185+12+x,285+125);
         }
         else
            //only if the loop fails
            g.drawString("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE",0,0);
            
         x+=300;
      }
      //draws ticket counter
      g.setColor(Color.blue);
      g.drawString("Tickets: " +Ticket.getTickets(), 100, 100); 
      
      //draws the flip page arrow
      g.setColor(Color.black);
      int[] arrowx = {905+300,955+300,955+300,1010+300,955+300,955+300,905+300};
      int[] arrowy = {40,40,20,50,80,60,60};
      
      g.fillPolygon(arrowx,arrowy,7);
      g.drawString("Next Page",910+300,100);
      
      //draws the rank and ticket display
      g.setColor(Color.DARK_GRAY);
      g.fillRect(910+100+50+50,200,200,175);
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(910+100-10+50+50,200+10,200,175);
      g.setColor(Color.black);
      g.fillRect(910+100+50+50,200+20,180,230-75);
      g.setColor(Color.white);
      g.setFont(new Font("Courier",Font.BOLD,36));
      g.setColor(Color.green);
      g.drawString("Tickets",910+100+50+50+10,200+20+28);
      g.setColor(Color.white);
      g.drawString("" +Ticket.getTickets(),910+100+50+50+50,200+20+28+28+15);
      g.setColor(Color.green);
      g.drawString("Rank",910+100+50+50+10,200+20+28+28+28+28);
      g.setColor(Color.white);
      g.setFont(new Font("Courier", Font.BOLD,20));
      g.drawString("" +TicketRank.returnRank(),910+100+50+50+5,200+20+28+28+28+28+28+5);
      
      //light beam with changing colors
      /*Color lighting = new Color(r,gr,b,100);
      g.setColor(lighting);
      int[] beamx = {562,100,1075,562+75};
      int[] beamy = {50,525,525,50};
      g.fillPolygon(beamx,beamy,4);
      g.fillArc(100,400,975,250,180,180);  */      
   }
}
