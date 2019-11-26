package tugas.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    private EditText txtactivity;
    private Button btnactivity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtactivity = (EditText) findViewById(R.id.editTextActivity);
        btnactivity = (Button) findViewById(R.id.btnActivity);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }
    public void btnActivityClick(View v)
    {
        Fragment1 newfragment = new Fragment1();
        fragmentTransaction.add(R.id.ui_container, newfragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        btnactivity.setEnabled(false);
    }
}