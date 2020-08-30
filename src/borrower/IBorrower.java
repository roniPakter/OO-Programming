package borrower;

import loans.IDebt;
import loans.ILoan;

public interface IBorrower {
    ILoan requestLoan(double amount, int periodMonths);
    void takeLoan(ILoan loan);
    double calcPersonalCredit();
    double getCredit();
    IDebt loan(int loanId);
}
