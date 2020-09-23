//This is a simple class that given the numefr tickets,
//it will return a rank given the amount
//It is used by the lobbys
public class TicketRank
{
   public static String returnRank()
   {
      String label;
      if(Ticket.getTickets() < 101)
      label = "Noob";
      else if( Ticket.getTickets() < 251)
      label = "Beginner";
      else if( Ticket.getTickets() < 501)
      label = "Intermediate";
      else if( Ticket.getTickets() < 1001)
      label = "Average";
      else if( Ticket.getTickets() < 2001)
      label = "Pro Gamer";
      else if( Ticket.getTickets() < 5001)
      label = "Epic Gamer";
      else
      label = "Ultimate Gamer";
      return label;
   }
}