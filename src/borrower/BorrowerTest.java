package borrower;

import interest.Interest;
import loans.ActiveLoan;
import loans.ILoan;
import loans.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class BorrowerTest {
    Borrower roni;
    Borrower moshe;
    Borrower yossi;
    Borrower yuri;

    public BorrowerTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        roni = new Borrower(123456789, "Roni Pakter", "Hanassi 64, Elad", "0527188935", "ronironi@gmail.com", dateFormat.parse("22/04/1991"), 6000, 3);
        moshe = new Borrower(987654321, "Moshe Boozaglo", "Kalanit 3, Eilat", "0506669636", "moshe1@gmail.com", dateFormat.parse("12/08/1980"), 13000, 2);
        yossi = new Borrower(111111111, "Yossi Cohen", "Rabi Akiva 44, Bnei Brak", "0501478523", "yossi6@gmail.com", dateFormat.parse("09/09/1983"), 1900, 13);
        yuri = new Borrower(222222222, "Yuri Maximov", "Hakneset 9, Jerusalem", "0520520522", "Yoori@gmail.com", dateFormat.parse("01/07/1953"), 2034, 0);
    }

    @org.junit.jupiter.api.Test
    void requestInterestSuggestion() {
        double interestSuggestion = yossi.getLoanInterestSuggestion(2000, 6);
        assert interestSuggestion == Interest.REJECTED_LOAN_REQUEST_INTEREST;
        interestSuggestion = moshe.getLoanInterestSuggestion(2000, 6);
        assert interestSuggestion == 0.006d;
    }

    @org.junit.jupiter.api.Test
    void takeLoan() {
        int loanId = moshe.takeLoan(new ActiveLoan(new Loan(40000, 60, moshe)));
        assert loanId != Borrower.REJECTED_LOAN_REQUEST_ID;
        loanId = moshe.takeLoan(new ActiveLoan(new Loan(200000, 70, moshe)));
        assert loanId == Borrower.REJECTED_LOAN_REQUEST_ID;
    }

    @org.junit.jupiter.api.Test
    void calcPersonalCredit() {
        assert  roni.getCredit() == 48000;
        roni.setIncome(3000);
        assert roni.getCredit() == 12000;
        roni.setNumOfChildren(9);
        assert roni.getCredit() == 0;
        roni.setIncome(100000);
        assert roni.getCredit() == 1140000;
        roni.takeLoan(new ActiveLoan(new Loan(50000, 100, roni)));
        assert roni.getCredit() == 1090000;
    }


    @org.junit.jupiter.api.Test
    void closeLoan() {
        int firstLoanId = yuri.takeLoan(new ActiveLoan(new Loan(10000, 10, yuri)));
        assert yuri.getCredit() == 8408;
        int secondLoanId = yuri.takeLoan(new ActiveLoan(new Loan(8000, 12, yuri)));

        ILoan firstLoan = yuri.loan(firstLoanId);
        ILoan secondLoan = yuri.loan(secondLoanId);

        yuri.closeLoan(firstLoan);
        assert yuri.getCredit() == 10408;
        yuri.closeLoan(secondLoan);
        assert yuri.getCredit() == 18408;
    }
}