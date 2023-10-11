package SWEA;

import java.util.Scanner;

/**
 * 원재의 메모리 복구하기
 * 
 * 메모리 bit중 하나를 골라 0인지 1인지 결정하면 해당 값이 메모리의 끝까지 덮어씌우는 것이다. 예를 들어 지금 메모리 값이
 * 0100이고, 3번째 bit를 골라 1로 설정하면 0111이 된다. 원래 상태가 주어질 때 초기화 상태 (모든 bit가 0) 에서 원래
 * 상태로 돌아가는데 최소 몇 번이나 고쳐야 하는지 계산해보자
 * 
 * 첫 번째 줄에 테스트 케이스의 수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있으며, 메모리의 원래 값이 주어진다. 메모리의
 * 길이는 1이상 50이하이다.
 * 
 * 1. 원래 메모리 입력 받기
 * 2. 원래 메모리로 돌아가기
 * 3. 카운트 횟수 출력
 * @author SSAFY
 *
 */
public class SWEA_1289 {

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int total_case;				// 전체 테스트 케이스 횟수
		total_case = sc.nextInt();
		
		for (int test_case = 1; test_case <= total_case; test_case++) {
			// 메모리 입력 받기
			String origin = sc.next();
			char[] originChar = new char[origin.length()];		// 원본 메모리를 char형으로 저장할 배열 선언
			for(int idx = 0; idx < originChar.length; idx++)	// 원본 메모리를 char형으로 변환 후 저장
				originChar[idx] = origin.charAt(idx);			
			
			char bit = '0';		// 0과 1이 바뀌었는지 확인을 위한 변수
			int count = 0; 		// 변한 횟수 세는 변수
			// 따로 저장하지 않고 숫자가 바뀔때마다 횟수를 세게 함
			for(int idx = 0; idx < originChar.length; idx++) {
				if(originChar[idx] != bit) {
					bit = (char)(97 - bit);
					count++;
				}
			}
			System.out.println("#"+ test_case + " " + count);
		}
		sc.close();
	}
}
