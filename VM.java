
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VM {

    private ConcurrentLinkedQueue<Long> inputs = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Long> outputs = new ConcurrentLinkedQueue<>();

    private long[] mem;
    private int ptr = 0, relBase = 0;

    public VM(String program, int memsize) {
        if(program.split(",").length > memsize)
            throw new IllegalArgumentException("Memsize too small");

        String extendedMemProgram = program + String.join("", Collections.nCopies(memsize - program.split(",").length, ",0"));
        mem = Arrays.stream(extendedMemProgram.split(",")).mapToLong(Long::parseLong).toArray();
    }
    
    public long getOutput() {
        while(executable() && !inputRequired())
            executeOperation();
        
        return outputs.poll();
    }
    
    public void giveInput(long input) {
        inputs.add(input);
        while(executable() && !inputRequired())
            executeOperation();
    }
    
    @Deprecated
    public long[] cameraInteraction(long input) {
        inputs.add(input);

        while(outputs.size() < 2 || (executable() && !inputRequired()))
            executeOperation();

        long[] res = new long[2];
        res[0] = outputs.poll();
        res[1] = outputs.poll();

        return res;
    }

    public boolean executable() {
        return getOpcode() != 99;
    }
    private boolean inputRequired() {return getOpcode() == 3;}

    private int getOpcode() {
        return (int)(mem[ptr]) % 100;
    }
    private int[] getParamModes() {
        return new int[]{(int)(mem[ptr]) % 1_000 / 100, (int)(mem[ptr]) % 10_000 / 1_000, (int)(mem[ptr]) % 100_000 / 10_000};
    }
    private int[] getArgs(int[] paramModes) {
        int maxParameters = Math.min(3, mem.length - ptr - 1);

        int[] args = new int[maxParameters];
        for(int i = 0; i < maxParameters; i++) {
            switch (paramModes[i]) {
                case 0: args[i] = (int)mem[ptr + i + 1]; break;
                case 1: args[i] = ptr + i + 1; break;
                case 2: args[i] = relBase + (int)mem[ptr + i + 1]; break;
            }
        }
        return args;
    }
    private void executeOperation() {

        int opcode = getOpcode();
        int[] paramModes = getParamModes();
        int[] args = getArgs(paramModes);

        switch(opcode) {
            case 1: //ADD
                mem[args[2]] = mem[args[0]] + mem[args[1]];
                ptr += 4; break;
            case 2: //MUL
                mem[args[2]] = mem[args[0]] * mem[args[1]];
                ptr += 4; break;
            case 3: //INPUT
                mem[args[0]] = inputs.poll();
                ptr += 2; break;
            case 4: //OUTPUT
                outputs.add(mem[args[0]]);
                ptr += 2; break;
            case 5: //JUMP-IF-TRUE
                if(mem[args[0]] != 0L)
                    ptr = (int)mem[args[1]];
                else
                    ptr += 3;
                break;
            case 6: //JUMP-IF-FALSE
                if(mem[args[0]] == 0L)
                    ptr = (int)mem[args[1]];
                else
                    ptr += 3;
                break;
            case 7: //LESS-THAN
                mem[args[2]] = mem[args[0]] < mem[args[1]]? 1L: 0L;
                ptr += 4; break;
            case 8: //EQUALS
                mem[args[2]] = mem[args[0]] == mem[args[1]]? 1L: 0L;
                ptr += 4; break;
            case 9: //ADJUST-RELATIVE-BASE
                relBase += (int)mem[args[0]];
                ptr += 2; break;
        }
    }
}
