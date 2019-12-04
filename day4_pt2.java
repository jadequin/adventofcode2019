//something isn't working here ... will fix it soon

String input = "265275-781584";
int lowerBnd = Integer.parseInt(input.split("-")[0]);
int upperBnd = Integer.parseInt(input.split("-")[1]);

boolean containsTwins;
int cntTwins;
boolean adjacentTwins;
int passwordCount = 0;

outer:
for(int num = lowerBnd; num <= upperBnd; num++) {
    int[] digits = Integer.toString(num).chars().map(c -> c-'0').toArray();

    containsTwins = false;
    adjacentTwins = false;
    cntTwins = 0;
    for(int i = 1; i < digits.length; i++) {
        if(digits[i] < digits[i - 1])
            continue outer;
        if(digits[i] == digits[i - 1]) {
            cntTwins += adjacentTwins? 1: 2;
            adjacentTwins = true;
            containsTwins = true;
        }
        else if(cntTwins % 2 == 1)
            continue outer;
        else {
            cntTwins = 0;
            adjacentTwins = false;
        }
    }
    if(containsTwins && cntTwins % 2 == 0) passwordCount++;
}
