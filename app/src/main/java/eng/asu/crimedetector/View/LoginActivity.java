package eng.asu.crimedetector.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import eng.asu.crimedetector.R;
import eng.asu.crimedetector.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    Intent loginIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /*
        //Toolbar Setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Login");
        */

        binding.signInButton.setOnClickListener(signInListener);

        loginIntent = new Intent(LoginActivity.this, MainActivity.class);
    }

    View.OnClickListener signInListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("FIREBASE LOGIN", "ENTERED ON CLICK");
            String email = binding.emailPrompt.getText().toString().trim().toLowerCase();
            String password = binding.passwordPrompt.getText().toString().trim();
            if(email.isEmpty())
                binding.emailPrompt.setError("Please insert email");
            else if(password.isEmpty())
                binding.passwordPrompt.setError("Please insert password");
            else {
                startActivity(loginIntent);
                finish();
            }
        }
    };

}