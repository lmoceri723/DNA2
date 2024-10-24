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
    // I have 4 unique, so my radix is 4
    public static final int radix = 4;
    // How many bits it takes to represent 4 unique codes
    public static final int bitsPerCode = 2;

    // Map to convert DNA characters to 2 bit codes
    public static int[] charMap = new int[256];  // Assuming ASCII characters

    // Hash using Horner's Method
    // Uses the polynomial rolling hash function
    public static long hornerHash(String string, int length) {
        long hash = 0;
        // Add the character values together, multiplying by the radix each time to shift the previous value
        for (int index = 0; index < length; index++) {
            // In other cases, we would mod by a large prime number here to stop
            // In this specific case with a radix of 4 and a max STR length of 20 bits (2 * 10 characters),
            // The large prime isn't necessary and would slow down the algorithm

            // This is the same as multiplying by the radix, as it's a power of 2.
            hash = hash << bitsPerCode;
            // Adds the new character to the hash using bitwise or
            // This works, as the previous operaton ensures that the lowest bitsPerCode bits are 0
            // In this case, there will be no carrying with addition, which makes it the same as bitwise or
            hash = hash | charMap[string.charAt(index)];
        }
        return hash;
    }

    // Uses the Rabin-Karp algorithm to find the highest number of consecutive STR repeats in sequence
    // Checks each substring of equal length to STR in the sequence
    public static int rabinKarp(String sequence, String STR) {
        int strLength = STR.length();
        int seqLength = sequence.length();

        // Get a mask of all ones corresponding to each bit in the hash (with length 2 * strLength)
        int numBits = strLength * 2;
        long mask = (1L << numBits) - 1;

        // Hash of the STR
        long strHash = hornerHash(STR, strLength);

        // Compute the hash for the first substring in the sequence
        long seqHash = hornerHash(sequence.substring(0, strLength), strLength);

        // Count the number of consecutive STR repeats
        int count = 0;
        int maxCount = 0;

        int index = 0;
        // While there is still at least one substring left to check
        while (index <= seqLength - strLength) {
            // Check if the hashes are equal
            if (seqHash == strHash) {
                // Increment counts and index accordingly
                count++;
                index += strLength;

                if (count > maxCount) {
                    maxCount = count;
                }

                // Compute the next hash, if the new substring is still within the bounds of sequence
                if (index <= seqLength - strLength) {
                    // Since all characters change, we need to rehash here and can't shift it
                    seqHash = hornerHash(sequence.substring(index, index + strLength), strLength);
                }
            } else {
                // Reset the count, as the sequence of repeats has stopped (or never started)
                count = 0;

                // Another check in case index is out of bounds
                if (index < seqLength - strLength) {
                    int nextChar = charMap[sequence.charAt(index + strLength)];
                    // Left shift by 2 bits to remove the first character
                    // Same as subtracting in Rabin-Karp
                    seqHash = seqHash << bitsPerCode;
                    // Add the new character using bitwise or for speed, as we know there will be no carrying
                    seqHash = seqHash | nextChar;
                    // Mask to actually remove the highest 2 bits so that the hash is the correct length
                    seqHash = seqHash & mask;
                }
                index++;
            }
        }

        return maxCount;
    }

    public static int STRCount(String sequence, String STR) {
        // Map bases to codes
        charMap['A'] = 0;
        charMap['a'] = 0;
        charMap['C'] = 1;
        charMap['G'] = 2;
        charMap['T'] = 3;

        // Return the result of the Rabin-Karp algorithm
        return rabinKarp(sequence, STR);
    }
}
