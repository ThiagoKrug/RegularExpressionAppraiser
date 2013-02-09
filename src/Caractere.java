
/**
 *
 * @author thiago
 */
public class Caractere {

    public static final Caractere STRING_VAZIA = new Caractere("ε");
    public static final Caractere CONJUNTO_VAZIO = new Caractere("Ø");
    private String caractere;

    public Caractere() {
    }

    public Caractere(String caractere) {
        this.caractere = caractere;
    }

    public String getCaractere() {
        return caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }
}
