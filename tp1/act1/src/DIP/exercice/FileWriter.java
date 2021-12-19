package DIP.exercice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Base64;

public class FileWriter implements  IWriter{

    private String _dest;
    public FileWriter(String dest) {
        this._dest = dest;
    }
    @Override
    public void write(String in) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new java.io.FileWriter(_dest));
            writer.append(in);
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
