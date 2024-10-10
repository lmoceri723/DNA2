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

    /**
     * Plan:
     *
     * Use an approach similar to binary search
     * Keep track of the longest unsearched substring
     * Enter the string at the middle of this unsearched substring
     * capture the middle character, plus length of STR forwards and backwards
     * check for an instance of STR in this capture, if it is found then check forwards and backwards to find the total
     * amount of STR repeats
     * Keep track of this largest amount of repeats
     * exit when largest amount of repeats * STR length is greater than the remaining unsearched substrings length
     */

    public static int findSTRInstance(int middle, String sequence, String STR) {
        int matchIndex = -1;
        String buffer = sequence.substring(middle - STR.length(), middle + STR.length());
        int matchedChars = 0;
        for (int i = 0; i < buffer.length(); i++) {
            if (matchedChars == STR.length()) {
                matchIndex = middle - STR.length() + i - STR.length();
            }
            if (buffer.length() - i < STR.length()) {
                break;
            }
            if (buffer.charAt(i) != STR.charAt(0)) {
                matchedChars = 0;
                continue;
            }
            else {
                matchedChars++;
            }
        }
        return matchIndex;
    }
    public static int STRCount(String sequence, String STR) {

        int[] largestUnsearchedSubstring = {0, sequence.length()};
        int mostRepeats = 0;

        while (true) {
            int unsearchedLength = largestUnsearchedSubstring[1] - largestUnsearchedSubstring[0];
            int largestRepeatSize = mostRepeats * STR.length();

            if (largestRepeatSize > unsearchedLength) {
                break;
            }

            int repeats = 0;
            int middle = (largestUnsearchedSubstring[0] + largestUnsearchedSubstring[1]) / 2;

            int matchIndex = findSTRInstance(middle, sequence, STR);
            if (matchIndex == -1) {
                // what to do
            }

        }
    }
}
