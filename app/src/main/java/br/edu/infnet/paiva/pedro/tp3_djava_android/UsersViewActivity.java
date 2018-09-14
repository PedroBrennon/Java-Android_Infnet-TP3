package br.edu.infnet.paiva.pedro.tp3_djava_android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersViewActivity extends AppCompatActivity {

    private Button btnBack;
    private ListView listView;
    private ArrayList<User> usersArrayList;
    private ArrayList<String> nameUsersListView;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_view);

        initFirebaseDatabase();
        findIds();
        eventClicks();
        getUsers();
    }

    private void getUsers() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersArrayList = new ArrayList<>();
                nameUsersListView = new ArrayList<>();

                for(DataSnapshot d: dataSnapshot.getChildren()){
                    User u = d.getValue(User.class);
                    usersArrayList.add(u);
                    nameUsersListView.add(u.getName());
                }

                listView.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.list_view_item,
                        R.id.name_user_list_view, nameUsersListView));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initFirebaseDatabase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(getString(R.string.Users));
    }

    private void findIds() {
        listView = findViewById(R.id.listView);
        btnBack = findViewById(R.id.btnBack);
    }

    private void eventClicks(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                User user = usersArrayList.get(position);

                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                i.putExtra(getString(R.string.user), (Serializable) user);
                startActivity(i);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
