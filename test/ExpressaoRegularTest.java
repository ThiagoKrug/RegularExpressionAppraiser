
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
    }
    
    @Test
    public void testQuebrarParenteses() {
        ExpressaoRegular er = new ExpressaoRegular();
        Nodo n = er.quebrarParenteses("(a.b).(a|b).((a|b).c)", 0, new Nodo());
        System.out.println(n);
        for (Iterator<Nodo> it = n.getNodos().iterator(); it.hasNext();) {
            Nodo nodo = it.next();
            System.out.println(nodo);
        }
    }
}