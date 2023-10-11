package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * �� ž���� �������� ��ȣ�� �������� ��, �ش� ž���� ���� ž���� ��ȣ�� �����Ѵ�.
 * ó���� �ԷµǴ� Ÿ���� ������ Ÿ�� ��ü�� ���� Ÿ���� ���̿� �ε����� �޾� �����Ѵ�.
 * ������ �ԷµǴ� Ÿ������ ���̿� �տ� �ִ� Ÿ���� ���̸� ���� ���Ͽ� ������ �´� ó���� �Ѵ�.
 * 
 * ù��° �ٿ� ž�� ������ �־�����.
 * ��° �ٿ� �� ž�� ���̰� �־�����.
 * 
 * �ð� �ʰ��� ���� ���Ͽ� �迭�� ���� ������ ���� �ҷ����� ���� �ƴ� ���� ���ڸ��� ���ð� ���ϰ� ����� �����Ѵ�.
 */
public class BOJ_2493 {
	// �⺻ �Է� ����� ���� ��ü ����
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int towerCount;		// ��ü Ÿ�� ��
	static Stack<Tower> towers;	// Ÿ�� ������ ������ ����
	
	// Ÿ�� ��ü
	static class Tower {
		int height;		// ����
		int index;		// Ÿ���� �ε���
		
		Tower(int height, int index){	// �Ķ���� ������
			this.height = height;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// ��ü ����
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		towers = new Stack<>();
		
		// Ÿ�� ���� �Է�
		towerCount = Integer.parseInt(br.readLine().trim());
		
		// Ÿ������ ���� �Է�
		// ���� �Է��̱� ������ ��Ʈ�� ��ū ���
		st = new StringTokenizer(br.readLine());
		
		for(int towerIdx = 1; towerIdx <= towerCount; towerIdx++) {
			// �� Ÿ�� �ε����� Ÿ�� ����
			int currentTowerHeight = Integer.parseInt(st.nextToken());
			while(true) {
				// ������ ������� ��� 0�� ����� �����Ѵ�.
				if(towers.isEmpty()) {
					sb.append(0).append(" ");	// 0 ����
				}
				// ���� ������ Ÿ�� ���̿� ���� Ÿ�� ���̸� ���Ѵ�.
				// ���� Ÿ���� ���̰� ���� Ÿ������ Ŭ ��� pop�� ���� ���� Ÿ���� ����
				// continue�� ���� �ε����� �ø��� �ʰ� �ش� Ÿ���� �� ���� �տ� �ִ� Ÿ���� ���ϰ� �Ѵ�.
				else if(towers.peek().height < currentTowerHeight) {
					towers.pop();
					continue;
				}
				// ���� Ÿ���� ���̰� ���� Ÿ������ ������ ���� Ÿ���� ��ġ�� �����ϰ�
				// ���� Ÿ���� push�� �����Ѵ�.
				else if(towers.peek().height >= currentTowerHeight) {
					sb.append(towers.peek().index).append(" ");	// ���� Ÿ�� ��ġ ����
				}
				// Ÿ�� ��ü�� ���ÿ� �����Ѵ�.
				towers.push(new Tower(currentTowerHeight, towerIdx));
				break;
			}	// while end
			
		}
		// ��� ���
		System.out.println(sb);
		towers.clear();
	}	// main end
}
