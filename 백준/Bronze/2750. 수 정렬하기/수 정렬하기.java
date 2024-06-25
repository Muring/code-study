import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        
        for (int idx = 0; idx < size; idx++) {
            int num = Integer.parseInt(br.readLine());
            arr[idx] = num;
        }

        Arrays.sort(arr);
        for (int num : arr) {
            System.out.println(num);
        }

    }
}