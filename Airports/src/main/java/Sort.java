import java.io.*;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Sort {// для сортировки столбца при запуске + поиск
    public static String[] sort(int colNum) throws IOException, URISyntaxException {
        colNum--;
        FileInputStream file = new FileInputStream("airports.dat");
        BufferedReader BReader;
        BReader = new BufferedReader(new InputStreamReader(file));

        String line; // строка
        String[] Col;
        Col = new String[(int) Files.lines(Paths.get("airports.dat")).count()]; // массив выбранной колонки


        int i1 = 0;
        while((line = BReader.readLine())!=null) {

                String[] arr = line.split(",");
                Col[i1] = arr[colNum] + "," + i1;
                i1++;


        }

        Arrays.sort(Col);


       return Col;
    }
}
