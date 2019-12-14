import java.nio.file.*;
import java.util.ArrayList;

class Point {
    int x, y;
    Point(int x, int y) {this.x = x; this.y = y;}
    double getAngle(Point other) {return Math.atan2(other.x - x, other.y - y);}
    public boolean equals(Object other) {
        if(other == null) return false;
        if(other == this) return true;
        if(!(other instanceof Point)) return false;
        Point that = (Point)other;
        return this.x == that.x && this.y == that.y;
    }
    public String toString() {return "P(" + x + "|" + y + ")";}
}

String puzzleInput = new String(Files.readAllBytes(Paths.get("day10_input.txt")), "UTF-8");
int height = puzzleInput.split("\r\n").length;
int width = puzzleInput.split("\r\n")[0].length();
puzzleInput = puzzleInput.replace("\r\n", "");

//Part 1
List<Point> asteroids = new ArrayList<>();
for(int i = 0; i < width * height; i++)
    if(puzzleInput.substring(i, i+1).equals("#")) 
        asteroids.add(new Point(i % width, i / width));

int maxSight = 0;
for(Point asteroid: asteroids) {
    Set<Double> angles = new HashSet<>();
    for(Point p: asteroids)
        if(!(asteroid.equals(p))) angles.add(asteroid.getAngle(p));
    if(angles.size() > maxSight) maxSight = angles.size();
}