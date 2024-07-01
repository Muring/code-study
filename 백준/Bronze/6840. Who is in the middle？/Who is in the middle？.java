import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[3];
        for (int idx = 0; idx < 3; idx++) {
            arr[idx] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        System.out.println(arr[1]);
    }
}
