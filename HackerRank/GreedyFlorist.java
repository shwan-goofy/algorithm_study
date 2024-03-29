import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int k, int[] c) {
        int res = 0;
        Integer[] cost = boxingParameters(c);
        sortIntegerArray(cost);
        
 
        for (int i = 0 ; i < cost.length; i++) {
            int stage = i / k;
            res += calcStage(stage, i, cost);
        }
        
        return res;
    }
    
    private static Integer[] boxingParameters(int[] arr) {
        return Arrays.stream(arr)
                    .boxed()
                    .toArray(Integer[]::new);

    }
    private static void sortIntegerArray(Integer[] cost) {
        Arrays.sort(cost, Collections.reverseOrder());
    }
    
    private static int calcStage(int stage, int nowIndex, Integer[] cost) {
        return (stage + 1) * cost[nowIndex];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
