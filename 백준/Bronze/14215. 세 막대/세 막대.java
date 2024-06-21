import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    private static int getMaxPerimeter(int[] length) {
        int AB = length[0] + length[1];
        int C = length[2];
        if (AB <= C)
            return AB + (AB - 1);
        else
            return AB + C;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int[] length = new int[3];
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 3; idx++)
            length[idx] = Integer.parseInt(st.nextToken());

        Arrays.sort(length);
        answer = getMaxPerimeter(length);
        System.out.println(answer);

    }
}
