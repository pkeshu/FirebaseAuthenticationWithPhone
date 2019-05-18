package com.keshar.phonenumberauthenticationfirebase;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String my_package_name = "com.imaginology.texas";
    String url = "";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text_open);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //Check whether Google Play store is installed or not:
//                    getApplicationContext().getPackageManager().getL
//                    url = "market://details?id=" + my_package_name;
                    startActivity(new Intent(getPackageManager().getLaunchIntentForPackage(my_package_name)));
                } catch ( final Exception e ) {
                    url = "https://play.google.com/store/apps/details?id=" + my_package_name;
                    final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    startActivity(intent);
                }

            }
        });
    }
}
