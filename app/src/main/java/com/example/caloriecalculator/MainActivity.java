// Importing the required packages
package com.example.caloriecalculator;

// Importing the required libraries
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.regex.Pattern;

// Creating the main class and extending it with the AppCompatActivity class
public class MainActivity extends AppCompatActivity {

    // Declaring the required variables
    private TextInputEditText age, height, weight;
    private RadioGroup gender;
    private MaterialRadioButton male;
    private TextView calories, required, textView1, textView2, textView3, textView4, textView5, textView6, text_dummy;
    public AppCompatButton calculate, reset;


    // Creating the onCreate method
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting the content view to the activity_main.xml file
        setContentView(R.layout.activity_main);

        // Initializing the variables
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        gender = findViewById(R.id.gender);
        male = findViewById(R.id.male);
        calories = findViewById(R.id.calories);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        text_dummy = findViewById(R.id.text_dummy);
        required = findViewById(R.id.required);
        calculate = findViewById(R.id.calculate);
        reset = findViewById(R.id.reset);

        // Creating the onClickListener for the calculate button
        calculate.setOnClickListener(v -> {

            // Getting the values from the text fields
            String ageText = Objects.requireNonNull(age.getText()).toString();
            String heightText = Objects.requireNonNull(height.getText()).toString();
            String weightText = Objects.requireNonNull(weight.getText()).toString();

            // Creating the pattern for the regular expression
            // This will check if the value is a number or not
            Pattern pattern = Pattern.compile("\\d+");

            // Creating the variables for the checks and setting them to false
            // These will be used to check if the values are empty or not
            boolean ageCheck;
            boolean heightCheck;
            boolean weightCheck;

            // Checking if the age text field is empty or not
            // If it is empty, then it will show an error message
            if(ageText.isEmpty()){
                age.setError("Please enter your age");
                age.requestFocus();
                ageCheck = false;
            } else if (!pattern.matcher(ageText).matches()) {
                age.setError("Please enter your age correctly");
                age.requestFocus();
                ageCheck = false;
            } else {
                age.setError(null);
                ageCheck = true;
            }

            // Checking if the height text field is empty or not
            // If it is empty, then it will show an error message
            if(heightText.isEmpty()){
                height.setError("Please enter your height");
                height.requestFocus();
                heightCheck = false;
            } else if (!pattern.matcher(ageText).matches()) {
                age.setError("Please enter your age correctly");
                age.requestFocus();
                heightCheck = false;
            } else {
                height.setError(null);
                heightCheck = true;
            }

            // Checking if the weight text field is empty or not
            // If it is empty, then it will show an error message
            if(weightText.isEmpty()){
                weight.setError("Please enter your weight");
                weight.requestFocus();
                weightCheck = false;
            } else if (!pattern.matcher(ageText).matches()) {
                age.setError("Please enter your age correctly");
                age.requestFocus();
                weightCheck = false;
            } else {
                weight.setError(null);
                weightCheck = true;
            }

            // Checking if the user has selected the gender or not
            if(gender.getCheckedRadioButtonId() == -1) {
                required.setText("Please Select Gender");
                required.setVisibility(View.VISIBLE);
            } else {
                required.setText("");
                required.setVisibility(View.GONE);

                // Checking if all the values are not empty
                if(ageCheck && heightCheck && weightCheck){

                    // Calling the calculateBMR method
                    calculateCalorie();
                }
            }



        });

    }

    // Creating the calculate method to calculate the calories required
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void calculateCalorie(){

        // Getting the values from the text fields
        int ageValue = Integer.parseInt(Objects.requireNonNull(age.getText()).toString());
        int heightValue = Integer.parseInt(Objects.requireNonNull(height.getText()).toString());
        int weightValue = Integer.parseInt(Objects.requireNonNull(weight.getText()).toString());

        // Creating the variable for the total calories
        double totalCalories;

        if(gender.getCheckedRadioButtonId()== male.getId()){
            // If user is "Male" then the following formula will be used to calculate the calories
            totalCalories = (10 * weightValue) + (6.25 * heightValue) - (5 * ageValue) + 5;

            // Setting the text to the calories text view

        } else {
            // If user is "Female" then the following formula will be used to calculate the calories
            totalCalories = (10 * weightValue) + (6.25 * heightValue) - (5 * ageValue)- 161;
        }
        calories.setText(String.format("%.2f", totalCalories)+"*");
        text_dummy.setVisibility(View.VISIBLE);

        // Setting the text to the calories in the table layout and rounding it to 2 decimal places
        textView1.setText(String.format("%.2f", totalCalories)+"*");
        textView2.setText(String.format("%.2f", totalCalories*1.149)+"*");
        textView3.setText(String.format("%.2f", totalCalories*1.220)+"*");
        textView4.setText(String.format("%.2f", totalCalories*1.292)+"*");
        textView5.setText(String.format("%.2f", totalCalories*1.437)+"*");
        textView6.setText(String.format("%.2f", totalCalories*1.583)+"*");

        // Setting the text to the text view and making it visible
        required.setText("*"+"Calculation is based on the Mifflin-St Jeor Equation");
        required.setTextSize(12);
        required.setVisibility(View.VISIBLE);

    }

    @SuppressLint("SetTextI18n")
    public void reset(View view) {
        age.setText("");
        height.setText("");
        weight.setText("");
        gender.clearCheck();
        calories.setText("Calories");
        textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");
        textView5.setText("");
        textView6.setText("");
        text_dummy.setVisibility(View.GONE);
        required.setVisibility(View.GONE);
    }

    public void calculate(View view) {

    }
}