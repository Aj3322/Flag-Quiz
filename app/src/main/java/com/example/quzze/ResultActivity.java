package com.example.quzze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.quzze.databinding.ActivityResultBinding;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        // Get the Intent that started this activity and extract the result and total questions
        Intent intent = getIntent();
        int result = intent.getIntExtra("RESULT", 0);
        int total = intent.getIntExtra("Total", 0);

        // Update the score text
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText("Your score is " + result + " out of " + total + ".");
        toolbar.setTitle(result >= total / 2 ? "You Won!" : "You Lost!");
        // Optionally, you can set the congratulations message
        TextView congratulationsText = findViewById(R.id.congratulationsText);
        congratulationsText.setText("Congratulations!");

        // Set up the finish button
        Button finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(v -> {
            // Close the activity when the user clicks finish
            Intent restartIntent = new Intent(ResultActivity.this, MainMenuActivity.class);
            startActivity(restartIntent);
            finish();
        });

        // Set up the play again button
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(v -> {
            // Restart the quiz or navigate to the quiz start screen
            Intent restartIntent = new Intent(ResultActivity.this, QuizQuestion.class);
            startActivity(restartIntent);
            finish(); // Optionally close this activity
        });
    }
}
