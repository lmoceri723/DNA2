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
    public static final int radix = 4;
    public static int[] charMap = new int[256];

    public static long hornerHash(String string, int length) {
        long hash = 0;
        for (int index = 0; index < length; index++) {
            hash = hash << 2;
            hash = hash | charMap[string.charAt(index)];
        }
        return hash;
    }

    public static int rabinKarp(String sequence, String STR) {
        int strLength = STR.length();
        int seqLength = sequence.length();
        int numBits = strLength * 2;
        long mask = (1L << numBits) - 1;
        long strHash = hornerHash(STR, strLength);
        long seqHash = hornerHash(sequence.substring(0, strLength), strLength);
        int count = 0;
        int maxCount = 0;
        int index = 0;

        while (index <= seqLength - strLength) {
            if (seqHash == strHash) {
                count++;
                index += strLength;
                if (count > maxCount) {
                    maxCount = count;
                }
                if (index <= seqLength - strLength) {
                    seqHash = hornerHash(sequence.substring(index, index + strLength), strLength);
                }
            } else {
                count = 0;
                if (index < seqLength - strLength) {
                    int nextChar = charMap[sequence.charAt(index + strLength)];
                    seqHash = seqHash << 2;
                    seqHash = seqHash | nextChar;
                    seqHash = seqHash & mask;
                }
                index++;
            }
        }

        return maxCount;
    }

    public static int STRCount(String sequence, String STR) {
        charMap['A'] = 0;
        charMap['a'] = 0;
        charMap['C'] = 1;
        charMap['G'] = 2;
        charMap['T'] = 3;
        return rabinKarp(sequence, STR);
    }
}
