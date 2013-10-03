package com.example.Translate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class Show extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        Button button = (Button) findViewById(R.id.back);
        TextView textView = (TextView) findViewById(R.id.output);
        textView.setTextSize(50);
        textView.setTextColor(Color.BLACK);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");

        Translater translater = new Translater(word);
        String answer = null;
        try {
            answer = translater.execute().get().toString();
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
        textView.setTextSize(Color.BLACK);



        LinearLayout layout = (LinearLayout) findViewById(R.id.imagesLayout);
        try {
            ArrayList<Drawable> images = PictureFinder.getImages(answer);
            for (int i = 0; i < 10; i++) {
                ImageView imageView = new ImageView(Show.this);
                imageView.setImageDrawable(images.get(i));
                layout.addView(imageView);
                layout.invalidate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            TextView errorText = (TextView) findViewById(R.id.errorWithPics);
            errorText.setTextColor(Color.RED);
            errorText.setTextSize(50);
            errorText.setText("error");
        }

        textView.setText(answer);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}