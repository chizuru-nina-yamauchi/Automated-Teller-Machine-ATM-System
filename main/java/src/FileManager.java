import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    // Singleton Pattern File Manager
    /*
    Singleton pattern restricts the instantiation of a class and
    ensures that only one instance of the class exists in the Java Virtual Machine.
    The singleton class must provide a global access point to get the instance of the class.
    */

    // Private static variable of the same class that is the only instance of the class.
    // Static = one per class
    private static FileManager instance;
    private static final String userRecordFilePath = "user_record.txt";

    private FileManager(){
        // Private constructor to prevent external instantiation
    }

    /*
    Public static method that returns the instance of the class,
    this is the global access point for the outer world to get the instance of the singleton class.
    */
    public static FileManager getInstance(){
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }
    // User authentication method that uses 'username' and 'password'
    public User authenticateUser(String username, String password){
        // I/O Exception handling = try - catch
        // Chain a BufferedWriter on to a new FileWriter with file name(in this case 'userRecordFilePath')
        try(BufferedReader reader = new BufferedReader(new FileReader(userRecordFilePath))){
            // Make a String variable to hold each line as the line is read
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(","); // an array of String(userData) that is split by ','.
                /*
                * If user data's length is 3 = if there are three data for the user in the file split by ','
                * If index 0 of user data is username
                * If index 1 of user data is password
                * -> Create the new user object with username, password, account balance
                */
                if(userData.length == 3 && userData[0].equals(username) && userData[1].equals(password)){
                    return new User(username,password, Double.parseDouble(userData[2]));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }



}
