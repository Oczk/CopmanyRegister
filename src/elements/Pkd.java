package elements;

public class Pkd {
    public String symbol;
    public boolean isSymbolCorrect;

    public Pkd(String symbol) {
        this.symbol = symbol;
        isSymbolCorrect = checkIsSymbolCorrect();
    }

    public Pkd(){
        this.symbol = "";
        this.isSymbolCorrect = false;
    }


    private boolean checkIsSymbolCorrect() {
        if (symbol.length() != 5) {
            return false;
        }

        if (Integer.parseInt(symbol) < 20000 || Integer.parseInt(symbol) > 50000) {
            return false;
        }

        for (int i = 0; i < symbol.length(); ++i) {
            if (!isThisADigit(symbol.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isThisADigit(char x) {
        if (Character.getNumericValue(x) >= 0 && Character.getNumericValue(x) < 10) {
            return true;
        } else {
            return false;
        }
    }
}
