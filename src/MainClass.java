/* Гурьевских В.Г.
(Task1) Решить задачу о нахождении длины максимальной последовательности с помощью матрицы.

(Task2) Количество маршрутов с препятствиями. Реализовать чтение массива с препятствием и нахождение количество маршрутов.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {

        //Нахождение длины максимальной последовательности с помощью матрицы.
//        try {
//            Task1();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //чтение массива с препятствием и нахождение количество маршрутов.
        try {
            Task2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //чтение массива с препятствием и нахождение количество маршрутов.
    private static void Task2() throws IOException {
        int[][] arr = new int[][]{{3, 3, 3, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 0}};
//        int[][] arr = new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        int j = 0;
        do {
            System.out.println("Input player coordinats through comma (0 - 3): ");
            String s1 = br.readLine();
            String[] p = s1.split(",");

            i = Integer.parseInt(p[0]);
            j = Integer.parseInt(p[1]);
        } while (arr[i][j] != 1);
        arr[i][j] = 5;

        int x = 0;
        int y = 0;
        do {
            System.out.println("Input exit coordinats through comma (0 - 3): ");
            String s1 = br.readLine();
            String[] p = s1.split(",");

            x = Integer.parseInt(p[0]);
            y = Integer.parseInt(p[1]);
        } while (arr[x][y] != 1);
        arr[x][y] = 9;

        int count = 0;
        count = findExitFromMap(arr, i, j, x, y);
        System.out.println(count);

    }

    //чтение массива с препятствием и нахождение количество маршрутов.
    private static int findExitFromMap(int[][] arr, int i, int j, int iEnd, int jEnd) {

        int[][] path = new int[arr.length][arr[0].length];

        for (int l = 0; l < arr.length; l++) {
            if (arr[l][j] != 1 && arr[l][j] != 9 && arr[l][j] != 5) continue;
            path[l][j] = 1;
        }

        for (int k = 0; k < arr[0].length; k++) {
            if (arr[i][k] != 1) continue;
            if (arr[i][k] != 1 && arr[i][k] != 9 && arr[i][k] != 5) continue;
            path[i][k] = 1;
        }

        for (int l = i + 1; l < arr.length; l++) {
            for (int k = j + 1; k < arr[0].length; k++){
                if (arr[l][k] == 0) continue;
                path[l][k] = path[l][k - 1] + path[l - 1][k];
            }
        }

        for (int l = i - 1; l >= 0; l--) {
            for (int k = j - 1; k >= 0; k--){
                if (arr[l][k] == 0) continue;
                path[l][k] = path[l][k + 1] + path[l + 1][k];
            }
        }

        for (int l = i - 1; l >= 0; l--) {
            for (int k = j + 1; k < arr[0].length; k++){
                if (arr[l][k] == 0) continue;
                path[l][k] = path[l][k - 1] + path[l + 1][k];
            }
        }

        for (int l = i + 1; l < arr.length; l++) {
            for (int k = j - 1; k >= 0; k--){
                if (arr[l][k] == 0) continue;
                path[l][k] = path[l][k + 1] + path[l - 1][k];
            }
        }

        return path[iEnd][jEnd];
    }

    //Нахождение длины максимальной последовательности с помощью матрицы.
    private static void Task1() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input first string: ");
        String s1 = br.readLine();
        System.out.println("Input second string: ");
        String s2 = br.readLine();
        br.close();
        ArrayList<Character> ss = new ArrayList<>();
        if (s1.length() < s2.length()) {
            ss = getSubsequence(s1, s2, ss);
        } else {
            ss = getSubsequence(s2, s1, ss);
        }
        if (ss.isEmpty()) {
            System.out.println("Subsequence length: " + ss.size());
        } else {
            System.out.println("Subsequence length: " + ss.size() + ", subsequence: " + ss.toString());
        }
    }

    private static ArrayList<Character> getSubsequence(String s1, String s2, ArrayList<Character> ss) {

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        int count = 0;
        int index = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = index; j < s2.length(); j++) {
                if (arr1[i] == arr2[j]) {
                    index = j + 1;
                    ss.add(arr1[i]);
                    count++;
                    break;
                }
            }
            if (index == 0) break;
        }
        return ss;
    }
}
