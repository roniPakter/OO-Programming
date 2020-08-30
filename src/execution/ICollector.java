package execution;

import execution.ICollectionMission;

import java.util.Date;

public interface ICollector {
    enum Violence {gentle, assertive, aggressive, violent}
    Violence getViolence();
    boolean isFree(Date missionDate);
    void giveMission(ICollectionMission collectionMission);
    void closeMission(ICollectionMission doneMission, String collectionReport);
}
