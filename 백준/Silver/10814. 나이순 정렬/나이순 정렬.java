import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static class Person {
        int age;
        String name;
        int idx;

        public Person(int age, String name, int idx) {
            this.age = age;
            this.name = name;
            this.idx = idx;
        }
    }

    private static void Solution(int laps) throws IOException {
        List<Person> personList = new ArrayList<>();

        // 입력
        for (int idx = 0; idx < laps; idx++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            personList.add(new Person(age, name, idx));
        }

        // 오름차순 정렬
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.age != o2.age)
                    return Integer.compare(o1.age, o2.age);
                else return Integer.compare(o1.idx, o2.idx);
            }
        });

        for (Person person : personList) {
            sb.append(person.age).append(" ").append(person.name).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int laps = Integer.parseInt(br.readLine());

        Solution(laps);
    }
}
