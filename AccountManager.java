package parking;

public class AccountManager {
	// Inserted accounts
   private static final User[] users = {
           new User("Earhl", "leader123", "ADMIN"),
           new User("Sydney", "manager123", "CUSTOMER"),
           new User("Wylle", "client123", "CUSTOMER")
   };
   
   // Account Verification
   public static User login(String username, String password) {
       for (User user : users) {
           if (user.getUsername().equals(username) &&
               user.getPassword().equals(password)) {
               return user;
           }
       }
       return null;
   }
}


