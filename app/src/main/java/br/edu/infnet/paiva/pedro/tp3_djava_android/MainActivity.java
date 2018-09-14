package br.edu.infnet.paiva.pedro.tp3_djava_android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword, edtPhone, edtCellPhone, edtCpf, edtCity;
    private EditText[] editTexts = new EditText[]{edtName, edtEmail, edtPassword, edtPhone, edtCellPhone, edtCpf, edtCity};
    private Button btnSave, btnCleanFields, btnUsersView;
    private int[] editTextsIds = new int[]{R.id.edtName, R.id.edtEmail, R.id.edtPassword, R.id.edtPhone, R.id.edtCellPhone, R.id.edtCpf, R.id.edtCity};
    private User user = new User();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFirebaseDatabase();
        findIds();
        eventClicks();
    }

    private View.OnClickListener saveUser = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(verifyEditTexts(editTexts)){
                String userId = databaseReference.push().getKey();
                databaseReference.child(userId).setValue(user);
                toastMessage(getApplicationContext(), "Usuário"+ user.getName() +" cadastrado com sucesso");
            } else {
                toastMessage(getApplicationContext(), "Ocorreu erro! Revise os campos e tente novamente!");
            }
        }
    };

    private View.OnClickListener usersView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), UsersViewActivity.class));
        }
    };

    private View.OnClickListener cleanFields = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (EditText e : editTexts) {
                if (!e.getText().toString().isEmpty()) {
                    e.setText("");
                }
            }
        }
    };

    private boolean verifyEditTexts(EditText[] editTexts) {
        boolean result = true;

        for (int e = 0; e < editTexts.length; e++) {
            if (editTexts[e].getText().toString().isEmpty() ||
                    editTexts[e].getText().toString() == null ||
                    !validateField(e, editTexts[e].getText().toString())) {
                editTexts[e].setError("Digite um valor válido");
                result = false;
            } else {
                user.setString(e, editTexts[e].getText().toString());
            }
        }

        return result;
    }

    private boolean validateField(int position, String field){
        boolean result = true;

        switch (position){
            case 0:
                if(field.length() < 3 || field.matches(".*\\d+.*")){
                    result = false;
                }
                break;
            case 1:
                if(!field.contains("@") && !field.contains(".")){
                    result = false;
                }
                break;
            case 2:
                if(field.length() < 6){
                    result = false;
                }
                break;
            case 3:
                if(field.length() < 10){
                    result = false;
                }
                break;
            case 4:
                if(field.length() < 11){
                    result = false;
                }
                break;
            case 5:
                if(field.length() < 11){
                    result = false;
                }
                break;
            case 6:
                if(field.length() < 3 || field.matches(".*\\d+.*")){
                    result = false;
                }
                break;
        }

        return result;
    }

    private void findEditTextsIds() {
        for (int e = 0; e < editTexts.length; e++) {
            editTexts[e] = findViewById(editTextsIds[e]);
        }
    }

    public void initFirebaseDatabase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(getString(R.string.Users));
    }

    private void eventClicks() {
        btnCleanFields.setOnClickListener(cleanFields);
        btnUsersView.setOnClickListener(usersView);
        btnSave.setOnClickListener(saveUser);
    }

    private void findIds() {
        btnSave = findViewById(R.id.btnSave);
        btnCleanFields = findViewById(R.id.btnCleanFields);
        btnUsersView = findViewById(R.id.btnUsersView);
        findEditTextsIds();
    }

    private void toastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
