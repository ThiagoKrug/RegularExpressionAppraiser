
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
            } else {
                // é um caractere qualquer
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
        for (int i = 0; i < er.length(); i++) {
            char carac = er.charAt(i);
            
        }
    }
    
    public String corrigirExpressaoRegular(String er) {
        String erCorrigida = "";
        Character lastChar = null;
        for (int i = 0; i < er.length(); i++) {
            char carac = er.charAt(i);
            if (lastChar != null) {
                if (isOperador(lastChar) == false && isOperador(carac) == false) {
                    erCorrigida += String.valueOf(".".charAt(0));
                }
            }
            erCorrigida += String.valueOf(carac);
        }
        return erCorrigida;
    }
}
