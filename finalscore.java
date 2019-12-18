package e.aleks.carach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finalscore extends AppCompatActivity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalscore);


        Button Back = findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(finalscore.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final TextView PlayerScroe = findViewById(R.id.USC1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String score = extras.getString("Score");

            PlayerScroe.setText(score);
        }

    }
}
