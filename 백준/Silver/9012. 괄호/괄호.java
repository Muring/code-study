import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            Stack<Character> stack = new Stack<>();
            String input = br.readLine();
            for (int stringIdx = 0; stringIdx < input.length(); stringIdx++) {
                char currentThesis = input.charAt(stringIdx);
                if (stack.empty()) stack.add(currentThesis);
                else if (stack.peek() == '(' && currentThesis == ')') stack.pop();
                else stack.add(currentThesis);
            }

            if (stack.empty()) sb.append("YES");
            else sb.append("NO");

            sb.append("\n");
        }
        System.out.println(sb);

    }
}