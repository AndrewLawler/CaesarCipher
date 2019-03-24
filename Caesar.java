public class Caesar implements RotationCipher {

    public String rotate(String s, int n){
        
        int len =  s.length();
        char[] Text = new char[len];
        char ch;

        if(n<0){
            n = 26+n;
        }
        
        for (int i=0; i<s.length(); i++){ 
            // need better way of doing this

            if(s.charAt(i) == ' '){
                Text[i] = ' ';
            }
            else if(s.charAt(i)== '.'){
                Text[i] = '.';
            }
            else if(s.charAt(i)== ','){
                Text[i] = ',';
            }
            else if(s.charAt(i)== '!'){
                Text[i] = '!';
            }

            // comment where i got this from
            else if(Character.isUpperCase(s.charAt(i))){ 
                ch = (char)(((int)s.charAt(i) + n - 65) % 26 + 65); 
                Text[i] = ch;
            } 
            else{ 
                ch = (char)(((int)s.charAt(i) + n - 97) % 26 + 97); 
                Text[i] = ch;
            } 
        } 

        String Answer = new String(Text);
        return Answer;

    }

    public String decipher(String s){

        System.out.println("working");
        
        double[] closeness = new double[27];

        for(int i=0; i<=26; i++){
            closeness[i] = 0;
        }

        String x = "";

        for(int i=1; i<=26; i++){
            x = rotate(s, i);
            double[] freq = frequencies(x);
            double y = chiSquared(freq);
            closeness[i] = y;
        }

        double smallest = closeness[1];
        int smallestIndex = 1;

        for(int i=1; i<=26; i++){
            if(closeness[i]<smallest){
                smallest = closeness[i];
                smallestIndex = i;
            }
        }

        String RealText = rotate(s, smallestIndex);
        return RealText;

    }

    public double[] frequencies(String s){

        String x = s.toLowerCase();        
        double[] letters = new double[26];

        for(int i=0; i<=25; i++){
            letters[i] = 0;
        }

        int len = s.length();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for(int i=0; i<len; i++){
            for(int k=0; k<=25; k++){
                if(x.charAt(i) == alphabet[k]){
                    letters[k]++;
                }
            }
        }

        for(int i=0; i<=25; i++){
            letters[i] = letters[i]/len;
        }

        return letters;
    }

    public double chiSquared(double[] letters){

        double total = 0;
        double second;
        double first;
        double third;

        double[] knownFreq = {
            0.0855, 0.0160, 0.0316, 0.0387, 0.1210,
            0.0218, 0.0209, 0.0496, 0.0733, 0.0022,
            0.0081, 0.0421, 0.0253, 0.0717, 0.0747,
            0.0207, 0.0010, 0.0633, 0.0673, 0.0894,
            0.0268, 0.0106, 0.0183, 0.0019, 0.0172,
            0.0011};
        
        for(int i=0; i<=25; i++){
            first = letters[i]-knownFreq[i];
            second = first*first;
            third = (second/knownFreq[i]);
            total = total + third;

            /*
            if(letters[i]==0){
                total = total + 0;
            }
            else{
                first = letters[i]-knownFreq[i];
                second = first*first;
                third = (second/knownFreq[i]);
                total = total + third;
            }
            */
        }
        
        System.out.println(total);
        return total;

    }

}
