import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;

class Orbit {
    private static Map<String, Orbit> orbitMap = new HashMap<String, Orbit>();
    private static ArrayList<Orbit> allOrbits = new ArrayList<>();
    private Orbit previousOrbit;

    public Orbit(String name) {
        previousOrbit = null;
        allOrbits.add(this);
        orbitMap.put(name, this);
    }
    public void setPreviousOrbit(Orbit orbit) {previousOrbit = orbit;}
    public int countOrbits(Orbit orbit) {return previousOrbit == orbit? 0: previousOrbit.countOrbits(orbit) + 1;}
    public int countOrbits() {return previousOrbit == null? 0: previousOrbit.countOrbits() + 1;}

    public static ArrayList<Orbit> getAllOrbits() {return allOrbits;}
    public static Orbit getOrbitByString(String orbitname) {return orbitMap.get(orbitname);}
}

//Part 1
String input = new String(Files.readAllBytes(Paths.get("day6_input.txt")), "UTF-8");
String[] relations = input.split("\r\n");

//Create all Orbits, including COM
new Orbit("COM");
for(String relation: relations) {
    String outerOrbit = relation.substring(4);
    new Orbit(outerOrbit);
}

//Define their previousOrbits
for(String relation: relations) {
    Orbit centralOrbit = Orbit.getOrbitByString(relation.substring(0, 3));
    Orbit outerOrbit = Orbit.getOrbitByString(relation.substring(4));
    outerOrbit.setPreviousOrbit(centralOrbit);
}

//Part 1
int checksum = 0;
for(Orbit orbit: Orbit.getAllOrbits())
    checksum += orbit.countOrbits();
System.out.println("Checksum of Map: " + checksum);