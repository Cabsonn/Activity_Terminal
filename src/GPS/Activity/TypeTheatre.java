package GPS.Activity;

public class TypeTheatre extends ActivityType {
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
            return 1;
        }
    }
}
