package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 *         [입력] 
 *         첫째 줄에 문자열 T가, 둘째 줄에 문자열 P가 주어진다. 
 *         T와 P의 길이 n, m은 1이상 100만 이하이고, 알파벳 대소문자와 공백으로만 이루어져 있다.
 * 
 *         [출력] 
 *         첫째 줄에, T 중간에 P가 몇 번 나타나는지를 나타내는 음이 아닌 정수를 출력한다. 
 *         둘째 줄에는 P가 나타나는 위치를 차례대로 공백으로 구분해 출력한다. 
 *         예컨대, T의 i～i+m-1번 문자와 P의 1～m번 문자가 차례로 일치한다면, i를 출력하는 식이다.
 * 
 *         [풀이] 
 *         KMP 알고리즘을 사용하여 풀면 되는 문제이다. 
 *         1. 전체 문자열 입력 
 *         2. 패턴 입력 
 *         3. 패턴의 접두 접미에 따른 부분 일치 테이블 생성 
 *         4. KMP 알고리즘 동작 
 *         5. 정답 출력
 *
 */

public class BOJ_1786 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static String text;
	static String pattern;
	static int[] table;
	static int answer;

	// 부분 일치 테이블 생성
	private static void makeTable() {
		int patternLen = pattern.length();
		table = new int[patternLen];

		int idx = 0; // 접두사이면서 접미사인 최대 문자열의 길이
		// patternIdx는 pattern의 마지막 인덱스
		for (int patternIdx = 1; patternIdx < patternLen; patternIdx++) {
			// 접두사이면서 접미사인 최대 문자열의 길이가 0이면 비교해볼 필요가 없으니 제외
			// 길이가 0이상일 때, 만약 패턴의 시작(idx)과 끝이 다르다면 해당 문자는 접두사이면서 접미사가 아닌 것이기 때문에 idx를 전 것으로
			// 되돌린다.
			// 예를 들면 abaca일 때, idx=1이 된다. 하지만 다음 문자열인 abacaa에서 a다음인 b를 포함한 ab를 가지고 이것이 뒤에
			// 있는지 확인하는데,
			// 뒤에는 aa이므로 다르다. 따라서 문자열의 길이가 2가 될 수 없으므로 0으로 줄여서 다시 검사하는 것이다.
			// 그렇게 되면 idx는 0이되고, 이를 토대로 제일 처음 a와 제일 끝 a를 검사하여 idx는 다시 1로 검사가 이루어진다.
			// 이는 마지막 문자열을 체크할 때 더 확실히 확인할 수 있는데, abacaaba에 도달 했을때를 보면, idx는 2인 상태이고, ab의
			// 일치까지 확인했다.
			// 이후, ab뒤의 'a'가 문자열의 맨 뒤에 있는지 확인하는 것인데 이때 맨 뒤에 없다면, 이전 문자열 길이 값인 1로 줄어들고 다시 ab의
			// 일치를 확인하여 2를 반환하게 된다.
			// 하지만 'a'가 문자열의 맨 뒤에 있으므로 idx는 3이되어 최대 문자열 길이가 3으로 저장되는 것이다.
			while (idx > 0 && pattern.charAt(patternIdx) != pattern.charAt(idx))
				idx = table[idx - 1];

			// pattern의 마지막 인덱스와 시작 인덱스가 같으면
			if (pattern.charAt(patternIdx) == pattern.charAt(idx)) {
				idx += 1;
				table[patternIdx] = idx;
			}
		}
	}

	private static void KMP() {
		// 만든 부분 일치 테이블을 활용하여 KMP를 수행한다.
		int textLen = text.length();
		int patternLen = pattern.length();

		int patternIdx = 0; // 현재 대응되는 글자 수
		// 전체 문자열을 하나씩 체크하며 나아간다.
		for (int textIdx = 0; textIdx < textLen; textIdx++) {
			// 현재 대응되는 패턴의 문자와 원본 문자가 일치하지 않는다면 현재 대응되는 글자의 수를 table의 값을 활용해 돌린다.
			// 왜냐하면 현재까지 확인된 패턴에 따라 넘어갈 범위가 달라지기 때문이다.
			while (patternIdx > 0 && text.charAt(textIdx) != pattern.charAt(patternIdx))
				patternIdx = table[patternIdx - 1];

			// 글자가 대응될 경우
			if (text.charAt(textIdx) == pattern.charAt(patternIdx)) {
				// 대응되는 글자 수가 패턴의 길이과 같다면 정답이므로 체크
				if (patternIdx == patternLen - 1) {
					answer++;
					sb.append(textIdx - patternIdx + 1).append(" ");
					patternIdx = table[patternIdx];
				}
				// 글자가 대응되지만 패턴의 길이와 일치하지 않는다면 아직 더 해야하기에 idx를 늘리고 진행
				else
					patternIdx += 1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 전체 문자열 입력
		// trim 쓰면 앞뒤에 공백이 있는 경우 사라지기 때문에 쓰지 말자...
		text = br.readLine();

		// pattern 입력
		pattern = br.readLine();

		// 부분 일치 테이블 만들기
		makeTable();
//		System.out.println(Arrays.toString(table));

		// KMP
		KMP();
		
		// 결과 출력
		System.out.println(answer);
		System.out.println(sb.toString());
	}
}
