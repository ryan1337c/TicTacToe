package eecs1022.lab8.tictactoe.model;

public class Game {
    private String p1;
    private String p2;
    private String play;
    private char [][] board = new char [3][3];
    private String status;
    private  String endResults = ""; // Win or Tie
    public Game(String p1, String p2){
        this.p1 = p1;
        this.p2 = p2;
        this.play = this.p1;
        this.status = this.play + "'s turn to play...";
        for (int row=0; row<board.length; row++){
            for (int col=0; col<board[row].length; col++){
                this.board[row][col] = '_';
            }
        }
    }
    public Game(){
        for (int row=0; row<board.length; row++){
            for (int col=0; col<board[row].length; col++){
                this.board[row][col] = '_';
            }
        }
    }
    public String getCurrentPlayer(){
        return this.play;
    }
    public String getStatus(){
        return this.status;
    }
    public char[][] getBoard(){
        return this.board;
    }
    public void setWhoPlaysFirst(char p){
        if (p == 'o'){
            this.play = this.p2;
            this.status = this.play + "'s turn to play...";
        }
        else{
            this.play = this.p1;
            this.status = this.play + "'s turn to play...";
        }
    }
    public void move(int row, int col){
        if (this.endResults.equals("Win")){
            this.status = "Error: game is already over with a winner.";
        }
        else if (this.endResults.equals("Tie")){
            this.status = "Error: game is already over with a tie.";
        }
        else if (!(row>=1 && row<=3)){
            this.status = "Error: row "+ row + " is invalid.";
        }
        else if (!(col>=1 && col<=3)){
            this.status = "Error: col "+ col + " is invalid.";
        }
        else{
            boardUpdate(row, col);
        }
    }
    private void boardUpdate(int row, int col){
        boolean endGame = false;
        int count = 0;
        if (this.play == this.p1){
            if (this.board[row-1][col-1] == '_'){
                this.board[row-1][col-1] = 'x';
                if (checkWin(row,col,'x') == true){
                    endGame = true;
                }
                else{
                    this.play = this.p2;
                    this.status = this.play + "'s turn to play...";
                }
            }
            else{
                this.status = "Error: slot @ "+"(" + row + ", "+col+ ") "+"is already occupied.";
            }
        }
        else{
            if (this.board[row-1][col-1] == '_'){
                this.board[row-1][col-1] = 'o';
                if (checkWin(row,col,'o') == true){
                    endGame = true;
                }
                else{
                    this.play = this.p1;
                    this.status = this.play + "'s turn to play...";
                }
            }
            else{
                this.status = "Error: slot @ "+"(" + row + ", "+col+ ") "+"is already occupied.";
            }
        }
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if (!(this.board[i][j] == '_')){
                    count++;
                }
            }
        }
        // checks for winner
        if (endGame == true){
            this.endResults = "Win";
            this.status = "Game is over with " + this.play + " being the winner.";
            this.play = null;
        }
        // checks if board is filled for tie
        else if (count == 9){
            this.endResults = "Tie";
            this.status = "Game is over with a tie between " + this.p1 + " and " + this.p2 + ".";
            this.play = null;
        }
    }
    private boolean checkWin (int row, int col, char symbol){
        int count = 0;
       // check row
        for (int i=0; i<3; i++){
            if (this.board[row-1][i] == symbol){
                count++;
            }
        }
        if (count == 3){
            return true;
        }
        count = 0;
        // check col
        for (int i=0; i<3; i++){
            if (this.board[i][col-1] == symbol){
                count++;
            }
        }
        if (count == 3){
            return true;
        }
        count = 0;
        // check diagonal
        for (int i=0; i<3; i++){
            if (this.board[i][i] == symbol){
                count++;
            }
        }
        if (count == 3){
            return true;
        }
        count = 0;
        // check anti-diagonal
        for (int i=0; i<3; i++){
            if (this.board[i][2-i] == symbol){
                count++;
            }
        }
        if (count == 3){
            return true;
        }
        count = 0;
        return false;
    }

}
