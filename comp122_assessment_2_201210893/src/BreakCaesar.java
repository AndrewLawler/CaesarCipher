/**
 * A class running the decipher method
 * BreakCaesar also extends Caesar as we use methods from that class
 * Takes input from user using command line arguments and then runs it
 * 
 * arg[0] = String
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * 
 */

public class BreakCaesar extends Caesar {   

   /**
    * constructor simply calls decipher with the string we have
    * @param jumbledText represents the string inputted
    */
    public BreakCaesar(String jumbledText){
        System.out.println(decipher(jumbledText));
    }
    
    /**
     * main simply validates the input using try{} catch{} blocks
     * @param args we use command line inputs for this program, therefore we refer to args0
     */
    public static void main(String[] args){

        // is pass = true, we have a String
        boolean pass = false;
        // if the user inputs one input, run this
        if(args.length == 1){
            // try and parse it to an Integer
            try{
                Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e){
                // fail = String, this is what we want.
                pass = true;
            }
            // we have a String so create a new object of the class and decipher the String
            if(pass==true){
                BreakCaesar two = new BreakCaesar(args[0]);
            }  
            // if we do not pass, you did not enter a string
            else{
                System.out.println("You did not enter a string!\nUsage: java BreakCaesar 'cipher text'");
            }
        }
        // if we dont enter any parameters we have entered too little inputs
        else if(args.length>1) {
            System.out.println("Too many parameters!\nUsage: java BreakCaesar 'cipher text'");
        }
        // if we enter more than one parameter we have too many inputs
        else if(args.length<1){
            System.out.println("Too few parameters!\nUsage: java BreakCaesar 'cipher text'");
        }
    }
    
}