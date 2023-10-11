package BOJ;

import java.util.Scanner;

/**
 * 평소에 질문을 잘 받아주기로 유명한 중앙대학교의 JH 교수님은 학생들로부터 재귀함수가 무엇인지에 대하여 많은 질문을 받아왔다. 매번 질문을
 * 잘 받아주셨던 JH 교수님이지만 그는 중앙대학교가 자신과 맞는가에 대한 고민을 항상 해왔다. 중앙대학교와 자신의 길이 맞지 않다고 생각한
 * JH 교수님은 결국 중앙대학교를 떠나기로 결정하였다. 떠나기 전까지도 제자들을 생각하셨던 JH 교수님은 재귀함수가 무엇인지 물어보는
 * 학생들을 위한 작은 선물로 자동 응답 챗봇을 준비하기로 했다. JH 교수님이 만들 챗봇의 응답을 출력하는 프로그램을 만들어보자.
 * 
 * 교수님이 출력을 원하는 재귀 횟수 N(1 ≤ N ≤ 50)이 주어진다.
 * 
 * 출력 예시를 보고 재귀 횟수에 따른 챗봇의 응답을 출력한다.
 * 
 * @author SSAFY
 *
 */

public class BOJ_17478 {
	static int count;	// 총 재귀 호출 횟수

	public static void recur(int depth, String under) {
		// 재귀 호출 횟수가 총 재귀 호출 횟수와 같아지면 종료
		if (depth == count) {
			System.out.println(under + "\"재귀함수가 뭔가요?\"");
			System.out.println(under+ "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(under + "라고 답변하였지.");
			return;
		}
		
		System.out.println(under + "\"재귀함수가 뭔가요?\"");
		System.out.println(under + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(under + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(under + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		
		// 재귀
		// 매번 재귀 할때마다 depth를 1씩 키우고 언더바 문자열을 더해 보내줌
		recur(++depth, under + "____");
		System.out.println(under + "라고 답변하였지.");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		count = sc.nextInt(); // 출력을 원하는 재귀 횟수 입력

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		// 반복 횟수와 _를 받을 스트링
		recur(0, "");

		sc.close();
	}
}
