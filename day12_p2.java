class Moon {
    static int moonsCtr = 0;
    static Moon[] moons = new Moon[4];
    int x, y, z;
    int vX = 0, vY = 0, vZ = 0;

    Moon(int x, int y, int z) {
        this.x = x; this.y = y; this.z = z;
        moons[moonsCtr++] = this;
    }
    void applyGravity(Moon other) {
        vX += (int)Math.signum(x - other.x) * (-1);
        vY += (int)Math.signum(y - other.y) * (-1);
        vZ += (int)Math.signum(z - other.z) * (-1);
    }
    void applyVelocity() {x += vX; y += vY; z += vZ;}
    private int pot() {return Math.abs(x) + Math.abs(y) + Math.abs(z);}
    private int kin() {return Math.abs(vX) + Math.abs(vY) + Math.abs(vZ);}
    int totalEnergy() {return pot() * kin();}
    public String toString() {
        return "pos=x: " + x + "y: " + y + "z: " + z + ", vel=x: " + vX + "y: " + vY + "z: " + vZ;
    }
    static void timeSteps(int steps) {
        for(int step = 0; step < steps; step++) {
            for(Moon i: moons)
                for(Moon j: moons)
                    if(i != j) {
                        i.applyGravity(j);
                    }
            for(Moon moon: moons)
                moon.applyVelocity();
        }
    }
}

long gcd(long a, long b) {return b==0? a: gcd(b, a%b);}
long lcm(long a, long b) {return Math.abs(a * b) / gcd(a, b);}

Moon Io = new Moon(3, 2, -6);
Moon Europa = new Moon(-13, 18, 10);
Moon Ganymede = new Moon(-8, -1, 13);
Moon Callisto = new Moon(5, 10, 4);

int[] initialStateX = new int[]{Io.x, Europa.x, Ganymede.x, Callisto.x};
int[] initialStateY = new int[]{Io.y, Europa.y, Ganymede.y, Callisto.y};
int[] initialStateZ = new int[]{Io.z, Europa.z, Ganymede.z, Callisto.z};

int stepsX = 0, stepsY = 0, stepsZ = 0;
boolean foundX = false, foundY = false, foundZ = false;
do {
    Moon.timeSteps(1);
    if(!foundX) {
        stepsX++;
        if(initialStateX[0] == Io.x && initialStateX[1] == Europa.x 
            && initialStateX[2] == Ganymede.x && initialStateX[3] == Callisto.x)
            foundX = true;
    }
    if(!foundY) {
        stepsY++;
        if(initialStateY[0] == Io.y && initialStateY[1] == Europa.y
            && initialStateY[2] == Ganymede.y && initialStateY[3] == Callisto.y)
            foundY = true;
    }
    if(!foundZ) {
        stepsZ++;
        if(initialStateZ[0] == Io.z && initialStateZ[1] == Europa.z
            && initialStateZ[2] == Ganymede.z && initialStateZ[3] == Callisto.z)
                foundZ = true;
    }
        
} while(!(foundX && foundY && foundZ));

long res = lcm(stepsX + 1, lcm(stepsY + 1, stepsZ + 1)); //there is need to correct that..
System.out.println("The system needs " + res + " Steps to repeat the initial state in the future");