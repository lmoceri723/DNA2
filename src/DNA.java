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
    public static long highestCount = 1;

    public static long hash(String string, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash * radix + string.charAt(i)) % largePrime;
        }
        return hash;
    }

    public static void rabinKarp(String STR, String sequence) {
        long strHash = hash(STR, STR.length());
        int strLength = STR.length();
        long seqHash = hash(sequence.substring(0, strLength), strLength);
        int seqLength = sequence.length();
        int count = 1;

        int index = strLength;
        while (index + strLength < seqLength) {
            if (sequence.substring(index, index + strLength).equals(STR)) {
                // Count the number of consecutive STRs
                count++;
                index += strLength;
            }
            else {
                count = 0;
                index++;
            }

            if (count > highestCount) {
                highestCount = count;
            }
        }
    }

    public static int STRCount(String sequence, String STR) {
        rabinKarp(STR, sequence);
        return (int) highestCount;
    }
}