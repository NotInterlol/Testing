package parking;
public class User {
   private final String username;
   private final String password;
   private final String role;
   
   // Constructor to be used
   public User(String username, String password, String role) {
       this.username = username;
       this.password = password;
       this.role = role;
   }
   /* ------------ GETTERS ------------ */
   
   // Gives out the username
   public String getUsername() {
       return username;
   }
   
   // Gives out the password
   public String getPassword() {
       return password;
   }
   
   // Gives out the role
   public String getRole() {
	   return role;
   }
   
   // Checks if user is admin
   public boolean isAdmin() {
	   return role.equalsIgnoreCase("Admin");
   }
   

}

