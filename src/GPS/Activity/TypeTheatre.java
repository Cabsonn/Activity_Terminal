package GPS.Activity;

public class TypeTheatre extends ActivityType {
    private final int YOUNG_AGE = 25;
    private final int OLD_AGE = 65;
    private final double HALF_DISCOUNT = 0.5;
    private final double OLD_DISCOUNT = 0.7;
    private final int NO_DISCOUNT = 0;
    public TypeTheatre(int age){
        super("Theatre", age);
        super.discount = this.calculateDiscount();
    }
    public double calculateDiscount(){
        if(age <= this.YOUNG_AGE){
            return this.HALF_DISCOUNT;
        }
        if(age >= this.OLD_AGE){
            return this.OLD_DISCOUNT;
        }else{
            return this.NO_DISCOUNT;
        }
    }
}
