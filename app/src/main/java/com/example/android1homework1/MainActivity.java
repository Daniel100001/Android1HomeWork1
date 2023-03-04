package com.example.android1homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 1;


    ImageButton whatsappButton;
    ImageButton googleButton;
    ImageButton galleryButton;
    ImageView image;

    EditText editTextWhatsapp;
    EditText editTextGoogle;
    EditText editTextGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        whatsappButton = findViewById(R.id.button);
        googleButton = findViewById(R.id.button1);
        galleryButton = findViewById(R.id.button2);
        editTextWhatsapp = findViewById(R.id.editText);
        editTextGoogle = findViewById(R.id.editText1);
        image = findViewById(R.id.ic);

        setUpListener();
        googleListener();
        galleryListener();

    }

    private void galleryListener() {
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            image.setImageURI(imageUri);
        }
    }

    private void googleListener() {
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchUrl = editTextGoogle.getText().toString();
                if(searchUrl.isEmpty()){
                    Toast.makeText(MainActivity.this, "Строка пуста" , Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com/search?q=" + searchUrl));
                startActivity(intent);
            }
        });
    }


    private void setUpListener() {
        whatsappButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = editTextWhatsapp.getText().toString();
                    if (phoneNumber.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://wa.me/" + phoneNumber));
                    startActivity(intent);
                }
        });
    }

}