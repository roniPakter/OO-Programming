package borrower;
import credit.CreditEvaluation;
import interest.Interest;
import loans.IDebt;
import loans.ILoan;
import person.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Borrower extends Person implements IBorrower{
    public static final int REJECTED_LOAN_REQUEST_ID = -1;
    private final List<ILoan> loansList = new ArrayList<>();
    private double credit;
    private double income;
    private int numOfChildren;
    private double usedCreditSum;

    public Borrower(){
        super();
    }

    public Borrower(int id, String name, String address, String phone, String email, Date dateOfBirth, double income, int numOfChildren) {
        super(id, name, address, phone, email, dateOfBirth);
        this.income = income;
        this.numOfChildren = numOfChildren;
        this.usedCreditSum = 0;
        calcPersonalCredit();
    }

    @Override
    public double getLoanInterestSuggestion(double amount, int periodMonths) {
        if (this.credit - amount < 0)
            return Interest.REJECTED_LOAN_REQUEST_INTEREST;
        return Interest.interest(periodMonths);
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
        calcPersonalCredit();
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
        calcPersonalCredit();
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    @Override
    public int takeLoan(ILoan loan) {
        if (this.credit - loan.getPrinciple() < 0)
            return REJECTED_LOAN_REQUEST_ID;
        this.credit -= loan.getPrinciple();
        this.usedCreditSum += loan.getPrinciple();
        loansList.add(loan);
        return loan.getLoanId();
    }

    @Override
    public void calcPersonalCredit() {
        this.credit = CreditEvaluation.creditEvaluation(this.income, this.numOfChildren) - this.usedCreditSum;
    }

    @Override
    public double getCredit() {
        return this.credit;
    }

    @Override
    public IDebt loan(int loanId) {
        return null;
    }

}
