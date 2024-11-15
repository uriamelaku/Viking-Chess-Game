import java.util.*;

//ID 315240127_208734889

public class GameLogic implements PlayableLogic {
    private final int BoardSize = 11;
    private ConcretePiece[] pieces = new ConcretePiece[37];
    private square[][] board = new square[BoardSize][BoardSize];
    private ConcretePlayer player1 = new ConcretePlayer(true);
    private ConcretePlayer player2 = new ConcretePlayer(false);
    private ConcretePlayer winner;
    private boolean turn = false;
    private boolean isGameFinish = false;
    private Stack<ConcretePiece> help_undo = new Stack<>();

    public GameLogic() {
        initializeNewGame();
    }

    private void initializeNewGame() {
        this.turn = false;
        this.winner = null;


        pieces[0] = new Pawn(player2);
        pieces[1] = new Pawn(player2);
        pieces[2] = new Pawn(player2);
        pieces[3] = new Pawn(player2);
        pieces[4] = new Pawn(player2);
        pieces[5] = new Pawn(player2);
        pieces[6] = new Pawn(player2);
        pieces[7] = new Pawn(player2);
        pieces[8] = new Pawn(player2);
        pieces[9] = new Pawn(player2);
        pieces[10] = new Pawn(player2);
        pieces[11] = new Pawn(player2);
        pieces[12] = new Pawn(player2);
        pieces[13] = new Pawn(player2);
        pieces[14] = new Pawn(player2);
        pieces[15] = new Pawn(player2);
        pieces[16] = new Pawn(player2);
        pieces[17] = new Pawn(player2);
        pieces[18] = new Pawn(player2);
        pieces[19] = new Pawn(player2);
        pieces[20] = new Pawn(player2);
        pieces[21] = new Pawn(player2);
        pieces[22] = new Pawn(player2);
        pieces[23] = new Pawn(player2);

        pieces[24] = new Pawn(player1);
        pieces[25] = new Pawn(player1);
        pieces[26] = new Pawn(player1);
        pieces[27] = new Pawn(player1);
        pieces[28] = new Pawn(player1);
        pieces[29] = new Pawn(player1);
        pieces[30] = new King(player1);
        pieces[31] = new Pawn(player1);
        pieces[32] = new Pawn(player1);
        pieces[33] = new Pawn(player1);
        pieces[34] = new Pawn(player1);
        pieces[35] = new Pawn(player1);
        pieces[36] = new Pawn(player1);

        ///////////////////////////////////////////////
        int c1=1;
        int c2=1;
        for (int i = 0; i < pieces.length; i++) {
            if(pieces[i].owner.isPlayerOne()){
                pieces[i].setId(c1);
                c1++;
            }else{
                pieces[i].setId(c2);
                c2++;
            }
        }
        ///////////////////////////////////////////////
        pieces[0].setXY(3, 0);
        pieces[1].setXY(4, 0);
        pieces[2].setXY(5, 0);
        pieces[3].setXY(6, 0);
        pieces[4].setXY(7, 0);
        pieces[5].setXY(5, 1);
        pieces[6].setXY(0, 3);
        pieces[7].setXY(10, 3);
        pieces[8].setXY(0, 4);
        pieces[9].setXY(10, 4);
        pieces[10].setXY(0, 5);
        pieces[11].setXY(1, 5);
        pieces[12].setXY(9, 5);
        pieces[13].setXY(10, 5);
        pieces[14].setXY(0, 6);
        pieces[15].setXY(10, 6);
        pieces[16].setXY(0, 7);
        pieces[17].setXY(10, 7);
        pieces[18].setXY(5, 9);
        pieces[19].setXY(3, 10);
        pieces[20].setXY(4, 10);
        pieces[21].setXY(5, 10);
        pieces[22].setXY(6, 10);
        pieces[23].setXY(7, 10);

        pieces[24].setXY(5, 3);
        pieces[25].setXY(4, 4);
        pieces[26].setXY(5, 4);
        pieces[27].setXY(6, 4);
        pieces[28].setXY(3, 5);
        pieces[29].setXY(4, 5);
        pieces[30].setXY(5, 5);
        pieces[31].setXY(6, 5);
        pieces[32].setXY(7, 5);
        pieces[33].setXY(4, 6);
        pieces[34].setXY(5, 6);
        pieces[35].setXY(6, 6);
        pieces[36].setXY(5, 7);
        ///////////////////////////////////////////////


        for (int i = 0; i < pieces.length; i++) {
            int x = pieces[i].getX();
            int y = pieces[i].getY();
            pieces[i].addSteps(new Position(x, y));
        }

        ///////////////////////////////////////////////
        initializeBoard();

        board[3][0] = new square(true);
        board[4][0] = new square(true);
        board[5][0] = new square(true);
        board[6][0] = new square(true);
        board[7][0] = new square(true);
        board[5][1] = new square(true);

        board[0][3] = new square(true);
        board[0][4] = new square(true);
        board[0][5] = new square(true);
        board[0][6] = new square(true);
        board[0][7] = new square(true);
        board[1][5] = new square(true);

        board[10][3] = new square(true);
        board[10][4] = new square(true);
        board[10][5] = new square(true);
        board[10][6] = new square(true);
        board[10][7] = new square(true);
        board[9][5] = new square(true);

        board[3][10] = new square(true);
        board[4][10] = new square(true);
        board[5][10] = new square(true);
        board[6][10] = new square(true);
        board[7][10] = new square(true);
        board[5][9] = new square(true);

        board[5][3] = new square(true);
        board[4][4] = new square(true);
        board[5][4] = new square(true);
        board[6][4] = new square(true);
        board[3][5] = new square(true);
        board[4][5] = new square(true);
        board[5][5] = new square(true);
        board[6][5] = new square(true);
        board[7][5] = new square(true);
        board[4][6] = new square(true);
        board[5][6] = new square(true);
        board[6][6] = new square(true);
        board[5][7] = new square(true);

        for (ConcretePiece p : pieces) {
            int x = p.getX();
            int y = p.getY();
            board[x][y].setCounterPlus(p);
        }
        this.help_undo = new Stack<>();
        for (ConcretePiece p : pieces) {
            help_undo.push(p);
        }


    }

