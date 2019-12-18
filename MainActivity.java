package e.aleks.carach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.B1);
        Button button2 = findViewById(R.id.B2);


        // play button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent play = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(play);
            }
        });

        // How to play button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent htp = new Intent(MainActivity.this, HTP.class);
                startActivity(htp);
            }
        });

    }
}
