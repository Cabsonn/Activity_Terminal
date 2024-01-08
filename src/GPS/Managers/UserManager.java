package GPS.Managers;

import GPS.User.User;
import GPS.User.UserCollection;
import GPS.Error;

public class UserManager {
    private int id;
    private final UserCollection userCollection;
    public UserManager() {
        this.userCollection = new UserCollection();
        this.id = 1;
    }
    public User getAuthenticated() {
        return this.userCollection.getAuthenticated();
    }
    public User addUser(String username, int age, String phone, String password) {
        User user = new User(this.id, username, age, phone, password);
        userCollection.add(user);
        this.id++;
        return user;
    }
    public Error checkName(User user, String username) {
        Error error = Error.NULL;
        if (user.getUsername().equals(username)) {
            return Error.DUPLICATE_USERNAME;
        }
        return error;
    }

    public Error checkPhone(User user, String phone) {
        Error error = Error.NULL;
        if (user.getPhone().equals(phone)) {
            return Error.DUPLICATE_PHONE;
        }
        return error;
    }

    public Error validateExistingUser(String username, String phone) {
        for (int i=0; i<this.userCollection.size();i++) {
            Error nameError = checkName(this.userCollection.get(i), username);
            if (nameError != Error.NULL) {
                return nameError;
            }
            Error phoneError = checkPhone(this.userCollection.get(i), phone);
            if (phoneError != Error.NULL) {
                return phoneError;
            }
        }
        return Error.NULL;
    }

    public Error check_In(String username, int age, String phone, String password) {
        Error error = validateExistingUser(username, phone);
        if (!error.isNull()) {
            return error;
        }
        error = User.checkAge(age);
        if (!error.isNull()) {
            return error;
        }
        error = User.checkPassword(password);
        if (!error.isNull()) {
            return error;
        }
        return Error.NULL;
    }

    private boolean isUserLoggedIn() {
        return this.userCollection.getAuthenticated() != null;
    }

    public User findUser(String username, String password) {
        for (int i = 0; i<this.userCollection.size();i++) {
            if (username.equals(this.userCollection.get(i).getUsername()) &&
                    password.equals(this.userCollection.get(i).getPassword())) {
                return this.userCollection.get(i);
            }
        }
        return null;
    }


    public Error login(String username, String password) {
        if (isUserLoggedIn()) {
            return Error.USER_ALREADY_LOGGED_IN;
        }
        User authenticatedUser = findUser(username, password);
        if (authenticatedUser != null) {
            this.userCollection.setAuthenticated(authenticatedUser);
            return Error.NULL;
        }
        return Error.USER_NOT_FOUND;
    }

    public Error logOut() {
        if (this.userCollection.getAuthenticated() != null) {
            this.userCollection.setAuthenticated(null);
            return Error.NULL;
        }
        return Error.NOT_LOGGED_USER;
    }
    public Error checkChangePassword(UserManager userManager){
        if(!userManager.isUserLoggedIn()){
            return Error.NOT_LOGGED_USER;
        }else{
            return Error.NULL;
        }

    }
    public void changePassword(String newPass, UserManager userManager){
        User user = userManager.getAuthenticated();
        user.setPassword(newPass);
    }
    public Error checkChangeUsername(String newName, UserManager userManager){
        if(!userManager.isUserLoggedIn()){
            return Error.NOT_LOGGED_USER;
        }if(!this.checkName(userManager.getAuthenticated(),newName).isNull()){
            return Error.DUPLICATE_USERNAME;
        }else{
            return Error.NULL;
        }

    }
    public void changeUsername(String newUsername, UserManager userManager){
        User user = userManager.getAuthenticated();
        user.setUsername(newUsername);
    }

}

