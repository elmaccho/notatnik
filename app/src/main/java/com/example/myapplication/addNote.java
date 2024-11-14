package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class addNote extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView noteImage;


    Button backBtn, saveBtn;
    ImageButton addImageBtn;
    EditText noteTitleET, noteET;
    private NotesDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Buttons
        backBtn = findViewById(R.id.backBtn);
        saveBtn = findViewById(R.id.saveBtn);
        addImageBtn = findViewById(R.id.addImage_Btn);
        noteImage = findViewById(R.id.noteImage);

        // EditTexts
        noteTitleET = findViewById(R.id.noteTitleET);
        noteET = findViewById(R.id.noteET);

        dbHelper = new NotesDatabaseHelper(this);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }

            private void openGallery() {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noteTitleET.getText().toString().trim();
                String content = noteET.getText().toString().trim();

                if(!title.isEmpty()){
                    boolean success = dbHelper.addNote(title,content);

                    if(success){
                        Toast.makeText(addNote.this, "Notatka zapisana!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(addNote.this, "", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addNote.this, "Wypełnij tytuł!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            noteImage.setImageURI(selectedImageUri);
        }
    }
}