    private void initializeBoard() {
        for (int x = 0; x <= 10; x++)
            for (int y = 0; y <= 10; y++)
                board[x][y] = new square(false);
    }

    public void setBoard(Position a, Position b) {
        board[a.getX()][a.getY()].setIsFull(false);
        board[b.getX()][b.getY()].setIsFull(true);
        ConcretePiece piece = getPieceAtXY(a.getX(), a.getY());
        piece.addSteps(new Position(b));
        piece.setDistance(a.getX(), a.getY(), b.getX(), b.getY());
        piece.setXY(b.getX(), b.getY());
        help_undo.push(piece);
    }

    public boolean isCleanLine(Position origin, Position target) {
        if (origin.getX() == target.getX()) {
            int bigger = Math.max(origin.getY(), target.getY());
            int smaller = Math.min(origin.getY(), target.getY());
            for (int i = smaller + 1; i < bigger; i++)
                if (board[origin.getX()][i].getIsFull())
                    return false;
        }
        if (origin.getY() == target.getY()) {
            int bigger = Math.max(origin.getX(), target.getX());
            int smaller = Math.min(origin.getX(), target.getX());
            for (int i = smaller + 1; i < bigger; i++)
                if (board[i][origin.getY()].getIsFull())
                    return false;
        }

        return true;
    }

    @Override
    public boolean move(Position a, Position b) {

        if (board[b.getX()][b.getY()].getIsFull()) //check if already there is a pawn in that place
            return false;

        if ((a.getX() != b.getX()) && (a.getY() != b.getY())) //check if the movement is not diagonal
            return false;

        if (!isCleanLine(a, b)) return false; //check if there is a clean path between a to b

        if (getPieceAtPosition(a).getType() != "♔") //check that not pawn can move to the corners
            if (inCorner(b.getX(), b.getY()))
                return false;

        if (isSecondPlayerTurn()) { //check that each player can move his own piece
            if (getPieceAtPosition(a).getOwner() != player2)
                return false;
        } else if (getPieceAtPosition(a).getOwner() != player1) {
            return false;
        }
        //check if the pawn eat enemy's pawn
        if (getPieceAtPosition(a).getType() != "♔")
            Eat(a,getPieceAtPosition(a).getOwner().isPlayerOne(), b.getX(), b.getY());


        board[b.getX()][b.getY()].setCounterPlus(getPieceAtXY(a.getX(), a.getY()));
        setBoard(a, b);
        this.turn = !(this.turn);
        this.isGameFinish = helpIsGameFinish(getPieceAtXY(b.getX(), b.getY()));
        if (this.isGameFinish) reset();
        //if (check) System.out.println("true");
        return true;
    }

