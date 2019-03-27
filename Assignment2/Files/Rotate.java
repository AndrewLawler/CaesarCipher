/**
 * A class running the Rotate method
 * Takes input from user using command line arguments and then runs it
 * 
 * arg[0] = first input
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * @see java.awt.main 
 * 
 */

// implements RotationCipher?
public class Rotate extends Caesar {
    
    // constructor to call rotate method from main Caesar class.
    public Rotate(String s, int x){
        System.out.println(rotate(s, x));
    }
    
    public static void main(String[] args){
    
        // Attributes needed for the inputs.
        boolean pass = false;
        boolean pass2 = true;
        String textInput = "";
        int number_of_rotations = 0;

        // if input is 2, text and rotation, run this.
        if(args.length ==  2){
            // check for int.
            try{
                number_of_rotations = Integer.parseInt(args[0]);
            }
            // if error, we did not enter an integer
            catch(NumberFormatException e){
                pass2 = false;
                System.out.println("You did not enter an Integer first!\nUsage: java rotate n 'cipher text'");
            }
            // check for string
            try{
                Integer.parseInt(args[1]);
            }
            // if error, must be string
            catch(NumberFormatException e){
                pass = true;
                textInput = args[1];
            }
            // pass=false, must be an integer
            if(pass==false){
                System.out.println("You did not enter a String second!\nUsage: java rotate n 'cipher text'");
            }
            // if first is an integer & the second is a string run this.
            if(pass==true && pass2==true){
                Rotate one = new Rotate(textInput, number_of_rotations);
            }
        }
        // too many parameters
        else if(args.length>2) {
            System.out.println("Too many parameters!\nUsage: java rotate n 'cipher text'");
        }
        // too few paramerers
        else if(args.length<2){
            System.out.println("Too few parameters!\nUsage: java rotate n 'cipher text'");
        }
    }
    
}