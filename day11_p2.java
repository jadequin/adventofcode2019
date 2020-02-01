/**
/  VM class required!
*/

String puzzleInput = "3,8,1005,8,342,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,0,10,4,10,1002,8,1,29,2,1006,19,10,1,1005,19,10,2,1102,11,10,3,8,102,-1,8,10,101,1,10,10,4,10,108,1,8,10,4,10,1001,8,0,62,2,1009,15,10,3,8,102,-1,8,10,101,1,10,10,4,10,108,1,8,10,4,10,1002,8,1,88,2,1101,6,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,114,1,105,8,10,1,1102,18,10,2,6,5,10,1,2,15,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,1,10,4,10,1001,8,0,153,1,105,15,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,178,1,1006,15,10,1006,0,96,1006,0,35,1,104,7,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,214,1006,0,44,2,1105,17,10,1,1107,19,10,1,4,16,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,0,10,4,10,102,1,8,252,1006,0,6,1,1001,20,10,1006,0,45,2,1109,5,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,1,8,10,4,10,102,1,8,287,2,101,20,10,2,1006,18,10,1,1009,9,10,3,8,102,-1,8,10,1001,10,1,10,4,10,108,1,8,10,4,10,1002,8,1,321,101,1,9,9,1007,9,1031,10,1005,10,15,99,109,664,104,0,104,1,21102,48210117528,1,1,21102,1,359,0,1105,1,463,21102,932700763028,1,1,21102,370,1,0,1105,1,463,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21102,1,179557207079,1,21102,417,1,0,1105,1,463,21102,1,28994202816,1,21101,0,428,0,1105,1,463,3,10,104,0,104,0,3,10,104,0,104,0,21101,0,709580710756,1,21102,1,451,0,1106,0,463,21102,825016201984,1,1,21101,462,0,0,1106,0,463,99,109,2,21201,-1,0,1,21102,40,1,2,21101,0,494,3,21102,1,484,0,1105,1,527,109,-2,2106,0,0,0,1,0,0,1,109,2,3,10,204,-1,1001,489,490,505,4,0,1001,489,1,489,108,4,489,10,1006,10,521,1101,0,0,489,109,-2,2105,1,0,0,109,4,1201,-1,0,526,1207,-3,0,10,1006,10,544,21102,1,0,-3,21202,-3,1,1,22102,1,-2,2,21102,1,1,3,21102,563,1,0,1105,1,568,109,-4,2106,0,0,109,5,1207,-3,1,10,1006,10,591,2207,-4,-2,10,1006,10,591,21202,-4,1,-4,1105,1,659,22102,1,-4,1,21201,-3,-1,2,21202,-2,2,3,21102,610,1,0,1106,0,568,21201,1,0,-4,21102,1,1,-1,2207,-4,-2,10,1006,10,629,21102,1,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,651,21202,-1,1,1,21102,1,651,0,106,0,526,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2106,0,0";

//PANEL
HashMap<Point, Color> panels = new HashMap<>();

//ROBOT
VM robot = new VM(puzzleInput, 10_000);

//ROBOT SETUP
Direction direction = Direction.UP;
Point panelPosition = new Point(0, 0);

//Painting robot starting panel
panels.put(panelPosition, Color.WHITE);


while(robot.executable()) {
    panels.putIfAbsent(panelPosition, Color.BLACK);

    long input = panels.get(panelPosition) == Color.BLACK? 0 : 1;
    long[] outputs = robot.cameraInteraction(input);

    /**
        * First, it will output a value indicating the color
        * to paint the panel the robot is over: 0 means to paint
        * the panel black, and 1 means to paint the panel white.
        */
    panels.put(panelPosition, outputs[0] == 0? Color.BLACK : Color.WHITE);

    /**
        * Second, it will output a value indicating the direction
        * the robot should turn: 0 means it should turn left 90 degrees,
        * and 1 means it should turn right 90 degrees.
        */
    direction = direction.turn(outputs[1] == 0? Direction.LEFT : Direction.RIGHT);

    /**
        * After the robot turns, it should always move forward exactly one panel.
        */
    panelPosition = new Point(panelPosition.x + direction.x, panelPosition.y + direction.y);
}


int minX = 0, maxX = 0, minY = 0, maxY = 0;
for(Point p : panels.keySet()) {
    if(p.x < minX) minX = p.x;
    if(p.x > maxX) maxX = p.x;
    if(p.y < minY) minY = p.y;
    if(p.y > maxY) maxY = p.y;
}

for(int x = minX; x <= maxX; x++) {
    for (int y = minY; y <= maxY; y++) {
        Color color = panels.getOrDefault(new Point(x , y), Color.BLACK);
        System.out.print(color == Color.BLACK? " " : "#");
    }
    System.out.println();
}