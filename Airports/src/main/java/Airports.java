import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        int[] IdMass = new int[(int)Files.lines(Paths.get("airports.dat")).count()];

        int iCount, i, ii;
        boolean ind = true;
        boolean ind2;
        boolean ind3;
        boolean ind4;
        int length = Mass.length;

        String[] sInd;


        do {
ind3 = true;
ind4 = true;
            i = 0;
            ii = 0; // если присутствуют кавычки прибавляем 1
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


            int cursor = length/2;
            int cursor2 = length/4;


            for (int i1 = length - 1; i1 > 0; i1 = i1/2) { // листаем массив

                iCount = 0;

                for (int i2 = 0; i2 < Num; i2++) { //листаем буквы

                    if (getNumericValue(id.charAt(i2)) == getNumericValue(Mass[cursor].charAt(i2 + ii))) {

                        iCount++;
                    } else {
                        break;
                    }


                }

                if (iCount == Num) {
                    i++;
                  break;


                }else if (getNumericValue(id.charAt(iCount)) < getNumericValue(Mass[cursor].charAt(iCount + ii))){

                    cursor = cursor - cursor2;
                    cursor2 = cursor2/2;


                }else if (getNumericValue(id.charAt(iCount)) > getNumericValue(Mass[cursor].charAt(iCount + ii))){
                    cursor = cursor + cursor2;
                    cursor2 = cursor2/2;

                }

            }


            if (i == 0) {System.out.println("Ничего не найдено");
            }else {

                do {
                    iCount = 0;

                    for (int i4 = 0; i4 < Num; i4++) { //листаем буквы
                        if(cursor == 0){
                            ind3 = false;
                            break;
                        }
                        else if (getNumericValue(id.charAt(i4)) == getNumericValue(Mass[cursor-1].charAt(i4 + ii))) {
                            iCount++;
                        } else {

                            ind3 = false;
                            break;
                        }
                    }
                    if (iCount == Num){
                        i++;
                    cursor--;
                    }

                }while(ind3);
                do {
                    iCount = 0;
                    for (int i6 = 0; i6 < Num; i6++) { //листаем буквы
                        if (getNumericValue(id.charAt(i6)) == getNumericValue(Mass[cursor+i].charAt(i6 + ii))) {
                            iCount++;
                        } else {
                            ind4 = false;
                            break;
                        }
                    }
                    if (iCount == Num)i++;
                }while (ind4);

                for (int i7 = 0; i7 < i; i7++) {
                    sInd = Mass[cursor].split(",");
                    IdMass[i7] = Integer.parseInt(sInd[1]);
                    BufferedReader r = new BufferedReader(new FileReader("airports.dat"));


                    for (int i8 = 0; i8 < IdMass[i7]; i8++) {
                        r.readLine();
                    }

                    System.out.println(r.readLine());
                    cursor++;
                }


                System.out.println("Количество найденных строк: " + i);
            }
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

