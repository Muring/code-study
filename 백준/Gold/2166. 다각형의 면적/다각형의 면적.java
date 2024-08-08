import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int[][] vertexArr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int vertexCnt = Integer.parseInt(br.readLine());
        vertexArr = new int[vertexCnt + 1][2];
        for (int vertex = 0; vertex < vertexCnt; vertex++) {
            st = new StringTokenizer(br.readLine());
            vertexArr[vertex][0] = Integer.parseInt(st.nextToken());
            vertexArr[vertex][1] = Integer.parseInt(st.nextToken());
        }

        vertexArr[vertexCnt][0] = vertexArr[0][0];
        vertexArr[vertexCnt][1] = vertexArr[0][1];

        long sum = 0;
        for (int idx = 0; idx < vertexCnt; idx++) {
            sum += (long) vertexArr[idx][0] * vertexArr[idx + 1][1] - (long) vertexArr[idx + 1][0] * vertexArr[idx][1];
        }
        sum = Math.abs(sum);
        System.out.printf("%.1f", 1d * sum / 2);
    }
}