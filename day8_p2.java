import java.nio.file.Files;
import java.nio.file.Paths;

String puzzleInput = new String(Files.readAllBytes(Paths.get("day8_input.txt")), "UTF-8");

int WIDTH = 25, HEIGHT = 6;
int SIZE = WIDTH * HEIGHT;

char[] picture = new char[SIZE];
for(int i = puzzleInput.length() - SIZE; i >= 0; i -= SIZE) {
    char[] layer = puzzleInput.substring(i, i + SIZE).toCharArray();
    for(int digit = 0; digit < picture.length; digit++)
        if(layer[digit] == '0' || layer[digit] == '1')
            picture[digit] = layer[digit];
}

int index = 0;
for(int h = 0; h < HEIGHT; h++) {
    for(int w = 0; w < WIDTH; w++) {
        if(picture[index] == '1')
            System.out.print("#");
        else
            System.out.print(" ");
        index++;
    }
    System.out.println();
}