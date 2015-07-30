
import com.shpp.cs.a.console.TextProgram;

public class SyllableCounting extends TextProgram {

    private char[] lettersArray = {'a', 'e', 'y', 'u', 'i', 'o'};

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.  
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int syllablesCounter = 0;
        char currentChar;
        boolean flag = false; //flag for checking are there 2 vowel letters in a row

        for (int i = 0; i < word.length(); i++) {
            word = word.toLowerCase();
            currentChar = word.charAt(i);
            //if current letter is 'e' in the end of the word - we don't count it
            if (i == (word.length() - 1) && currentChar == 'e')
                break;

            //cheking are there 2 vowel letters in a row
            if (flag) {
                flag = false;
            } else {
                for (char x : lettersArray) {
                    if (currentChar == x) {
                        syllablesCounter++;
                        flag = true;
                    }
                }
            }

        }

        if (syllablesCounter == 0)
            syllablesCounter++;
        return syllablesCounter;
    }
}