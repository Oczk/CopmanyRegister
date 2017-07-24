package elements;

public class ShortName {
    public String name;
    public boolean isNameCorrect;

    public ShortName(String name) {
        this.name = name;
        isNameCorrect = checkIsNameCorrect();
    }

    private boolean checkIsNameCorrect() {
        if (name.length() > 50 || name.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
