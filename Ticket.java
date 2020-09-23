//This is a simple ticket class that stores Ticket values, and return ticket values, and is used globally for ticket var
public class Ticket
{ 
   static int tickets = 0;
   
   public static void addTickets(int a)
   {
      tickets += a;
   }
   
   public static int getTickets()
   {
      return tickets;
   }  
}
