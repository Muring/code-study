import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    // 계수 저장 메서드
    private static void coefficient(StringTokenizer st, int[] arr) {
        for (int idx = 0; idx < 3; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
    }

    // 방정식 값이 0인지 확인하는 메서드
    private static boolean runEquation(int[] arr, int x, int y) {
        return arr[0] * x + arr[1] * y - arr[2] == 0;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr1 = new int[3];
        int[] arr2 = new int[3];

        // a, b, c, d, e, f 입력
        st = new StringTokenizer(br.readLine());
        coefficient(st, arr1);
        coefficient(st, arr2);

        // 이차 방정식 실행
        for (int xIndex = -999; xIndex < 1000; xIndex++) {
            for (int yIndex = -999; yIndex < 1000; yIndex++) {
                if (runEquation(arr1, xIndex, yIndex) && runEquation(arr2, xIndex, yIndex)) {
                    System.out.println(xIndex + " " + yIndex);
                    return;
                }
            }
        }
    }
}
