public interface RotationCipher {
    /* main interface methods.
     Passing correct parameters in. */
    public String rotate(String s, int n);
    public String decipher(String s);
    public double[] frequencies(String s);
    public double chiSquared(double[] letters);
}
