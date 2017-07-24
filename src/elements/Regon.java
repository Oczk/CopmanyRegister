package elements;

public class Regon {

    public String number;
    public boolean isNumberCorrect;

    private int[] numberArr;

    public Regon(String number) {
        this.number = number;
        numberArr = new int[number.length()];
        parseNumberToArr(number);
        this.isNumberCorrect = checkIsNumberCorrect();
    }

    private void parseNumberToArr(String number) {
        for (int i = 0; i < number.length(); ++i) {
            this.numberArr[i] = Character.getNumericValue(number.charAt(i));
        }
    }

    private boolean checkIsNumberCorrect() {
        if(numberArr.length == 9) {
            return calcControlSum() == numberArr[8];
        } else {
            return calcControlSum() == numberArr[13];
        }
    }

    private int calcControlSum(){
        int controlSum;

        if(numberArr.length ==9){
            controlSum = numberArr[0]*8 + numberArr[1]*9;

            for(int i=2; i<numberArr.length-1; ++i){
                controlSum += i*numberArr[i];
            }
        } else {
            controlSum = 2*numberArr[0] + 4*numberArr[1] + 8*numberArr[2] + 5*numberArr[3] + 9*numberArr[5]
                    + 7*numberArr[6] + 3*numberArr[7] + 6*numberArr[8] + numberArr[9] + 2*numberArr[10]
                    + 4*numberArr[11] + 8*numberArr[12];
        }

        //if controlSum mod 11 gives 10, then return just 0
        return controlSum%11 != 10 ? controlSum%11 : 0;
    }
}
