package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author sehyeon.eom
 * 
 * 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 0이 되고 모두 1로만 되어있으면 결과는 1이다.
 * 0과 1이 섞여 있으면 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 이렇게 4개로 나누어 압축하게 된다.
 * 
 * 1. 첫째 줄에는 영상의 크기를 입력받는다.
 * 2. 둘째 줄 부터는 영상의 정보인 문자열을 입력받는다.
 * 3. 압축한 결과 출력
 *
 * 8
 * 11110000
 * 11110000
 * 00011100
 * 00011100
 * 11110000
 * 11110000
 * 11110011
 * 11110011
 * 
 * 결과 - ((110(0101))(0010)1(0001))
 * 
 * 분할 정복 문제이다.
 * 먼저 크게 확인하고 0과 1이 섞여있다면 4등분하여 다시 검사한다.
 * 이를 반복하여 결과를 출력한다.
 */
public class BOJ_1992 {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int videoSize;
	static int video[][];
	
	// 압축하여 결과를 저장하는 메서드
	// row와 col은 각각의 사분면의 시작 지점을 나타낸다.
	// -> 해당 시작 지점부터 각  사분면의 요소를 검사한다.
	// size는 현재 사분면의 한 변의 길이를 나타낸다.
	private static void conditionCheck(int startRow, int startCol, int size) {
		
		// 결과를 출력할 조건
		// 이 문제에서는 현재 사분면의 모든 값이 전부 같다면 압축이 가능하기에 해당 조건을 추가해줘야 한다.

		// 수행
		// 해당 사분면에 0과 1이 동시에 있는지 확인한다.
		int temp = video[startRow][startCol];	// 각 사분면의 가장 처음 값
		boolean flag = false;	// 동시에 없다면 false, 있다면 true
		for(int row = startRow; row < startRow + size; row++) {
			for(int col = startCol; col < startCol + size; col++) {
				if(temp != video[row][col]) {
					flag = true;	// 다른 값이 있다는 표시
					break;
				}
			}
			if(flag == true)
				break;
		}
		
		// 동시에 없을 경우
		// 압축이 가능하다는 소리다.
		// 압축을 실행한다.
		if(flag == false) {
			sb.append(temp);
			return;
		}
		
		// 동시에 있을 경우
		else if(flag == true) {
			sb.append("(");
			
			// 사이즈를 절반으로 줄여 사분면을 확인한다.
			int newSize = size / 2;
			
			// 1사분면
			conditionCheck(startRow, startCol, newSize);
			// 2사분면
			conditionCheck(startRow, startCol + newSize, newSize);
			// 3사분면
			conditionCheck(startRow + newSize, startCol, newSize);
			// 4사분면
			conditionCheck(startRow + newSize, startCol + newSize, newSize);
			

		}
		// 해당 부분에서의 체크는 끝난 것이므로 괄호를 닫아준다.
		sb.append(")");
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		// 첫째 줄 입력
		videoSize = Integer.parseInt(br.readLine().trim());
		video = new int[videoSize][videoSize];
		
		// 이후 비디오 정보 입력
		// 공백 입력이 아니기 때문에 따로 처리해줘야한다.
		String str;
		for(int row = 0; row < videoSize; row++) {
			str = br.readLine().trim();	// 한 줄 입력
			for(int col = 0; col < videoSize; col++) {
				video[row][col] = str.charAt(col) - '0';	// 쪼개서 배열에 저장
			}
		}
		
		// 저장 이후 처리
		// 분할 정복으로 처리한다.
		conditionCheck(0, 0, videoSize);
		
		// 결과 출력
		System.out.println(sb);
	}
}
