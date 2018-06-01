package gorodilov;

       /*Домашнее задание
        1 Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в
        методичку;
        2 Переделать проверку победы, чтобы она не была реализована просто набором условий,
        например, с использованием циклов.
        3 * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и
        количества фишек 4. Очень желательно не делать это просто набором условий для каждой из
        возможных ситуаций;
        4 *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.*/


import java . util . Random ;
import java . util . Scanner ;

public class Main {
    public static int SIZE = 4;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }







public static boolean checkWin(char symb) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++)
        {
            if (checkDiagonal(symb) || checkLines(symb)) return true;
        }
    }
    return false;
}

  public static boolean checkLines ( char symb)
    {
        boolean x, y;
        for (int i = 0; i < SIZE; i++) {
            x = true;
            y = true;
            for (int j = 0; j < SIZE; j++) {
                x = x&(map[i][j] == symb);
                y = y&(map[j][i] == symb);
            }
            if (x || y) return true;
        }

        return false;
    }




    public static boolean checkDiagonal ( char symb)
    {
        boolean rightD, leftD;
        rightD = true;
        leftD = true;
        for (int i = 0; i < SIZE; i++) {
            rightD = rightD&(map[i][i] == symb);
            leftD = leftD&(map[SIZE - i - 1][i] == symb);
        }

        if (rightD || leftD) return true;

        return false;
    }



    public static boolean isMapFull ()
        {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) return false;
                }
            }
            return true;
        }
        public static void aiTurn () {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
        }
        public static void humanTurn () {
            int x, y;
            do {
                System.out.println("Введите координаты в формате X Y");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
            map[y][x] = DOT_X;
        }
        public static boolean isCellValid ( int x, int y ){
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (map[y][x] == DOT_EMPTY) return true;
            return false;
        }
        public static void initMap () {
            map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        public static void printMap () {
            for (int i = 0; i <= SIZE; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

