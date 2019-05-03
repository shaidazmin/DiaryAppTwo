package com.example.nz.diaryapptwo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  EditText inputText;
    Button saveButton, clearButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) findViewById(R.id.diaryEditText);
        saveButton = (Button) findViewById(R.id.saveButton);
        clearButton = (Button) findViewById(R.id.deleteButton);
        saveButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        showDiry();

    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.deleteButton){
            inputText.setText("");
        }
        else if(v.getId()== R.id.saveButton){
            String notes = inputText.getText().toString();
            if(notes != null){
                writeNote(notes) ;
            }else {
            Toast.makeText(MainActivity.this,"Please! Write your wish first.",Toast.LENGTH_SHORT).show();
            }
        }
    }


    // write text here ....

    public void writeNote(String notes){
        try {
            FileOutputStream fileOutputStream = openFileOutput("note.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(notes.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void showDiry(){
        try {
            FileInputStream fileInputStream = openFileInput("note.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String value;
            StringBuffer stringBuffer = new StringBuffer();
            while ((value = bufferedReader.readLine()) != null ){
                stringBuffer.append(value+"\n");
            }
            inputText.setText(stringBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
