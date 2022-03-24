import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import static java.lang.Character.getNumericValue;

public class Airports {



    public static void main(String[] args) throws IOException, URISyntaxException {

        int column;  //по умолчанию 2

           try {column = Integer.parseInt (args[0]);
               if(column < 1 | column > 14){column = 2;
               System.out.println("Неверный формат записи. Значение по умолчанию - 2");}
           }catch (ArrayIndexOutOfBoundsException e){
               column = 2;
           }catch (NumberFormatException e){
               System.out.println("Неверный формат записи. Значение по умолчанию - 2");
               column = 2;
           }
           
        String[] mass;
        mass = Reader.read(column); // отсортированный массив
        int numberCounter, indexCounter, check, number;
        boolean index = true;
        boolean index2;
        String[] stringIndex;

        do {
            System.gc();
            indexCounter = 0;// счетчик найденных строк
            check = 0; // переменная для обхода кавычек, если первый символ ", то прибавляем 1
            System.out.println("Введите строку:");
            Scanner console = new Scanner(System.in);
            String searchValue = console.nextLine();
            long timer = System.currentTimeMillis(); // засекаем время
            number = searchValue.length();

            if(searchValue.length() == 0){
                System.out.println("Отсутствуют введенные данные, попробуйте снова" + "\n");
                continue;
            }
            if (mass[0].charAt(0) == '"') check = 1; // можно вставить в цикл чтобы проверять каждую строку

            for (String mass1 : mass) { // листаем отсортированный столбец

                numberCounter = 0; //если  numberCounter равен num, все сравниваемые символы равны

                for (int i2 = 0; i2 < number; i2++) { //листаем символы

                    if (getNumericValue(searchValue.charAt(i2)) == getNumericValue(mass1.charAt(i2 + check))) { // Сравниваем через  getNumericValue чтобы проигнорировать регистр
                        numberCounter++;
                    } else {
                        break;
                    }

                }

                if (numberCounter == number) { //если  numberCounter равен number, нужная строка найдена
                    stringIndex = mass1.split(",");
                    indexCounter++;

                    BufferedReader r = new BufferedReader(new FileReader("airports.dat"));
                    for (int i8 = 0; i8 < Integer.parseInt(stringIndex[1]); i8++) { //Пропускаются строки до нужной
                        r.readLine();
                    }
                    System.out.println(r.readLine()); // выводится найденная строка

                }
            }


            if (indexCounter == 0) {
                System.out.println("Ничего не найдено");
            }
            System.out.println("Количество найденных строк: " + indexCounter);
            System.out.println("Время выполнения: " + (double) (System.currentTimeMillis() - timer));
            System.gc();
            do{
                index2 = true;
                System.out.println("Продолжить выполнение?");
                System.out.println("Y/N");
                Scanner console2 = new Scanner(System.in);
                String id2 = console2.nextLine();
                switch (id2){
                    case "Y":
                    case "y":
                        index2 = false;
                        break;
                    case "N":
                    case "n":
                        index2 = false;
                        index = false;
                        break;
                    default:
                        System.out.println("Неверный ввод");
                        break;

                }
            } while(index2);

        }while (index);
    }
}

