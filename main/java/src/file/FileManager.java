package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import users.User;

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
    private static final String userRecordFilePath = "main/user_record.txt";

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
    // users.User authentication method that uses 'username' and 'password'
    // = Verify user credentials against information stored in text files
    public User authenticateUser(String username, String password){
        // I/O Exception handling = try with resources - catch
        // Chain a BufferedWriter on to a new FileWriter with file name(in this case 'userRecordFilePath')
        try(BufferedReader reader = new BufferedReader(new FileReader(userRecordFilePath))){
            // Make a String variable to hold each line as the line is read
            String line;
            while((line = reader.readLine()) != null) {
                String[] userData = line.split(","); // an array of String(userData) that is split by ',' = making userData into the pieces with ','.
                /*
                 * If user data's length is 3 = if there are three data for the user in the file split by ','
                 * If index 0 of user data is username
                 * If index 1 of user data is password
                 * -> Create the new user object with username, password, account balance
                 */
                if (userData.length == 3 && userData[0].equals(username) && userData[1].equals(password)) {
                    return new User(username, password, Double.parseDouble(userData[2]));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    // Balance Inquiry
    public double getBalance(String username){
        // I/O Exception handling = try with resources - catch
        // Chain a BufferedWriter on to a new FileWriter with file name(in this case 'userRecordFilePath')
        try(BufferedReader reader = new BufferedReader(new FileReader(userRecordFilePath))){
            // Make a String variable to hold each line as the line is read
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(","); // an array of String(userData) that is split by ','.
                /*
                 * If user data's length is 3 = if there are three data for the user in the file split by ','
                 * If index 0 of user data is username
                 * -> Get the account balance of the username
                 */
                if(userData.length == 3 && userData[0].equals(username)){
                    return  Double.parseDouble(userData[2]);
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1; // user not found
    }

    // Update the user's balance in the file when the user deposits money into their account using username and new account balance
    public void updateBalance(String username, double newBalance){
        // I/O Exception handling = try - catch

        try{
            // File object represents a file at a particular path(but doesn't represent the actual content of the file
            File inputFile = new File(userRecordFilePath);
            // Using a temporary file during the update process to ensure data consistency.
            File temporaryFile = new File("temporary.txt");

            // Chain a BufferedWriter on to a new FileWriter(+ FileReader) with file name(in this case 'inputFile' + 'temporaryFile')
            try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(temporaryFile))){
                // Make a String variable to hold each line as the line is read
                String line;
                while((line = reader.readLine()) != null){
                    String[] userData = line.split(","); // an array of String(userData) that is split by ','.
                    /*
                     * If user data's length is 3 = if there are three data for the user in the file split by ','
                     * If index 0 of user data is username
                     * -> Write the username, index 1 of user data(password) and new balance in the file
                     *    and then go to the new line
                     */
                    if(userData.length == 3 && userData[0].equals(username)){
                        writer.write(username + "," + userData[1] + "," + newBalance + "\n");
                    }else {
                        writer.write(line + "\n");
                    }
                }
            }

            temporaryFile.renameTo(inputFile); // in the end, rename the file to inputFile: means 'file name = userRecordFile'


        }catch(IOException e){
            e.printStackTrace();
        }

    }

}

