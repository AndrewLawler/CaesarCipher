/**
 * A class implementing the interface we made in RotationCipher. Caesar implemenets RotationCipher as Caesar is the subclass. In Caesar we have taken the methods
 * from the interface and we have implemented them so that they now work. Throughout the project i focused on making the project as easy to use as possible.
 * With the use of the public variables at the top which are easily distinguishable, and the knownFreq array. You can easily change your language.
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * 
 */
public class Caesar implements RotationCipher {

    // if you change the alphabet for a different language, just edit this. (make sure you enter alphabet as lowercase)
    int alphabetlength = 26;
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    // arrays used for letter frequency and chiSquared values
    double[] letters = new double[alphabetlength];
    double[] closeness = new double[alphabetlength+1];

    /**
     * Rotate
     * 
     * This is the class responsible for rotating the string by n
     * 
     * @param s represents the String which is taken in, this is the string we want to rotate
     * @param n represents the rotation value
     * @return we return the new string which has been rotated
     */
    public String rotate(String s, int n){
        // length of the string
        int len =  s.length();
        // char array which will contain the rotated letters
        char[] Text = new char[len];

        /* if the rotation is less than 0. we add the number to 0.
        eg: -2 = alphabetlength(26)+-2 = 24 so we rotate 24 which is the same as taking 2 off of the string.
        */
        if(n<0){
            n = alphabetlength+n;
        }
        
        // looping to rotate all letters
        for (int i=0; i<s.length(); i++){ 
            // check if the letter is a character
            if(Character.isLetter(s.charAt(i))){
                // This if statement below was inspired and modified from: https://www.geeksforgeeks.org/caesar-cipher/
                // if s is an upper case letter, do the calculation below
                if(Character.isUpperCase(s.charAt(i))){ 
                    // ch = int(s(i) + n-65) % 26+65 converted to char. This is the formula for rotating an upper case character by position n.
                    char ch = (char)(((int)s.charAt(i) + n - 65) % alphabetlength + 65); 
                    Text[i] = ch;
                } 
                // if character is not upper case, it must be lower case as we have confirmed it is a letter
                else{ 
                    // same as above but modified for lower case letters, this formula follows the exact same pattern.
                    char ch = (char)(((int)s.charAt(i) + n - 97) % alphabetlength + 97); 
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

    /**
     * Decipher
     * 
     * This method is completely responsibe for deciphering a string input. It first takes the string and rotates it for every possible rotation. 
     * It then compares the frequencies by sending them to the chiSquared method. 
     * 
     * @param jumbledText represents the string taken in as an input, this string is then deciphered
     * @return we simply return the deciphered string 
     */
    public String decipher(String jumbledText){
        // frequencies for the language we are using, in this case its English
        double[] knownFreq = {
            0.0855, 0.0160, 0.0316, 0.0387, 0.1210,
            0.0218, 0.0209, 0.0496, 0.0733, 0.0022,
            0.0081, 0.0421, 0.0253, 0.0717, 0.0747,
            0.0207, 0.0010, 0.0633, 0.0673, 0.0894,
            0.0268, 0.0106, 0.0183, 0.0019, 0.0172,
            0.0011};

        // Looping through all possible rotations of the string and then using frequencies and chiSquared to find out if its close to English or not 
        for(int i=1; i<=alphabetlength; i++){
            String Rotated = rotate(jumbledText, i);
            closeness[i] = chiSquared(frequencies(Rotated), knownFreq);
        }

        // Smallest number will be the closest to English, we will find its index
        double smallest = closeness[1];
        int smallestIndex = 1;
        // looping through the closeness array to find the smallest number
        for(int i=1; i<=alphabetlength; i++){ 
            if(closeness[i]<smallest){
                smallest = closeness[i];
                smallestIndex = i;
            }
        }

        // the index is the desired rotation which will allow us to get back to english, we then use this
        String RealText = rotate(jumbledText, smallestIndex);
        return RealText;

    }

    /**
     * frequencies
     * 
     * This method is responsible for taking a string input and returning a double[] of its frequencies for every letter.
     * 
     * @param plainText represents the String we take as an input (the string we are finding the frequencies for)
     * @return returns the strings frequencies as a double[]
     */
    public double[] frequencies(String plainText){

        // convert to lower case so we dont have any issues comparing it to alphabet array
        String plainTextlower = plainText.toLowerCase();        
        // len = length of the string
        int len = plainText.length();

        // loop the string values against the alphabet values and find when they match.
        for(int i=0; i<len; i++){
            for(int k=0; k<=alphabetlength-1; k++){
                // add one to the corresponding letters array position if letters(i)==alphabet(i). / eg: if x.charAt(i)==a, letters[0]++;
                if(plainTextlower.charAt(i) == alphabet[k]){
                    letters[k]++;
                }
            }
        }
        // loop again to divide every value by the amount of numbers in the string
        for(int i=0; i<=alphabetlength-1; i++){
            letters[i] = letters[i]/len;
        }
        // return the double[] array
        return letters;
    }

    /**
     * chiSquared
     * 
     * This is the method which is responsible for taking the knownFreq array and the new frequencies array we just calculated. 
     * We compare letter by letter the frequencies array with the knownFreq array using a simple calculation.
     * We then return the total given.
     * 
     * Calculation in its raw format:
     * ((frequencies[i]-Language[i])^2 / Language[i])
     * 
     * @param letters represents the frequencies array that was returned from the frequencies() method.
     * @param knownFreq represents the language frequencies for the language we are using. This was initialised in decipher()
     * @return returns the total of the calculation for the set rotation
     */
    public double chiSquared(double[] letters, double[] knownFreq){

        // total is the total we calculate for the given rotation, first represents the first part of the calculation
        double total = 0;
        double first;
    
        /* loop to run (alphabetlength) times, performs mathetmatical equation on every rotation using the letter frequencies. 
        This is how we calculate the chiSquared value */
        for(int i=0; i<=alphabetlength-1; i++){
            // Equation explained in comment above
            first = Math.pow(letters[i]-knownFreq[i], 2);
            total = total + (first/knownFreq[i]);
        }
        // return total calculated
        return total;

    }

}
