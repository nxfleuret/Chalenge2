package tugas.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment1 fragment = new Fragment1();
        fragmentTransaction.add(R.id.ui_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        final Fragment2 fragment2 = new Fragment2();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Chalenge 2");
        toolbar.inflateMenu(R.menu.sidemenu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.sidemenu:
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.addToBackStack(null);
                        ft.replace(R.id.ui_container, fragment2);
                        ft.commit();
                        return true;
                    default:
                        return this.onMenuItemClick(item);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
