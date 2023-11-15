import org.mindrot.jbcrypt.BCrypt;

public class UserManagement {

    /**
     * Registers a new user with a hashed password.
     *
     * @param username The username of the user.
     * @param plainTextPassword The plain text password of the user.
     */
    public void registerUser(String username, String plainTextPassword) {
        // Hash the password
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

        // Here, add your code to store the username and hashed password in your database.
        // For example: database.saveUser(username, hashedPassword);

        System.out.println("User registered with hashed password.");
    }

    /**
     * Attempts to log in a user.
     *
     * @param username The username of the user.
     * @param plainTextPassword The password provided by the user trying to log in.
     * @return true if the login is successful, false otherwise.
     */
    public boolean loginUser(String username, String plainTextPassword) {
        // Here, retrieve the stored hashed password from the database based on the username.
        // String storedHash = database.getHashedPassword(username);
        String storedHash = ""; // Replace this with actual retrieval of the hashed password.

        // Check if the entered password matches the stored hash
        if (BCrypt.checkpw(plainTextPassword, storedHash)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }

    public static void main(String[] args) {
        UserManagement userManager = new UserManagement();

        // Example usage
        String username = "testUser";
        String password = "myPassword123";

        // Registering a new user
        userManager.registerUser(username, password);

        // Attempting to log in
        boolean loginSuccess = userManager.loginUser(username, password);
        System.out.println("Login was " + (loginSuccess ? "successful" : "unsuccessful"));
    }
}
