import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 자료구조 생성
        int structureCount = Integer.parseInt(br.readLine());
        Deque<Integer> que = new ArrayDeque<>();

        // 자료구조 타입 저장
        int[] type = new int[structureCount];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < structureCount; idx++)
            type[idx] = Integer.parseInt(st.nextToken());

        // 사실상 스택은 그저 거쳐가기 때문에 고려하지 않고 큐인 경우에만 Deque에 값 저장
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < structureCount; idx++) {
            int num = Integer.parseInt(st.nextToken());
            if (type[idx] == 0) que.offer(num);
        }

        // 수열 생성 후, 큐를 한 칸씩 뒤로 민다.
        int seqCount = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < seqCount; idx++) {
            que.offerFirst(Integer.parseInt(st.nextToken()));
            sb.append(que.pollLast()).append(" ");
        }

        System.out.println(sb);
    }
}