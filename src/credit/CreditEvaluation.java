package credit;

public class CreditEvaluation {
    private static final int AVERAGE_EXPENSE_FOR_PERSON = 500;
    private static final int NUMBER_OF_SALARIES_CALCULATED_FOR_CREDIT = 12;

    public static double creditEvaluation(double income, int numOfChildren){
        return (income * NUMBER_OF_SALARIES_CALCULATED_FOR_CREDIT) - ((numOfChildren + 1) * AVERAGE_EXPENSE_FOR_PERSON);
    }
}
