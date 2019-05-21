/**
 * RotationCipher is the interface for the project, in this interface we initialise the methods we will then implement in Caesar
 * 
 * @author Andrew Lawler
 * @version JDK 11.0.1
 * 
 */
public interface RotationCipher {
    // Initialising the public (abstract) methods
    public String rotate(String s, int n);
    public String decipher(String jumbledText);
    public double[] frequencies(String plainText);
    public double chiSquared(double[] letters, double[] knownFreq);
}
