package DIP.exercice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Base64;

public class FileReader implements  IReader{

    private String _src;
    public FileReader(String src) {
        this._src = src ;
    }
    @Override
    public String read() throws IOException {
        BufferedReader reader = null;
        String content = "";
        try {
            reader = new BufferedReader(new java.io.FileReader(_src));
            String aLine;
            while ((aLine = reader.readLine()) != null) {
                content += aLine;
            }
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }

        return content;
    }
}
