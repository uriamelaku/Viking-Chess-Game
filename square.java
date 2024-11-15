import java.util.ArrayList;
import java.util.List;

public class square {
    private boolean isFull;
    private ArrayList<ConcretePiece> counter = new ArrayList<>();
    private int x;
    private int y;

    public square(boolean isFull) {
        this.isFull = isFull;
    }

    public int getCounter() {
        return this.counter.size();
    }

    public void setCounterPlus(ConcretePiece piece) {
        if (!this.counter.contains(piece))
            this.counter.add(piece);
    }

    public void undo() {
        this.isFull = false;
        counter.remove(counter.size() - 1);
    }


    public boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(boolean b) {
        this.isFull = b;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
