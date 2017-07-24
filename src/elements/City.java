package elements;

public class City {
    public String cityName;
    private String zipCode;
    public boolean isZipCodeCorrect;

    public City(String name) {
        this.cityName = name;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
        isZipCodeCorrect = checkIsZipCodeCorrect();
    }

    public String getZipCode() {
        return zipCode;
    }

    private boolean isThisADigit(char x) {
        if (Character.getNumericValue(x) >= 0 && Character.getNumericValue(x) < 10) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIsZipCodeCorrect() {
        if (zipCode.length() != 6) {
            return false;
        } else {
            for (int i = 0; i < zipCode.length(); ++i) {
                if (i != 2) {
                    if (!isThisADigit(zipCode.charAt(i))) {
                        return false;
                    }
                } else {
                    if (zipCode.charAt(i) != '-') {
                        return false;
                    }
                }

                //max range of zip code
                if (i > 2) {
                    if (zipCode.charAt(0) == '9' && zipCode.charAt(1) == '9') {
                        if (zipCode.charAt(i) != '0') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
