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
        try {
            Task1();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //чтение массива с препятствием и нахождение количество маршрутов.
        try {
            Task2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //чтение массива с препятствием и нахождение количество маршрутов.
    private static void Task2() throws IOException {
        int[][] arr = new int[][]{{3, 3, 3, 1}, {1, 1, 1, 1}, {-1, 1, 1, 1}, {-1, 1, 1, -1}};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        int j = 0;
        do {
            System.out.println("Input player coordinats through comma (0 - 3): ");
            String s1 = br.readLine();
            char[] p = s1.toCharArray();
            i = Integer.parseInt(p[0] + "");
            j = Integer.parseInt(p[2] + "");
        } while (arr[i][j] != 1);
        arr[i][j] = 5;

        int x = 0;
        int y = 0;
        do {
            System.out.println("Input exit coordinats through comma (0 - 3): ");
            String s1 = br.readLine();
            char[] p = s1.toCharArray();
            x = Integer.parseInt(p[0] + "");
            y = Integer.parseInt(p[2] + "");
        } while (arr[x][y] != 1);
        arr[x][y] = 9;

        ArrayList<int[]> path = new ArrayList<>();
        ArrayList<int[]> pathBad = new ArrayList<>();

        int count = 0;
        count = findExitFromMap(arr, path, pathBad, i, j, count);
        System.out.println(count);

    }

    private static int findExitFromMap(int[][] arr, ArrayList<int[]> pathBad, ArrayList<int[]> path, int i, int j, int count) {

        int[] nextCoord = tryGoToExit(arr, i, j);

        if(nextCoord == new int[]{-1, -1} || pathBad.contains(nextCoord)) {
            pathBad.add(path.get(path.size() - 1));
            path.remove(path.size() - 1);
            int[] z = path.get(path.size() - 1);
            nextCoord = tryGoToExit(arr, z[0], z[1]);
        }
        if (nextCoord != new int[]{-1, -1}) {
            path.add(nextCoord);
            count += findExitFromMap(arr, path, pathBad, nextCoord[0], nextCoord[1], count);
        }
        return count;
    }

    private static int[] tryGoToExit(int[][] arr, int i, int j) {
        int[] nextCoord = findNextCoordinate(i + 1, j + 1, arr);
        if (nextCoord == new int[]{-1, -1}) {
            nextCoord = findNextCoordinate(i + 1, j - 1, arr);
            if (nextCoord == new int[]{-1, -1}) {
                nextCoord = findNextCoordinate(i - 1, j - 1, arr);
                if (nextCoord == new int[]{-1, -1}) {
                    nextCoord = findNextCoordinate(i - 1, j + 1, arr);
                    if (nextCoord == new int[]{-1, -1}) {
                        return nextCoord;
                    }
                }
            }
        }
        return nextCoord;
    }

    private static int[] findNextCoordinate(int i, int j, int[][] arr) {
        int[] arrNext = new int[2];
        if (arr[i][j] == 1) {
            arrNext[0] = i;
            arrNext[1] = j;
        } else {
            arrNext[0] = -1;
            arrNext[1] = -1;
        }
        return arrNext;
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
