package tugas.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {

    private EditText interval, number;

    private SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "com.example.android.AndroidFragment";

    private final String INTERVAL = "INTERVAL";
    private final String NUMBER = "NUMBER";
    private final long DEFAULT_INTERVAL = 500;
    private final int DEFAULT_NUMBER = 7;

    public Fragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        interval = getActivity().findViewById(R.id.interval);
        number = getActivity().findViewById(R.id.number);

        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        long intervalVal = mPreferences.getLong(INTERVAL, DEFAULT_INTERVAL);
        int numberVal = mPreferences.getInt(NUMBER, DEFAULT_NUMBER);
        interval.setText(String.valueOf(intervalVal));
        number.setText(String.valueOf(numberVal));

        interval.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                long millis = Long.parseLong(String.valueOf(interval.getText().toString().isEmpty() ? DEFAULT_INTERVAL : interval.getText().toString()));
                preferencesEditor.putLong(INTERVAL, millis);
                preferencesEditor.apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putInt(NUMBER, Integer.parseInt(String.valueOf(number.getText().toString().isEmpty() ? 7 : number.getText().toString())));
                preferencesEditor.apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}