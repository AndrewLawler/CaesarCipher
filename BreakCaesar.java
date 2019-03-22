public class BreakCaesar extends Caesar {   

    public BreakCaesar(String s){
        String Answer  = decipher(s);
        System.out.println(Answer);
    }
    
    public static void main(String[] args){
        String whatever = "";
        int a = 0;

        if(args.length > 0){
            whatever = args[0];
        }
        else {
        }

        BreakCaesar two = new BreakCaesar(whatever);
    }
    
}