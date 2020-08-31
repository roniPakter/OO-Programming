package loans;

import borrower.IBorrower;
import interest.Interest;

import java.util.Date;

public class Loan implements ILoan{
    private static int serialId = 10000;
    private int loanId;
    private double principle;
    private double interestRate;
    private Date startDate;
    private int periodMonths;
    protected double paidAlready;
    protected boolean isActive;
    private IBorrower borrower;

    public Loan() {
    }

    public Loan(double principle, int periodMonths, IBorrower borrower) {
        this.loanId = serialId++;
        this.principle = principle;
        this.interestRate = Interest.interest(periodMonths);
        this.periodMonths = periodMonths;
        this.borrower = borrower;
        this.startDate = null;
        this.paidAlready = 0;
        this.isActive = false;
    }

    @Override
    public int getLoanId() {
        return loanId;
    }

    @Override
    public double getPrinciple() {
        return principle;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    protected void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public int getPeriodMonths() {
        return this.periodMonths;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public double paid() {
        return this.paidAlready;
    }

    @Override
    public IBorrower borrower() {
        return this.borrower;
    }
}
