/**
 * A class taking the input and running the rotate method
 * Takes input from user using command line arguments and then runs the rotate method on it using a caesar object
 * 
 * arg[0] = Rotation
 * arg[1] = String
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * 
 */

public class Rotate {
    
    /**
     * main simply handles the validation for the application
     * 
     * @param args we need args becaue we use command line arguments for this program
     */
    
    public static void main(String[] args){
    
        // pass is the boolean for the String
        boolean pass = false;
        // pass2 is the boolean for the Integer
        boolean pass2 = true;
        // textInput will be the String we set args[1] to
        String textInput = "";
        // number_of_rotations is the int we set args[0] to
        int number_of_rotations = 0;

        // if we have two inputs, run this section.
        if(args.length ==  2){
            // check for Integer input by parsing it
            try{
                number_of_rotations = Integer.parseInt(args[0]);
            }
            // If we get an error, we must have a String
            catch(NumberFormatException e){
                pass2 = false;
                System.out.println("You did not enter an Integer first!\nUsage: java rotate n 'cipher text'");
            }
            // check for String by parsing to Integer, if error we have String
            try{
                Integer.parseInt(args[1]);
            }
            // if we catch an error we must have a String input, this is good
            catch(NumberFormatException e){
                pass = true;
                textInput = args[1];
            }
            // if pass still equals false, we need to tell the user they inputted an Integer
            if(pass==false){
                System.out.println("You did not enter a String second!\nUsage: java rotate n 'cipher text'");
            }
            // if first is an integer & the second is a string we can use the rotate method
            if(pass==true && pass2==true){
                // creates a new Caesar object so we can use it's methods
                Caesar Rotating = new Caesar();
                String converted = Rotating.rotate(textInput, number_of_rotations);
                System.out.println(converted);
            }
        }
        // if we have more than two inputs, we have too many
        else if(args.length>2) {
            System.out.println("Too many parameters!\nUsage: java rotate n 'cipher text'");
        }
        // if we have less than two inputs, we have too little inputs
        else if(args.length<2){
            System.out.println("Too few parameters!\nUsage: java rotate n 'cipher text'");
        }
    }
    
}