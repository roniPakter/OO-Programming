package interest;

public  class Interest {
    private static final double PERIOD_COEFFICIENT = 1000;
    public static double interest(int monthsPeriod){
        return monthsPeriod / PERIOD_COEFFICIENT;
    }
}
