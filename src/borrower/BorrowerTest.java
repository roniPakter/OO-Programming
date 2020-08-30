package borrower;

import interest.Interest;
import loans.ActiveLoan;
import loans.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class BorrowerTest {

    List<Borrower> borrowers;
    Borrower roni;
    Borrower moshe;
    Borrower yossi;
    Borrower yuri;

    public BorrowerTest() throws ParseException {
        borrowers = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        roni = new Borrower(123456789, "Roni Pakter", "Hanassi 64, Elad", "0527188935", "ronironi@gmail.com", dateFormat.parse("22/04/1991"), 6000, 3);
        moshe = new Borrower(987654321, "Moshe Boozaglo", "Kalanit 3, Eilat", "0506669636", "moshe1@gmail.com", dateFormat.parse("12/08/1980"), 13000, 2);
        yossi = new Borrower(111111111, "Yossi Cohen", "Rabi Akiva 44, Bnei Brak", "0501478523", "yossi6@gmail.com", dateFormat.parse("09/09/1983"), 1900, 13);
        yuri = new Borrower(222222222, "Yuri Maximov", "Hakneset 9, Jerusalem", "0520520522", "Yoori@gmail.com", dateFormat.parse("01/07/1953"), 2034, 0);
        borrowers.add(roni);
        borrowers.add(moshe);
        borrowers.add(yossi);
        borrowers.add(yuri);
    }

    @org.junit.jupiter.api.Test
    void requestInterestSuggestion() {
        double interestSuggestion = yossi.getLoanInterestSuggestion(2000, 6);
        assert interestSuggestion != Interest.REJECTED_LOAN_REQUEST_INTEREST;
        assert interestSuggestion == 0.006d;
    }

    @org.junit.jupiter.api.Test
    void takeLoan() {
        int loanId = moshe.takeLoan(new ActiveLoan(new Loan(40000, moshe.getLoanInterestSuggestion(40000, 60), 60, moshe)));
        assert loanId != Borrower.REJECTED_LOAN_REQUEST_ID;
        loanId = moshe.takeLoan(new ActiveLoan(new Loan(200000, moshe.getLoanInterestSuggestion(200000, 70), 70, moshe)));
        assert loanId == Borrower.REJECTED_LOAN_REQUEST_ID;
    }

    @org.junit.jupiter.api.Test
    void calcPersonalCredit() {

    }


    @org.junit.jupiter.api.Test
    void loan() {
    }
}