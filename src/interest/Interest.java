package interest;

public  class Interest {
    private static final double PERIOD_COEFFICIENT = 1000;
    public static final int REJECTED_LOAN_REQUEST_INTEREST = -1;
    public static double interest(int monthsPeriod){
        return (double)monthsPeriod / PERIOD_COEFFICIENT;
    }
}
