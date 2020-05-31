import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket soc = new Socket("localhost",4999);

        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        ArrayList<String> words = new ArrayList<String>();
        String wordClient; //Player one's word
        String wordServer;  //Player two's word

        int ifController;   //Checks for word mistakes

        System.out.println("Player Two is ready.");
        System.out.println("Start the game.");
        System.out.println("");

        wordClient = clientInput.readLine();    //First word
        words.add(wordClient);
        out.println(wordClient);

        while(true) {
            wordServer = in.readLine();         //The word is received from the Player Two
            words.add(wordServer);              //The word is added to the arraylist
            System.out.println("Player Two: " + wordServer);
            wordClient = clientInput.readLine();
            while(true) {
                ifController = 0;                           //Checks for word mistakes
                for (int i = 0; i < words.size(); i++) {    //Checks whether the word has been written before
                    if (wordClient.equals(words.get(i))) {
                        System.out.println("This word was written. Write another word.");
                        ifController++;
                        break;
                    }
                }
                if(ifController==1) {          //If there is a mistake in the word, another word is written
                    wordClient = clientInput.readLine();
                    continue;
                }
                else
                    break;
            }
            //Checks whether the first two letters of the typed word is same as the last two letters of the previous word
            while(true) {
                ifController=0;
                if (wordServer.substring(wordServer.length() - 2).equals(wordClient.substring(0, 2))) {
                    break;
                }
                else {
                    System.out.println("The first two letters of this word are not the same as the last two letters of the previous word.");
                    System.out.println("Write another word.");
                    ifController++;
                }
                if(ifController==1){        //If there is a mistake in the word, another word is written
                    wordClient = clientInput.readLine();
                    continue;
                }
            }
            while(true) {   //Second control of word mistake
                ifController = 0;                           //Checks for word mistakes
                for (int i = 0; i < words.size(); i++) {    //Checks whether the word has been written before
                    if (wordClient.equals(words.get(i))) {
                        System.out.println("This word was written. Write another word.");
                        ifController++;
                        break;
                    }
                }
                if(ifController==1) {          //If there is a mistake in the word, another word is written
                    wordClient = clientInput.readLine();
                    continue;
                }
                else
                    break;
            }
            while(true) {   //Second control of typed or not
                ifController=0;
                if (wordServer.substring(wordServer.length() - 2).equals(wordClient.substring(0, 2))) {
                    break;
                }
                else {
                    System.out.println("The first two letters of this word are not the same as the last two letters of the previous word.");
                    System.out.println("Write another word.");
                    ifController++;
                }
                if(ifController==1){        //If there is a mistake in the word, another word is written
                    wordClient = clientInput.readLine();
                    continue;
                }
            }
            words.add(wordClient);      //The word is added to the arraylist
            out.println(wordClient);    //The word is sent to the Player Two
        }
    }
}