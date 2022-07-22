import logic.Logic;
import logic.RepeatedLogic;

public class Main {

    public static void main(String[] args) {
        new RepeatedLogic(new Logic(), 10).run();
    }

}