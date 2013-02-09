
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thiago
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void doisEstados() {
        Estado q0 = new Estado();
        q0.setEstadoInicial(true);
        Estado q1 = new Estado();
        q1.setEstadoFinal(true);
        FuncaoTransicao ft = new FuncaoTransicao(q1, new Caractere("a"));
        q0.addFuncaoTransicao(ft);

        AFN afn = new AFN();
        afn.setEstadoInicial(q0);
        assertTrue(afn.calcularStringValida("a"));
    }

    @Test
    public void tresEstados() {
        Estado q0 = new Estado();
        q0.setEstadoInicial(true);
        Estado q1 = new Estado();
        Estado q2 = new Estado();
        q2.setEstadoFinal(true);

        FuncaoTransicao ft1 = new FuncaoTransicao(q1, new Caractere("a"));
        q0.addFuncaoTransicao(ft1);

        FuncaoTransicao ft2 = new FuncaoTransicao(q2, new Caractere("b"));
        q1.addFuncaoTransicao(ft2);

        AFN afn = new AFN();
        afn.setEstadoInicial(q0);
        assertTrue(afn.calcularStringValida("ab"));
    }
    
    @Test
    public void testConcatenacao() {
        AFN afn1 = new AFN();
        
        Estado q0 = new Estado();
        q0.setEstadoInicial(true);
        Estado q1 = new Estado();
        q1.setEstadoFinal(true);
        
        Caractere a = new Caractere("a");
        
        FuncaoTransicao ft = new FuncaoTransicao(q1, a);
        q0.addFuncaoTransicao(ft);

        afn1.addCaractere(a);
        afn1.addEstado(q0);
        afn1.addEstado(q1);
        afn1.setEstadoInicial(q0);
        afn1.addEstadoFinal(q1);
        System.out.println(afn1.toString());
        
        AFN afn2 = new AFN();
        
        Estado q2 = new Estado();
        q2.setEstadoInicial(true);
        Estado q3 = new Estado();
        q3.setEstadoFinal(true);
        
        Caractere b = new Caractere("b");
        
        FuncaoTransicao ft2 = new FuncaoTransicao(q3, b);
        q2.addFuncaoTransicao(ft2);

        afn2.addCaractere(b);
        afn2.addEstado(q2);
        afn2.addEstado(q3);
        afn2.setEstadoInicial(q2);
        afn2.addEstadoFinal(q3);
        System.out.println(afn2.toString());
        System.out.println();
        
        AFN result = new Concatenacao().calcularConcatenacao(afn1, afn2);
        System.out.println(result);
        
        List<Caractere> resultAlfabeto = new ArrayList<>();
        resultAlfabeto.add(a);
        resultAlfabeto.add(b);
        resultAlfabeto.add(Caractere.STRING_VAZIA);
        assertEquals(result.getAlfabeto(), resultAlfabeto);
        
        List<Estado> resultEstados = new ArrayList<>();
        resultEstados.add(q0);
        resultEstados.add(q1);
        resultEstados.add(q2);
        resultEstados.add(q3);
        assertEquals(result.getConjuntoEstados(), resultEstados);
        
        assertEquals(result.getEstadoInicial(), q0);
        
        List<Estado> resultEstadosFinais = new ArrayList<>();
        resultEstadosFinais.add(q3);
        assertEquals(result.getConjuntoEstadosFinais(), resultEstadosFinais);
    }
}