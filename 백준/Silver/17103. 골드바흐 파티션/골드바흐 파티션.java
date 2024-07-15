import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 17103. 골드바흐 파티션
 * <p>
 * 단순 반복문으로 소수를 찾으면 시간초과가 난다.
 * 소수 DP배열을 만들어 시간 소모를 줄인다.
 *
 * @Author MuRing
 */
public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static boolean[] primeArray = new boolean[1000001]; // 소수 저장 배열

    // 초기 소수 상태 확인 메서드
    private static void setPrime() {
        // false이면 소수, true이면 비소수
        primeArray[0] = primeArray[1] = true;
        for (int idx = 2; idx < primeArray.length; idx++) {
            // 만약 해당 수가 소수이면,
            if (!primeArray[idx]) {
                // 해당 수의 모든 배수를 전부 소수가 아니라고 체크한다.
                for (int jdx = 2; idx * jdx < primeArray.length; jdx++)
                    primeArray[idx * jdx] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 소수 세팅
        setPrime();

        // 입력
        int lpas = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < lpas; idx++) {
            int input = Integer.parseInt(br.readLine());
            int partitionCount = 0;
            // 골드바흐 파티션은 짝수의 경우에만 해당되기 때문에 2의 배수인지 아닌지 확인
            if (input % 2 == 0 && input != 0) {
                for (int a = 2; a <= input / 2; a++) {
                    if (!primeArray[a] && !primeArray[input - a]) partitionCount++;
                }
            }
            sb.append(partitionCount).append("\n");
        }
        System.out.println(sb);
    }
}