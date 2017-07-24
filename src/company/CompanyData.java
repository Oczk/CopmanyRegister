package company;

import elements.City;
import elements.Pkd;
import elements.Regon;
import elements.ShortName;


public class CompanyData {
    private Regon regon;
    private ShortName shortName;
    private City city;
    private Pkd pkd;

    public boolean dataCorrectness;
    public String dataCorrectnessMsg = "";

    public CompanyData() {
        regon = new Regon();
        shortName = new ShortName();
        city = new City();
        pkd = new Pkd();
    }

    public void checkDataCorrectness(){
        dataCorrectness = true;
        if(!regon.isNumberCorrect){
            dataCorrectness = false;
            dataCorrectnessMsg += "REGON niepoprawny. ";

        }
        if(!shortName.isNameCorrect){
            dataCorrectness = false;
            dataCorrectnessMsg += "Nazwa niepoprawna. ";
        }
        if(!city.isZipCodeCorrect){
            dataCorrectness = false;
            dataCorrectnessMsg += "Kod pocztowy niepoprawny. ";
        }
        if(!pkd.isSymbolCorrect){
            dataCorrectness = false;
            dataCorrectnessMsg += "Symbol PKD niepoprawny.";
        }

    }

    public void clear(){
        dataCorrectnessMsg = "";
    }


    //setters
    public void setRegon(String regon) {
        this.regon = new Regon(regon);
    }

    public void setShortName(String name) {
        this.shortName = new ShortName(name);
    }

    public void setCity(String city) {
        this.city = new City(city);
    }

    public void setZipCode(String code) {
        city.setZipCode(code);
    }

    public void setPkd(String symbol) {
        this.pkd = new Pkd(symbol);
    }


    //getters
    public String getRegon() {
        return regon.number;
    }

    public String getShortName() {
        return shortName.name;
    }

    public String  getCity() {
        return city.cityName;
    }

    public String getZipCode(){
        return city.getZipCode();
    }

    public String getPkd() {
        return pkd.symbol;
    }
}
