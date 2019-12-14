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
    void applyVelocity() {
        x += vX; y += vY; z += vZ;
    }
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

Moon Io = new Moon(3, 2, -6);
Moon Europa = new Moon(-13, 18, 10);
Moon Ganymede = new Moon(-8, -1, 13);
Moon Callisto = new Moon(5, 10, 4);

Moon.timeSteps(1000);
int systemEnergy = 0;
for(Moon moon: Moon.moons) {
    systemEnergy += moon.totalEnergy();
}

System.out.println("Total Energy in the system is: " + systemEnergy);