import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoadGame extends TextProgram {
    private static final String FILE_NAME = "en-dictionary.txt";
    private static BufferedReader br;
    private String threeLetters;
    private ArrayList words= new ArrayList();

    public void run() {
        threeLetters = readLine("enter 3 letters");

        //check are there exactly 3 letters
        if (threeLetters.length() < 3) {
            println("wrong amount of letters");
            return;
        }

        //change all letters to lower case
        threeLetters.toLowerCase();

        //read file with all words
        readFile();

        searchSuitableWords();

        //uncomment next line for search for triplets without words
       // searchForTriplets();
    }

    private void searchForTriplets() {
        for (char i='a';i<='z';i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                for (char k = 'a'; k <= 'z'; k++) {
                    if(wordsCounter(i,j,k)==0)
                        println(""+i+j+k);
                }
            }
        }

    }

    private int wordsCounter(char chi, char chj, char chk) {
        String currentWord;
        threeLetters=""+chi+chj+chk; //String.valueOf(chi+chj+chk);
        int counter=0;
        while ((currentWord = getNextWordFromFile())!= null) {
            for(int i=0;i<currentWord.length();i++){
                if (threeLetters.charAt(0)==currentWord.charAt(i)) {
                    //println("1");
                    for (int i1 = i+1; i1 < currentWord.length(); i1++) {
                        if (threeLetters.charAt(1) == currentWord.charAt(i1)) {
                            // println("2");
                            for (int i2 = i1+1; i2 < currentWord.length(); i2++) {
                                if (threeLetters.charAt(2) == currentWord.charAt(i2)) {
                                    counter++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }


    private void searchSuitableWords() {
        String currentWord = "";

        while ((currentWord = getNextWordFromFile())!= null) {
            for(int i=0;i<currentWord.length();i++){
                if (threeLetters.charAt(0)==currentWord.charAt(i)) {
                    //println("1");
                    for (int i1 = i+1; i1 < currentWord.length(); i1++) {
                        if (threeLetters.charAt(1) == currentWord.charAt(i1)) {
                            // println("2");
                            for (int i2 = i1+1; i2 < currentWord.length(); i2++) {
                                if (threeLetters.charAt(2) == currentWord.charAt(i2)) {
                                    words.add(currentWord);
                                    println(currentWord);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String getNextWordFromFile() {
        String str="";
        try {
            str = br.readLine();
        } catch (IOException e) {
            println("problem in readNextLine");
        }
        return str;
    }

    private void readFile() {
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
        } catch (FileNotFoundException e) {
            println("something wrong with file name");
        }
    }
}
 