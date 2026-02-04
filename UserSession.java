package parking;

public class UserSession {
	private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
    
    public static void logout() {
        currentUser = null;
    }
}
