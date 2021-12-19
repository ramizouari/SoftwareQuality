package DIP.exercice;

import java.io.IOException;
import java.util.Base64;

public class EncodingModule {

    public void encodeWithFiles() throws IOException {
        IReader fileReader = new FileReader("DIP/src/com/directi/training/dip/exercise/beforeEncryption.txt");
        IWriter fileWriter = new FileWriter("DIP/src/com/directi/training/dip/exercise/afterEncryption.txt");

        String s = fileReader.read();
        String encodedString = Base64.getEncoder().encodeToString(s.getBytes());
        fileWriter.write(encodedString);
    }

    public void encodeBasedOnNetworkAndDatabase() throws IOException {
        IReader networkReader = new NetworkReader("http", "myfirstappwith.appspot.com", "/index.html");
        IWriter databaseWriter = new DatabaseWriter();
        String input = networkReader.read();
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        databaseWriter.write(encodedString);
    }
}
