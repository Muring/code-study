import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Balloon {
    int value;
    int index;

    public Balloon(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

//    private static void moveBalloon()

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 풍선 갯수 입력
        int balloonCount = Integer.parseInt(br.readLine());
        Deque<Balloon> que = new ArrayDeque<>();

        // 풍선 정보 입력
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < balloonCount; idx++) {
            que.offer(new Balloon(Integer.parseInt(st.nextToken()), idx + 1));
        }

        // 풍선 터뜨리기 시작
        while (!que.isEmpty()) {
            Balloon currentBalloon = que.pollFirst();
            sb.append(currentBalloon.index).append(" ");

            if (que.isEmpty()) break;

            if (currentBalloon.value > 0) {
                for (int idx = 0; idx < currentBalloon.value - 1; idx++) {
                    Balloon temp = que.pollFirst();
                    que.offerLast(temp);
                }
            } else {
                for (int idx = currentBalloon.value; idx < 0; idx++) {
                    Balloon temp = que.pollLast();
                    que.offerFirst(temp);
                }
            }
        }
        System.out.println(sb);
    }
}