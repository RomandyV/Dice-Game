
/****************************************************************************************
 * A class that contains methods for a dice game using the GVdie class.
 *
 * @author Romandy Vu
 * @version 10/26/2020
 ****************************************************************************************/
public class Chuck
{
    // instance variables
    /**The array to hold the GVdie objects */
    private GVdie[] arrayOfDie;

    /**An integer variable to hold the number of credits*/
    private int credits;

    /** A String variable to display message to the user*/
    private String message = "";

    /** A boolean array to hold the bets */
    private boolean[] bets;

    /*********************************************************************************
     * Constructor for objects of class Chuck, starts by setting the credits to 10,
     * Creates the GVdie array to hold 3 objects of GVdie, creates the GVdie object through
     * a loop (object is referenced as "arrayOfDie[i]" where "i" is some integer to be 0-2),
     * creates a boolean array to store the bets (bets are set to "false" by default), and
     * sets the dice to the "blank" side using a loop and the setBlank method from GVdie.
     **********************************************************************************/
    public Chuck()
    {
        // initialise instance variables
        credits = 10;

        arrayOfDie = new GVdie[3];

        for (int i =0; i < 3; i++){
            arrayOfDie[i] = new GVdie();
        }

        bets = new boolean[9];

        for(int ii = 0; ii < 3; ii++){
            arrayOfDie[ii].setBlank();
        }
    }

    /************************************************************************************
     * Method getCredits returns the number of credits
     *
     * @return returns the number of credits as integers.
     **********************************************************************************/
    public int getCredits(){
        return credits;
    }

    /***********************************************************************************
     * Method getMessage returns a message
     *
     * @return returns a String that contains a message from a result.
     **********************************************************************************/
    public String getMessage(){
        return message;

    }

    /***********************************************************************************
     * Method getDice returns the array of GVdie objects.
     *
     * @return returns an array of the GVdie class that contains the GVdie objects.
     *********************************************************************************/
    public GVdie[] getDice(){
        return arrayOfDie;
    }

    /************************************************************************************
     * Method getSumDiceValues gets the sum of all the GVdie objects.
     *
     * @return returns an integer that gets the sum of the integers of the GVdie Objects
     * using a loop and the getValue method.
     **********************************************************************************/
    private int getSumDiceValues(){
        int sum = 0;
        for(int i = 0; i < 3; i++){
            sum = sum + arrayOfDie[i].getValue();

        }

        return sum;
    }

    /******************************************************************************************
     * Method isDoubles checks to see if two of the GVdie objects matches with a specific value
     * (num).
     *
     * @param num A integer parameter that is specified so that it can compare the value of the 
     * GVdie objects to see if it matches.
     * @return returns true if only 2 of the die matches the argument, else returns false.
     ***************************************************************************************/
    private boolean isDoubles(int num){
        boolean doublesResult = false;
        int doublesCount = 0;

        for(int i = 0; i < 3; i++){
            if (arrayOfDie[i].getValue() == num){
                doublesCount++;

            }
        }

        if (doublesCount >= 2){
            doublesResult = true;

        }
        return doublesResult;
    }

    /**************************************************************************************
     * Method isTriplets Checks to see if all of the GVdie objects matches with eachother.
     *
     * @return returns true if ALL the die matches with eachother, else returns false.
     *****************************************************************************************/
    private boolean isTriplets(){
        boolean tripletsResult = false;
        int tripletCount = 0;

        for (int i = 0; i < 3; i++){
            if (arrayOfDie[i].getValue() == arrayOfDie[0].getValue()){
                tripletCount++;
            }

        }

        if (tripletCount == 3){
            tripletsResult = true;

        }

        return tripletsResult;
    }

    /***************************************************************************************
     * Method checkLarge Adds 2 additional credits and updates the message 
     * if the sum of the GVdie objects is larger than 10 and 
     * the GVdie objects are not the same (Done by using the getSumDiceValue method 
     * and the isTriplets method).
     *
     ***************************************************************************************/
    private void checkLarge(){
        if ((getSumDiceValues() > 10) && (isTriplets() == false)){
            credits = credits + 2;
            message = "You win!";

        }

    }

    /***************************************************************************************
     * Method checkSmall Adds 2 additional credits and updates the message
     * if the sum of the GVdie objects is smaller than 11 and
     * the GVdie are not triplets.
     *
     **************************************************************************************/
    private void checkSmall(){
        if((getSumDiceValues() < 11) && (isTriplets() == false)){
            credits = credits + 2;
            message ="You win!";
        }

    }

    /***************************************************************************************
     * Method checkField Adds 2 additional credits and updates the message
     * if the sum of the GVdie objects is smaller than 8 or
     * greater than 12.
     *
     ***************************************************************************************/
    private void checkField(){
        if((getSumDiceValues() < 8) || (getSumDiceValues() > 12)){
            credits = credits + 2;
            message = "You win!";
        }

    }

    /**************************************************************************************
     * Method checkNumber
     *
     * @param num A parameter that will be used to see if the GVdie objects matches.
     * If the GVdie object matches with num, then depending on how much GVdie objects matches, 
     * credits will be added and the message will update.
     *************************************************************************************/
    private void checkNumber(int num){
        int result = num;
        if (isDoubles(result)){
            if (isTriplets()){
                credits = credits + 11;
                message = "You win!";
            }
            else{
                credits = credits + 3;
                message = "You win!";
            }

        }

        else{
            boolean ifOneMatch = false;
            for(int i = 0; i < 3; i++){
                if (arrayOfDie[i].getValue() == num){
                    ifOneMatch = true;
                }

            }

            if (ifOneMatch){
                credits = credits + 2;
                message = "You win!";
            }
        }
    }

