package borrower;
import interest.Interest;
import loans.ActiveLoan;
import loans.IDebt;
import loans.ILoan;
import loans.Loan;
import person.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Borrower extends Person implements IBorrower{
    private List<ILoan> loansList;
    private double credit;

    public Borrower(){
        super();
        loansList = new ArrayList<>();
    }

    public Borrower(int id, String name, String address, String phone, String email, Date dateOfBirth) {
        super(id, name, address, phone, email, dateOfBirth);
        loansList = new ArrayList<>();
        this.credit = this.calcPersonalCredit();
    }

    @Override
    public ILoan requestLoan(double amount, int periodMonths) {
        if (this.credit - amount < 0)
        return null;
        double interest = Interest.interest(periodMonths);
        Loan loan = new Loan(amount, interest, periodMonths, this);
        return loan;
    }

    @Override
    public void takeLoan(ILoan loan) {
        loansList.add(new ActiveLoan((Loan)loan, this));
    }

    @Override
    public double calcPersonalCredit() {
        return 0;
    }

    @Override
    public double getCredit() {
        return 0;
    }

    @Override
    public IDebt loan(int loanId) {
        return null;
    }
}
