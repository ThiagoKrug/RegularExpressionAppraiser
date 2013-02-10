
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author thiago
 */
public class AFN {

    private List<Caractere> alfabeto;
    private List<Estado> conjuntoEstados;
    private Estado estadoInicial;
    private List<Estado> conjuntoEstadosFinais;

    public AFN() {
        this.alfabeto = new ArrayList<>();
        this.conjuntoEstados = new ArrayList<>();
        this.conjuntoEstadosFinais = new ArrayList<>();
    }

    public AFN(List<Caractere> alfabeto, List<Estado> conjuntoEstados, Estado estadoInicial, List<Estado> conjuntoEstadosFinais) throws Exception {
        this.alfabeto = alfabeto;
        this.conjuntoEstados = conjuntoEstados;
        this.estadoInicial = estadoInicial;
        this.conjuntoEstadosFinais = new ArrayList<>();
        for (Iterator<Estado> it = conjuntoEstadosFinais.iterator(); it.hasNext();) {
            Estado estado = it.next();
            if (estado.isEstadoFinal()) {
                this.conjuntoEstadosFinais.add(estado);
            } else {
                throw new Exception("Não foi possível adicionar estado final à lista de estados finais. O estado informado não é um estado final.");
            }
        }
    }

    public List<Caractere> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(List<Caractere> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public void addAlfabeto(List<Caractere> alfabeto) {
        for (Iterator<Caractere> it = alfabeto.iterator(); it.hasNext();) {
            Caractere caractere = it.next();
            this.addCaractere(caractere);
        }
    }

    public boolean addCaractere(Caractere caractere) {
        if (this.alfabeto.contains(caractere) == false) {
            return this.alfabeto.add(caractere);
        }
        return false;
    }

    public List<Estado> getConjuntoEstados() {
        return conjuntoEstados;
    }

    public void setConjuntoEstados(List<Estado> conjuntoEstados) {
        this.conjuntoEstados = conjuntoEstados;
    }

    @Deprecated
    public void calcularConjuntoEstados() {
        Estado estadoAtual = this.getEstadoInicial();
        List<Estado> estados = new ArrayList<>();
        while (estadoAtual != null) {
            for (Iterator<FuncaoTransicao> it = estadoAtual.getFuncoesTransicao().iterator(); it.hasNext();) {
                FuncaoTransicao funcaoTransicao = it.next();
                estadoAtual = funcaoTransicao.getResultado();
            }
        }
    }

    @Deprecated
    private List<Estado> retrieveEstados(Estado estado, List<Estado> estados) {
        for (Iterator<FuncaoTransicao> it = estado.getFuncoesTransicao().iterator(); it.hasNext();) {
            FuncaoTransicao funcaoTransicao = it.next();
            Estado resultado = funcaoTransicao.getResultado();
            if (estados.contains(resultado) == false) {
                estados.add(resultado);
                return this.retrieveEstados(estado, estados);
            }
        }
        return null;
    }

    public boolean addEstado(Estado estado) {
        if (this.conjuntoEstados.contains(estado) == false) {
            return this.conjuntoEstados.add(estado);
        }
        return false;
    }

    public void addEstados(List<Estado> estados) {
        for (Iterator<Estado> it = estados.iterator(); it.hasNext();) {
            Estado estado = it.next();
            this.addEstado(estado);
        }
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public List<Estado> getConjuntoEstadosFinais() {
        return conjuntoEstadosFinais;
    }

    public void setConjuntoEstadosFinais(List<Estado> conjuntoEstadosFinais) {
        boolean estadoFinal = true;
        for (Iterator<Estado> it = conjuntoEstadosFinais.iterator(); it.hasNext();) {
            Estado estado = it.next();
            if (!estado.isEstadoFinal()) {
                estadoFinal = false;
                break;
            }
        }
        if (estadoFinal) {
            this.conjuntoEstadosFinais = conjuntoEstadosFinais;
        }
    }

    public boolean addEstadoFinal(Estado estadoFinal) {
        if (this.conjuntoEstadosFinais.contains(estadoFinal) == false && estadoFinal.isEstadoFinal()) {
            return this.conjuntoEstadosFinais.add(estadoFinal);
        }
        return false;
    }

    public void addEstadosFinais(List<Estado> estadosFinais) {
        for (Iterator<Estado> it = estadosFinais.iterator(); it.hasNext();) {
            Estado estado = it.next();
            this.addEstadoFinal(estado);
        }
    }

    public void removeEstadosFinais() {
        for (Iterator<Estado> it = this.conjuntoEstados.iterator(); it.hasNext();) {
            Estado estado = it.next();
            estado.setEstadoFinal(false);
        }
    }

    public boolean calcularStringValidaAFD(String string) {
        Estado estadoAtual = this.estadoInicial;
        for (int i = 0; i < string.length(); i++) {
            String carac = String.valueOf(string.charAt(i));
            for (Iterator<FuncaoTransicao> it = estadoAtual.getFuncoesTransicao().iterator(); it.hasNext();) {
                FuncaoTransicao funcaoTransicao = it.next();
                if (carac.equalsIgnoreCase(funcaoTransicao.getCaractere().getCaractere())) {
                    estadoAtual = funcaoTransicao.getResultado();
                } else if (funcaoTransicao.getCaractere().getCaractere().equalsIgnoreCase(Caractere.STRING_VAZIA.getCaractere())) {
                    estadoAtual = funcaoTransicao.getResultado();
                    i--;
                }
            }
        }
        if (estadoAtual.isEstadoFinal()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String alfabeto = this.prepareToPrintAlfabeto();
        String conjuntoEstados = this.prepareToPrintConjuntoEstados();
        String conjuntoEstadosFinais = this.prepareToPrintConjuntoEstadosFinais();
        String estadoInicial = "Estado Inicial: {" + this.estadoInicial.getId() + "}\n";
        String funcoesTransicao = this.prepareToPrintTabelaFuncoesTransicao();
        return alfabeto + conjuntoEstados + conjuntoEstadosFinais + estadoInicial + funcoesTransicao;
    }

    private String prepareToPrintAlfabeto() {
        StringBuilder sb = new StringBuilder();
        sb.append("Afabeto: {");
        for (Iterator<Caractere> it = alfabeto.iterator(); it.hasNext();) {
            Caractere caractere = it.next();
            sb.append(caractere.getCaractere());
            sb.append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "}\n");
        return sb.toString();
    }

    private String prepareToPrintConjuntoEstados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conjunto de Estados: {");
        for (Iterator<Estado> it = conjuntoEstados.iterator(); it.hasNext();) {
            Estado estado = it.next();
            sb.append(estado.getId());
            sb.append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "}\n");
        return sb.toString();
    }

    private String prepareToPrintConjuntoEstadosFinais() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conjunto de Estados Finais: {");
        for (Iterator<Estado> it = conjuntoEstadosFinais.iterator(); it.hasNext();) {
            Estado estado = it.next();
            sb.append(estado.getId());
            sb.append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "}\n");
        return sb.toString();
    }

    private String prepareToPrintTabelaFuncoesTransicao() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tabela de Funções de Transição:\n");
        sb.append("   | ");
        for (Iterator<Caractere> it = alfabeto.iterator(); it.hasNext();) {
            Caractere caractere = it.next();
            sb.append(caractere.getCaractere());
            sb.append(" | ");
        }
        sb.append("\n");
        for (Iterator<Estado> it = conjuntoEstados.iterator(); it.hasNext();) {
            Estado estado = it.next();
            sb.append(estado.getId());
            sb.append(" | ");

            for (Iterator<FuncaoTransicao> it1 = estado.getFuncoesTransicao().iterator(); it1.hasNext();) {
                FuncaoTransicao funcaoTransicao = it1.next();

                for (Iterator<Caractere> it2 = alfabeto.iterator(); it2.hasNext();) {
                    Caractere caractere = it2.next();
                    if (caractere.getCaractere().equalsIgnoreCase(funcaoTransicao.getCaractere().getCaractere())) {
                        sb.append(funcaoTransicao.getResultado().getId());
                    } else {
                        sb.append("  ");
                    }
                    sb.append(" | ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
