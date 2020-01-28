package com.inducesmile.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    ImageView cameraImage;
    ArrayList<ImageObjects> addDatabase = MainActivity.quizData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button cam = (Button) findViewById(R.id.button_camera);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
                //galleryAddPic();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quiz:
                Intent intentQuiz = new Intent(AddActivity.this, QuizActivity.class);
                startActivity(intentQuiz);
                return true;

            case R.id.add:
                Intent intentAdd = new Intent(AddActivity.this, AddActivity.class);
                startActivity(intentAdd);
                return true;

            case R.id.database:
                Intent intentDatabase = new Intent(AddActivity.this, DatabaseActivity.class);
                startActivity(intentDatabase);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
static final int RESULT_LOAD_RESULT = 2;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cameraImage = (ImageView) findViewById(R.id.camera_imageView);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            cameraImage.setImageBitmap(imageBitmap);

        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void savePic (View view){
        BitmapDrawable drawable = (BitmapDrawable) cameraImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        addDatabase.add(new ImageObjects(bitmap, "Hello"));
    }

    public void loadExistingPhoto (View view){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_RESULT);
        }

        /*@Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == RESULT_LOAD_RESULT && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();
                bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView imageToUpload = findViewById(R.id.imageView_add);
                EditText editText = findViewById(R.id.name_edit);
                name = editText.getText().toString();
                Log.i("Name: ", ""+ name);
                imageToUpload.setImageBitmap(bitmap);
            }
        }


    }
 */





}
