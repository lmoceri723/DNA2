/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Landon Moceri
 *</p>
 */

public class DNA {
    public static int radix = 256;
    public static long largePrime = 54321102419L;

    public static long hash(String string, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++)
            hash = (hash * radix + string.charAt(i)) % largePrime;
        return hash;
    }

    public static void rabinKarp(String STR, String sequence) {
        long strHash = hash(STR, STR.length());
        int strLength = STR.length();
        long seqHash = hash(sequence.substring(0, strLength), strLength);
        long seqLength = sequence.length();

        for (int i = strLength; i < seqLength; i++)
            if (strHash == seqHash)
                // begin checking for consecutive appearances
                seqHash = hash(sequence[1 + (i - strLength), i])


    }



    public static int STRCount(String sequence, String STR) {

        // Create a map from the characters to their integer values
       char[] charArray = sequence.toCharArray();
//        byte[] map = new byte[256];
//        map['A'] = 0;
//        map['C'] = 1;
//        map['G'] = 2;
//        map['T'] = 3;


    }
}