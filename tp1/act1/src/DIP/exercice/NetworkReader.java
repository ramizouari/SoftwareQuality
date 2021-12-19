package DIP.exercice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkReader implements IReader{
    private String _protocol;
    private String _host;
    private String _uri;
    public NetworkReader(String protocol, String host, String uri) {
        this._protocol = protocol ;
        this._host = host ;
        this._uri = uri ;
    }
    @Override
    public String read() throws IOException {
        URL url;
        url = new URL(_protocol, _host, _uri);
        InputStream in;
        in = url.openStream();
        InputStreamReader reader = new InputStreamReader(in);
        StringBuilder inputString1 = new StringBuilder();
        int c;
        c = reader.read();
        while (c != -1) {
            inputString1.append((char) c);
            c = reader.read();
        }
        String inputString = inputString1.toString();
        return inputString;
    }
}
