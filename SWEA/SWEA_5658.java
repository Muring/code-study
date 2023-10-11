package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 보물상자 비밀번호
 * 
 * [제약 사항]
 * N은 4의 배수이고, 8이상 28이하의 정수이다. (8 ≤ N ≤ 28)   
 * N개의 숫자는 각각 0이상 F이하로 주어진다. (A~F는 알파벳 대문자로만 주어진다.)
 * K는 생성 가능한 수의 개수보다 크게 주어지지 않는다.
 * 
 * [입력]
 * 가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
 * 테스트 케이스의 첫 번째 줄에는 숫자의 개수 N과 크기 순서 K가 주어 진다.
 * 그 다음 줄에는 16진수 0~F 숫자가 공백 없이 N개 주어진다.
 * 
 * [출력]
 * 출력의 각 줄은 '#t'로 시작하고, 공백을 한 칸 둔 다음 정답을 출력한다.
 * (t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)
 * 
 * [풀이]
 * 1. 테스트 케이스 수 입력
 * 2. 각 테스트 케이스
 * 	2-1. 숫자의 개수(N)와 몇 번째 숫자(K)를 선택할 것인지 입력
 * 	2-2. 자물쇠를 회전시키며 나오는 숫자들을 모두 저장 및 정렬
 * 	2-3. 저장된 숫자들 중 K번째 숫자 저장
 * 3. 정답 출력
 *
 */
public class SWEA_5658 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int numLen;
	static int selectIdx;
	static String originHexNum;
	static String copyHexNum;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			// 숫자의 길이와 몇 번째 숫자를 고를지 입력 받는다.
			st = new StringTokenizer(br.readLine());
			numLen = Integer.parseInt(st.nextToken());
			selectIdx = Integer.parseInt(st.nextToken());
			
			// 최초 숫자 입력은 추후 회전시켜 되돌아 왔을때를 확인하기 위해 남겨둔다.
			originHexNum = br.readLine().trim();
			copyHexNum = originHexNum;
			
			while(true) {
				// 숫자 구하기
				// 3개씩 쪼갠 수를 저장한다.
				// 저장할 때 중복 수는 제거해야하기 때문에 
				
				// 자물쇠 회전
				// 맨 앞자리의 숫자를 맨 뒤로 옮긴다.
				copyHexNum = copyHexNum.substring(1, copyHexNum.length()) + copyHexNum.charAt(0);
				if(copyHexNum.equals(originHexNum)) break;
			}
			
			
		}	// tc end
		System.out.println(Integer.parseInt("1F7", 16));
	}	// main end
}
