
/**
 *
 * @author thiago
 */
public class FuncaoTransicao {

    private Estado resultado;
    private Caractere caractere;

    public FuncaoTransicao() {
    }

    public FuncaoTransicao(Estado resultado, Caractere caractere) {
        this.resultado = resultado;
        this.caractere = caractere;
    }

    public Caractere getCaractere() {
        return caractere;
    }

    public void setCaractere(Caractere caractere) {
        this.caractere = caractere;
    }

    public Estado getResultado() {
        return resultado;
    }

    public void setResultado(Estado resultado) {
        this.resultado = resultado;
    }
    
    public Estado calcularFuncaoTransicao(Caractere caratere) {
        if (this.caractere == caratere) {
            return this.resultado;
        }
        return null;
    }
}
