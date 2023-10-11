package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회전 초밥 벨트에 놓인 접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호가 주어진다.
 * 	 - 단, 2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d이다. 
 * 벨트의 한 위치부터 시작하여 회전 방향을 따라갈 때 초밥의 종류 (1 이상 d이하의 정수)를 나타내는 정수가 하나씩 주어진다.
 * 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댓값 출력
 * 
 * 1. 데이터 입력
 * 2. 이후 받는 접시의 정보를 저장
 * 3. 식사 시작
 *    - 초기 식사 결과를 구한다.
 *    - 이후 투포인터를 활용해 한 칸씩 뒤로 밀며 결과값 갱신
 *    - 쿠폰의 사용 여부 확인
 * 4. 결과 출력
 * @author sehyeon.eom
 *
 */

public class BOJ_15961 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int plateCount;	// 회전 초밥 벨트에 놓인 접시 수
	static int sushiCount;	// 초밥의 가짓 수
	static int eatInRow;	// 연속해서 먹는 접시 수
	static int couponNumber;// 쿠폰 번호
	
	static int sushiTypeCount;		// 현재 먹은 초밥 종류 개수
	static int maxSushiTypeCount;	// 가장 많이 먹은 초밥 종류 개수
	static int sushiEaten[];		// 해당 번호의 초밥을 먹었는지 안먹었는지 확인하는 배열
	static int conveyorSushiList[];	// 회전 초밥 리스트
	
	// 가장 처음 연속해서 먹는 처리
	private static void init() {
		for(int idx = 0; idx < eatInRow; idx++) {
			int now = conveyorSushiList[idx];
			sushiEaten[now]++;
			if(sushiEaten[now] == 1)
				sushiTypeCount++;
		}
		
		checkCoupon();
	}
	
	// 초기 식사 이후 식사의 처리 메서드
	private static void eatSushi() {
		
		for(int start = 0; start < plateCount - 1; start++) {
			int del = conveyorSushiList[start];
			int add = conveyorSushiList[(start + eatInRow) % plateCount];
			
			// 앞에 초밥 제외
			sushiEaten[del]--;
			if(sushiEaten[del] == 0)
				sushiTypeCount--;
			
			// 뒤에 추가
			sushiEaten[add]++;
			if(sushiEaten[add] == 1)
				sushiTypeCount++;
			
			// 쿠폰번호의 초밥을 이미 먹었는지 안먹었는지 확인
			checkCoupon();
		}
		
	}
	
	// 쿠폰의 사용 여부 확인 메서드
	private static void checkCoupon() {
		// 쿠본 번호의 초밥을 먹은적이 없다면 종류 개수 추가
		if(sushiEaten[couponNumber] == 0)
			maxSushiTypeCount = Math.max(maxSushiTypeCount, sushiTypeCount + 1);
		else
			maxSushiTypeCount = Math.max(maxSushiTypeCount, sushiTypeCount);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 데이터 입력
		st = new StringTokenizer(br.readLine());
		plateCount = Integer.parseInt(st.nextToken());
		sushiCount = Integer.parseInt(st.nextToken());
		eatInRow = Integer.parseInt(st.nextToken());
		couponNumber = Integer.parseInt(st.nextToken());
		
		// 스시 접시 정보를 저장할 리스트를 생성
		// 회전 초밥의 회전성을 위해 연속으로 먹는 초밥의 수 만큼 추가 생성
		conveyorSushiList = new int[plateCount];	
		for(int plateIdx = 0; plateIdx < plateCount; plateIdx++) {
			// 모든 접시의 정보를 저장한다.
			conveyorSushiList[plateIdx] = Integer.parseInt(br.readLine().trim());
		}
		
		sushiEaten = new int[sushiCount + 1];	// 초밥의 번호는 1부터이기 때문에 +1
		
		// 최초 식사
		init();
		// 이후 식사
		eatSushi();
		
		System.out.println(maxSushiTypeCount);
		
	}
}
