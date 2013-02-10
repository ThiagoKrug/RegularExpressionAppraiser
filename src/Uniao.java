
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author thiago
 */
public class Uniao {

    public AFN calcularUniao(AFN afn1, AFN afn2) {
        Estado estadoInicial1 = afn1.getEstadoInicial();
        Estado estadoInicial2 = afn2.getEstadoInicial();
        
        Estado estadoInicial = new Estado();
        estadoInicial.setEstadoInicial(true);
        
        FuncaoTransicao ft1 = new FuncaoTransicao();
        ft1.setCaractere(Caractere.STRING_VAZIA);
        ft1.setResultado(estadoInicial1);
        FuncaoTransicao ft2 = new FuncaoTransicao();
        ft2.setCaractere(Caractere.STRING_VAZIA);
        ft2.setResultado(estadoInicial2);
        
        estadoInicial.addFuncaoTransicao(ft1);
        estadoInicial.addFuncaoTransicao(ft2);
        
        afn1.addEstados(afn2.getConjuntoEstados());
        afn1.setConjuntoEstadosFinais(afn2.getConjuntoEstadosFinais());
        afn1.addEstado(estadoInicial);
        afn1.addAlfabeto(afn2.getAlfabeto());
        afn1.addCaractere(Caractere.STRING_VAZIA);
        afn1.setEstadoInicial(estadoInicial);
        return afn1;
    }
}
