package execution;

import borrower.Borrower;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;


class CollectorTest {
    Collector dangerousRoni;
    Borrower moshe;
    Borrower yossi;
    Borrower yuri;
    Borrower borrowerRoni;
    SimpleDateFormat dateFormat;

    public CollectorTest() throws ParseException {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dangerousRoni = new Collector(123456789, "Roni Pakter", "Hanassi 64, Elad", "0527188935", "ronironi@gmail.com", dateFormat.parse("22/04/1991"), ICollector.Violence.assertive, 100);
        moshe = new Borrower(987654321, "Moshe Boozaglo", "Kalanit 3, Eilat", "0506669636", "moshe1@gmail.com", dateFormat.parse("12/08/1980"), 13000, 2);
        yossi = new Borrower(111111111, "Yossi Cohen", "Rabi Akiva 44, Bnei Brak", "0501478523", "yossi6@gmail.com", dateFormat.parse("09/09/1983"), 1900, 13);
        yuri = new Borrower(222222222, "Yuri Maximov", "Hakneset 9, Jerusalem", "0520520522", "Yoori@gmail.com", dateFormat.parse("01/07/1953"), 2034, 0);
        borrowerRoni = new Borrower(123456789, "Roni Pakter", "Hanassi 64, Elad", "0527188935", "ronironi@gmail.com", dateFormat.parse("22/04/1991"), 6000, 3);

    }
    @Test
    void isFree() {
        dangerousRoni.giveMission(new CollectionMission(moshe, true));
        assert dangerousRoni.isFree();

        dangerousRoni.giveMission(new CollectionMission(yossi, true));
        dangerousRoni.giveMission(new CollectionMission(yuri, true));
        dangerousRoni.giveMission(new CollectionMission(borrowerRoni, true));
        assert !dangerousRoni.isFree();
    }

    @Test
    void giveMission() {
        dangerousRoni.giveMission(new CollectionMission(moshe, true));
        assert dangerousRoni.getMission(moshe) != null;
        assert dangerousRoni.getMission(moshe).borrowerDetails() == moshe;
    }

    @Test
    void closeMission() {
        CollectionMission mission = new CollectionMission(moshe, true);
        dangerousRoni.giveMission(mission);
        dangerousRoni.giveMission(new CollectionMission(yossi, true));
        dangerousRoni.giveMission(new CollectionMission(yuri, true));
        dangerousRoni.giveMission(new CollectionMission(borrowerRoni, true));
        dangerousRoni.closeMission(mission, "Very good, I made him pay");
        assert dangerousRoni.getMission(moshe) == null;
        assert dangerousRoni.isFree();
    }

    @Test
    void calcSalary() {
    }
}