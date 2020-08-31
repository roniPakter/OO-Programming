package execution;

import borrower.IBorrower;

import java.util.Date;

public interface ICollector {
    enum Violence {gentle, assertive, aggressive, violent}
    Violence getViolence();
    boolean isFree();
    void giveMission(ICollectionMission collectionMission);
    ICollectionMission getMission(IBorrower borrower);
    void closeMission(ICollectionMission doneMission, String collectionReport);
}
