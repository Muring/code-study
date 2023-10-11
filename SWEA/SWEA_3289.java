package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 초기에 {1} ... {n}이 각각 n개의 집합을 이루고 있다.
 * 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
 * 
 * 1. 첫 번째 줄에 테스트 케이스의 수가 주어진다.
 * 2. 각 테스트 케이스의 첫째 줄에 n(1<=n<=1,000,000), m(1<=m<=100,000)이 주어진다.
 * 	  m은 입력으로 주어지는 연산의 개수이다.
 * 3. 다음 m개의 줄에는 연산이 주어진다.
 *    0 a b는 a와 b가 포함되어 있는 집합을 합치는 것이고
 *    1 a b는 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산이다.
 *    a와 b는 n이하의 자연수이며 같을 수 있다.
 * 4. 각 테스트 케이스마다 1로 시작하는 입력에 대해서 같은 집합에 속해있다면 1을 아니라면 0을 출력한다. 
 * @author sehyeon.eom
 *
 */


public class SWEA_3289 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int numCount;	// 전체 숫자
	static int calLen;	// 연산 개수
	
	static int parentList[];
	static int rankList[];
	
	// 처음 해당 숫자들 집합화 메소드
	private static void make() {
		parentList = new int[numCount + 1];
		rankList = new int[numCount + 1];
		
		// 자기 자신이 루트 노드이다.
		for(int idx = 0; idx < numCount; idx++) {
			parentList[idx] = idx;
		}
	}
	
	// 합집합 처리 메소드
	private static void union(int e1, int e2) {
		// 먼저 각 원소의 부모들을 찾아온다.
		int e1Parent = find(e1);
		int e2Parent = find(e2);
		
		// 부모가 같다면 동일 집합에 속해있다는 것
		if(e1Parent == e2Parent)	return;
		
		// 부모가 동일하지 않다면 서로 다른 집합에 존재한다.
		parentList[e1Parent] = e2Parent;
	}
	
	// 두 원소가 같은 집합인지 확인하는 메소드
	private static void unionCheck(int e1, int e2) {
		// 먼저 각 원소의 부모들을 찾아온다.
		int e1Parent = find(e1);
		int e2Parent = find(e2);
		
		// 부모가 같다면 동일 집합에 속해있다는 것
		if(e1Parent == e2Parent) {
			sb.append(1);
			return;
		}

		// 부모가 동일하지 않다면 서로 다른 집합에 존재한다.
		sb.append(0);	
	}
	
	// 해당 요소의 루트 노드 찾는 메소드
	private static int find(int element) {
		
		// 내가 부모라면 더 위의 부모는 없으니 리턴
		if(parentList[element] == element) {
			return element;
		}
		
		// 경로 압축
		// 부모를 찾으면서 최종 부모 찾기
		return parentList[element] = find(parentList[element]);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 입력 및 시작
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			sb.append("#").append(tc).append(" ");
			// 정보 입력
			st = new StringTokenizer(br.readLine());
			numCount = Integer.parseInt(st.nextToken());
			calLen = Integer.parseInt(st.nextToken());
			
			// 최소 단위 집합 생성
			make();
			
			int option = 0;	// 입력 값에 따라 메뉴가 달라짐
			int element1 = 0, element2 = 0;	// 어느 집합인지 저장할 변수
			for(int idx = 0; idx < calLen; idx++) {
				// 각 연산마다 입력 값
				st = new StringTokenizer(br.readLine());
				option = Integer.parseInt(st.nextToken());
				element1 = Integer.parseInt(st.nextToken());
				element2 = Integer.parseInt(st.nextToken());
				
				// 합집합 처리
				if(option == 0) {
					union(element1, element2);
				}
				// 옵션이 1이라면 같은 집합에 포함되어 있는지 확인
				else {
					unionCheck(element1, element2);
				}
			}	// for end
			
			sb.append("\n");
			
		}	// tc end
		System.out.println(sb);
	}
}