    private boolean helpIsGameFinish(ConcretePiece c) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getType() == "♔")
                if (inCorner(pieces[i].getX(), pieces[i].getY())) {
                    this.player1.winsPlus();
                    this.winner = player1;
                    return true;
                } else if (isSurrounded(pieces[i].getX(), pieces[i].getY())) {
                    this.player2.winsPlus();
                    this.winner = player2;
                    return true;
                }
        }
        return false;
    }

    public void Eat(Position a,boolean player, int x, int y) { //check if the pawn eat enemy's pawn
        if (isInBoard(x, y + 1) && board[x][y + 1].getIsFull() && getPieceAtXY(x, y + 1).getType() != "♔" && getPieceAtXY(x, y + 1).getOwner().isPlayerOne() != player)
            if (!isInBoard(x, y + 2) || inCorner(x, y + 2) || (board[x][y + 2].getIsFull() && getPieceAtXY(x, y + 2).getType() != "♔" && getPieceAtXY(x, y + 2).getOwner().isPlayerOne() == player)) {
                board[x][y + 1].setIsFull(false);
                help_undo.push(getPieceAtXY(x, y + 1));
                getPieceAtXY(x, y + 1).setXY(-1, -1);
                ((Pawn)getPieceAtPosition(a)).killPlus();

            }

        if (isInBoard(x, y - 1) && board[x][y - 1].getIsFull() && getPieceAtXY(x, y - 1).getType() != "♔" && getPieceAtXY(x, y - 1).getOwner().isPlayerOne() != player)
            if (!isInBoard(x, y - 2) || inCorner(x, y - 2) || (board[x][y - 2].getIsFull() && getPieceAtXY(x, y - 2).getType() != "♔" && getPieceAtXY(x, y - 2).getOwner().isPlayerOne() == player)) {
                board[x][y - 1].setIsFull(false);
                help_undo.push(getPieceAtXY(x, y - 1));
                getPieceAtXY(x, y - 1).setXY(-1, -1);
                ((Pawn)getPieceAtPosition(a)).killPlus();
            }

        if (isInBoard(x + 1, y) && board[x + 1][y].getIsFull() && getPieceAtXY(x + 1, y).getType() != "♔" && getPieceAtXY(x + 1, y).getOwner().isPlayerOne() != player)
            if (!isInBoard(x + 2, y) || inCorner(x + 2, y) || (board[x + 2][y].getIsFull() && getPieceAtXY(x + 2, y).getType() != "♔" && getPieceAtXY(x + 2, y).getOwner().isPlayerOne() == player)) {
                board[x + 1][y].setIsFull(false);
                help_undo.push(getPieceAtXY(x + 1, y));
                getPieceAtXY(x + 1, y).setXY(-1, -1);
                ((Pawn)getPieceAtPosition(a)).killPlus();
            }

        if (isInBoard(x - 1, y) && board[x - 1][y].getIsFull() && getPieceAtXY(x - 1, y).getType() != "♔" && getPieceAtXY(x - 1, y).getOwner().isPlayerOne() != player)
            if (!isInBoard(x - 2, y) || inCorner(x - 2, y) || (board[x - 2][y].getIsFull() && getPieceAtXY(x - 2, y).getType() != "♔" && getPieceAtXY(x - 2, y).getOwner().isPlayerOne() == player)) {
                board[x - 1][y].setIsFull(false);
                help_undo.push(getPieceAtXY(x - 1, y));
                getPieceAtXY(x - 1, y).setXY(-1, -1);
                ((Pawn)getPieceAtPosition(a)).killPlus();
            }
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getX() == x && pieces[i].getY() == y)
                return pieces[i];
        }
        return null;
    }

    public ConcretePiece getPieceAtXY(int x, int y) {
        for (int i = 0; i < pieces.length; i++)
            if (pieces[i].getX() == x && pieces[i].getY() == y)
                return pieces[i];
        return null;
    }

    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    public boolean inCorner(int x, int y) { //if (x,y) is in the corner
        if ((x == 0 && y == 0) || (x == 10 && y == 0) || (x == 0 && y == 10) || (x == 10 && y == 10))
            return true;
        return false;
    }

    public boolean isInBoard(int x, int y) {
        if (x <= 10 && x >= 0 && y <= 10 && y >= 0)
            return true;
        return false;
    }

    public boolean isSurrounded(int x, int y) { //if king surrounded by attacker's pawns
        int count = 0;
        if (x == 10 || x == 0 || y == 10 || y == 0) {
            if (isInBoard(x, y + 1) && board[x][y + 1].getIsFull() && !getPieceAtXY(x, y + 1).getOwner().isPlayerOne())
                count++;
            if (isInBoard(x + 1, y) && board[x + 1][y].getIsFull() && !getPieceAtXY(x + 1, y).getOwner().isPlayerOne())
                count++;
            if (isInBoard(x - 1, y) && board[x - 1][y].getIsFull() && !getPieceAtXY(x - 1, y).getOwner().isPlayerOne())
                count++;
            if (isInBoard(x, y - 1) && board[x][y - 1].getIsFull() && !getPieceAtXY(x, y - 1).getOwner().isPlayerOne())
                count++;
            if (count >= 3)
                return true;
        } else {
            if (isInBoard(x, y + 1) && board[x][y + 1].getIsFull() && !getPieceAtXY(x, y + 1).getOwner().isPlayerOne())
                count++;
            if (isInBoard(x + 1, y) && board[x + 1][y].getIsFull() && !getPieceAtXY(x + 1, y).getOwner().isPlayerOne())
                count++;
            if (isInBoard(x - 1, y) && board[x - 1][y].getIsFull() && !getPieceAtXY(x - 1, y).getOwner().isPlayerOne())
                count++;
            if (isInBoard(x, y - 1) && board[x][y - 1].getIsFull() && !getPieceAtXY(x, y - 1).getOwner().isPlayerOne())
                count++;
            if (count == 4)
                return true;
        }
        return false;
    }

    @Override
    public boolean isGameFinished() {
        return this.isGameFinish;
    }

    private void report() {

        List<ConcretePiece> listHelp = new ArrayList<>(Arrays.asList(this.pieces));
        listHelp.sort(new stepsComperator());
        if (this.winner == player1) {
            print_steps_one(listHelp);
            print_steps_two(listHelp);
            System.out.printf("***************************************************************************\n");
        } else {
            print_steps_two(listHelp);
            print_steps_one(listHelp);
            System.out.printf("***************************************************************************\n");
        }

        King temp = ((King) (listHelp.remove(findKing(listHelp))));
        listHelp.sort(new killsComperator());
        print_kills(listHelp);
        listHelp.add(temp);

        listHelp.sort(new distanceComperator());
        print_distance(listHelp);

        ArrayList<square> squares = new ArrayList<>();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                square sq = board[x][y];
                squares.add(sq);
                sq.setX(x);
                sq.setY(y);
            }
        }
        squares.sort(new PiecesComparator());
        print_square(squares);
    }

    private void print_square(List<square> lst) {
        for (square s : lst)
            if (s.getCounter() >= 2)
                System.out.printf("(" + s.getX() + ", " + s.getY() + ")" + s.getCounter() + " pieces\n");
        System.out.printf("***************************************************************************\n");
    }

    private void print_distance(List<ConcretePiece> lst) {
        for (ConcretePiece piece : lst) {
            if (piece.getDistance() > 0) {
                if (piece.getOwner().isPlayerOne()) {
                    if (piece.getType() == "♔") {
                        System.out.printf("K" + piece.getId() + ": " + piece.getDistance() + " squares\n");
                    } else {
                        System.out.printf("D" + piece.getId() + ": " + piece.getDistance() + " squares\n");
                    }
                } else {
                    System.out.printf("A" + piece.getId() + ": " + piece.getDistance() + " squares\n");
                }
            }
        }
        System.out.printf("***************************************************************************\n");
    }

    private int findKing(List<ConcretePiece> lst) {
        for (int i = 0; i < pieces.length; i++) {
            if (lst.get(i).getType() == "♔")
                return i;
        }
        return 0;
    }

    private void print_kills(List<ConcretePiece> lst) { //add compare between who won>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        for (ConcretePiece piece : lst) {
            if (((Pawn) piece).getKills() > 0) {
                if (piece.getOwner().isPlayerOne())
                    System.out.printf("D" + piece.getId() + ": " + ((Pawn) piece).getKills() + " kills\n");
                else
                    System.out.printf("A" + piece.getId() + ": " + ((Pawn) piece).getKills() + " kills\n");
            }
        }
        System.out.printf("***************************************************************************\n");
    }

    private void print_steps_one(List<ConcretePiece> lst) {
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getOwner().isPlayerOne() && lst.get(i).getCount_steps() >= 2) {
                String c = "";
                if (lst.get(i).getType() == "♔")
                    c = "K";
                else
                    c = "D";
                int id = lst.get(i).getId();
                System.out.printf(c + id + ": ");
                lst.get(i).printStuck();
            }
        }
    }

    private void print_steps_two(List<ConcretePiece> lst) {
        for (int i = 0; i < lst.size(); i++) {
            if (!lst.get(i).getOwner().isPlayerOne() && lst.get(i).getCount_steps() >= 2) {
                String c = "A";
                int id = lst.get(i).getId();
                System.out.printf(c + id + ": ");
                lst.get(i).printStuck();
            }
        }
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !(this.turn);
    }

    @Override
    public void reset() {
//        if(check) System.out.println("reset  !!!!!!!!!!!!!!!!!");
        if (this.isGameFinish) {
            this.isGameFinish = !this.isGameFinish;
            report();
        }
        initializeNewGame();
    }

    @Override
    public void undoLastMove() {

        if (help_undo.size() <= 37) return;
        ConcretePiece temp = help_undo.pop();
        if (temp.getX() != -1 && temp.getY() != -1) {
            board[temp.getX()][temp.getY()].undo();
            temp.undoPiece();
            board[temp.getX()][temp.getY()].setIsFull(true);
        }
        this.turn = !this.turn;

        ConcretePiece temp2 = help_undo.peek();
        while (temp2.getX() == -1 && temp2.getY() == -1) {
            temp2 = help_undo.pop();
            temp2.undoPiece();
            board[temp2.getX()][temp2.getY()].setIsFull(true);
        }
    }

    @Override
    public int getBoardSize() {
        return this.BoardSize;
    }

    public ConcretePlayer getWinner() {
        return this.winner;
    }

    class stepsComperator implements Comparator<ConcretePiece> {
        @Override
        public int compare(ConcretePiece o1, ConcretePiece o2) {

            return o1.getCount_steps() - o2.getCount_steps();
        }
    }

    class killsComperator implements Comparator<ConcretePiece> {
        @Override
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            int killsComparison = ((Pawn) o2).getKills() - ((Pawn) o1).getKills();
            if (killsComparison != 0) {
                return killsComparison;
            } else {
                int idComparison = o1.getId() - o2.getId();
                if (idComparison != 0) {
                    return idComparison;
                } else {
                    ConcretePlayer winner = getWinner();
                    if (o1.getOwner() == winner) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }
    }

    class distanceComperator implements Comparator<ConcretePiece> {
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            int distanceComparison = o2.getDistance() - o1.getDistance();

            if (distanceComparison != 0) {
                return distanceComparison;
            } else {
                // If distance is equal, sort by tool number in ascending order
                int idComparison = o1.getId() - o2.getId();

                if (idComparison != 0) {
                    return idComparison;
                } else {
                    ConcretePlayer winner = getWinner();
                    if (o1.getOwner() == winner) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }
    }


    class PiecesComparator implements Comparator<square> {
        @Override
        public int compare(square square1, square square2) {
            int count1 = square1.getCounter();
            int count2 = square2.getCounter();

            int countDifference = count2 - count1;
            if (countDifference != 0) {
                return countDifference;
            }

            int xDifference = square1.getX() - square2.getX();
            if (xDifference != 0) {
                return xDifference;
            }

            return square1.getY() - square2.getY();
        }
    }
}
