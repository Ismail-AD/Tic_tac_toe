package com.appdev.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean playerXTurn = true; // Flag to keep track of current player

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        button.setTextColor(getResources().getColor(R.color.black,null));
        if (button.getText().toString().isEmpty()) { // Check if the button is empty
            if (playerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            playerXTurn = !playerXTurn; // Switch player turn

            checkWinner();
        }
    }

    private void clearButtons() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setText(""); // Clear the text
        }
    }
    private void checkWinner() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkRowColumn(gridLayout, i, 0, i, 1, i, 2)) {
                displayWinner(gridLayout.getChildAt(i*3));
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (checkRowColumn(gridLayout, 0, i, 1, i, 2, i)) {
                displayWinner(gridLayout.getChildAt(i));
                return;
            }
        }

        // Check diagonals
        if (checkRowColumn(gridLayout, 0, 0, 1, 1, 2, 2) || checkRowColumn(gridLayout, 0, 2, 1, 1, 2, 0)) {
            displayWinner(gridLayout.getChildAt(4));
            return;
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            if (button.getText().toString().isEmpty()) {
                draw = false;
                break;
            }
        }
        if (draw) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkRowColumn(GridLayout gridLayout, int row1, int col1, int row2, int col2, int row3, int col3) {
        Button b1 = (Button) gridLayout.getChildAt(row1 * 3 + col1);
        Button b2 = (Button) gridLayout.getChildAt(row2 * 3 + col2);
        Button b3 = (Button) gridLayout.getChildAt(row3 * 3 + col3);
        return !b1.getText().toString().isEmpty() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }
    private void disableAllButtons() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setEnabled(false); // Disable the button
        }
    }
    private void enableAllButtons() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setEnabled(true); // Disable the button
        }
    }

    private void displayWinner(View view) {
        String winner = ((Button) view).getText().toString();
        Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();
        disableAllButtons();
    }


    public void resetGame(View view) {
        clearButtons();
        enableAllButtons();
    }
}