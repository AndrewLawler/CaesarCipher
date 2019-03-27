/**
 * A class implementing the interface
 *  
 * INFO
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * @see java BreakCaesar.main 
 * 
 */

public class Caesar implements RotationCipher {

    // if you change the alphabet for a different language, just edit this.
    int alphabetlength = 26;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    // arrays used throughout
    double[] letters = new double[alphabetlength];
    double[] closeness = new double[alphabetlength+1];

    // main rotate method.
    public String rotate(String s, int n){
        
        // Attributes for this, including length of the input, the char text array and the char itself.

        int len =  s.length();
        // new rotated char array
        char[] Text = new char[len];
        // initialising ch
        char ch;

        /* if the rotation is less than 0. we add the number to 0.
        eg: -2 = alphabetlength(26)+-2 = 24 so we rotate 24 which is the same as taking 2 off of the string.
        */
        if(n<0){
            n = alphabetlength+n;
        }
        
        // for loop for chaning all new letters
        for (int i=0; i<s.length(); i++){ 
            // check if the letter is a character
            if(Character.isLetter(s.charAt(i))){
                // This if statement below was inspired and modified from: https://www.geeksforgeeks.org/caesar-cipher/
                // if s is an upper case letter, do the calculation below
                if(Character.isUpperCase(s.charAt(i))){ 
                    // ch = int(s(i) + n-65) % 26+65 converted to char. This is the formula for rotating an upper case character by position n.
                    ch = (char)(((int)s.charAt(i) + n - 65) % alphabetlength + 65); 
                    Text[i] = ch;
                } 
                // if character is not upper case, it must be lower case as we have confirmed it is a letter
                else{ 
                    // same as above but modified for lower case letters, this formula follows the exact same pattern.
                    ch = (char)(((int)s.charAt(i) + n - 97) % alphabetlength + 97); 
                    Text[i] = ch;
                } 
            }
            // not a letter, must be a form of symbol/punctuation
            else{
                Text[i] = s.charAt(i);
            }
            
        } 
        // creating String with char array text, returing it.
        String Answer = new String(Text);
        return Answer;
    }

    public String decipher(String s){

        double[] knownFreq = {
            0.0855, 0.0160, 0.0316, 0.0387, 0.1210,
            0.0218, 0.0209, 0.0496, 0.0733, 0.0022,
            0.0081, 0.0421, 0.0253, 0.0717, 0.0747,
            0.0207, 0.0010, 0.0633, 0.0673, 0.0894,
            0.0268, 0.0106, 0.0183, 0.0019, 0.0172,
            0.0011};

        /* Looping through all possible rotations and storing their chiSquared value inside the
        position for the rotation inside the closeness array */
        for(int i=1; i<=alphabetlength; i++){
            String x = rotate(s, i);
            double[] freq = frequencies(x);
            double finalValue = chiSquared(freq, knownFreq);
            closeness[i] = finalValue;
        }

        // Looping to find the smallest number in the closeness array, setting the smallestIndex to the index of this number.
        double smallest = closeness[1];
        int smallestIndex = 1;

        for(int i=1; i<=alphabetlength; i++){
            if(closeness[i]<smallest){
                smallest = closeness[i];
                smallestIndex = i;
            }
        }

        // the index is the rotation we need, rotate and calculate the decoded string.
        String RealText = rotate(s, smallestIndex);
        return RealText;

    }

    public double[] frequencies(String s){

        // convert to lower case so we dont have any issues.
        String x = s.toLowerCase();        
        // set len to the length of the string
        int len = s.length();

        // loop the string values against the alphabet values and find when they match.
        for(int i=0; i<len; i++){
            for(int k=0; k<=alphabetlength-1; k++){
                // add one to the corresponding letters array position if letters(i)==alphabet(i)
                if(x.charAt(i) == alphabet[k]){
                    letters[k]++;
                }
            }
        }
        // loop again to divide every value by the amount of numbers in the string
        for(int i=0; i<=alphabetlength-1; i++){
            letters[i] = letters[i]/len;
        }
        return letters;
    }

    public double chiSquared(double[] letters, double[] knownFreq){

        // Attributes needed for this method.

        double total = 0;
        double second;
        double first;
        double third;

        /* loop to run 26 times, completes equation on every rotation using the letter frequencies. 
        (letter(i)-english(i))^2/english(i),
        this is how we calculate the chiSquared value */
        for(int i=0; i<=alphabetlength-1; i++){
            /*first = letters[i]-knownFreq[i];
            second = first*first;
            third = (second/knownFreq[i]);
            total = total + third;*/
            // shorter version
            first = Math.pow(letters[i]-knownFreq[i], 2);
            total = total + (first/knownFreq[i]);
        }
        
        return total;

    }

}
