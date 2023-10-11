package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 스네이크버드
 * 
 * 스네이크버드의 주요 먹이는 과일이며 과일 하나를 먹으면 길이가 1만큼 늘어난다.
 * 과일들은 지상으로부터 일정 높이를 두고 떨어져 있으며 i(1 <= i <= N)번째 과일의 높이는 h이다.
 * 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있다.
 * 스네이크버드의 처음 길이가 L일때 과일을 먹어 늘릴 수 있는 최대 길이를 구하세요
 * 
 * 1. 첫번째 줄에 과일 개수와 초기 길이가 주어진다.
 * 2. 과일의 높이가 띄어쓰기로 구분되어 주어진다.
 *
 */

public class BOJ_16435 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int appleCount;		// 사과 개수
	static int snakeLen;		// 스네이크버드의 초기 길이
	static int[] applesHeight;	// 사과들의 높이
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 사과 개수와 초기 뱀의 길이 입력
		st = new StringTokenizer(br.readLine());
		appleCount = Integer.parseInt(st.nextToken());
		snakeLen = Integer.parseInt(st.nextToken());
		
		// 사과 개수를 입력 받았으니 그 만큼 각 사과의 높이가 저장될 배열을 생성한 후 저장
		applesHeight = new int[appleCount];
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < appleCount; idx++) {
			applesHeight[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 입력 받은 사과 높이들을 오름차순으로 정렬한다.
		Arrays.sort(applesHeight);
		
		// 이후 앞에서부터 사과의 높이가 뱀의 높이보다 작거나 같으면 뱀의 길이를 늘인다.
		// 뱀이 다음 사과에 닿지 못한다면 break하고 결과를 출력한다.
		for(int idx = 0; idx < appleCount; idx++) {
			if(applesHeight[idx] <= snakeLen)
				snakeLen++;
			else
				break;
		}
		sb.append(snakeLen);
		
		System.out.println(snakeLen);
		
	}	// main end
}
