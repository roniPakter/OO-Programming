package borrower;

import loans.IDebt;
import loans.ILoan;

public interface IBorrower {
    double getLoanInterestSuggestion(double amount, int periodMonths);
    int takeLoan(ILoan loan);
    void calcPersonalCredit();
    double getCredit();
    ILoan loan(int loanId);
    void closeLoan(ILoan loan);
}
