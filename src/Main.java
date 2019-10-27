import model.Point;
import model.World;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        World w = new World();
        printPos(w);

        Scanner in = new Scanner(System.in);
        String line = "";

        while ((line = in.nextLine()) != null) {
            line = line.toUpperCase();
            switch (line) {
                case "W": {
                    Point old = w.getHeroPos();
                    Point n = new Point(old.getX(), old.getY() - 1);
                    w.moveHeroTo(n);
                    break;
                }
                case "A": {
                    Point old = w.getHeroPos();
                    Point n = new Point(old.getX() - 1, old.getY());
                    w.moveHeroTo(n);
                    break;
                }
                case "S": {
                    Point old = w.getHeroPos();
                    Point n = new Point(old.getX(), old.getY() + 1);
                    w.moveHeroTo(n);
                    break;
                }
                case "D": {
                    Point old = w.getHeroPos();
                    Point n = new Point(old.getX() + 1, old.getY());
                    w.moveHeroTo(n);
                    break;
                }
            }
            printPos(w);
        }
    }

    private static void printPos(World w) {
        Point p = w.getHeroPos();

        System.out.println("Pacman(" + p.getX() + "; " + p.getY() + ")");
        System.out.print("input (W, A, S, D): ");
    }
}
