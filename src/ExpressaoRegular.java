
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author thiago
 */
public class ExpressaoRegular {

    public boolean validarExpressaoRegular(String er) {
        int paranteses = 0;
        Character lastChar = null;
        for (int i = 0; i < er.length(); i++) {
            char carac = er.charAt(i);
            if (paranteses < 0) {
                return false;
            }
            if (carac == "(".charAt(0)) {
                paranteses++;
            } else if (carac == ")".charAt(0)) {
                paranteses--;
            } else if (isOperador(carac)) {
                if (lastChar == null) {
                    return false;
                }
                if (i == er.length() - 1 && carac != "*".charAt(0)) {
                    return false;
                }
                if ((i == er.length() - 1) == false) {
                    if (er.charAt(i + 1) == ")".charAt(0) && carac != "*".charAt(0)) {
                        return false;
                    }
                }
                if (lastChar.charValue() == "|".charAt(0) || lastChar.charValue() == ".".charAt(0)) {
                    return false;
                }
                if (lastChar.charValue() == "*".charAt(0) && carac == "*".charAt(0)) {
                    return false;
                }
            } else {
                if (lastChar != null) {
                    if (isOperador(lastChar) == false && lastChar.charValue() != "(".charAt(0) && lastChar.charValue() != ")".charAt(0)) {
                        return false;
                    }
                }
            }
            lastChar = carac;
        }
        if (paranteses == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOperador(char carac) {
        if (carac == ".".charAt(0)) {
            return true;
        } else if (carac == "|".charAt(0)) {
            return true;
        } else if (carac == "*".charAt(0)) {
            return true;
        }
        return false;
    }

    public Nodo quebrarExpressaoRegular(String er, int altura, Nodo n) {
        int parenteses = 0, inicio = 0, fim;
        boolean a = false;
        for (int i = 0; i < er.length(); i++) {
            char c = er.charAt(i);
            if ("(".charAt(0) == c) {
                parenteses++;
                a = true;
                if (parenteses == 1) {
                    inicio = i;
                }
            } else if (")".charAt(0) == c) {
                parenteses--;
                if (parenteses == 0) {
                    fim = i;
                    n.setAltura(altura);
                    String carac = er.substring(inicio + 1, fim);
                    if (carac.length() > 1) {
                        Nodo nodo = quebrarExpressaoRegular(carac, altura + 1, new Nodo());
                        if (nodo.getCarac().equalsIgnoreCase("") == false) {
                            n.addNodo(nodo);
                        }
                    }
                    n.setCarac(n.getCarac() + "§");
                }
            } else if (parenteses == 0) {
                n.setCarac(n.getCarac() + c);
            }
        }
        return n;
    }

    public AFN transformarEmAFN(Nodo nodo) throws Exception {
        List<AFN> afnsNodos = this.getAFNsNodos(nodo);
        List<AFN> afnsChars = this.getAFNsChars(nodo);
        List<AFN> afns = this.getAFNs(nodo, afnsNodos, afnsChars);

        AFN afn1 = null;
        AFN afn2 = null;
        AFN afn = null;
        int cont = 0;
        for (int i = 0; i < nodo.getCarac().length(); i++) {
            char c = nodo.getCarac().charAt(i);
            if (isOperador(c)) {
                if (c == ".".charAt(0)) {
                    afn1 = afns.get(cont);
                    cont++;
                    afn2 = afns.get(cont);
                    cont++;
                    if (afn1 != null && afn2 != null) {
                        afn = new Concatenacao().calcularConcatenacao(afn1, afn2);
                    } else {
                        throw new Exception("afn1 ou afn2 está nulo!");
                    }
                } else if (c == "|".charAt(0)) {
                    afn1 = afns.get(cont);
                    cont++;
                    afn2 = afns.get(cont);
                    cont++;
                    if (afn1 != null && afn2 != null) {
                        afn = new Uniao().calcularUniao(afn1, afn2);
                    } else {
                        throw new Exception("afn1 ou afn2 está nulo!");
                    }
                } else if (c == "*".charAt(0)) {
                }
            }
        }
        return afn;
    }

    public List<AFN> getAFNs(Nodo nodo, List<AFN> afnsNodos, List<AFN> afnsChars) {
        int contNodos = 0;
        int contChars = 0;
        List<AFN> afns = new ArrayList<>();
        for (int i = 0; i < nodo.getCarac().length(); i++) {
            char c = nodo.getCarac().charAt(i);
            if (c == "§".charAt(0)) {
                afns.add(afnsNodos.get(contNodos));
                contNodos++;
            } else if (isOperador(c) == false) {
                afns.add(afnsChars.get(contChars));
                contChars++;
            }
        }
        return afns;
    }

    public List<AFN> getAFNsNodos(Nodo nodo) throws Exception {
        List<AFN> afnsNodos = new ArrayList<>();
        for (Iterator<Nodo> it = nodo.getNodos().iterator(); it.hasNext();) {
            Nodo n = it.next();
            afnsNodos.add(this.transformarEmAFN(n));
        }
        return afnsNodos;
    }

    public List<AFN> getAFNsChars(Nodo nodo) {
        List<AFN> afnsChars = new ArrayList<>();
        for (int i = 0; i < nodo.getCarac().length(); i++) {
            char c = nodo.getCarac().charAt(i);
            if ("§".charAt(0) != c && isOperador(c) == false) {
                AFN afn = new AFN();

                Estado q0 = new Estado();
                q0.setEstadoInicial(true);
                Estado q1 = new Estado();
                q1.setEstadoFinal(true);

                Caractere a = new Caractere(String.valueOf(c));

                FuncaoTransicao ft = new FuncaoTransicao(q1, a);
                q0.addFuncaoTransicao(ft);

                afn.addCaractere(a);
                afn.addEstado(q0);
                afn.addEstado(q1);
                afn.setEstadoInicial(q0);
                afn.addEstadoFinal(q1);

                afnsChars.add(afn);
            }
        }
        return afnsChars;
    }
}
