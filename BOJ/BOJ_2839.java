package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 설탕 배달
 * 
 * 사탕가게에 설탕을 N만큼 배달해야 한다.
 * 설탕 봉지는 3킬로그램과 5킬로그램 봉지가 있다.
 * 
 * 최대한 적은 봉지를 들고가려고 한다.
 * 상근이가 설탕을 N킬로그램 배달해야 할 때, 봉지를 몇 개를 가져가면 되는가?
 * 
 * 1. 첫째 줄에 N이 주어진다.
 * 2. 배달하는 최소 봉지 개수를 출력한다. 만약 정확하게 N킬로 그램을 만들 수 없다면 -1을 출력
 *
 */

public class BOJ_2839 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int sugarWeight;		// 전체 설탕 무게
	static int[] count;			// 메모이제이션에 사용할 봉지 개수 카운트 배열
	
	private static void greedy() {
		// 만약 해당 무게를 5로 나눈 나머지 확인
		int leftWeight = sugarWeight % 5;
		
		// 무게를 5로 나눈 나머지가 0이라면 5킬로그램 봉지만으로 무게가 정확히 나눠 떨어진다는 것이다.
		if(leftWeight == 0)
			sb.append((sugarWeight / 5));
		// 무게를 5로 나눈 나머지가 3이라면 남은 무게는 3이라는 의미이니 봉지 개수 +1을 해주면 총 개수가 나온다.
		else if(leftWeight % 3 == 0)
			sb.append((sugarWeight / 5) + (leftWeight / 3));
		// 만들 수 없다면 -1을 반환
		else {
			int index = 1;
			int flag = 0;
			while(leftWeight + index*5 <= sugarWeight) {
				if((leftWeight + index*5) % 3 == 0) {
					sb.append((sugarWeight / 5) - index + (leftWeight + index*5) / 3);
					flag++;
					break;
				}
				else {
					index++;
				}
			}
			if(flag == 0)
				sb.append("-1");
			
		}
	}
	
	private static void dp() {
		count = new int[sugarWeight + 1];	// 봉지 개수 저장 배열
		count[0] = 0;
		count[1] = -1;
		count[2] = -1;
		
		for(int weight = 3; weight <= sugarWeight; weight++) {
			// 3과 5 모두 사용 가능하다면
			if(weight >= 5 && count[weight - 3] >= 0 && count[weight - 5] >= 0) {
				count[weight] = Math.min(count[weight - 3], count[weight - 5]) + 1;
			}
			// 3만 사용할 수 있는 경우
			else if(count[weight - 3] >= 0)
				count[weight] = count[weight - 3] + 1;
			// 5만 사용할 수 있는 경우
			else if(weight >= 5 && count[weight - 5] >= 0)
				count[weight] = count[weight - 5] + 1;
			// 둘 다 사용할 수 없는 경우
			else
				count[weight] = -1;


		}
		
		sb.append(count[sugarWeight]);
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 설탕 무게 입력
		sugarWeight = Integer.parseInt(br.readLine().trim());
		
//		greedy();
		
		dp();


		System.out.println(sb);
	}
}
