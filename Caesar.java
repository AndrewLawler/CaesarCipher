public class Caesar implements RotationCipher {

    public String rotate(String s, int n){
        
        int len =  s.length();
        char[] Text = new char[len];

        for (int i=0; i<s.length(); i++){ 
            if (Character.isUpperCase(s.charAt(i))){ 
                char ch = (char)(((int)s.charAt(i) + n - 65) % 26 + 65); 
                Text[i] = ch;
            } 
            else{ 
                char ch = (char)(((int)s.charAt(i) + n - 97) % 26 + 97); 
                Text[i] = ch;
            } 
        } 

        String Answer = new String(Text);
        return Answer;

    }

    public String decipher(String s){
        double[] closeness = new double[26];

        for(int i=0; i<=25; i++){
            closeness[i] = 0;
        }

        for(int i=0; i<=25; i++){
            String x = rotate(s, i+1);
            double[] freq = frequencies(x);
            double y = chiSquared(freq);
            closeness[i] = y;
        }

        double smallest = closeness[0];
        int smallestIndex = 0;

        for(int i=0; i<=25; i++){
            if(closeness[i]<smallest){
                smallest = closeness[i];
                smallestIndex = i;
            }
        }

        String RealText = rotate(s, smallestIndex+1);
        return RealText;
    }

    public double[] frequencies(String s){
        double[] letters = new double[26];
        int len = s.length();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for(int i=0; i<len; i++){
            for(int k=0; k<=25; k++){
                if(s.charAt(i) == alphabet[k]){
                    letters[i]++;
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

        double[] knownFreq = {
            0.0855, 0.0160, 0.0316, 0.0387, 0.1210,
            0.0218, 0.0209, 0.0496, 0.0733, 0.0022,
            0.0081, 0.0421, 0.0253, 0.0717, 0.0747,
            0.0207, 0.0010, 0.0633, 0.0673, 0.0894,
            0.0268, 0.0106, 0.0183, 0.0019, 0.0172,
            0.0011};
        
        for(int i=0; i<=25; i++){
            first = letters[i]-knownFreq[i];
            // System.out.println("first"+first);
            // System.out.println(knownFreq[i]);
            // System.out.println(letters[i]);
            second = first*first;
            total = total + (second/knownFreq[i]);
            // System.out.println(second);
            // System.out.println(total);
        }

        return total;
    }

}
