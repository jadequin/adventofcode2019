import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;

class Orbit {
    private static Map<String, Orbit> orbitMap = new HashMap<String, Orbit>();
    private static ArrayList<Orbit> allOrbits = new ArrayList<>();
    private Orbit previousOrbit;
    private String name;

    public Orbit(String name) {
        this.name = name;
        previousOrbit = null;
        allOrbits.add(this);
        orbitMap.put(name, this);
    }
    public String getName() {return name;}
    public Orbit getPreviousOrbit() {return previousOrbit;}
    public void setPreviousOrbit(Orbit orbit) {previousOrbit = orbit;}
    public int countOrbits(Orbit orbit) {return previousOrbit == orbit? 0: previousOrbit.countOrbits(orbit) + 1;}
    public int countOrbits() {return previousOrbit == null? 0: previousOrbit.countOrbits() + 1;}
    public String getPath() {return previousOrbit == null? "": previousOrbit.getPath() + previousOrbit.getName() + ",";}

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

//Part 2
Orbit YOU = Orbit.getOrbitByString("YOU");
Orbit SAN = Orbit.getOrbitByString("SAN");

String[] youPath = YOU.getPath().split(",");
String[] sanPath = SAN.getPath().split(",");
Orbit intersection = null;
for(int i = 0; i < youPath.length && i < sanPath.length; i++)
    if(!(youPath[i].equals(sanPath[i]))) {
        intersection = Orbit.getOrbitByString(youPath[i - 1]);
        break;
    }
System.out.println(YOU.countOrbits(intersection) + SAN.countOrbits(intersection));