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

        while (true) {
            String input = br.readLine();
            Stack<Character> stack = new Stack<>();
            if (input.equals(".")) break;
            for (int idx = 0; idx < input.length(); idx++) {
                char currentChar = input.charAt(idx);

                if (currentChar == '(' || currentChar == '[') stack.add(currentChar);
                else if (currentChar == ')') {
                    if (stack.empty()) stack.add(currentChar);
                    else if (stack.peek() == '(') stack.pop();
                    else stack.add(currentChar);
                } else if (currentChar == ']') {
                    if (stack.empty()) stack.add(currentChar);
                    else if (stack.peek() == '[') stack.pop();
                    else stack.add(currentChar);
                }
            }
            if (stack.empty()) sb.append("yes");
            else sb.append("no");
            sb.append("\n");
        }
        System.out.println(sb);

    }
}