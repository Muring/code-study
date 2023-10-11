package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 도영이가 만든 맛있는 음식
 * 
 * 1. 재료의 갯수를 입력받는다.
 * 2. 각 재료의 신맛 sour와 쓴맛 bitter를 알고 있다.
 * 3. 재료를 사용할 때 음식의 신맛은 사용한 재료의 신맛의 곱이고 쓴맛은 합이다.
 * 4. 재료는 적어도 하나는 사용해야한다.
 * 5. 신맛과 쓴맛의 차이를 작게 만들려고 한다.
 * 6. 신맛과 쓴맛의 차이가 가장 작은 요리를 만들어라.
 * 
 * 첫째 줄에 재료의 개수
 * 둘쨰 줄에 신맛과 쓴맛이 공백으로 구분되어 주어진다.
 * 가장 차이가 작은 요리의 차이를 출력한다.
 *
 */

public class BOJ_2961 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int total;				// 전체 재료 개수
	static int[] sourTastes;		// 신맛
	static int[] bitterTastes;		// 쓴맛
	static int leastTasteDiffer;	// 가장 맛 차이가 적은 값
	static boolean[] isSelected;	// 숫자의 사용 여부
	
	// 요리하는 메서드
	// 무엇을 구현해야 하는가? -> 모든 맛의 조합을 구하고 거기서 최소값인 것을 저장해 출력한다.
	// 그러기 위해서는 어떻게 해야하는가? -> 부분집합을 사용해 모든 맛의 조합을 구한다.
	private static void cook(int count) {
		if(count == total) {
			int sour = 1;		// 신맛은 곱 연산이기에 1로 초기화
			int bitter = 0;		// 쓴맛 초기화
			int idx = 0;		// 공집합 처리
			for(int i = 0; i < total; i++) {
				if(isSelected[i]) {				// 모든 숫자가 선택 됐다면
					idx++;						
					sour *= sourTastes[i];		// 모든 재료들로 신맛을 계산
					bitter += bitterTastes[i];	// 모든 재료들로 쓴맛을 계산
				}
			}
			
			if(idx == 0) return;				// 공집합이면 종료
			if(leastTasteDiffer > Math.abs(sour - bitter))
				leastTasteDiffer = Math.abs(sour - bitter);
				
			return;
		}
		
		isSelected[count] = true;
		cook(count + 1);
		isSelected[count]= false;
		cook(count + 1);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		 
		
		// 전체 재료 개수 입력
		total = Integer.parseInt(br.readLine().trim());
		
		// 전체 재료 수 만큼 맛 배열 생성
		sourTastes = new int[total];
		bitterTastes = new int[total];
		
		// 각 재료의 맛 입력
		for(int idx = 0; idx < total; idx++) {
			st = new StringTokenizer(br.readLine()); 
			sourTastes[idx] = Integer.parseInt(st.nextToken());
			bitterTastes[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 요리해라!
		// 신맛은 서로 곱하고 쓴맛은 더해라!
		// 최소값을 비교할때 최대값으로 초기화를 해준다.
		// 최대값으로 초기화를 해주지 않으면 기본적으로 0이 들어가기에 제대로된 최소값을 구할 수 없다.
		isSelected = new boolean[total];
		leastTasteDiffer = Integer.MAX_VALUE;	
		cook(0);
		
		// 결과 출력
		System.out.printf("%d\n", leastTasteDiffer);
	}
}
