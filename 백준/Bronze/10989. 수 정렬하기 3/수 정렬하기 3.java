import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static void slowVer() throws IOException {
        int laps = Integer.parseInt(br.readLine());
        int[] nums = new int[laps];

        for (int idx = 0; idx < laps; idx++) {
            nums[idx] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        for (int idx = 0; idx < laps; idx++) {
            sb.append(nums[idx]).append("\n");
        }
        System.out.println(sb);
    }

    // 10000보다 작거나 같은 자연수만 입력 받기 때문에 10000 범위의 배열을 만들고
    // 해당 배열에 입력 받는 수를 인덱스로 입력 받은 갯수를 세어 순서대로 출력한다.
    // 이러면 정렬할 필요가 없기 때문에 입력이 많을 때 더 빨라진다.
    private static void fastVer() throws IOException {
        int laps = Integer.parseInt(br.readLine());
        int[] count = new int[10001];

        for (int idx = 0; idx < laps; idx++) {
            count[Integer.parseInt(br.readLine())]++;
        }

        for (int idx = 0; idx < count.length; idx++) {
            while (count[idx] > 0) {
                sb.append(idx).append("\n");
                count[idx]--;
            }
        }
        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

//        slowVer();
        fastVer();
    }
}
