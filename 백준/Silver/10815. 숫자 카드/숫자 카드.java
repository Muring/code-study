import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static final int IV = 10000000; // 10,000,000 상수 독립 변수

    /**
     * 카드 배열 등록 메서드
     *
     * @param arr    저장할 배열
     * @param status 카드 보유 여부 확인 배열
     * @param count  등록될 카드의 수
     * @throws IOException 버퍼드 리더 예외 처리
     */
    private static void enList(int[] arr, int[] status, int count) throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < count; idx++) {
            int num = Integer.parseInt(st.nextToken());
            arr[idx] = num; // 개별 배열에 입력 값 저장
            status[num + IV]++; // 전체 상태 배열에 해당 수 보유 여부 체크
        }
    }

    private static void Solution() throws IOException {
        // -10,000,000 <= count <= 10,000,000이기 때문에 해당 크기만큼 배열 생성
        int[] status = new int[2 * IV + 1];

        // 내 카드 등록
        int myCardCount = Integer.parseInt(br.readLine());
        int[] myCards = new int[myCardCount];
        enList(myCards, status, myCardCount);

        // 덱 카드 등록
        int deckCount = Integer.parseInt(br.readLine());
        int[] deckCards = new int[deckCount];
        enList(deckCards, status, deckCount);

        for (int idx = 0; idx < deckCount; idx++) {
            // 내가 가지고 있고, 덱에도 있다면 카운트가 2가 되어 있을 것이기 때문에 2일 때 결과 저장
            if (status[deckCards[idx] + IV] == 2)
                sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        Solution();
    }
}

