import com.shpp.cs.a.console.TextProgram;

public class AlgorismAlgorithms extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        String sum = "";
        int resultLength;//in fact not length of final string, but the number of iterations
        int x1, x2, x1x2, buffer = 0;

        //length of n1 have to be <= length of n2
        if(n1.length()<n2.length()){
            String t1=n2;
            n2=n1;
            n1=t1;
        }


        //difference between length of n1 and n2
        int sizeDif = n1.length() - n2.length();

        //calculating length of final string
        if (n1.length() < n2.length())
            resultLength = n2.length();
        else
            resultLength = n1.length();

        int j;
        for (int i = resultLength - 1; i >= 0; i--) {
            j = i - sizeDif; //index of n2 array

            x1 = fromCharToInt(n1.charAt(i));
            if (j < 0)
                x2 = 0;
            else
                x2 = fromCharToInt(n2.charAt(j));

            x1x2 = x1 + x2 + buffer;
            buffer = (int) x1x2 / 10;
            x1x2 = (int) x1x2 % 10;
            sum = sum + fromIntToChar(x1x2);
            if (i == 0 && buffer != 0)
                sum=sum+buffer;
        }

        sum = reverse(sum);
        return sum;
    }

    private String reverse(String sum) {
        String newSum = "";
        for (int i = sum.length() - 1; i >= 0; i--)
            newSum = newSum + sum.charAt(i);
        return newSum;
    }

    private int fromCharToInt(char c) {
        int value = c - '0';
        return value;
    }

    private char fromIntToChar(int i) {
        char value = (char) (i + '0');
        return value;
    }
}