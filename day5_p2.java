String input = "3,225,1,225,6,6,1100,1,238,225,104,0,1101,69,55,225,1001,144,76,224,101,-139,224,224,4,224,1002,223,8,223,1001,224,3,224,1,223,224,223,1102,60,49,225,1102,51,78,225,1101,82,33,224,1001,224,-115,224,4,224,1002,223,8,223,1001,224,3,224,1,224,223,223,1102,69,5,225,2,39,13,224,1001,224,-4140,224,4,224,102,8,223,223,101,2,224,224,1,224,223,223,101,42,44,224,101,-120,224,224,4,224,102,8,223,223,101,3,224,224,1,223,224,223,1102,68,49,224,101,-3332,224,224,4,224,1002,223,8,223,1001,224,4,224,1,224,223,223,1101,50,27,225,1102,5,63,225,1002,139,75,224,1001,224,-3750,224,4,224,1002,223,8,223,1001,224,3,224,1,223,224,223,102,79,213,224,1001,224,-2844,224,4,224,102,8,223,223,1001,224,4,224,1,223,224,223,1,217,69,224,1001,224,-95,224,4,224,102,8,223,223,1001,224,5,224,1,223,224,223,1102,36,37,225,1101,26,16,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1107,677,677,224,102,2,223,223,1006,224,329,1001,223,1,223,1108,677,677,224,1002,223,2,223,1006,224,344,1001,223,1,223,107,226,226,224,1002,223,2,223,1006,224,359,101,1,223,223,1008,226,226,224,102,2,223,223,1005,224,374,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,389,1001,223,1,223,1008,677,226,224,1002,223,2,223,1005,224,404,1001,223,1,223,7,677,226,224,102,2,223,223,1005,224,419,1001,223,1,223,1008,677,677,224,1002,223,2,223,1006,224,434,1001,223,1,223,108,226,226,224,102,2,223,223,1006,224,449,1001,223,1,223,108,677,677,224,102,2,223,223,1006,224,464,1001,223,1,223,107,226,677,224,1002,223,2,223,1005,224,479,101,1,223,223,1108,226,677,224,1002,223,2,223,1006,224,494,1001,223,1,223,107,677,677,224,1002,223,2,223,1006,224,509,101,1,223,223,7,677,677,224,102,2,223,223,1006,224,524,1001,223,1,223,1007,226,677,224,1002,223,2,223,1005,224,539,1001,223,1,223,8,226,677,224,1002,223,2,223,1005,224,554,101,1,223,223,8,677,677,224,102,2,223,223,1005,224,569,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,584,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,599,1001,223,1,223,1107,677,226,224,1002,223,2,223,1006,224,614,1001,223,1,223,1108,677,226,224,1002,223,2,223,1005,224,629,1001,223,1,223,1007,677,677,224,102,2,223,223,1006,224,644,1001,223,1,223,108,226,677,224,102,2,223,223,1005,224,659,101,1,223,223,8,677,226,224,1002,223,2,223,1006,224,674,1001,223,1,223,4,223,99,226";

int[] intcode = Stream.of(input.split(",")).mapToInt(Integer::parseInt).toArray();
int INPUT = 5;


int ptr = 0;
while(true) {
    int opcode = intcode[ptr] % 100;
    if(opcode == 99) {System.out.println("HALT"); break;}

    int[] paramsmode = new int[3];
    paramsmode[0] = (intcode[ptr] % 1000) / 100;
    paramsmode[1] = (intcode[ptr] % 10_000) / 1_000;
    paramsmode[2] = (intcode[ptr] % 100_000) / 10_000;
    ptr++;

    int num1, num2;
    int param1, param2;
    switch(opcode) {
        case 1:
            num1 = paramsmode[0] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            num2 = paramsmode[1] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            intcode[intcode[ptr++]] = num1 + num2;
            break;
        case 2:
            num1 = paramsmode[0] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            num2 = paramsmode[1] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            intcode[intcode[ptr++]] = num1 * num2;
            break;
        case 3:
            int userInput = INPUT; // I should use a real input option here..
            intcode[intcode[ptr++]] = userInput;
            break;
        case 4:
            System.out.println(intcode[intcode[ptr++]]);
            break;
        case 5:
            if((paramsmode[0] == 0? intcode[intcode[ptr]]: intcode[ptr]) != 0)
                ptr = paramsmode[1] == 0? intcode[intcode[ptr + 1]]: intcode[ptr + 1];
            else
                ptr += 2;
            break;
        case 6:
            if((paramsmode[0] == 0? intcode[intcode[ptr]]: intcode[ptr]) == 0)
                ptr = paramsmode[1] == 0? intcode[intcode[ptr + 1]]: intcode[ptr + 1];
            else
                ptr += 2;
            break;
        case 7:
            param1 = paramsmode[0] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            param2 = paramsmode[1] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            if(param1 < param2)
                intcode[intcode[ptr++]] = 1;
            else
                intcode[intcode[ptr++]] = 0;
            break;
        case 8:
            param1 = paramsmode[0] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            param2 = paramsmode[1] == 0? intcode[intcode[ptr++]]: intcode[ptr++];
            if(param1 == param2)
                intcode[intcode[ptr++]] = 1;
            else
                intcode[intcode[ptr++]] = 0;
            break;
    }
}