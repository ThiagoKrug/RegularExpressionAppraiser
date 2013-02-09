
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiago
 */
public class Estado {

    private static int cont = 0;
    private String id;
    private List<FuncaoTransicao> funcoesTransicao;
    private boolean estadoInicial;
    private boolean estadoFinal;

    public Estado() {
        this.id = Estado.getIdEstado();
        this.funcoesTransicao = new ArrayList<>();
    }

    public Estado(List<FuncaoTransicao> funcoesTransicao, boolean estadoInicial, boolean estadoFinal) {
        this.id = Estado.getIdEstado();
        this.funcoesTransicao = funcoesTransicao;
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
    }
    
    private static String getIdEstado() {
        return "q" + (cont++);
    }

    public String getId() {
        return id;
    }

    public List<FuncaoTransicao> getFuncoesTransicao() {
        return funcoesTransicao;
    }

    public void setFuncoesTransicao(List<FuncaoTransicao> funcoesTransicao) {
        this.funcoesTransicao = funcoesTransicao;
    }

    public boolean addFuncaoTransicao(FuncaoTransicao funcaoTransicao) {
        return this.funcoesTransicao.add(funcaoTransicao);
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }
}
