package com.example.nathapong.app64_recievefirebasechildrendata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edtBoxerName, edtBoxerPunchPower, edtBoxerPunchSpeed;
    Button btnSendData;
    TextView txtReceiveData;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private String oldTxtReceiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtBoxerName = (EditText)findViewById(R.id.edtBoxerName);
        edtBoxerPunchPower = (EditText)findViewById(R.id.edtBoxerPunchPower);
        edtBoxerPunchSpeed = (EditText)findViewById(R.id.edtBoxerPunchSpeed);
        btnSendData = (Button)findViewById(R.id.btnSendData);
        txtReceiveData = (TextView)findViewById(R.id.txtReceiveData);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boxer boxer = new Boxer(edtBoxerName.getText().toString(),
                                        Integer.parseInt(edtBoxerPunchPower.getText().toString()),
                                        Integer.parseInt(edtBoxerPunchSpeed.getText().toString()));

                databaseReference.child(databaseReference.push().getKey()).setValue(boxer);

                edtBoxerName.setText("");
                edtBoxerPunchPower.setText("");
                edtBoxerPunchSpeed.setText("");
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                oldTxtReceiveData = "";

                for (DataSnapshot child : dataSnapshot.getChildren()){

                    Boxer boxer = child.getValue(Boxer.class);

                    if (oldTxtReceiveData == null){
                        oldTxtReceiveData = "";
                    }

                    txtReceiveData.setText(oldTxtReceiveData +
                                            "Name : " + boxer.getBoxerName() + "\n" +
                                            "Punch Power : " + boxer.getBoxerPunchPower() + "\n" +
                                            "Punch Speed : " + boxer.getBoxerPunchSpeed() + "\n\n\n");

                    oldTxtReceiveData = txtReceiveData.getText().toString();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
