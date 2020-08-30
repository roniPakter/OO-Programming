package loans;

import borrower.IBorrower;

public interface ILoan {
    int getLoanId();
    double getPrinciple();
    double getInterestRate();
    int getPeriodMonths();
    boolean isActive();
    double paid();
    IBorrower borrower();
}
