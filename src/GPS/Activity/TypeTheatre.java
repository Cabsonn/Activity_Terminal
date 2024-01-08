package GPS.Activity;

public class TypeTheatre extends ActivityType {
    private final int YOUNG_AGE = 25;
    private final double HALF_DISCOUNT = 0.5;
    private final double OLD_DISCOUNT = 0.7;
    private final int NO_DISCOUNT = 0;
    public TypeTheatre(int age){
        super("Theatre", age);
        super.discount = this.calculateDiscount();
    }
    public double calculateDiscount(){
        if(age <= 25){
            return 0.5;
        }
        if(age >= 65){
            return 0.7;
        }else{
            return 0;
        }
    }
}
