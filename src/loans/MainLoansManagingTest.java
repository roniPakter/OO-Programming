package loans;

import borrower.Borrower;
import execution.CollectionMission;
import execution.Collector;
import execution.ICollector;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class MainLoansManagingTest {
    Collector dangerousRoni;
    Borrower moshe;
    SimpleDateFormat dateFormat;

    public MainLoansManagingTest() throws ParseException {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dangerousRoni = new Collector(123456789, "Roni Pakter", "Hanassi 64, Elad", "0527188935", "ronironi@gmail.com", dateFormat.parse("22/04/1991"), ICollector.Violence.assertive, 100);
        moshe = new Borrower(987654321, "Moshe Boozaglo", "Kalanit 3, Eilat", "0506669636", "moshe1@gmail.com", dateFormat.parse("12/08/1980"), 13000, 2);
    }

    @Test
    void integrationTest() {
        double interest = moshe.getLoanInterestSuggestion(1000, 10);
        int loanId = moshe.takeLoan(new ActiveLoan(new Loan(1000, 10, moshe)));
        ILoan loan = moshe.loan(loanId);
        ((ActiveLoan)loan).pay(70);
        dangerousRoni.giveMission(new CollectionMission(moshe, false));
        dangerousRoni.closeMission(dangerousRoni.getMission(moshe), "all fine");
    }

}