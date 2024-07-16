import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            st = new StringTokenizer(br.readLine());
            int menu = Integer.parseInt(st.nextToken());

            switch (menu) {
                // 정수를 스택에 넣는다.
                case 1 -> {
                    int num = Integer.parseInt(st.nextToken());
                    stack.add(num);
                }
                // 스택에 정수가 있다면 맨 위의 정수를 빼고 출력, 없으면 -1
                case 2 -> {
                    if (!stack.empty()) sb.append(stack.pop());
                    else sb.append(-1);

                }
                // 스택에 들어있는 정수의 개수 출력
                case 3 -> {
                    sb.append(stack.size());
                }
                // 스택이 비어있으면 1, 아니면 0
                case 4 -> {
                    if (stack.empty()) sb.append(1);
                    else sb.append(0);
                }
                // 스택에 정수가 있다면 맨 위의 정수 출력, 없으면 -1
                case 5 -> {
                    if (!stack.empty()) sb.append(stack.peek());
                    else sb.append(-1);
                }
            }
            if (menu != 1) sb.append("\n");
        }
        System.out.println(sb);
    }
}