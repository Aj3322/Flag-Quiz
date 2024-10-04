package com.example.quzze;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private final String[] continents = {"Africa", "Asia", "Europe", "North America", "Oceania", "South America"};
    private final int[] continentImages = {
            R.drawable.africa, R.drawable.africa, R.drawable.africa,
            R.drawable.africa, R.drawable.africa, R.drawable.africa
    };

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find tvWelcome and gridLayout inside the fragment's layout
        TextView tvWelcome = view.findViewById(R.id.tvWelcome);
        GridLayout gridLayout = view.findViewById(R.id.gridLayout);

        // Set welcome message
        String userName = getActivity().getIntent().getStringExtra("USER_NAME");
        if (userName != null && !userName.isEmpty()) {
            tvWelcome.setText("Welcome " + userName);
        }

        // Set up the GridLayout items
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View itemView = gridLayout.getChildAt(i);
            ImageView ivContinent = itemView.findViewById(R.id.ivContinent);
            TextView tvContinentName = itemView.findViewById(R.id.tvContinentName);

            ivContinent.setImageResource(continentImages[i]);
            tvContinentName.setText(continents[i]);

            final int index = i;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start quiz for the selected continent
                    startQuizForContinent(continents[index]);
                }
            });
        }

        return view;
    }

    private void startQuizForContinent(String continent) {
        String userName = getActivity().getIntent().getStringExtra("USER_NAME");
        Intent intent = new Intent(getActivity(), QuizQuestion.class);
        intent.putExtra("CONTINENT", continent);
        intent.putExtra("NAME", userName);
        startActivity(intent);
    }

}