    /*************************************************************************************
     * Method checkAllBets uses a loop to check if a bet is placed,
     * for a bet that is placed, a credit is deducted and will increase
     * if the dice roll matches with the placed bets using the checkNumber,
     * checkLarge, checkField, checkSmall methods.
     *
     ***********************************************************************************/
    private void checkAllBets(){
        message = "You lose.";

        for (int i = 0; i < 9; i++){
            if(bets[i]){
                credits = credits -1;
                
                if(i <= 5){
                    checkNumber(i + 1);
                
                }
                
                else{
                    switch (i){
                        case 6:
                        checkField();
                        break;
                        
                        case 7:
                        checkSmall();
                        break;
                        
                        case 8:
                        checkLarge();
                        break;
                    
                    }
                
                }

            }

        }
    }

    /*********************************************************************************
     * Method addCredits adds additional credits to the pool based on the amount,
     * only if the amount is positive.
     *
     * @param amount A parameter that will increase the the credit by "amount",
     * only if the amount is positive.
     ******************************************************************************/
    public void addCredits(int amount){
        if (amount > 0){
            credits = credits + amount;

        }

    }

    /******************************************************************************
     * Method placeBet places a bet where the certain bet is a certain index
     * in the bets array.
     *
     * @param bet A parameter that will set the bet in an array to true. Where
     * the argument can only be from 1 to 9.
     ***************************************************************************/
    public void placeBet(int bet){
        int index = bet - 1;
        if ((index >= 0) && (index < 10)){
            bets[index] = true;

        }

        else{
        }
    }

    /******************************************************************************
     * Method cancelBet cancels a bet where the certain bet is a certain index
     * in the bets array.
     *
     * @param bet A parameter that will set the bet in an array to false. The
     * argument can only be from 1 to 9.
     ******************************************************************************/
    public void cancelBet(int bet){
        if ((bet > 0) && (bet < 10)){
            bets[bet - 1] = false;

        }
    }

    /*****************************************************************************
     * Method clearAllBets sets all the bets in the bets array to false to
     * remove the bet.
     *
     *****************************************************************************/
    public void clearAllBets(){
        for (int i = 0; i < 9; i++){
            bets[i] = false;

        }

    }

    /*******************************************************************************
     * Method roll rolls the GVdie Objects, checks the bets, and clear the bets IF
     * THERE IS ENOUGH CREDITS (using enoughCredits method) OTHERWISE WILL MESSAGE
     * WILL UPDATE THAT THERE IS NOT ENOUGH CREDITS.
     *
     ******************************************************************************/
    public void roll(){
        if (enoughCredits()){
            for(int i = 0; i < 3; i++){
                arrayOfDie[i].roll();

            }

            checkAllBets();

            clearAllBets();
        }

        else{
            message = "Not enough credits to place for all bets";
            for(int i = 0; i < 3; i++){
                arrayOfDie[i].setBlank();

            }
        }
    }

    /*************************************************************************************
     * Method reset sets the GVdie objects in the array to blank using a loop, 
     * then clears the message, sets the credits to 10, 
     * and clears all the bets using the clearAllBets method.
     *
     *************************************************************************************/
    public void reset(){
        for(int i = 0; i < 3; i++){
            arrayOfDie[i].setBlank();

        }
        message = "";
        credits = 10;
        clearAllBets();

    }

    /*************************************************************************************
     * Method enoughCredits checks to see if there is enough credits left over
     * (greater than or equal to 0) after placing the bets.
     *
     * @return returns true if amount of credits after the cost of placing the bets
     * is not negative, if it is negative then it returns false.
     *************************************************************************************/
    public boolean enoughCredits(){
        boolean enough = false;
        int count = 0;

        for (int i = 0; i < 9; i++){
            if (bets[i]){
                count++;

            }

        }

        if ((credits - count) >= 0){
            enough = true;

        }

        return enough;
    }

    /************************************************************************************
     * Method diceToString returns a string of the dice rolled in the array.
     *
     * @return The return value is a string of the dice rolled in the array.
     ************************************************************************************/
    public String diceToString(){
        String diceRolls = "[";
        for(int i = 0; i < 3; i++){
            String result = arrayOfDie[i].getValue() + "";
            if( i < 2){
                result += ",";
            
            }
            
            else{
                result += "]";
            
            }
            diceRolls += result;

        }
        return diceRolls;
    }

    /*********************************************************************************************************
     * Method testRoll used to test to see if the bets work without the need to repeatedly run the program
     * until the die rolls are desired.
     *
     * @param values A parameter that takes 3 int augments that the die in the array will match to
     * respectively
     *********************************************************************************************************/
    public void testRoll(int[] values){

        if (enoughCredits()){
            for(int i = 0; i < 3; i++){
                if ((values[i] > 6) || (values[i] < 1)){
                    values[i] = 1;
                }

            }

            int j = 0;
            while(j != 3){
                if(values[j] == arrayOfDie[j].getValue()){
                    j++;
                }

                else{
                    arrayOfDie[j].roll();
                }
            }

            checkAllBets();

            clearAllBets();

        }
        else{
            message = "Not enough credits.";

        }

    }
}
