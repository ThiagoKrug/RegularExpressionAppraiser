
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author thiago
 */
public class Concatenacao {
    
    public AFN calcularConcatenacao(AFN afn1, AFN afn2) {
        List<Estado> estadosFinais = afn1.getConjuntoEstadosFinais();
        for (Iterator<Estado> it = estadosFinais.iterator(); it.hasNext();) {
            Estado estadoFinal = it.next();
            FuncaoTransicao ft = new FuncaoTransicao();
            ft.setCaractere(Caractere.STRING_VAZIA);
            ft.setResultado(afn2.getEstadoInicial());
            estadoFinal.addFuncaoTransicao(ft);
        }
        afn1.removeEstadosFinais();
        afn1.setConjuntoEstadosFinais(afn2.getConjuntoEstadosFinais());
        afn1.addEstados(afn2.getConjuntoEstados());
        afn1.addAlfabeto(afn2.getAlfabeto());
        afn1.addCaractere(Caractere.STRING_VAZIA);
        return afn1;
    }
    
}
