package loans;

import java.util.Date;

public interface IDebt extends IExecutable {
    void addInterest();
    double remainingDebt();
    void pay(double sumToPay);
    Date lastPaymentDate();
}
