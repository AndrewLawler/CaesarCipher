public class BreakCaesar extends Caesar {   

    // constructor to call main decipher method and pass in new parameters
    public BreakCaesar(String s){
        String Answer  = decipher(s);
        // Printing new answer
        System.out.println(Answer);
    }
    
    public static void main(String[] args){

        // Attributes needed to take inputs effectively

        boolean pass = false;
        String text = "";
        int a = 0;

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
                // set text to args0.
                text = args[0];
            }  
            // if we do not pass, you did not enter a string
            else if(pass==false){
                System.out.println("You did not enter a string!");
            }
        }
        else {
            System.out.println("Wrong input length!");
        }

        // create new object of this class
        BreakCaesar two = new BreakCaesar(text);
    }
    
}