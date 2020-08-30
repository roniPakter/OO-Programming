package execution;

import borrower.IBorrower;

public interface ICollectionMission {
    IBorrower borrowerDetails();
    void setCollector(ICollector collector);
    void closeMission(String collectingReport);
}
