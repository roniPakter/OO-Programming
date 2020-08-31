package loans;

import borrower.IBorrower;
import interest.Interest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ActiveLoan extends Loan implements IDebt, IExecutable {
    private double remainingDebt;
    private Date lastPaymentDate;

    public ActiveLoan(ILoan loan){
        super(loan.getPrinciple(), loan.getPeriodMonths(), loan.borrower());
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
    public double pay(double sumToPay) {
        if (sumToPay <= 0)
            return 0;
        double changeSum = sumToPay - this.remainingDebt;
        changeSum = changeSum <= 0 ? 0 : changeSum;
        double actualSumToPay = sumToPay - changeSum;

        this.remainingDebt -= actualSumToPay;
        this.paidAlready += actualSumToPay;
        lastPaymentDate = new Date();
        return changeSum;
    }

    @Override
    protected void setStartDate(Date startDate) {
        super.setStartDate(startDate);
        this.lastPaymentDate = startDate;
    }

    @Override
    public Date getLastPaymentDate() {
        return this.lastPaymentDate;
    }

    @Override
    public IBorrower borrower() {
        return super.borrower();
    }

    @Override
    public boolean isSkiving() {
        Date now = new Date();
        long interval = now.getTime() - this.lastPaymentDate.getTime();
        interval = TimeUnit.DAYS.convert(interval, TimeUnit.MILLISECONDS);
        return interval > IDebt.MAX_DAYS_TO_WAIT_WITH_PAYMENT;
    }
}
