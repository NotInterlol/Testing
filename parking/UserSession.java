package parking;

public class UserSession {
	private static User currentUser;
	
	// Stores the current user
    public static void setUser(User user) {
        currentUser = user;
    }
    
    // Checks who is getting logged in
    public static User getUser() {
        return currentUser;
    }
    
    // Checks if the user is admin
    public static boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
    
    // Lets users logout
    public static void logout() {
        currentUser = null;
    }
}
