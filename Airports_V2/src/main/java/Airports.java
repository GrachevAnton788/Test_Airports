import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import static java.lang.Character.getNumericValue;

public class Airports {



    public static void main(String[] args) throws IOException, URISyntaxException {

        int ColNum;  //по умолчанию 2

           try {ColNum = Integer.parseInt (args[0]);
               if(ColNum < 1 | ColNum > 14){ColNum = 2;
               System.out.println("Неверный формат записи. Значение по умолчанию - 2");}
           }catch (ArrayIndexOutOfBoundsException e){
               ColNum = 2;
           }catch (NumberFormatException e){
               System.out.println("Неверный формат записи. Значение по умолчанию - 2");
               ColNum = 2;
           }

        int Num; // количество символов
        String[] Mass;
        Mass = Sort.sort(ColNum); // отсортированный массив
        int iCount, i, ii;
        boolean ind = true;
        boolean ind2;


        String[] sInd;


        do {
            System.gc();
            i = 0;// счетчик найденных строк
            ii = 0; // переменная для обхода кавычек, если первый символ ", то прибавляем 1
            System.out.println("Введите строку:");
            Scanner console = new Scanner(System.in);
            String id = console.nextLine();
            long m = System.currentTimeMillis(); // засекаем время
            Num = id.length();

            if(id.length() == 0){
                System.out.println("Отсутствуют введенные данные, попробуйте снова" + "\n");
                continue;
            }
            if (Mass[0].charAt(0) == '"') ii = 1; // можно вставить в цикл чтобы проверять каждую строку

            for (String mass : Mass) { // листаем отсортированный столбец

                iCount = 0; //если  icount равен num, все сравниваемые символы равны

                for (int i2 = 0; i2 < Num; i2++) { //листаем символы

                    if (getNumericValue(id.charAt(i2)) == getNumericValue(mass.charAt(i2 + ii))) { // Сравниваем через  getNumericValue чтобы проигнорировать регистр
                        iCount++;
                    } else {
                        break;
                    }


                }

                if (iCount == Num) { //если  icount равен num, нужная строка найдена
                    sInd = mass.split(",");
                    i++;

                    BufferedReader r = new BufferedReader(new FileReader("airports.dat"));
                    for (int i8 = 0; i8 < Integer.parseInt(sInd[1]); i8++) { //Пропускаются строки до нужной
                        r.readLine();
                    }
                    System.out.println(r.readLine()); // выводится найденная строка

                }
            }


            if (i == 0) {
                System.out.println("Ничего не найдено");
            }
            System.out.println("Количество найденных строк: " + i);
            System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - m));
            System.gc();
            do{
                ind2 = true;
                System.out.println("Продолжить выполнение?");
                System.out.println("Y/N");
                Scanner console2 = new Scanner(System.in);
                String id2 = console2.nextLine();
                switch (id2){
                    case "Y":
                    case "y":
                        ind2 = false;
                        break;
                    case "N":
                    case "n":
                        ind2 = false;
                        ind = false;
                        break;
                    default:
                        System.out.println("Неверный ввод");
                        break;

                }
            } while(ind2);

        }while (ind);
    }
}

