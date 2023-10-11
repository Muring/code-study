package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 * 
 * 치킨 배달
 * 
 * 크기가 NxN인 도시가 있다.
 * 도시의 각 칸은 빈 칸, 집, 치킨집 중 하나이다.
 * 도시의 칸은 (r, c)로 나타나고 r, c 는 1부터 시작한다.
 * 집과 가장 가까운 치킨집의 거리를 '치킨 거리'라고 한다.
 * 도시의 치킨 거리는 도시 내의 모든 치킨 거리를 합한 값이다.
 * 도시에 있는 치킨집 중에서 최대 M개를 고르고 나머지 치킨집은 모두 폐업이다.
 * 어떻게 고르면 도시의 치킨 거리가 가장 작을지 구하라
 * 
 * 1. 첫째 줄에 도시의 크기와 최대 치킨집 개수를 입력받는다.
 * 2. 이후 도시의 정보가 주어진다.
 * 3. 0은 빈 칸, 1은 집, 2는 치킨집이다.(1 <= 집 < 2N) (M <= 치킨집의 개수 <= 13)
 * 4. 도시의 치킨 거리의 최솟값을 구하라
 * 
 * 가장 먼저 입력된 도시의 정보로부터 각 집마다의 치킨집 거리를 구해 배열에 저장한다.
 * 저장된 배열로 M개짜리 조합을 구해 각 조합의 총 합으로 도시의 치킨 거리를 구한다.
 * 이때, 가장 작은 값의 도시 치킨 거리를 결과로 출력한다.
 *
 */

// 집에 대한 정보를 받을 클래스
class House{
	int row;
	int col;
	
	public House(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class BOJ_15686 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int citySize;			// 전체 도시 사이즈
	static int cityChickenLen;		// 도시의 치킨 거리
	static int remainCount;			// 살아남는 치킨집 개수
	static int[] houseToChickenLen;	// 집에서의 치킨 거리
	static House[] LengthComb;		// 치킨 거리 조합 저장할 배열
	
	static final int MAX_VALUE = Integer.MAX_VALUE;		// 도시 치킨 거리를 초기화하기 위한 값
	
	static ArrayList<House> home;			// 집 좌표 저장 리스트
	static ArrayList<House> chickenHouse;	// 치킨집 좌표 저장 리스트
	
	// 조합 메서드
	private static void comb(int count, int start) {
		// 조합의 완성
		if(count == remainCount) {
			// 해당 조합일 떄의 모든 도시와의 치킨 거리를 구한다.
			int distance = 0;	// 각 치킨 거리 변수 -> 현재 치킨 거리
			houseToChickenLen = new int[home.size()];	// 최종 가장 작은 집과 치킨집 사이의 치킨 거리를 저장할 배열
			Arrays.fill(houseToChickenLen, MAX_VALUE);	// 값을 비교하기 위해 최대값을 미리 저장해 놓는다.
			
//			System.out.println(LengthComb[0].row + " " + LengthComb[0].col);
//			System.out.println(LengthComb[1].row + " " + LengthComb[1].col);
			
			// 치킨 조합의 개수만큼 반복을 돌린다.
			for(int chickenIdx = 0; chickenIdx < remainCount; chickenIdx++) {
				// 각 조합의 치킨집과 모든 집의 치킨 거리를 구한다.
				for(int homeIdx = 0; homeIdx < home.size(); homeIdx++) {
					distance = Math.abs(LengthComb[chickenIdx].row - home.get(homeIdx).row) +
							Math.abs(LengthComb[chickenIdx].col - home.get(homeIdx).col);
					// 만약 현재 치킨 거리가 저장된 치킨 거리보다 짧다면 바꾼다.
					if(houseToChickenLen[homeIdx] > distance)
						houseToChickenLen[homeIdx] = distance;
				}
			}
			
			int sum = 0;	// 임시 도시 치킨 거리 변수
			for(int idx = 0; idx < home.size(); idx++)
				sum += houseToChickenLen[idx];
			
//			System.out.println(sum);
//			System.out.println();
			
			// 만약 임시 도시 치킨 거리가 현재 저장된 도시 치킨 거리보다 작다면 바꾼다.
			if(sum < cityChickenLen)
				cityChickenLen = sum;
			
			return;
		}	// if end
		
		// 치킨 집의 조합을 구한다.
		for(int idx = start; idx < chickenHouse.size(); idx++) {
			LengthComb[count] = chickenHouse.get(idx);
			comb(count + 1, idx + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 초기화 및 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		cityChickenLen = MAX_VALUE;
		
		// 도시의 사이즈와 살아남을 치킨집의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		citySize = Integer.parseInt(st.nextToken());
		remainCount = Integer.parseInt(st.nextToken());

		home = new ArrayList<>();				// 집 리스트
		chickenHouse = new ArrayList<>();		// 치킨집 리스트
		LengthComb = new House[remainCount];	// 치킨 거리 조합 저장할 배열
		
		// 도시 정보 입력
		// 집 리스트와 치킨집 리스트에 해당 각자의 좌표를 모두 저장
		int temp;		// 입력 받은 값을 임시 저장할 변수
		for(int row = 1; row <= citySize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 1; col <= citySize; col++) {
				temp = Integer.parseInt(st.nextToken());
				// 집일 때
				if(temp == 1)
					home.add(new House(row, col));
				// 치킨집일 때
				else if(temp == 2)
					chickenHouse.add(new House(row, col));
			}
		}

		// 각 치킨집에서 집까지의 치킨 거리를 구한다. --------------------------------------------
		// 치킨집의 조합을 구한다.
		// 구한 치킨집의 조합으로 모든 집과의 치킨 거리(= 도시 치킨 거리)를 구해 더한다. -> cityChickenLen에 들어간다.
		// 최소의 도시 치킨 거리를 구한다.
		comb(0, 0);
					
		sb.append(cityChickenLen);
		System.out.println(sb);
	}
}
