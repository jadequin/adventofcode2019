import java.nio.file.Files;
import java.nio.file.Paths;

int fuel(int mass) {
    return (mass / 3) < 2? 0: (mass / 3) - 2 + fuel((mass / 3) - 2);
}
void part2() {
    //not working without: 1. copy&paste input line into jshell 2. auskommentieren
    String input = new String(Files.readAllBytes(Paths.get("day1_input.txt")), "UTF-8");
    int[] masses = Stream.of(input.split("\r\n")).mapToInt(Integer::parseInt).toArray();
    int sum = 0;
    for(int mass: masses)
        sum += fuel(mass);
    System.out.println("fuel needed: " + sum);
}