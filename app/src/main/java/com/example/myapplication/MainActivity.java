package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageButton addBtn;
    private NotesDatabaseHelper dbHelper;
    private ListView notesListView;  // Dodane: Przechowywanie referencji do ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja dbHelper i notesListView
        dbHelper = new NotesDatabaseHelper(this);
        notesListView = findViewById(R.id.notesListView); // Zakładając, że masz ListView w XML z id 'notesListView'

        // Wyświetlanie notatek
        displayNotes();

        // Inicjalizacja przycisku do dodawania notatek
        addBtn = findViewById(R.id.addBtn); // Upewnij się, że masz ImageButton w XML z id 'addBtn'

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przejście do ekranu dodawania notatki
                Intent intent = new Intent(MainActivity.this, addNote.class);
                startActivity(intent);
            }
        });
    }

    private void displayNotes() {
        // Pobierz wszystkie notatki z bazy danych
        Cursor cursor = dbHelper.getAllNotes();

        // Mapowanie danych z bazy danych na widoki
        String[] from = new String[] { "title", "content" };
        int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

        // Adapter do wyświetlania notatek w ListView
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor, from, to, 0);

        // Ustawienie adaptera dla ListView
        notesListView.setAdapter(adapter);
    }
}
