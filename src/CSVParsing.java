import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParsing extends TextProgram {
    private ArrayList column;
    public ArrayList<String> rawArray;
    private static final String filename = "for-csv.txt";
    private BufferedReader br;

    public void run() {
        extractColumn(chooseTheColumnNumber());
        column.forEach(this::println);
    }

    private int chooseTheColumnNumber() {
        return readInt("Enter the column number");
    }

    private void extractColumn(int columnIndex) {
        readFile();
        getArrayOfRows();

        for (String x : rawArray) {
            getColumnArrayFromLinesArray(x, columnIndex);
        }
    }

    private void getColumnArrayFromLinesArray(String x, int columnIndex) {
        int temp = findStartIndex(x, columnIndex);
        column.add(x.substring(temp, findEndIndex(x, columnIndex, temp)));
    }

    private int findEndIndex(String x, int columnIndex, int index) {
        int stringLength=x.length();
        while (true) {
            index++;
            // Dish,Origin

            //is it the end of the string?
            if ((index - 1) == stringLength){
                index--;
                break;
            }

            if(x.charAt(index)==','&&x.charAt(index-1)!=' '&&(x.charAt(index+1)!=' '||(x.charAt(index+1)!=0))){
                if(x.charAt(index-1)!='"')
                    index--;
                break;
            }
        }

        return index;
    }

    private int findStartIndex(String x, int columnIndex) {

        int index = 0;
        // column 3
        //Dish,Origin,Dish,Origin
        if (columnIndex == 0) {
            println("start index: ~0");
            return 0;
        }
        for (int i = 0; i < columnIndex; i++) {
            while (x.charAt(index) != ',' && (x.charAt(index + 1) != ' ' || x.charAt(index + 1) != '"'))
                index++;
            if (x.charAt(index) == ',')
                index++;
            if (x.charAt(index) == '"')
                index++;
        }
        println("start index:" + index);
        return index;
    }

    private void getArrayOfRows() {
        rawArray = new ArrayList<String>();
        try {
            String line;
            while ((line = br.readLine()) != null)
                rawArray.add(line);

        } catch (IOException e) {
            println("IO Exception");
        }
    }


    private void readFile() {
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            println("something wrong with file name");
        }
    }
}