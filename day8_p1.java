import java.nio.file.Files;
import java.nio.file.Paths;

String puzzleInput = new String(Files.readAllBytes(Paths.get("day8_input.txt")), "UTF-8");

int WIDTH = 25, HEIGHT = 6;
int SIZE = WIDTH * HEIGHT;
int fewestZeros = SIZE, indexFewestZeros = 0; 
for(int i = 0; i < puzzleInput.length(); i += SIZE) {
    int zeros =  SIZE - puzzleInput.substring(i, i + SIZE).replaceAll("0", "").length();
    if(zeros < fewestZeros) {
        fewestZeros = zeros;
        indexFewestZeros = i;
    }
}
String layerFewestZeros = puzzleInput.substring(indexFewestZeros, indexFewestZeros + SIZE);
int numOfOnes = SIZE - layerFewestZeros.replaceAll("1", "").length();
int numOfTwos = SIZE - layerFewestZeros.replaceAll("2", "").length();

System.out.println(numOfOnes + " * " + numOfTwos + " = " + numOfOnes * numOfTwos);