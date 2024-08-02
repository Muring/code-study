import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static boolean isSame(Map<String, Integer> basket, String[] want, int[] number) {
        for (int idx = 0; idx < number.length; idx++) {
            if (basket.get(want[idx]) != number[idx]) return false;
        }
        return true;
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        // 물건을 담을 바구니를 초기화
        Map<String, Integer> basket = new HashMap<>();
        for (String str : want)
            basket.put(str, 0);

        // 날짜 증가
        for (int startDay = 0; startDay <= discount.length - 10; startDay++) {
            basket.replaceAll((key, value) -> 0);
            for (int day = startDay; day < startDay + 10; day++) {
                if (basket.containsKey(discount[day]))
                    basket.replace(discount[day], basket.get(discount[day]) + 1);
            }
//            System.out.println(basket.values());  // 할인 시작 날짜부터 담아낸 바구니 확인
            if (isSame(basket, want, number)) answer++; // 모든 물건이 제대로 담겼는지 확인한다.
        }
        return answer;
    }
}