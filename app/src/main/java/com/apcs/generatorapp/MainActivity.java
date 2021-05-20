package com.apcs.generatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);

        String name = prefs.getString("MY_NAME", "your name");
        Long tel = prefs.getLong("MY_TEL",0);
        String email = prefs.getString("MY_EMAIL", "your email");

        ((TextView)findViewById(R.id.nameLabel)).setText(name);
        ((TextView)findViewById(R.id.telLabel)).setText(tel+"");
        ((TextView)findViewById(R.id.emailLabel)).setText(email);

        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                if (name.equals("your name")||tel == 0||email.equals("your email")||name.equals("")||email.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill out all required information", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    while (true) {
                        if (Long.toString(tel).length() != 10) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Please provide a valid 10-digit number", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        if (name.equals("your name")||name.contains(",")||name.equals("")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Please fill in your first and last name, with no special characters", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        if (email.equals("your email")||!email.contains("@")||!email.contains(".")||email.equals("")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Please provide a valid email", Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        else {
                            try {
                                BitMatrix bitMatrix = multiFormatWriter.encode((name + "," + tel + "," + email), BarcodeFormat.QR_CODE, 750, 750);
                                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                imageView.setImageBitmap(bitmap);
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    public void showEdit(View view) {
        startActivity(new Intent(getApplicationContext(), edit.class));
    }

}