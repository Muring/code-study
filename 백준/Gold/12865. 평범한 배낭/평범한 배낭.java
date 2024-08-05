import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int itemCnt; // 물품의 수
    static int weightLimit; // 준서가 버틸 수 있는 무게
    static int[][] dp;
    static List<Item> itemList; // 아이템 목록

    // 아이템 객체
    private static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static void print() {
        for (int[] row : dp)
            System.out.println(Arrays.toString(row));
        System.out.println();
    }


    // 배낭에 넣을 수 있는 물건들의 가치합의 최댓값 구하기
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        st = new StringTokenizer(br.readLine());
        itemCnt = Integer.parseInt(st.nextToken());
        weightLimit = Integer.parseInt(st.nextToken());
        itemList = new ArrayList<>();
        dp = new int[itemCnt + 1][weightLimit + 1];

        // 아이템 목록 입력
        for (int item = 0; item < itemCnt; item++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            itemList.add(new Item(weight, value));
        }

        // dp
        for (int currentWeightIdx = 1; currentWeightIdx <= weightLimit; currentWeightIdx++) {
            for (int itemIdx = 1; itemIdx <= itemCnt; itemIdx++) {
                if (itemList.get(itemIdx - 1).weight <= currentWeightIdx) {
                    int remainWeight = currentWeightIdx - itemList.get(itemIdx - 1).weight; // 현재 아이템의 무게를 뺀 나머지 들 수 있는 무게
                    int currentTotalValue = dp[itemIdx - 1][remainWeight] + itemList.get(itemIdx - 1).value;   // 현재 아이템의 무게를 뺀 나머지 무게의 최대값 + 현재 아이템 무게
                    int prevTotalValue = dp[itemIdx - 1][currentWeightIdx];

                    dp[itemIdx][currentWeightIdx] = Math.max(currentTotalValue, prevTotalValue);
                } else {
                    dp[itemIdx][currentWeightIdx] = dp[itemIdx - 1][currentWeightIdx];
                }
//                print();
            }
        }
        System.out.println(dp[itemCnt][weightLimit]);
    }
}