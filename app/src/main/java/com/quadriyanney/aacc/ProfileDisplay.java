package com.quadriyanney.aacc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileDisplay extends AppCompatActivity {

    String username, photo, url;

    TextView name;
    TextView github_url;
    CollapsingToolbarLayout collapse;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_display);

        collapse = (CollapsingToolbarLayout) findViewById(R.id.collapse);

        Bundle data = getIntent().getExtras();

        username = data.getString("username");
        photo = data.getString("photo");
        url = data.getString("url");

        name = (TextView) findViewById(R.id.profile_name);
        github_url = (TextView) findViewById(R.id.html_url);
        imageView = (ImageView) findViewById(R.id.profile_photo);

        name.setText(username);
        github_url.setText(url);
        Glide.with(getBaseContext()).load(photo).placeholder(R.drawable.ic_person_black_24dp).into(imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.profile_photo);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);

    }

    public void OpenBrowser(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void ShareProfile(View view) {

        String message = "Check this awesome developer @" + username + "," + url;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(intent, "Share"));
        }
    }
}
