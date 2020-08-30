package loans;

import borrower.IBorrower;
import interest.Interest;

import java.util.Date;

public class Loan implements ILoan{
    static int serialId = 10000;
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

    public Loan(double principle, double interestRate, int periodMonths, IBorrower borrower) {
        this.loanId = serialId++;
        this.principle = principle;
        this.interestRate = interestRate;
        this.periodMonths = periodMonths;
        this.borrower = borrower;
        this.startDate = null;
        this.paidAlready = 0;
        this.isActive = false;
    }

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

    public void setStartDate(Date startDate) {
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
