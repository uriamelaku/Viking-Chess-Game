import java.awt.*;
import java.util.Stack;

public abstract class ConcretePiece implements Piece {
    private Stack<Position> steps = new Stack<>();
    private int count_steps = 0;
    protected int id;
    protected Player owner;
    protected String type;
    private int X;
    private int Y;

    private int distance = 0;

    public ConcretePiece() {
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public void setXY(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int xa, int ya, int xb, int yb) {
        this.distance = this.distance + Math.abs(xa - xb) + Math.abs(ya - yb);
    }

    public void addSteps(Position a) {
        this.count_steps += 1;
        this.steps.push(a);
    }

    public int getCount_steps() {
        return count_steps;
    }

    public void printStuck() {
        String s = "";
        while (!steps.empty()) {
            Position p = steps.pop();
            int x = p.getX();
            int y = p.getY();
            s = "(" + x + ", " + y + ")" + s;
            if (!steps.isEmpty())
                s = ", " + s;
        }
        s = "[" + s + "]\n";
        System.out.printf(s);
    }

    public void undoPiece() {
        Position b = steps.peek();
        int xb = b.getX();
        int yb = b.getY();
        if (this.getX() == -1 && this.getY() == -1) {
            this.setXY(xb, yb);
        } else {
            b = steps.pop();
            Position a = steps.peek();
            int xa = a.getX();
            int ya = a.getY();
            xb = b.getX();
            yb = b.getY();
            this.distance = this.distance - Math.abs(xa - xb) + Math.abs(ya - yb);
            if (getType() != "♔")
                ((Pawn) (this)).kiilMinus();
            this.setXY(xa, ya);
        }
    }
}
