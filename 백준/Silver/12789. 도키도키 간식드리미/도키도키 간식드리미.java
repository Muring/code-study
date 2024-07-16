import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());    // 전체 인원 수
        st = new StringTokenizer(br.readLine());
        int targetIdx = 1;    // 번호
        Stack<Integer> stack = new Stack<>();

        // 숫자 뽑기 시작
        while (st.hasMoreTokens()) {
            if (!stack.empty() && stack.peek() == targetIdx) {
                stack.pop();
                targetIdx++;
            } else {
                int currentTarget = Integer.parseInt(st.nextToken());
                if (currentTarget == targetIdx) targetIdx++;
                else stack.add(currentTarget);
            }
        }

        // 숫자는 다 뽑았는데 스택이 남아 있을 경우
        if (!stack.empty() && targetIdx != count + 1) {
            while (!stack.empty()) {
                if (stack.pop() != targetIdx) break;
                targetIdx++;
            }
        }

        if (targetIdx == count + 1) System.out.println("Nice");
        else System.out.println("Sad");
    }
}