package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author sehyeon.eom
 *
 * 규영이와 인영이는 1에서 18까지 적힌 숫자 카드로 게임한다.
 * 둘이서 9개씩 카드를 나눠가진다.
 * 9라운드에 거쳐 게임을 진행한다.
 * 높은 수의 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻는다.
 * 진 사람은 아무런 점수도 얻을 수 없다.
 * 총점이 더 높은 사람이 승리
 * 총점이 같으면 무승부
 * 규영이가 내는 카드의 순서를 고정하면 인영이가 내는 경우의 수에 따라 승패가 정해진다.
 * 해당 승패로 총점을 구해서 총점이 더 높은 사람이 이긴다.
 * 규영이가 이기는 경우와 지는 경우가 총 몇 가지인지 구하라.
 * 
 * 1. 전체 테스트 케이스 수를 입력받는다.
 * 2. 각 테스트 케이스 첫번째 줄에는 아홉개의 정수가 공백으로 구분되어 주어진다.
 * 3. 규영이는 주어진 정수대로 카드를 낸다.
 * 
 * 주어진 정수가 아닌 정수들로 순열을 만든다.
 * 각 순열당 승패를 비교하여 카운트한다.
 * 이후 결과 출력
 */

// 각 플레이어의 정보를 저장할 클래스
class Player{
	int[] cardList;		// 각각의 카드 목록을 저장할 배열
	int pointSum;		// 승패의 총점
	
	Player(int cardCount, int pointSum){
		this.cardList = new int[cardCount];
		this.pointSum = pointSum;
	}
}

public class SWEA_6808 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int cardCount = 9;	// 고정된 카드의 수
	static boolean[] used;			// 카드가 사용됐는지 안됐는지 확인
	static int win;					// 승리한 횟수
	static int lose;				// 패배한 횟수
	
	// next Permutation같은 경우에는 뒤에서부터 확인하며 최종적으로는 순열을 내림차순으로 만들며
	// 해당 순열의 모든 경우의 수를 구하는 방법이다.
	// 이때, 뒤에서부터 찾은 가장 큰 값인 꼭대기 값을 통해 앞과 뒤를 비교하게 되는데
	// 현재 꼭대기보다 꼭대기 앞의 값이 더 높거나 같다면 꼭대기를 바꾸고
	// '꼭대기보다 작은 값'이 앞에 있다면 해당 값을 꼭대기 뒤쪽에서 '꼭대기보다 작은 값'보다 큰 값과 위치를 바꾼다.
	// 그리고 꼭대기 자리부터 맨 뒤까지의 수를 오름차순으로 정렬하고를 반복하면 역순열이 완성된다.
	private static boolean np(Player inYoung) {
		// 꼭대기 지점 인덱스
		int peakIdx = cardCount - 1;
		
		// 해당 꼭대기 지점앞에 다른 수가 있고 해당 수가 꼭대기보다 크다면
		while(peakIdx > 0 && inYoung.cardList[peakIdx - 1] >= inYoung.cardList[peakIdx]) 
			--peakIdx;
		
		// 현재 상태가 가장 큰 순열의 형태라면
		if(peakIdx == 0) return false;
		
		// 꼭대기 앞의 위치의 숫자와 교환할 한 단계 큰 수를 뒤쪽부터 찾기
		int searchIdx = cardCount - 1;
		while(inYoung.cardList[peakIdx - 1] >= inYoung.cardList[searchIdx])
			--searchIdx;
		
		// 해당 숫자를 찾았으면 꼭대기 앞의 값과 교환해준다.
		swap(inYoung, peakIdx - 1, searchIdx);
		
		// 이후 꼭대기 자리부터 맨 뒤까지의 수를 오름차순 형태로 바꿈
		int sortIdx = cardCount - 1;
		while(peakIdx < sortIdx)
			swap(inYoung, peakIdx++, sortIdx--);
//		Arrays.sort(inYoung.cardList, peakIdx, cardCount - 1);
		
		return true;
	}
	
	private static void swap(Player inYoung, int a, int b) {
		int temp = inYoung.cardList[a];
		inYoung.cardList[a] = inYoung.cardList[b];
		inYoung.cardList[b] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 각 플레이어의 정보를 기억할 객체 생성
		Player gyuYoung = new Player(cardCount, 0);	// 고정된 카드
		Player inYoung = new Player(cardCount, 0);	// 유동적인 카드
		used = new boolean[cardCount * 2 + 1];
				
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			Arrays.fill(used, false);	// 사용됐는지 확인하는 배열 매 테스트 케이스마다 초기화
			win = 0;
			lose = 0;
			
			// 고정된 카드 반환 순서를 입력받는다.
			st = new StringTokenizer(br.readLine());
			for(int idx = 0; idx < cardCount; idx++) {
				gyuYoung.cardList[idx] = Integer.parseInt(st.nextToken());
				used[gyuYoung.cardList[idx]] = true;
			}
				
			// 고정된 카드 외의 다른 카드들의 목록을 저장한다.
			int cardIdx = 0;
			for(int idx = 1; idx <= cardCount * 2; idx++) {
				if(used[idx] == false)
					inYoung.cardList[cardIdx++] = idx;
			}
			
			// 순열을 구하기 전 인영이의 카드의 경우의 수를 구하기 위한 nextPermutation을 위한 정렬
			Arrays.sort(inYoung.cardList);
			
			// 이후 nextPermutation을 활용해 각 경우의 수의 결과를 구한다.
			// 게임 시작
			do {
				// 라운드 시작
				// 여기서 배회 다음 순열인 인영이와 고정된 카드를 가지고 있는 규영이를 비교해 승패를 구한다.
				// 매 라운드마다 전 라운드 총점 초기화
				gyuYoung.pointSum = 0;
				inYoung.pointSum = 0;
				
				for(int round = 0; round < cardCount; round++) {
					// 규영이 이겼을 경우
					if(gyuYoung.cardList[round] > inYoung.cardList[round]) {
						gyuYoung.pointSum += gyuYoung.cardList[round] + inYoung.cardList[round];
					}
					// 인영이 이겼을 경우
					else
						inYoung.pointSum += gyuYoung.cardList[round] + inYoung.cardList[round];	
				}
				// 총점을 비교해 해당 게임의 승자를 정한다.
				if(gyuYoung.pointSum > inYoung.pointSum)
					win++;
				else if(gyuYoung.pointSum < inYoung.pointSum)
					lose++;
//					System.out.println(Arrays.toString(inYoung.cardList));
			} while (np(inYoung));
			
			// 결과 저장
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append("\n");
		}	// tc end
		System.out.println(sb);
	}
}
