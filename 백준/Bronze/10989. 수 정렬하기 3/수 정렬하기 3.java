import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        int[] nums = new int[laps];

        for (int idx = 0; idx < laps; idx++) {
            nums[idx] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        for (int idx = 0; idx < laps; idx++) {
            sb.append(nums[idx]).append("\n");
        }
        System.out.println(sb);
    }
}
