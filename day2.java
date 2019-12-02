/* Mögliche Verbesserungen:
/ -Intcodes objektorientiert implementieren
/ -schlankerer Code durch zusammenfassen?
*/

int[] translateToInt(String input) {
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
int[] executeOperations(int[] intcode) {
    //assert intcode.length >= 4;
    //and assert intcode[] contains 99 as a exit possibility

    int[] res = intcode;
    int pos = 0;
    while(intcode[pos] != 99) {
        int number1 = intcode[intcode[pos + 1]];
        int number2 = intcode[intcode[pos + 2]];

        if(intcode[pos] == 1)
            res[intcode[pos + 3]] = number1 + number2;
        else if(intcode[pos] == 2)
            res[intcode[pos + 3]] = number1 * number2;
        else
            throw new IllegalArgumentException("illegal operator found: valid operators are 1 for + and 2 for *");
        pos += 4;
    }
    return res;
}
String translateToString(int[] output) {
    String res = "";
    for(int i: output)
        res += i + ",";
    return res.substring(0, res.length() - 1);
}
void test() {
    String[] programs = {
        "1,0,0,0,99", 
        "2,3,0,3,99", 
        "2,4,4,5,99,0", 
        "1,1,1,4,99,5,6,0,99"};
    
    for(String s: programs) {
        int[] intcode = translateToInt(s);
        int[] executedIntcode = executeOperations(intcode);
        String result = translateToString(executedIntcode);
        System.out.println(result);
    }
}
