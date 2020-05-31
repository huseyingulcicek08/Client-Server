import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {

        System.out.println("Waiting for Player One...");

        ServerSocket ss = new ServerSocket(4999);
        Socket soc = ss.accept();
        System.out.println("Player One connected.");
        System.out.println("Player One is starting the game.");
        System.out.println("");

        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        ArrayList<String> words = new ArrayList<String>();
        String wordServer;
        String wordClient;
        int ifController;

        while(true){
            wordClient = in.readLine();          //The word is received from the Player One
            words.add(wordClient);              //The word is added to the arraylist
            System.out.println("Player One: " + wordClient);
            wordServer = serverInput.readLine();
            while(true) {
                ifController = 0;               //Checks for word mistakes
                for (int i = 0; i < words.size(); i++) {    //Checks whether the word has been written before
                    if (wordServer.equals(words.get(i))) {
                        System.out.println("This word was written. Write another word.");
                        ifController++;
                        break;
                    }
                }
                if(ifController==1) {          //If there is a mistake in the word, another word is written
                    wordServer = serverInput.readLine();
                    continue;
                }
                else
                    break;
            }
            //Checks whether the first two letters of the typed word is same as the last two letters of the previous word
            while(true) {
                ifController=0;
                if (wordClient.substring(wordClient.length() - 2).equals(wordServer.substring(0, 2))) {
                    break;
                }
                else {
                    System.out.println("The first two letters of this word are not the same as the last two letters of the previous word.");
                    System.out.println("Write another word.");
                    ifController++;
                }
                if (ifController > 0)          //If there is a mistake in the word, another word is written
                    wordServer = serverInput.readLine();
                continue;
            }
            while(true) {   //Second control of word mistake
                ifController = 0;               //Checks for word mistakes
                for (int i = 0; i < words.size(); i++) {    //Checks whether the word has been written before
                    if (wordServer.equals(words.get(i))) {
                        System.out.println("This word was written. Write another word.");
                        ifController++;
                        break;
                    }
                }
                if(ifController==1) {          //If there is a mistake in the word, another word is written
                    wordServer = serverInput.readLine();
                    continue;
                }
                else
                    break;
            }
            while(true) {   //Second control of typed or not
                ifController=0;
                if (wordClient.substring(wordClient.length() - 2).equals(wordServer.substring(0, 2))) {
                    break;
                }
                else {
                    System.out.println("The first two letters of this word are not the same as the last two letters of the previous word.");
                    System.out.println("Write another word.");
                    ifController++;
                }
                if (ifController > 0)          //If there is a mistake in the word, another word is written
                    wordServer = serverInput.readLine();
                continue;
            }
            words.add(wordServer);      //The word is added to the arraylist
            out.println(wordServer);    //The word is sent to the Player One
        }
    }
}