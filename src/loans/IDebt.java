package loans;

import java.util.Date;

public interface IDebt extends IExecutable {
    void addInterest();
    double remainingDebt();
    double pay(double sumToPay);
    Date getLastPaymentDate();
}
