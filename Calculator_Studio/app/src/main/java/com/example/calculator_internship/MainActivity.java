package com.example.calculator_internship;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private String currentInput = "";
    private double firstNumber = 0;
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Attach listener to all buttons inside gridLayout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View v = gridLayout.getChildAt(i);
            if (v instanceof Button) {
                v.setOnClickListener(this::onButtonClick);
            }
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C": // Clear
                currentInput = "";
                tvResult.setText("0");
                firstNumber = 0;
                operator = "";
                break;

            case "⌫": // Backspace
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    tvResult.setText(currentInput);
                }
                break;

            case "+": case "-": case "×": case "÷": case "%":
                if (!currentInput.isEmpty()) {
                    firstNumber = Double.parseDouble(currentInput);
                    operator = buttonText;
                    currentInput += " " + operator + " ";
                    isNewOp = true;
                }
                break;

            case "=":
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentInput);
                    double result = calculate(firstNumber, secondNumber, operator);
                    tvResult.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                    operator = "";
                }
                break;

            default:
                if (isNewOp) {
                    currentInput = "";
                    isNewOp = false;
                }
                currentInput += buttonText;
                tvResult.setText(currentInput);
                break;
        }
    }

    private double calculate(double num1, double num2, String op) {
        switch (op) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "×": return num1 * num2;
            case "÷": return num2 != 0 ? num1 / num2 : 0;
            case "%": return num1 % num2;
            default: return num2;
        }
    }
}