/* Гурьевских В.Г.
(Task1) Решить задачу о нахождении длины максимальной последовательности с помощью матрицы.
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
