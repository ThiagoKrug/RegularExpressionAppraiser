
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Informe a expressão regular:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String regularExpression = "";
        try {
            regularExpression = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String string = "";
        while (true) {
            System.out.println("Informe uma string:");
            try {
                string = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
    public void teste(String string) {
        String er = "abc";
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            char carac = string.charAt(i);
            if (carac == er.charAt(j)) {
                
                j++;
            }
        }
    }
}
