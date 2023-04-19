package com.example.homepage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.util.Base64;
import android.util.SparseArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
    private Button previousButton = null;
    private int clickCount = 0;
    private SharedPreferences mPrefs;
    //BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavBar);
//
//        bottomNavigationView.setOnClickListener();




        // Initialize SharedPreferences instance
        mPrefs = getPreferences(MODE_PRIVATE);

        // Restore the state of quiz_container if it was previously saved
        if (mPrefs.contains("quiz_container_state")) {
            String quizContainerState = mPrefs.getString("quiz_container_state", "");
            if (!quizContainerState.isEmpty()) {
                RelativeLayout quizContainer = findViewById(R.id.quiz_container);
                quizContainer.removeAllViews();
                try {
                    // Inflate the saved state and add it to quiz_container
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setInput(new StringReader(quizContainerState));
                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            String name = parser.getName();
                            if (name.equals("Button")) {
                                Button newButton = new Button(this);
                                newButton.setId(View.generateViewId());
                                newButton.setText(parser.getAttributeValue(null, "text"));
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                                params.addRule(RelativeLayout.ALIGN_TOP, previousButton.getId());
                                params.addRule(RelativeLayout.RIGHT_OF, previousButton.getId());
                                newButton.setLayoutParams(params);
                                newButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(Homepage.this, QuizActivity.class);
                                        intent.putExtra("layoutResourceId", R.layout.quiz_layout);
                                        startActivity(intent);
                                    }
                                });
                                quizContainer.addView(newButton);
                                previousButton = newButton;
                            } else if (name.equals("View")) {
                                View newRow = new View(this);
                                newRow.setId(View.generateViewId());
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                        0,
                                        0);
                                params.addRule(RelativeLayout.BELOW, previousButton.getId());
                                newRow.setLayoutParams(params);
                                quizContainer.addView(newRow);
                                previousButton = null;
                            }
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void CreateNewQuiz(View v) {
        showConfirmationDialog();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Draw a New Card");
        builder.setMessage("Are you sure you want to add a new button?");

        // Add EditText view to dialog
        builder.setMessage("Name of the quiz :");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Create new Button widget
                Button newButton = new Button(Homepage.this);

                // Set button text to user input or default name

                String buttonText = input.getText().toString().trim();
                if (buttonText.isEmpty()) {
                    buttonText = "Cardlets" + clickCount;
                }
                newButton.setText(buttonText);

                // Set layout parameters for new button
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                // Check if new button will fit on current row
                float buttonWidth = newButton.getPaint().measureText(newButton.getText().toString()) + newButton.getPaddingLeft() + newButton.getPaddingRight();
                int containerWidth = findViewById(R.id.quiz_container).getWidth();
                if (previousButton != null && previousButton.getRight() + buttonWidth > containerWidth) {
                    // Create new row
                    View newRow = new View(Homepage.this);
                    newRow.setId(View.generateViewId());
                    RelativeLayout.LayoutParams rowParams = new RelativeLayout.LayoutParams(
                            0,
                            0);
                    rowParams.addRule(RelativeLayout.BELOW, previousButton.getId());
                    newRow.setLayoutParams(rowParams);
                    RelativeLayout quizContainer = findViewById(R.id.quiz_container);
                    quizContainer.addView(newRow);

                    // Position new button to left of new row
                    params.addRule(RelativeLayout.BELOW, newRow.getId());
                    params.addRule(RelativeLayout.ALIGN_START, newRow.getId());
                } else if (previousButton != null) {
                    // Position new button to right of previous button
                    params.addRule(RelativeLayout.RIGHT_OF, previousButton.getId());
                    params.addRule(RelativeLayout.ALIGN_TOP, previousButton.getId());
                } else {
                    // Position new button to left of "add" button
                    params.addRule(RelativeLayout.START_OF, R.id.add_Button);
                    params.addRule(RelativeLayout.ALIGN_TOP, R.id.add_Button);
                }

                // Generate a unique ID for the new button
                int buttonId = View.generateViewId();
                newButton.setId(buttonId);

                // Set the layout params for the new button
                newButton.setLayoutParams(params);

                // Set an OnClickListener for the new button
                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
// Create an Intent to start the new activity
                        Intent intent = new Intent(Homepage.this, AnotherActivity.class);
                        intent.putExtra("layoutResourceId", R.layout.quiz_layout);

// Start the new activity
                        startActivity(intent);
                    }
                });

                // Add the new button to the container
                RelativeLayout quizContainer = findViewById(R.id.quiz_container);
                quizContainer.addView(newButton);

                // Update previousButton to the newly added button
                previousButton = newButton;

                // Increment the clickCount
                clickCount++;
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Save the state of quiz_container in SharedPreferences
        RelativeLayout quizContainer = findViewById(R.id.quiz_container);
        SparseArray<View> views = new SparseArray<>();
        int childCount = quizContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = quizContainer.getChildAt(i);
            views.put(child.getId(), child);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(views);
            oos.flush();
            String state = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
            mPrefs.edit().putString("quiz_container_state", state).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottommenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "Homepage selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Homepage.class));
                return true;
            case R.id.profile:
                Toast.makeText(this, "Profile page selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}