import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("""
                SHIP NAME      CLASS          DEPLOYMENT IN SERVICE
                N2 Bomber      Heavy Fighter  Limited    21       \s
                J-Type 327     Light Combat   Unlimited  1        \s
                NX Cruiser     Medium Fighter Limited    18       \s
                N1 Starfighter Medium Fighter Unlimited  25       \s
                Royal Cruiser  Light Combat   Limited    4        \s
                  """);
    }
}
