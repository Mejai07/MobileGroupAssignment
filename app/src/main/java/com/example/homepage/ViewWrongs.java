package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class ViewWrongs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wrongs);

        TextView tv=findViewById(R.id.textView3);
        String strFileName = "Review.txt";
        FileInputStream fis;
        StringBuffer buffer = new StringBuffer();
        DataInputStream dataIO;
        String strLine = null;
        try {
            fis = openFileInput(strFileName);
            dataIO = new DataInputStream(fis);
            while ((strLine = dataIO.readLine()) != null){
                buffer.append(strLine + "\n");
            }
            dataIO.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv.setText(buffer);
    }
}