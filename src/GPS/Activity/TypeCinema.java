package GPS.Activity;

public class TypeCinema extends ActivityType {
    private final int YOUNG_YEAR = 25;
    private final double HALF_DISCOUNT = 0.5;
    private final int NO_DISCOUNT = 0;
    public TypeCinema(int age){
        super("Cinema",age);
        super.discount = this.calculateDiscount();
    }
    public double calculateDiscount() {
        if(age <= this.YOUNG_YEAR){
            return this.HALF_DISCOUNT;
        }else{
            return this.NO_DISCOUNT;
        }
    }
}
