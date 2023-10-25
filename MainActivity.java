import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends Activity {

    private TextView resultTextView;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultTextView = findViewById(R.id.resultTextView);
    }

    public void onNumberClick(View view) {
        if (isNewInput) {
            resultTextView.setText("");
            isNewInput = false;
        }

        Button button = (Button) view;
        String currentText = resultTextView.getText().toString();
        currentText += button.getText().toString();
        resultTextView.setText(currentText);
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();
        firstNumber = Double.parseDouble(resultTextView.getText().toString());
        isNewInput = true;
    }

    public void onEqualsClick(View view) {
        double secondNumber = Double.parseDouble(resultTextView.getText().toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    resultTextView.setText("Error");
                    return;
                }
                break;
        }

        resultTextView.setText(String.valueOf(result));
        isNewInput = true;
    }
}
