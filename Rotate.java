public class Rotate extends Caesar {
    
    public Rotate(String s, int x){
        String Answer  = rotate(s, x);
        System.out.println(Answer);
    }
    
    public static void main(String[] args){
        String number_of_rotationns;
        String whatever = "";
        int a = 0;

        if(args.length > 1){
            number_of_rotationns = args[0];
            whatever = args[1];
            a = Integer.parseInt(number_of_rotationns);
        }
        else {
        }

        Rotate one = new Rotate(whatever, a);
    }
    
}