
/**
 * Write a description of class SampleOutput here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SampleOutput
{
    public static void main(String[] args){
    Chuck game = new Chuck();
    System.out.println("You have: " + game.getCredits() + " credits");
    
    System.out.println("Message: " + game.getMessage() + "\n");
    
    game.placeBet(1);
    System.out.println("Placed bet on getting a 1");
    game.placeBet(9);
    System.out.println("Placed bet on getting a large"); 
    game.cancelBet(9);
    System.out.println("Cancelled bet on getting a large");
    
    System.out.println("Enough credits?: " + game.enoughCredits() + "\n");
    
    game.roll();
    System.out.println("Rolling the die \n ");
    
    System.out.println("The dice rolls are: " + game.diceToString());
    System.out.println("Message: " + game.getMessage());
    
    System.out.println("You now have: " + game.getCredits() + " credits \n");
    
    game.addCredits(10);
    System.out.println("Credits added");
    System.out.println("You now have: " + game.getCredits() + " credits \n");
    
    game.reset();
    System.out.println("Game has been reset");
    System.out.println("You have: " + game.getCredits() + " credits");
    System.out.println("Message: " + game.getMessage());
    
    
    
    }
}
