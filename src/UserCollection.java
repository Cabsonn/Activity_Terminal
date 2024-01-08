import java.util.List;
import java.util.ArrayList;
public class UserCollection {
    private final List<User> userlist;
    private User authenticated;

    public UserCollection(){
        authenticated = null;
        this.userlist = new ArrayList<>();
    }
    public User getAuthenticated(){
        return this.authenticated;
    }
    public void add(User user){
        this.userlist.add(user);
    }
    public User get(int i){
        return this.userlist.get(i);
    }
    public int size(){
        return this.userlist.size();
    }
    public void setAuthenticated(User user){
        this.authenticated = user;
    }
    public void listUsers(){
        if(!this.userlist.isEmpty()){
            for (User user : this.userlist) {
                System.out.print(user.getUsername()+"; ");
            }
        }
    }
    public void remove(User user){
        this.userlist.remove(user);
    }

}
