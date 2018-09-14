package br.edu.infnet.paiva.pedro.tp3_djava_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;

import java.io.Serializable;

public class UserActivity extends AppCompatActivity {

    private Button btnBack;
    private TextView txtName, txtEmail, txtPassword, txtPhone, txtCellPhone, txtCpf, txtCity;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Serializable getUser = getIntent().getSerializableExtra(getString(R.string.user));

        if(getUser != null){
            user = new User();
            user = (User) getUser;
        }

        findIds();
        eventClicks();
        passTexts();
    }

    private void passTexts() {
        int i = 0;
        StringBuilder hidePassword = new StringBuilder();

        while(i<user.getPassword().length()){
            hidePassword.append("*");
            i++;
        }

        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPassword.setText(hidePassword);
        txtPhone.setText(user.getPhone());
        txtCellPhone.setText(user.getCellphone());
        txtCpf.setText(user.getCpf());
        txtCity.setText(user.getCity());
    }

    private void findIds(){
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtPhone = findViewById(R.id.txtPhone);
        txtCellPhone = findViewById(R.id.txtCellPhone);
        txtCpf = findViewById(R.id.txtCpf);
        txtCity = findViewById(R.id.txtCity);
        btnBack = findViewById(R.id.btnBack);
    }

    private void eventClicks(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
