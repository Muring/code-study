import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        String answer = "";
        // nm 값에 따른 색상을 출력합니다.
        if (num >= 620 && num <= 780) answer = "Red";
        else if (num >= 590 && num < 620) answer = "Orange";
        else if (num >= 570 && num < 590) answer = "Yellow";
        else if (num >= 495 && num < 570) answer = "Green";
        else if (num >= 450 && num < 495) answer = "Blue";
        else if (num >= 425 && num < 450) answer = "Indigo";
        else if (num >= 380 && num < 425) answer = "Violet";

        System.out.println(answer);
    }
}