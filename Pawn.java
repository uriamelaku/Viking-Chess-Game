public class Pawn extends ConcretePiece {

    private int kills = 0;


    public Pawn(Player owner) {
        this.owner=owner;
        if(this.owner.isPlayerOne()){
            this.type = "♙";
        }else{
            this.type="♟";
        }
    }

    public void killPlus() {
        this.kills = this.kills + 1;
    }
    public void kiilMinus(){
        this.kills = this.kills - 1;
    }

    public int getKills() {
        return this.kills;
    }


}