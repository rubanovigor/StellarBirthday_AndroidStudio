package com.aiworker.stellarbirthday;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;
import com.aiworker.stellarbirthday.R;

public class AboutStars extends Activity{
    private TextView tvAboutStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about_stars);

        View backgroundImage = findViewById(R.id.background);
        Drawable background = backgroundImage.getBackground();

        tvAboutStar = (TextView) findViewById(R.id.tvAboutStar);
        Stellar.iniStarsArray();
        tvAboutStar.setText(Stellar.getStellarBirthdayStarFunnyInfo(MainActivity.days));

        // -- get BirthdayStar name based on Birthday Date from Facebook
        // add logic here
//			BirthdayStar = "sirius";
        // -- end


    }

}
