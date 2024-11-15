public class ConcretePlayer implements Player {
    private int Wins=0;
    private boolean first_second;

    public ConcretePlayer(boolean first_second) {
        this.first_second = first_second;
    }

    @Override
    public boolean isPlayerOne() {
        return first_second;
    }

    @Override
    public int getWins() {
        return Wins;
    }

    public void winsPlus() {
        this.Wins++;
    }

    public void setWins(int wins) {
        Wins = wins;
    }
}
