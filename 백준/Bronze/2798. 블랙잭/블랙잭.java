import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 브루트포스 문제이지만 좀 더 효율적으로 풀기 위해 조합을 사용하여 풀었다.
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int totalCardCnt; // 전체 카드 수
    static int targetNum;   // 목표 수
    static int[] cardList;  // N 만큼의 카드 저장 배열
    static int maxSum;      // 답; 조합 가능한 최대 수
    static int[] arr;       // 조합한 카드 배열
    static final int CARD_LIMIT = 3;    // 조합 가능한 카드 수

    /**
     * 조합 생성 메소드
     *
     * @param arr   조합된 카드를 저장할 배열
     * @param start 현재 조합 배열 인덱스
     * @param count 현재 조합의 길이
     */
    private static void comb(int[] arr, int start, int count) {
        // 기저조건
        if (count == CARD_LIMIT) {
            int sum = Arrays.stream(arr).sum();
            if (sum <= targetNum && maxSum < sum) maxSum = sum;

            return;
        }

        // 조합 구하기
        for (int idx = start; idx < cardList.length; idx++) {
            arr[count] = cardList[idx];
            comb(arr, idx + 1, count + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // N과 M 입력
        st = new StringTokenizer(br.readLine());
        totalCardCnt = Integer.parseInt(st.nextToken());
        targetNum = Integer.parseInt(st.nextToken());

        // N만큼 카드 저장
        cardList = new int[totalCardCnt];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < totalCardCnt; idx++) {
            cardList[idx] = Integer.parseInt(st.nextToken());
        }

        //  M을 넘지 않으면서 M에 최대한 가까운 카드 3장의 합을 구하기
        maxSum = 0;
        arr = new int[CARD_LIMIT];
        comb(arr, 0, 0);
        System.out.println(maxSum);
    }
}