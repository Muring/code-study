import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int rowSize, colSize;
    static int[][] matrix1, matrix2;

    private static void setMatrix(int[][] matrix) throws IOException {
        for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine());
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                matrix[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void getMatrixSum(StringBuilder sb) {
        for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for(int colIdx = 0; colIdx < colSize; colIdx++) {
                sb.append(matrix1[rowIdx][colIdx] + matrix2[rowIdx][colIdx]).append(" ");
            }
            sb.append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        matrix1 = new int[rowSize][colSize];
        matrix2 = new int[rowSize][colSize];

        setMatrix(matrix1);
        setMatrix(matrix2);

        getMatrixSum(sb);

        System.out.println(sb);
    }
}
