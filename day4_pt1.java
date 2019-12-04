String input = "265275-781584";
int lowerBnd = Integer.parseInt(input.split("-")[0]);
int upperBnd = Integer.parseInt(input.split("-")[1]);

boolean adjacentTwins;
int passwordCount = 0;

outer:
for(int num = lowerBnd; num <= upperBnd; num++) {
    int[] digits = Integer.toString(num).chars().map(c -> c-'0').toArray();

    adjacentTwins = false;
    for(int i = 1; i < digits.length; i++) {
        if(digits[i] < digits[i - 1])
            continue outer;
        if(digits[i] == digits[i - 1])
            adjacentTwins = true;
    }
    if(adjacentTwins) passwordCount++;
}