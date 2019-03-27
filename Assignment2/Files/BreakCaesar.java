/**
 * A class running the decipher method
 * Takes input from user using command line arguments and then runs it
 * 
 * arg[0] = first input
 * arg[1] = second input
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * @see java BreakCaesar.main 
 * 
 */

 // implements RotationCipher?
public class BreakCaesar extends Caesar {   

    // constructor to call main decipher method and pass in new parameters
    public BreakCaesar(String s){
        System.out.println(decipher(s));
    }
    
    public static void main(String[] args){

        // Attributes needed to take inputs effectively
        boolean pass = false;
        // if input is only 1, try this.
        if(args.length == 1){
            // try and make it an integer.
            try{
                Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e){
                // fail = String, this is what we want.
                pass = true;
            }
            if(pass==true){
                BreakCaesar two = new BreakCaesar(args[0]);
            }  
            // if we do not pass, you did not enter a string
            else{
                System.out.println("You did not enter a string!\nUsage: java BreakCaesar 'cipher text'");
            }
        }
        else if(args.length>1) {
            System.out.println("Too many parameters!\nUsage: java BreakCaesar 'cipher text'");
        }
        else if(args.length<1){
            System.out.println("Too few parameters!\nUsage: java BreakCaesar 'cipher text'");
        }
    }
    
}