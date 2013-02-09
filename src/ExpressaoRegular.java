
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

    public void quebrarExpressaoRegular(String er) {
    }

    public Nodo quebrarParenteses(String er, int altura, Nodo n) {
        int parenteses = 0, inicio = 0, fim;
        for (int i = 0; i < er.length(); i++) {
            char c = er.charAt(i);
            if ("(".charAt(0) == c) {
                parenteses++;
                if (parenteses == 1) {
                    inicio = i;
                }
            } else if (")".charAt(0) == c) {
                parenteses--;
                if (parenteses == 0) {
                    fim = i;
                    n.setAltura(altura);
                    String carac = er.substring(inicio + 1, fim);
                    n.setCarac(n.getCarac() + carac);
                    if (carac.length() > 1) {
                        Nodo nodo = quebrarParenteses(carac, altura + 1, new Nodo());
                        if (nodo.getCarac().equalsIgnoreCase("") == false) {
                            n.addNodo(nodo);
                        }
                    }
                }
            }
        }
        return n;
    }
}
