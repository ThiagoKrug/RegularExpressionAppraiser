
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
        assertTrue(er.validarExpressaoRegular("abc"));
        assertTrue(er.validarExpressaoRegular("a.bc"));
        assertTrue(er.validarExpressaoRegular("a.b.c"));
        assertTrue(er.validarExpressaoRegular("a|bc"));
        assertTrue(er.validarExpressaoRegular("a|b|c"));
        assertTrue(er.validarExpressaoRegular("a*b|c"));
        assertTrue(er.validarExpressaoRegular("a*b*c"));
        assertTrue(er.validarExpressaoRegular("a*b*c*"));
        assertTrue(er.validarExpressaoRegular("(abc)"));
        assertTrue(er.validarExpressaoRegular("((ab)c)"));
        assertTrue(er.validarExpressaoRegular("((ab)*c)"));
    }
    
    @Test
    public void validarExpressaoRegularFalse() {
        ExpressaoRegular er = new ExpressaoRegular();
        assertFalse(er.validarExpressaoRegular("abc."));
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
    }
    
    @Test
    public void corrigirExpressaoRegular() {
        ExpressaoRegular er = new ExpressaoRegular();
        assertEquals("a.b.c", er.corrigirExpressaoRegular("abc"));
        assertEquals("a.b.c", er.corrigirExpressaoRegular("a.bc"));
        assertEquals("a.b.c", er.corrigirExpressaoRegular("a.b.c"));
        assertEquals("a|b.c", er.corrigirExpressaoRegular("a|bc"));
        assertEquals("a|b|c", er.corrigirExpressaoRegular("a|b|c"));
        assertEquals("a*.b|c", er.corrigirExpressaoRegular("a*b|c"));
        assertEquals("a*.b*.c", er.corrigirExpressaoRegular("a*b*c"));
        assertEquals("a*.b*.c*", er.corrigirExpressaoRegular("a*b*c*"));
        assertEquals("(a.b.c)", er.corrigirExpressaoRegular("(abc)"));
        assertEquals("((a.b).c)", er.corrigirExpressaoRegular("((ab)c)"));
        assertEquals("((a.b)*.c)", er.corrigirExpressaoRegular("((ab)*c)"));
    }
}