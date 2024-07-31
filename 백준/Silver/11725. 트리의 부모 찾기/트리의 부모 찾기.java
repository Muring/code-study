import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int nodeCnt; // 노드의 개수
    static ArrayList<Integer>[] list; // 트리를 구현할 리스트
    static int[] parentArr; // 부모 노드 저장 배열
    static boolean[] isRoot; // 부모 노드로 지정 되었는지 확인하는 배열
    static final int ROOT = 1;

    private static void init() throws IOException {
        nodeCnt = Integer.parseInt(br.readLine());
        list = new ArrayList[nodeCnt + 1];

        // 1번부터 시작되기 때문에 범위를 nodeCnt+1로 지정해야 한다.
        for (int idx = 0; idx < nodeCnt + 1; idx++)
            list[idx] = new ArrayList<>();
    }

    private static void createTree() throws IOException {
        for (int idx = 0; idx < nodeCnt - 1; idx++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }
    }

    private static void findParent(int root) {
        // 각 부모 노드에 연결되어 있는 노드만큼 반복하여 해당 노드의 부모 노드를 기록한다.
        isRoot[root] = true;
        for (int node : list[root]) {
            if (!isRoot[node]) {
                parentArr[node] = root;
                findParent(node);
            }
        }
    }

    private static void printResult() {
        // 1번 노드는 기본 부모 노드이기 때문에 2번부터 시작
        for (int idx = 2; idx < nodeCnt + 1; idx++)
            sb.append(parentArr[idx]).append("\n");
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // 초기화
        init();

        // 트리 정보 입력
        createTree();

        // 부모 노드 찾기
        parentArr = new int[nodeCnt + 1];
        isRoot = new boolean[nodeCnt + 1];
        findParent(ROOT);

        // 결과 출력
        printResult();
    }
}