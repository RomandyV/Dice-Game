
/**
 * Tests the Chuck class
 *
 * @author Romandy Vu
 * @version 10/26/2020
 */
public class ChuckTest
{
    public static void main(String[] args){
        System.out.println("Testing begins");
        GVdie d1 = new GVdie();
        GVdie[] test = new GVdie[1];
        d1.setBlank();
        Chuck game = new Chuck();
        assert game.getCredits() == 10: "Should be 10"; ///Testing the getCredits method
        assert game.getMessage().equals("") : "Should be blank"; ///Testing the getMessage method
        game.placeBet(2);
        game.cancelBet(2);
        game.placeBet(1);
        game.testRoll(new int [] {1, 2, 3});
        assert game.enoughCredits() == true: "Should be true"; ///testing enoughCredits method
        assert game.getCredits() == 11: "Should be 11 due to getting 1 2"; //Testing placeBet
        //roll,testRoll, getSumDiceValues, checkAllBets, and cancelBet method.
        game.placeBet(3);
        game.testRoll(new int[] {3,3,3});
        assert game.getCredits() == 21: "Should be 21 due to getting 3 3s increasing credits by 10"; ///testing isTriplets
        //method
        
        game.placeBet(3);
        game.testRoll(new int[] {2,3,3});
        assert game.getCredits() == 23: "Should be 23 due to a double"; ///testing isDoubles method.
        
        game.placeBet(4);
        game.placeBet(5);
        game.placeBet(6);
        game.testRoll(new int[] {4,5,6});
        assert game.getCredits() == 26: "Should be 26 due to getting 4,5,6"; ///testing if index 3,4,5 works of the bets
        ///array and the checkField
        
        game.placeBet(7);
        game.placeBet(8);
        game.placeBet(9);
        
        game.testRoll(new int[] {4,5,1});
        assert game.getCredits() == 25: "Should be 25 due to only getting small (+1 credit) and losing 2 credits" +
        "from not getting a field or large."; ///testing checkField, checkLarge, checkSmall method.
        
        game.reset();
        assert game.getCredits() == 10: "Should be 10 due to reset"; ///testing reset method
        
        game.placeBet(7);
        game.placeBet(8);
        game.placeBet(9);
        game.clearAllBets();
        game.testRoll(new int[] {4,5,6});
        assert game.getCredits() == 10: "Should be 10 due to not putting any bets"; ///testing clearAllBets method.
        
        assert game.diceToString().equals("[4,5,6]"): "Should be in the format \"[4,5,6]\""; ///testing diceToString method.
        
        System.out.println("Testing ended");
        

        
    }
}
