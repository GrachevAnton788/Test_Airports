import java.io.*;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Reader {// для сортировки столбца при запуске + поиск
    public static String[] read(int column) throws IOException, URISyntaxException {
        column--;  //номер колонки уменьшается на 1, учитывая что индексы начинаются с 0
        FileInputStream file = new FileInputStream("airports.dat");
        BufferedReader BReader;
        BReader = new BufferedReader(new InputStreamReader(file));

        String line; // строка
        String[] Col; //колонка
        Col = new String[(int) Files.lines(Paths.get("airports.dat")).count()]; // массив выбранной колонки


        int i1 = 0;
        while((line = BReader.readLine())!=null) { //запечатываем в массив, оставляем индекс для нахождения строки в исходном файле

                String[] arr = line.split(",");
                Col[i1] = arr[column] + "," + i1;
                i1++;


        }

        Arrays.sort(Col);


       return Col;
    }
}
