import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) stack.pop();
            else stack.add(num);
        }

        while (!stack.empty())
            sum += stack.pop();

        System.out.println(sum);
    }
}