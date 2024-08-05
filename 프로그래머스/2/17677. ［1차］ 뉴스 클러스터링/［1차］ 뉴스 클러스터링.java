import java.util.*;

public class Solution {

    // 다중집합 만들기
    private static List<String> createMultiSet(String str) {
        List<String> multiSet = new ArrayList<>();
        String match = "[a-z]{2}";    // 알파벳만 남기는 정규식

        for (int idx = 0; idx < str.length() - 1; idx++) {
            String temp = str.substring(idx, idx + 2);
            if (temp.matches(match))
                multiSet.add(temp);
        }
        return multiSet;
    }

    // 교집합 개수 구하기
    private static int getIntersectionCnt(List<String> set1, List<String> set2) {
        int count = 0;
        for (String str : set1) {
            if (set2.contains(str)) {
                count++;
                set2.remove(str);
            }
        }
        return count;
    }

    // 합집합 개수 구하기
    private static int getUnionCnt(List<String> set1, List<String> set2, int interCnt) {
        return set1.size() + set2.size();
    }

    // 자카드 유사도 구하기
    private static int getJaccardSimilarity(int interCnt, int unionCnt) {
        return unionCnt == 0 ? 65536 : (int) (interCnt * 1.0 / unionCnt * 65536);
    }

    public static int solution(String str1, String str2) {
        // 대소문자 구분을 안하기 위해서 전부 소문자로 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 다중집합 만들기
        List<String> set1 = createMultiSet(str1);
        List<String> set2 = createMultiSet(str2);

        // 교집합 개수 구하기
        int interCnt = getIntersectionCnt(set1, set2);
        // 합집합 개수 구하기
        int unionCnt = getUnionCnt(set1, set2, interCnt);

        // 자카드 유사도 구하기
        return getJaccardSimilarity(interCnt, unionCnt);
    }

}