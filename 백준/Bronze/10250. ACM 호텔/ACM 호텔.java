import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int laps;    // 전체 테스트 케이스 수
    static int floorCnt; // 호텔 높이
    static int roomCnt; // 각 층의 방 수
    static int targetCustomerIdx;   // 목표 손님 번호

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            st = new StringTokenizer(br.readLine());
            floorCnt = Integer.parseInt(st.nextToken());
            roomCnt = Integer.parseInt(st.nextToken());
            targetCustomerIdx = Integer.parseInt(st.nextToken());

            int targetFloor;
            int targetRoom;
            if (targetCustomerIdx % floorCnt == 0) {
                targetFloor = floorCnt;
                targetRoom = targetCustomerIdx / floorCnt;
            } else {
                targetFloor = targetCustomerIdx % floorCnt;
                targetRoom = targetCustomerIdx / floorCnt + 1;
            }

            sb.append(targetFloor * 100 + targetRoom).append("\n");
        }
        System.out.println(sb);
    }
}