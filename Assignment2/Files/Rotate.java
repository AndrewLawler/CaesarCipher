public class Rotate extends Caesar {
    
    // constructor to call rotate method from main Caesar class.
    public Rotate(String s, int x){
        String Answer  = rotate(s, x);
        // Printing answer.
        System.out.println(Answer);
    }
    
    public static void main(String[] args){
    
        // Attributes needed for the inputs.

        boolean pass = false;
        boolean pass2 = true;
        String number_of_rotations = "";
        String textInput = "";
        int a = 0;

        // if input is 2, text and rotation, run this.

        if(args.length ==  2){
            // check for int.
            try{
                Integer.parseInt(args[0]);
            }
            // if error, we did not enter an integer
            catch(NumberFormatException e){
                pass2 = false;
                System.out.println("You did not enter an integer first!");
            }
            // if we did enter an integer, set number_of_rotations to args0.
            if(pass2 == true){
                number_of_rotations = args[0];
            }

            // check for string
            try{
                Integer.parseInt(args[1]);
            }
            catch(NumberFormatException e){
                // if we fail, it means we entered a string.
                pass = true;
            }
            // if we entered a string, set textInput to args1
            if(pass==true){
                textInput = args[1];
            }  
            else if(pass==false){
                System.out.println("You did not enter a string second!");
            }

            // if first int, second string run this.
            if( pass==true && pass2==true){
                // set a to the integer of number_of_rotations (we know this works so no try catch)
                a = Integer.parseInt(number_of_rotations);
            }
            else{
                System.out.println("Wrong inputs!");
            }
        }
        else {
            System.out.println("Wrong input length!");
        }

        // create input
        Rotate one = new Rotate(textInput, a);
    }
    
}