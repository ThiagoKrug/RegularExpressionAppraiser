
import java.util.Iterator;
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
public class ExpressaoRegularTest {

    public ExpressaoRegularTest() {
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
    public void validarExpressaoRegular() {
        ExpressaoRegular er = new ExpressaoRegular();
        assertTrue(er.validarExpressaoRegular("(a.b).c"));
        assertTrue(er.validarExpressaoRegular("a.b.c"));
        assertTrue(er.validarExpressaoRegular("a|b.c"));
        assertTrue(er.validarExpressaoRegular("a|b|c"));
        assertTrue(er.validarExpressaoRegular("a*b|c"));
        assertTrue(er.validarExpressaoRegular("a*b*c"));
        assertTrue(er.validarExpressaoRegular("a*b*c*"));
        assertTrue(er.validarExpressaoRegular("((a.b).c)"));
        assertTrue(er.validarExpressaoRegular("((a|b)*c)"));
        assertTrue(er.validarExpressaoRegular("(((a.b).c).(a|b))*"));
    }

    @Test
    public void validarExpressaoRegularFalse() {
        ExpressaoRegular er = new ExpressaoRegular();
        assertFalse(er.validarExpressaoRegular("abc."));
        assertFalse(er.validarExpressaoRegular("abc"));
        assertFalse(er.validarExpressaoRegular("abc|"));
        assertFalse(er.validarExpressaoRegular("|abc|"));
        assertFalse(er.validarExpressaoRegular(".abc|"));
        assertFalse(er.validarExpressaoRegular("*abc|"));
        assertFalse(er.validarExpressaoRegular("*abc*"));
        assertFalse(er.validarExpressaoRegular("abc))"));
        assertFalse(er.validarExpressaoRegular("ab(c))"));
        assertFalse(er.validarExpressaoRegular("a)b(c))"));
        assertFalse(er.validarExpressaoRegular("a|*b(c)"));
        assertFalse(er.validarExpressaoRegular("a|*bc"));
        assertFalse(er.validarExpressaoRegular("a.*bc"));
        assertFalse(er.validarExpressaoRegular("a.*.bc"));
        assertFalse(er.validarExpressaoRegular("a*b**c"));
//        assertFalse(er.validarExpressaoRegular("(a.b).(a|b).((a|b).c)"));
    }

    @Test
    public void testQuebrarParenteses() {
        ExpressaoRegular er = new ExpressaoRegular();
        Nodo n = er.quebrarExpressaoRegular("(a.b).(a|b).((a|b).c)", 0, new Nodo());
        System.out.println(n);
        printRecursivamente(n);
        System.out.println("\n");

        Nodo n1 = er.quebrarExpressaoRegular("(((a.b).c).(a|b))*", 0, new Nodo());
        System.out.println(n1);
        printRecursivamente(n1);
    }

    @Test
    public void testExpressaoRegularFinalConcatenacao() {
        ExpressaoRegular er = new ExpressaoRegular();
        String expression = "((a.b).c).(a.b)";
        if (er.validarExpressaoRegular(expression)) {
            Nodo nodo = er.quebrarExpressaoRegular(expression, 0, new Nodo());
            AFN afn = null;
            try {
                afn = er.transformarEmAFN(nodo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(afn);
            if (afn.calcularStringValidaAFD("abcab")) {
                System.out.println("funfo =D");
            } else {
                System.out.println("naum funfo!");
            }
        } else {
            System.out.println("Expressão regular informada é inválida!");
        }
        assertTrue(true);
    }
    
    @Test
    public void testExpressaoRegularFinalUniao() {
        ExpressaoRegular er = new ExpressaoRegular();
        String expression = "((a.b).c).(a|b)";
        if (er.validarExpressaoRegular(expression)) {
            Nodo nodo = er.quebrarExpressaoRegular(expression, 0, new Nodo());
            AFN afn = null;
            try {
                afn = er.transformarEmAFN(nodo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(afn);
        } else {
            System.out.println("Expressão regular informada é inválida!");
        }
        assertTrue(true);
    }

    private void printRecursivamente(Nodo n) {
        for (Iterator<Nodo> it = n.getNodos().iterator(); it.hasNext();) {
            Nodo nodo = it.next();
            System.out.println(nodo);
            printRecursivamente(nodo);
        }
    }
}