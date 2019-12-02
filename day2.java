class Intcode {
    String input;
    String output;
    int[] intcodeArr;

    Intcode(String input) {
        this.input = input;
        intcodeArr = parseToIntArr(input);
    }
    private int[] parseToIntArr(String input) {
        int[] res = new int[input.split(",", -1).length];
        try {
            int cnt = 0;
            for(String val: input.split(",", -1)) {
                res[cnt] = Integer.parseInt(val);
                cnt++;
            }
        }
        catch(NumberFormatException nfe) {
            throw new IllegalArgumentException("please enter integer values, seperated by commas!");
        }
        return res;
    }
    private void executeOperations() {
        //assert intcodeArr.length >= 4;
        //and assert intcodeArr[] contains 99 as a exit possibility
        int pos = 0;
        while(intcodeArr[pos] != 99) {
            int number1 = intcodeArr[intcodeArr[pos + 1]];
            int number2 = intcodeArr[intcodeArr[pos + 2]];

            if(intcodeArr[pos] == 1)
                intcodeArr[intcodeArr[pos + 3]] = number1 + number2;
            else if(intcodeArr[pos] == 2)
                intcodeArr[intcodeArr[pos + 3]] = number1 * number2;
            else
                throw new IllegalArgumentException("illegal operator found: valid operators are 1 for + and 2 for *");
            pos += 4;
        }
    }
    private String arrayToString() {
        String res = "";
        for(int i: intcodeArr)
            res += i + ",";
        return res.substring(0, res.length() - 1);
    }
    public String execute() {
        executeOperations();
        output = arrayToString();
        return output;
    }
    public String toString() {
        return input;
    }
}

//test
String[] programs = {
        "1,0,0,0,99", 
        "2,3,0,3,99", 
        "2,4,4,5,99,0", 
        "1,1,1,4,99,5,6,0,99"};
    
for(String s: programs) {
    int[] intcodeArr = translateToInt(s);
    int[] executedIntcode = executeOperations(intcodeArr);
    String result = translateToString(executedIntcode);
    System.out.println(result);
}
