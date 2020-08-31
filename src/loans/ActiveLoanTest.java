package loans;

import borrower.Borrower;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;


class ActiveLoanTest {
    ActiveLoan loan;
    Borrower roni;
    SimpleDateFormat dateFormat;

    public ActiveLoanTest() throws ParseException {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        roni = new Borrower(123456789, "Roni Pakter", "Hanassi 64, Elad", "0527188935", "ronironi@gmail.com", dateFormat.parse("22/04/1991"), 6000, 3);
        loan = new ActiveLoan(new Loan(100, 10, roni));
    }

    @Test
    void addInterest() {
        double interest = loan.getInterestRate();
        loan.addInterest();
        double debtAfterIncrease = loan.getPrinciple() + (loan.getPrinciple() * interest);
        assert debtAfterIncrease == (100 + (100 * interest));
        assert loan.remainingDebt() == debtAfterIncrease;

        loan.addInterest();
        double debtAfterSecondIncrease = debtAfterIncrease + (debtAfterIncrease * interest);
        assert debtAfterSecondIncrease == (101 + (101 * interest));
        assert loan.remainingDebt() == debtAfterSecondIncrease;
        
    }

    @Test
    void pay() {
        double change = loan.pay(60);
        assert change == 0;
        assert loan.remainingDebt() == 40;
        assert loan.paid() == 60;

        change = loan.pay(0);
        assert change == 0;
        assert loan.remainingDebt() == 40;
        assert loan.paid() == 60;

        change = loan.pay(-54);
        assert change == 0;
        assert loan.remainingDebt() == 40;
        assert loan.paid() == 60;

        change = loan.pay(50);
        assert change == 10;
        assert loan.remainingDebt() == 0;
        assert loan.paid() == 100;

    }

    @Test
    void isSkiving() throws ParseException {
        assert !loan.isSkiving();
        loan.setStartDate(dateFormat.parse("22/10/2019"));
        assert loan.isSkiving();
        loan.pay(39);
        assert !loan.isSkiving();
    }
}