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

        // Create a map from the characters to their integer values
        char[] charArray = sequence.toCharArray();
        byte[] map = new byte[256];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        // Transform the STR into an integer
        int transformedSTR = 0;
        int strLength = STR.length();
        for (int i = 0; i < strLength; i++) {
            int code = map[STR.charAt(i)];
            transformedSTR = transformedSTR << 2;
            transformedSTR = transformedSTR | code;
        }

        int highestCount = 0;
        int buffer = 0;

        // Fill the buffer with the first str.length characters
        for (int i = 0; i < strLength; i++) {
            buffer = buffer << 2;
            buffer = buffer | map[charArray[i]];
        }

        // Make a mask to clear the highest (32 - 2 * STR.length) bits of the buffer
        int mask = (1 << (2 * strLength)) - 1;

        // Iterate through the rest of the sequence, updating the buffer and checking for matches
        int index = strLength;
        int count = 0;
        while (index <= charArray.length) {
            // Clear the highest bits of the buffer, as we only care about the last 2 * STR.length bits
            buffer = buffer & mask;

            // Check if the buffer matches the transformed STR
            if (buffer == transformedSTR) {
                count++;
                // Move ahead by the length of the STR, as we have found a match
                index += strLength;

                // Update the buffer with the next STR.length characters
                // If index is higher than the length of the sequence, do not update the buffer as the STR can't exist
                if (index <= charArray.length) {
                    buffer = 0;
                    for (int i = 0; i < strLength; i++) {
                        buffer = buffer << 2;
                        buffer = buffer | map[charArray[index - strLength + i]];
                    }
                }
                // If the buffer does not match the transformed STR, move to the next character
            } else {
                if (index < charArray.length) {
                    buffer = buffer << 2;
                    buffer = buffer | map[charArray[index]];
                }
                index++;
                // If the buffer is not a match, reset the count
                count = 0;
            }

            // Update the highest count
            if (count > highestCount) {
                highestCount = count;
            }
        }

        return highestCount;
    }
}