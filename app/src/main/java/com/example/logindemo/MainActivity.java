package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declaration reference variable
    EditText user, password;
    Switch remember;
    Button login, view;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping
        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        remember=findViewById(R.id.remember);
        login=findViewById(R.id.login);
        view=findViewById(R.id.view);
        listView=findViewById(R.id.listview);


        //onclick listener

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginModel loginModel;

                try{
                    loginModel=new LoginModel(-1, user.getText().toString(), Integer.parseInt(password.getText().toString()), remember.isChecked());
                    Toast.makeText(MainActivity.this, loginModel.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error in login", Toast.LENGTH_SHORT).show();
                    loginModel=new LoginModel(-1, "error", 0, false);
                }
                DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                boolean success=dataBaseHelper.addOne(loginModel);
                Toast.makeText(MainActivity.this, "Success"+success, Toast.LENGTH_SHORT).show();


            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Veiw Button Clicked", Toast.LENGTH_SHORT).show();
                DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                ArrayAdapter<LoginModel> arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryOne());
                listView.setAdapter(arrayAdapter);
            }
        });
    }
}