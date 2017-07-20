package apps.mohanad.com.miwok1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a new object instance of the defined event listener
        NumbersClickListener clickListener = new NumbersClickListener();

        //find the number view
        TextView numbersView = (TextView)findViewById(R.id.numbers);

        final Intent numbersIntent = new Intent(this, NumbersActivity.class);

        //attach the listener to the view
        numbersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(numbersIntent);
            }
        });


    }

    /**
     * open the family activity
     * @param view
     */
    public void openFamilyActivity(View view) {
        Intent intent = new Intent(this, FamilyActivity.class);
        startActivity(intent);
    }

    /**
     * open the colors activity
     * @param view
     */
    public void openColorsActivity(View view) {
        Intent intent = new Intent(this, ColorsActivity.class);
        startActivity(intent);
    }

    /**
     * open the phrases activity
     * @param view
     */
    public void oprnPhrasesActivity(View view) {
        Intent intent = new Intent(this, PhrasesActivity.class);
        startActivity(intent);
    }
}
