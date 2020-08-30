package execution;

import borrower.IBorrower;

public class CollectionMission implements ICollectionMission{

    private IBorrower borrower;
    private boolean isDone;
    private ICollector collector;
    private boolean isUrgent;
    private String collectionReport;

    public CollectionMission(IBorrower borrower, boolean isUrgent){
        this.borrower = borrower;
        this.isDone = false;
        this.collector = null;
        this.isUrgent = isUrgent;
        this.isDone = false;
    }

    @Override
    public IBorrower borrowerDetails() {
        return borrower;
    }

    @Override
    public void setCollector(ICollector collector) {
        this.collector = collector;
    }

    @Override
    public void closeMission(String collectingReport) {
        this.isDone = true;
        this.collectionReport =collectingReport;
    }
}
