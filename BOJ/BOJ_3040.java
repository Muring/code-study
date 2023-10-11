package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 백설 공주와 일곱 난쟁이
 * 
 * 일곱 난쟁이는 광산으로 일하러 간다. 이때 백설공주는 그들을 위해 저녁을 준비한다.
 * 의자 일곱개, 접시 일곱개, 나이프 일곱개 준비
 * 두 명의 가짜 난쟁이가 함께 돌아왔다.
 * 백설공주는 이럴 때를 대비해 난쟁이들의 모자에 100보다 작은 양의 정수를 적어놓았다.
 * 일곱 난쟁이의 모자에 쓰여 있는 숫자의 합이 100이다.
 * 아홉 난쟁이의 모자에 쓰여있는 수가 주어졌을 때, 일곱 난쟁이를 찾아라
 * 
 * 1. 총 아홉개의 줄에 자연수가 주어진다. 1~99
 * 2. 일곱 난쟁이가 쓴 모자에 쓰여있는 수를 한 줄에 하나씩 출력
 * 
 * 조합으로 입력 받은 수의 조합을 구하고 해당 조합에서 수의 합이 100인 조합을 구하여 각각 출력한다.
 *
 */
public class BOJ_3040 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
		
	static final int totalDwarves = 9; 	// 전체 난쟁이 수
	static int[] dwarves;				// 전체 난쟁이 저장 배열
	static int[] dwarfComb;				// 난쟁이 조합
	static final int combLen = 7;		// 골라야하는 난쟁이 수	
	
	
	private static void comb(int count, int start) {
		// 기저 조건
		if(count == combLen) {
			// 조합이 완성된 상태다.
			// 조합이 완성되었으면 해당 조합의 총 합이 100인지 확인한다.
			findTrueDwarves();
			return;
		}
		
		for(int idx = start; idx < totalDwarves; idx++) {
			dwarfComb[count] = dwarves[idx];
			comb(count + 1, idx + 1);
		}
	}
	
	// 해당 드워프들이 진짜 7명인지 확인하는 메서드
	private static void findTrueDwarves() {
		// 전체 합을 저장할 변수
		int sum = 0;
		
		// 조합의 모든 요소들의 합을 구한다.
		for(int idx = 0; idx < combLen; idx++) {
			sum += dwarfComb[idx];
		}
		
		// 합이 100이라면 원래의 일곱 난쟁이라는 의미이다.
		if(sum == 100) {
			for(int idx = 0; idx < combLen; idx++) {
				sb.append(dwarfComb[idx]).append("\n");
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		dwarves = new int[totalDwarves];
		dwarfComb = new int[combLen];
		
		// 전체 난쟁이 정보 입력
		for(int idx = 0; idx < totalDwarves; idx++) {
			dwarves[idx] = Integer.parseInt(br.readLine());
		}
		
		// 난쟁이의 조합을 구한다.
		comb(0, 0);
		
		System.out.println(sb);
	}
}
