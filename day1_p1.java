import java.nio.file.Files;
import java.nio.file.Paths;

int fuel(int mass) {
    return (mass / 3) - 2;
}

String input = new String(Files.readAllBytes(Paths.get("day1_input.txt")), "UTF-8");
int[] masses = Stream.of(input.split("\r\n")).mapToInt(Integer::parseInt).toArray();
int sum = 0;
for(int mass: masses)
sum += fuel(mass);
System.out.println("fuel needed: " + sum);
