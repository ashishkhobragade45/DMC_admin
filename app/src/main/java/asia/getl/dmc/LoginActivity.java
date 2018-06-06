package asia.getl.dmc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import asia.getl.dmc.model.User;
import info.hoang8f.widget.FButton;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText edt_phone,edt_password;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edt_phone = (MaterialEditText) findViewById(R.id.edt_phone);
        edt_password = (MaterialEditText) findViewById(R.id.edt_password);

        btn_signin = (Button) findViewById(R.id.btn_signin);

        // init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edt_phone.getText().toString()).exists()) {

                            User user = dataSnapshot.child(edt_phone.getText().toString()).getValue(User.class);

                            if (user.getPassword().equals(edt_password.getText().toString())) {
                                Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,Home.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();

                            }


                        }
                        else {
                            Toast.makeText(LoginActivity.this, "User invalid", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
