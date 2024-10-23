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
        for (int i = 0; i < length; i++) {
            hash = (hash * radix + string.charAt(i)) % largePrime;
        }
        return hash;
    }

    public static int rabinKarp(String STR, String sequence) {
        long strHash = hash(STR, STR.length());
        int strLength = STR.length();
        long seqHash = hash(sequence.substring(0, strLength), strLength);
        long seqLength = sequence.length();
        int count = 0;

        for (int i = seqLength; i <= seqLength - strLength; i++) {
            if (sequence.substring(i, i + strLength).equals(STR)) {
                count++;

            }
        }


        return count;
    }

    public static int STRCount(String sequence, String STR) {
        return rabinKarp(STR, sequence);
    }
}