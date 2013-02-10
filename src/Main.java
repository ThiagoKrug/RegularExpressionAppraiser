
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
        AFN afn = null;
        ExpressaoRegular er = new ExpressaoRegular();
        if (er.validarExpressaoRegular(regularExpression)) {
            Nodo nodo = er.quebrarExpressaoRegular(regularExpression, 0, new Nodo());
            try {
                afn = er.transformarEmAFN(nodo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Expressão regular informada é inválida!");
        }
        
        String string = "";
        while (true) {
            System.out.println("Informe uma string:");
            try {
                string = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (afn.calcularStringValidaAFD(string)) {
                System.out.println("String informada é válida!");
            } else {
                System.out.println("String informada é inválida!");
            }
        }
    }
}
