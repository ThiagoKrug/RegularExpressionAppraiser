
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author thiago
 */
public class Nodo {
    
    private List<Nodo> nodos;
    private int altura;
    private String carac;
    private boolean operador;
    private boolean caractere;

    public Nodo() {
        this.nodos = new ArrayList<>();
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }
    
    public boolean addNodo(Nodo nodo) {
        return this.nodos.add(nodo);
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getCarac() {
        return carac;
    }

    public void setCarac(String carac) {
        this.carac = carac;
    }

    public boolean isOperador() {
        return operador;
    }

    public void setOperador(boolean operador) {
        this.operador = operador;
        this.caractere = !operador;
    }

    public boolean isCaractere() {
        return caractere;
    }

    public void setCaractere(boolean caractere) {
        this.caractere = caractere;
        this.operador = !caractere;
    }
    
}
