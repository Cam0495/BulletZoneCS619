package edu.unh.cs.cs619_2015_project2.g10;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.unh.cs.cs619_2015_project2.g10.R.layout.activity_start_up);

        ImageView image = (ImageView)findViewById(R.id.startImage);
        Picasso.with(this)
                .load("https://wiki.bzflag.org/images/f/f3/Pstanks.png")
                .resize( 50, 50 )
                .into(image);

        Button button = (Button)findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain( );
            }
        });
    }

    private void startMain( ){
        Intent intent = new Intent( StartUp.this, TankClientActivity_.class );
        StartUp.this.startActivity( intent );
    }

}
