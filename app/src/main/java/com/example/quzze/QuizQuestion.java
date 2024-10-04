package com.example.quzze;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Handler;

import com.example.quzze.model.Country;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvProgress;
    private ImageView ivFlag;
    private RadioGroup rgOptions;
    private Button btnSubmit;

    private List<Country> countries = new ArrayList<>();
    private String continent;
    private int currentQuestionIndex = 0;
    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        // Get the selected continent from the Intent
        String continent = getIntent().getStringExtra("CONTINENT");


        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        // Set the AppBar title as the continent name
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(continent); // Set the title to the continent name
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set up back arrow click listener
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Set status bar color to match the app bar
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        // View bindings
        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);
        ivFlag = findViewById(R.id.ivFlag);
        rgOptions = findViewById(R.id.rgOptions);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Load countries for the selected continent
        loadCountriesForContinent(continent);

        // Load the first question
        loadQuestion();

        // Handle button click for checking answer
        btnSubmit.setOnClickListener(v -> checkAnswer());
    }

    // Optional: Override onBackPressed if you want to customize back behavior
    @Override
    public void onBackPressed() {
        super.onBackPressed();  // Or you can handle a custom behavior
    }
    // Method to load questions based on the selected continent
    private void loadCountriesForContinent(String continent) {
        if (continent != null) {
            switch (continent) {
                case "Africa":
                    countries.add(new Country("Kenya", R.drawable.ke));
                    countries.add(new Country("Nigeria", R.drawable.ng));
                    countries.add(new Country("South Africa", R.drawable.za));
                    countries.add(new Country("Egypt", R.drawable.eg));
                    break;
//                case "Asia":
//                    countries.add(new Country("China", R.drawable.china));
//                    countries.add(new Country("India", R.drawable.india));
//                    countries.add(new Country("Japan", R.drawable.japan));
//                    countries.add(new Country("South Korea", R.drawable.south_korea));
//                    break;
//                case "Europe":
//                    countries.add(new Country("France", R.drawable.france));
//                    countries.add(new Country("Germany", R.drawable.germany));
//                    countries.add(new Country("United Kingdom", R.drawable.uk));
//                    countries.add(new Country("Italy", R.drawable.italy));
//                    break;
                // Add more continents here...
            }
        }
    }

    // Method to load the current question
    private void loadQuestion() {
        // Update progress
        tvProgress.setText((currentQuestionIndex + 1) + "/" + countries.size());
        progressBar.setProgress((currentQuestionIndex + 1) * 100 / countries.size());

        // Load the flag and options for the current question
        Country currentCountry = countries.get(currentQuestionIndex);
        ivFlag.setImageResource(currentCountry.getFlagResource());

        // Shuffle options and set to RadioButtons
        List<String> options = generateOptions(currentCountry);
        for (int i = 0; i < rgOptions.getChildCount(); i++) {
            ((RadioButton) rgOptions.getChildAt(i)).setText(options.get(i));
        }
    }

    // Method to check the selected answer
    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkAnswer() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        int answerIndex = rgOptions.indexOfChild(selectedRadioButton);


        // Check if the answer is correct
        if (selectedRadioButton.getText().equals(countries.get(currentQuestionIndex).getName())) {
            selectedRadioButton.setBackground(getDrawable(R.drawable.radio_checked)); // Correct answer color
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            result++;
        } else {
            selectedRadioButton.setBackgroundResource(R.drawable.radio_unchecked); // Incorrect answer color
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

        // Delay for a brief moment before moving to the next question
        new Handler().postDelayed(() -> {
            // Reset the selected RadioButton color to default
            selectedRadioButton.setBackgroundResource(R.drawable.radio_button_background);

            // Move to the next question or finish the quiz
            if (currentQuestionIndex < countries.size() - 1) {
                currentQuestionIndex++;
                loadQuestion();
            } else {
                // End of quiz
                String name = getIntent().getStringExtra("NAME");
                Toast.makeText(this, "Quiz finished!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuizQuestion.this, ResultActivity.class);
                intent.putExtra("RESULT", result);
                intent.putExtra("Total", countries.size());
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
        }, 1000);
    }

    // Method to generate options (including the correct answer and random countries)
    private List<String> generateOptions(Country correctCountry) {
        List<String> options = new ArrayList<>();
        options.add(correctCountry.getName());

        // Add random options (this can be improved with a more robust random selection)
        for (Country country : countries) {
            if (!country.getName().equals(correctCountry.getName())) {
                options.add(country.getName());
            }
        }
        // Shuffle options for randomness
        java.util.Collections.shuffle(options);
        return options;
    }
}
