package eecs1022.lab8.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eecs1022.lab8.tictactoe.model.Game;


public class MainActivity extends AppCompatActivity {

    Game c;
    String board;
    String player1;
    String player2;
    /* Hint: How do you share the same game object between button clicks
     * (attached with controller methods) of the app?
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Hint: How do you display the initial status to the output textview
         * when the app is first launched?
         */
        c = new Game();
        board = "";
        setContentsOfTextView(R.id.outputResults, "No game has been started.");
    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    /* Hints on controller methods:
     *
     * Declare two controller methods, each of which declared with a parameter with type View (see Week 9 Tutorials).
     *  - One method (say cm1) should be attached to the "START/RESTART" button,
     *      whereas the other method (say cm2) should be attached to the "MOVE" button.
     *
     *  - Controller method cm1 should:
     *    + Retrieve the names of the two players from the corresponding textfields.
     *    + Retrieve the player (who will play first) from the spinner.
     *    + Then, re-create the shared game object and invoke the relevant method(s).
     *    + Finally, display the expected output of the game (i.e., board and status) to the output textview.
     *
     * - Controller method cm2 should:
     *    + Retrieve the row and column numbers (as strings) from the corresponding textfields.
     *      You may need to convert the retrieved text, e.g., "1" to an integer value using Double.parseInt.
     *    + Then, invoke the relevant method(s) on the shared game object.
     *    + Finally, display the expected output of the game (i.e., board and status) to the ouptut textview.
     */

    public void startRestartButton (View view){
        board = "";
        // Reading player name input and starting the game
        player1 = getInputOfTextField(R.id.inputP1Name);
        player2 = getInputOfTextField(R.id.inputP2Name);
        c = new Game (player1, player2);

        // Display Board
        for (int i=0; i<c.getBoard().length; i++){
            for (int j=0; j<c.getBoard()[i].length; j++){
                board += c.getBoard()[i][j];
                if (j!=c.getBoard()[i].length - 1){
                    board+="    ";
                }
            }
            board+="\n";
        }
        // See who plays first
        if (getItemSelected(R.id.PlayerList).equals("Player X")){
            c.setWhoPlaysFirst('x');
            setContentsOfTextView(R.id.outputResults, board + "\n" + c.getStatus());
        }
        else{
            c.setWhoPlaysFirst('o');
            setContentsOfTextView(R.id.outputResults, board + "\n" + c.getStatus());
        }
    }

    public void moveButton(View view){
        board = "";
        // Reading user input for row and col
        String textRow = getInputOfTextField(R.id.userRow);
        String textCol = getInputOfTextField(R.id.userCol);
        c.move(Integer.parseInt(textRow), Integer.parseInt(textCol));

        // Display Board
        for (int i=0; i<c.getBoard().length; i++){
            for (int j=0; j<c.getBoard()[i].length; j++){
                board += c.getBoard()[i][j];
                if (j!=c.getBoard()[i].length - 1){
                    board+="    ";
                }
            }
            board+="\n";
        }
        setContentsOfTextView(R.id.outputResults, board + "\n" + c.getStatus());
    }



}