package loans;

import borrower.IBorrower;
import execution.ICollectionMission;
import interest.Interest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ActiveLoan extends Loan implements IDebt, IExecutable {
    private double remainingDebt;
    Date lastPaymentDate;

    public ActiveLoan(ILoan loan){
        super(loan.getPrinciple(), loan.getInterestRate(), loan.getPeriodMonths(), loan.borrower());
        this.remainingDebt = this.getPrinciple();
        Date now = new Date();
        this.setStartDate(now);
        this.lastPaymentDate = now;
        this.isActive = true;
    }
    @Override
    public void addInterest() {
        this.remainingDebt += (this.remainingDebt * Interest.interest(this.getPeriodMonths()));
    }

    @Override
    public double remainingDebt() {
        return this.remainingDebt;
    }

    @Override
    public void pay(double sumToPay) {
        this.remainingDebt -= sumToPay;
        this.paidAlready += sumToPay;
        lastPaymentDate = new Date();
    }

    @Override
    public Date lastPaymentDate() {
        return this.lastPaymentDate;
    }

    @Override
    public IBorrower borrower() {
        return super.borrower();
    }

    @Override
    public ICollectionMission execute() {
        return null;
    }

    @Override
    public boolean isSkiving() {
        Date now = new Date();
        long interval = now.getTime() - this.lastPaymentDate.getTime();
        interval = TimeUnit.DAYS.convert(interval, TimeUnit.MILLISECONDS);
        if (interval > IDebt.MAX_DAYS_TO_WAIT_WITH_PAYMENT)
            return true;
        return false;
    }
}
