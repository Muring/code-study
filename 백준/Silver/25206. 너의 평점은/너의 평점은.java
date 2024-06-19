import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static Map<String, Double> gradeList;
    static final int TOTAL_SUBJECT = 20;

    static {
        // 성적 목록 초기화
        gradeList = new HashMap<>();
        gradeList.put("A+", 4.5);
        gradeList.put("A0", 4.0);
        gradeList.put("B+", 3.5);
        gradeList.put("B0", 3.0);
        gradeList.put("C+", 2.5);
        gradeList.put("C0", 2.0);
        gradeList.put("D+", 1.5);
        gradeList.put("D0", 1.0);
        gradeList.put("F", 0.0);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // 전공평점 = myScoreSum / maxScoreSum
        double maxScoreSum = 0;    // 전체 총 학점
        double myScoreSum = 0;  // 내 전공 과목별 합 (학점 x 과목평점)

        for(int idx = 0; idx <TOTAL_SUBJECT; idx++) {
            st = new StringTokenizer(br.readLine());
            String subject = st.nextToken();
            double score = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();

            if(!grade.equals("P")){
                // 전체 총 학점 계산
                maxScoreSum += score;
                // 내 전공 과목별 합계 계산
                myScoreSum += score * gradeList.get(grade);
            }

        }
        System.out.println(myScoreSum / maxScoreSum);
    }
}
