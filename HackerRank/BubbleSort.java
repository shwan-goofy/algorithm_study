import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countSwaps' function below.
     *
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static void countSwaps(List<Integer> a) {
    // Write your code here
        int count = 0;
        
        for (int i = a.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                count += swap(j, j + 1, a);
            }
        }
        
        System.out.println("Array is sorted in " + count + " swaps.");
        System.out.println("First Element: " + a.get(0));
        System.out.println("Last Element: " + a.get(a.size() - 1));
    }
    
    private static int swap(int index_a, int index_b, List<Integer> list) {
        if (list.get(index_a) <= list.get(index_b)) return 0;
        Integer tmp = list.get(index_a);
        list.set(index_a, list.get(index_b));
        list.set(index_b, tmp);
        return 1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.countSwaps(a);

        bufferedReader.close();
    }
}
