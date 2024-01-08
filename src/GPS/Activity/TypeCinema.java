package GPS.Activity;

public class TypeCinema extends ActivityType {
    public TypeCinema(int age){
        super("Cinema",age);
        super.discount = this.calculateDiscount();
    }
    public double calculateDiscount() {
        if(age <= 21){
            return 0.5;
        }else{
            return 1;
        }
    }
}
