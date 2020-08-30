package loans;

import execution.ICollectionMission;

public interface IExecutable {
    static final int MAX_DAYS_TO_WAIT_WITH_PAYMENT = 50;
    ICollectionMission execute();
    boolean isSkiving();
}
