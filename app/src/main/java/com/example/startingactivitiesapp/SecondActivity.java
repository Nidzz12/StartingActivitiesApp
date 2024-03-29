package com.example.startingactivitiesapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityResultLauncher<Intent> cameraResult;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        // Register activity result launcher
        cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Log.w("Nidhi", "Result2....>");
                        }
                    }
                });

        Intent fromPrevious = getIntent();
        String emailAdress= fromPrevious.getStringExtra("EmailAddress");
        TextView txtWelcome=(TextView) findViewById(R.id.txtWelcome);
        txtWelcome.setText("Welcome Back: "+emailAdress );

        Button btnPhone=(Button)findViewById(R.id.btnPhone);
        Button btnPic=(Button)findViewById(R.id.btnPic);
        EditText editText=(EditText) findViewById(R.id.edtPhone);

        btnPhone.setOnClickListener(view -> {
            // Replace phoneNumber with the recipient's phone number
            String phoneNumber = editText.getText().toString();
            // Create the intent
            Intent call = new Intent(Intent.ACTION_DIAL);
            // Set the data URI with the recipient's phone number
            call.setData(Uri.parse("tel:" + phoneNumber));
            // Start the activity
            startActivity( call);

        });



        btnPic.setOnClickListener(view -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);

        });
        /////



    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("Nidhi","Done pic1");
        Bitmap thumbnail=data.getParcelableExtra("data");
        ImageView img=(ImageView) findViewById(R.id.img1);
        img.setImageBitmap(thumbnail);


    }
}