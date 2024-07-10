import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static long getSum(int num) {
        if (num == 1)
            return num;
        return num + getSum(num - 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        long sum = getSum(num);
        long doubleSum = sum * sum;
        sb.append(sum).append("\n")
                .append(doubleSum).append("\n")
                .append(doubleSum);
        System.out.println(sb);
    }
}