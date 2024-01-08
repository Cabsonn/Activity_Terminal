import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanCollection {
        private final String list;
        private final List<Plan> planCollection;
        public PlanCollection(){
            this.planCollection = new ArrayList<>();
            this.list = "Planes:";
        }
        public void add(Plan plan){
            this.planCollection.add(plan);
        }
        public int size(){
            return this.planCollection.size();
        }
        public Plan get(int i){
            return this.planCollection.get(i);
        }
        public void listPlans(){
            Date actualDate = new Date();
            if(!this.planCollection.isEmpty()){
                for (Plan plan : this.planCollection) {
                    if (plan.getDate().after(actualDate)) {
                        System.out.println(this.list + plan.getSummary());
                    }
                }
            }
        }
        public void remove(Plan plan) {
            this.planCollection.remove(plan);
        }

}
