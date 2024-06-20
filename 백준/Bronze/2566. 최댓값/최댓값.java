import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MaxNumInfo{
    private int value;
    private int rowIdx;
    private int colIdx;

    public MaxNumInfo() {
        this.value = 0;
        this.rowIdx = 0;
        this.colIdx = 0;
    }

    public int getValue() {
        return this.value;
    }
    public int getRowIdx() {
        return this.rowIdx;
    }
    public int getColIdx() {
        return this.colIdx;
    }

    public void update(int value, int rowIdx, int colIdx) {
        this.value = value;
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static final int MAP_SIZE = 9;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        MaxNumInfo maxNum = new MaxNumInfo();
        int num = 0;

        for(int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
            st = new StringTokenizer(br.readLine());
            for(int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
                num = Integer.parseInt(st.nextToken());
                if(num > maxNum.getValue())
                    maxNum.update(num, rowIdx, colIdx);
            }
        }
        sb.append(maxNum.getValue()).append("\n");
        sb.append(maxNum.getRowIdx() + 1).append(" ").append(maxNum.getColIdx() + 1);
        System.out.println(sb);
    }
}
