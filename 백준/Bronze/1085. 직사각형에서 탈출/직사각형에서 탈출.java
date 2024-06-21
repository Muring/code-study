import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int currentCol = Integer.parseInt(st.nextToken());
        int currentRow = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());
        int rowSize = Integer.parseInt(st.nextToken());

        int minRow = Math.min(currentRow, rowSize - currentRow);
        int minCol = Math.min(currentCol, colSize - currentCol);

        System.out.println(Math.min(minCol, minRow));
    }
}
