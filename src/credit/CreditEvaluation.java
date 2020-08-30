package credit;

public class CreditEvaluation {
    private static final int AVERAGE_EXPENSE_FOR_PERSON = 500;
    private static final int NUMBER_OF_SALARIES_CALCULATED_FOR_CREDIT = 12;

    public static double creditEvaluation(double income, int numOfChildren){
        double credit = (income - ((numOfChildren + 1) * AVERAGE_EXPENSE_FOR_PERSON)) * NUMBER_OF_SALARIES_CALCULATED_FOR_CREDIT;
        return credit >= 0 ? credit : 0;
    }
}
