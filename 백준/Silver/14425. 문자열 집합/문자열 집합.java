import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;


    private static void Solution(List<String> set, String[] arr) {
        int count = 0;

        for (String str : arr) {
            if (set.contains(str)) count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int baseCount = Integer.parseInt(st.nextToken());
        int checkCount = Integer.parseInt(st.nextToken());
        List<String> baseSet = new ArrayList<>();
        String[] checkArr = new String[checkCount];

        for (int idx = 0; idx < baseCount; idx++) {
            baseSet.add(br.readLine());
        }
        for (int idx = 0; idx < checkCount; idx++) {
            checkArr[idx] = br.readLine();
        }

        Solution(baseSet, checkArr);
    }
}

