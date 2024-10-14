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

    public static int STRCount(String sequence, String STR) {

        char[] charArray = sequence.toCharArray();
        byte[] map = new byte[256];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        int transformedSTR = 0;
        for (int i = 0; i < STR.length(); i++) {
            int code = map[STR.charAt(i)];
            transformedSTR = transformedSTR << 2;
            transformedSTR = transformedSTR | code;
        }

        int highestCount = 0;
        int buffer = 0;

        // Fill the buffer with the first


        return highestCount;
    }
}
