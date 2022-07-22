package logic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RepeatedLogic implements Runnable {
    private final Logic logic;
    private final int secondsToWaitUntilNextCall;

    public RepeatedLogic(Logic logic, int secondsToWaitUntilNextCall) {
        this.logic = logic;
        this.secondsToWaitUntilNextCall = secondsToWaitUntilNextCall;
    }

    @Override
    public void run() {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(logic, 0, secondsToWaitUntilNextCall, TimeUnit.SECONDS);
    }
}
