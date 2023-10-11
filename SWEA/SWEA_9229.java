package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * totalSnack만큼의 과자 봉지가 존재한다.
 * 각 과자봉지는 weight를 가진다.
 * overweight 이상의 과자 무게를 가지고 나갈 수 없다.
 * 한번에 들고 갈 수 있는 최대 과자의 무게의 합을 출력하라. (2개만 들고갈 수 있음)
 *
 */
public class SWEA_9229 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int totalSnack;			// 전체 과자 봉지 개수
	static int overWeight;			// 과적 무게
	static int[] weights;			// 과자 봉지 무게
	static int max;					// 최대 들 수 있는 무게
	static final int holdable = 2;	// 최대 들 수 있는 과자 봉지 수
		
	// 조합으로 들 수 있는 과자 봉지의 무게 가짓수를 구한다.
	// myWeights는 내가 드는 과자 봉지의 무게 두 개를 저장하느 배열이다.
	// count는 현재 몇 개의 과자 봉지가 선택되었는지 저장하는 변수이다.
	// start는 현재 몇 번째 인덱스의 과자부터 시작하는지를 나타내는 변수이다.
	public static void comb(int[] myWeights, int count, int start) {
		// 재귀가 끝날 때 -> 조합의 결과가 나왔을 때
		if(count == holdable) {
			// 해당 조합의 결과가 현재 저장된 최대 무게보다 크면 해당 무게 저장
			int sum = myWeights[0] + myWeights[1];	// 내가 선택한 과자 봉지들의 무게들의 합
			if(sum > max && sum <= overWeight)		// 무게들의 합이 현재 최대 무게보다 크고 과적하지 않는 경우
				max = sum;
			return;	// 종료
		}
			
		// 과자 봉지 무게의 처음부터 끝까지 비교를 시작한다.
		for(int idx = start; idx < totalSnack; idx++) {
			myWeights[count] = weights[idx];		// 내가 들 과자 봉지의 무게를 저장
			comb(myWeights, count + 1, idx + 1);	// 이 다음 조합을 구하기 위한 재귀
		}
	}
		
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 테스트 케이스 입력
		int totalCase = Integer.parseInt(br.readLine().trim());
		
		// 전체 테스트 케이스만큼 반복
		for(int tc = 1; tc <= totalCase; tc++) {
			st = new StringTokenizer(br.readLine());
			totalSnack = Integer.parseInt(st.nextToken());	// 전체 과자 수 입력
			overWeight = Integer.parseInt(st.nextToken());	// 과적 무게 입력
			
			// 각 과자 봉지 무게 저장
			weights = new int[totalSnack];
			st = new StringTokenizer(br.readLine());		// 한 줄 입력
			for(int idx = 0; idx < totalSnack; idx++)
				weights[idx] = Integer.parseInt(st.nextToken());	// 한 줄을 잘라서 각각 저장
			
			// 각 과자 봉지 무게 조합 구하기
			int[] myWeights = new int[holdable];
			comb(myWeights, 0, 0);
			
			// 각 테스트 케이스의 마무리
			// 만약 들고갈 방법이 없는 경우
			if(max == 0)
				max = -1;	// max는 -1로 바꾼다.
			System.out.printf("#%d %d\n",tc, max);
			max = 0;	// 다음 테스트 케이스를 위한 max 값 초기화
		}	// tc end
	}
}
