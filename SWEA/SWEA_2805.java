package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author SeHyeon.Eom
 * 
 * 농장물 수확하기
 * 1. 가장 첫 줄에는 totalCase가 주어진다.
 * 2. 이후 농장의 크기가 주어진다.
 * 3. 그리고 농장 내 농작물의 가치가 주어진다.
 * 4. 각 크기에 맞게 마름모 범위 내의 농작물 총 가치 출력
 * 5. 마름모 계산은 위에서 부터 내려오는 인덱스와 아래서부터 올라가는 인덱스로 두개의 행을 동시 계산
 * 6. 이후 가운데 겹치는 부분은 따로 계산
 * 7. 결과 출력
 */

public class SWEA_2805 {
	static BufferedReader br; 	// 입력 선언
	static int totalCase;		// 전체 테스트 케이스 개수
	static int size;			// 농장의 크기
	static int[][] map;			// 농장
	
	static int downIdx;			// 밑에서부터 훑고 가는 인덱스
	static int upIdx;			// 위에서부터 훍고 가는 인덱스
	static int sum;				// 전체 농작물 가치
	static int rowSize;			// 해당 열의 세야하는 농장 범위 

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 총 테스트 케이스 입력
		totalCase = Integer.parseInt(br.readLine().trim());
		
		// 테스트 케이스 실행
		for(int testCase = 1; testCase <= totalCase; testCase++) {
			// 정보를 입력받아 맵에 저장한다.
			size = Integer.parseInt(br.readLine().trim());		// 농장의 크기 입력
			
			map = new int[size][size];				// 농장 생성
			for(int row = 0; row < size; row++) {
				String str = br.readLine().trim();	// 각 줄의 농장 정보 임시로 저장
				
				for(int col = 0; col < size; col++) {
					map[row][col] = str.charAt(col) - '0';	// 농장에 정보 저장
				}
			}
			
			// 저장이 끝나고 저장된 맵을 가지고 가치를 계산한다.
			// 저장을 위해 필요한 변수 초기화
			sum = 0;
			rowSize = 1;
			upIdx = size - 1;
			downIdx = 0;
			int farmLoc = size / 2;	// 처음 계산을 시작하는 농장의 위치 -> 농장 사이즈가 5일 경우 2, 1, 0 순으로 감
			
			// 위와 아래의 행이 겹치기 전 계산
			while(upIdx > downIdx) {
				for(int idx = farmLoc; idx < farmLoc + rowSize; idx++) {
					sum += map[upIdx][idx];		// 위에서부터 내려오며 농작물 값을 더함
					sum += map[downIdx][idx];	// 아래서부터 올라가며 농작물 값을 더함
				}
				upIdx--; downIdx++; rowSize += 2; farmLoc--;	// 각 인덱스 조절 및 확인하는 농장 범위 조절 
			}
			
			// 겹치는 중간 행의 계산
			for(int idx = 0; idx < size; idx++)
				sum += map[upIdx][idx];
			
			// 결과 출력
			System.out.printf("#%d %d\n", testCase, sum);
		}
	}

}
