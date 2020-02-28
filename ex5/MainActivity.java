package com.example.ex5;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.ex5.database;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    database db;
    public static String EXTRA_MESSAGE = "",message="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_insert=findViewById(R.id.bt_insert);
        Button bt_update=findViewById(R.id.bt_update);
        Button bt_delete=findViewById(R.id.bt_delete);
        Button bt_display=findViewById(R.id.bt_display);
        
        final EditText et_code=findViewById(R.id.et_code);
        final EditText et_name=findViewById(R.id.et_name);
        final EditText et_department=findViewById(R.id.et_department);
        final EditText et_gender=findViewById(R.id.et_gender);
        final EditText et_salary=findViewById(R.id.et_salary);

        db=new database(getApplicationContext());

        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insert(Integer.parseInt(et_code.getEditableText().toString()),
                        et_name.getEditableText().toString(),
                        et_department.getEditableText().toString(),
                        Integer.parseInt(et_salary.getEditableText().toString()),
                        et_gender.getEditableText().toString()
                        );
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.update(Integer.parseInt(et_code.getEditableText().toString()),
                        et_name.getEditableText().toString(),
                        et_department.getEditableText().toString(),
                        Integer.parseInt(et_salary.getEditableText().toString()),
                        et_gender.getEditableText().toString()
                );
            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(Integer.parseInt(et_code.getEditableText().toString()));
            }
        });
        bt_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.display(Integer.parseInt(et_code.getEditableText().toString()));
                message = "";
                while(res.moveToNext()){
                    message += (res.getString(0));
                    message += " ";
                    message += (res.getString(1));
                    message += " ";
                    message += (res.getString(2));
                    message += " ";
                    message += (res.getString(3));
                    message += " ";
                    message += ((res.getString(4)));
                    message += " ";
                    message += "\n";
                }
                sendMessage();
            }
        });
    }
    public void sendMessage() {
        Intent intent = new Intent(this, Result.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}