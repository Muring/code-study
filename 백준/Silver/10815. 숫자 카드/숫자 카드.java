import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static final int IV = 10000000;

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
            arr[idx] = num;
            status[num + IV]++;
        }
    }

    private static void Solution() throws IOException {
//        int myCardCount = Integer.parseInt(br.readLine());
//        int[] myCards = new int[myCardCount];
//        enList(myCards, myCardCount);
//
//        int deckCount = Integer.parseInt(br.readLine());
//        int[] deckCards = new int[deckCount];
//        enList(deckCards, deckCount);
//
//        int[] result = new int[deckCount];
//
//        for (int myCardIdx = 0; myCardIdx < myCardCount; myCardIdx++) {
//            for (int deckCardIdx = 0; deckCardIdx < deckCount; deckCardIdx++) {
//                // 카드가 일치하는 경우
//                if (myCards[myCardIdx] == deckCards[deckCardIdx]) {
//                    result[deckCardIdx]++;
//                }
//            }
//        }
//
//        for (int num : result) {
//            sb.append(num).append(" ");
//        }

        // -10,000,000 <= count <= 10,000,000이기 때문에 해당 크기만큼 배열 생성
        int[] status = new int[2 * IV + 1];

        int myCardCount = Integer.parseInt(br.readLine());
        int[] myCards = new int[myCardCount];
        enList(myCards, status, myCardCount);

        int deckCount = Integer.parseInt(br.readLine());
        int[] deckCards = new int[deckCount];
        enList(deckCards, status, deckCount);

        for (int idx = 0; idx < deckCount; idx++) {
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

