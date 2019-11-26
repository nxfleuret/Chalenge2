package tugas.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class Fragment1 extends Fragment implements View.OnClickListener {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private Button buttonStart;
    private Button buttonStop;
    private Thread bgthread1;
    private Thread bgthread2;
    private Thread bgthread3;
    private long speed;
    private int jackpot;
    private TextView num;
    private int num1;
    private int num2;
    private int num3;
    private int count;
    private View.OnClickListener click;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "com.example.android.AndroidFragment";
    private final String INTERVAL = "INTERVAL";
    private final String NUMBER = "NUMBER";

    public Fragment1() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment1_layout, container, false);

        text1 = view.findViewById(R.id.textView1);
        text2 = view.findViewById(R.id.textView2);
        text3 = view.findViewById(R.id.textView3);
        buttonStart = view.findViewById(R.id.start);
        buttonStop = view.findViewById(R.id.stop);

        count = 0;

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
        speed = mPreferences.getLong(INTERVAL, 500L);
        jackpot = mPreferences.getInt(NUMBER, 7);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.stop:
                if(bgthread1 != null && count == 0){
                    bgthread1.interrupt();
                    count++;
                }

                else if(bgthread2 != null && count == 1){
                    bgthread2.interrupt();
                    count++;
                }

                else if(bgthread3 != null && count == 2){
                    bgthread3.interrupt();
                    count=0;
                }

                if(num1 == jackpot && num2 == jackpot && num3 == jackpot) {
                    MyNotification.showNotification(getActivity(),"Jackpot", "Jackpot", new Intent());
                }
                System.out.println(count);
                break;
            case R.id.start:
                if(bgthread1 == null || bgthread1.getState() == Thread.State.TERMINATED &&
                        bgthread2 == null || bgthread2.getState() == Thread.State.TERMINATED &&
                        bgthread3 == null || bgthread3.getState() == Thread.State.TERMINATED){
                    Runnable runnable1 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(!bgthread1.isInterrupted()){
                                    Thread.sleep(speed);
                                    text1.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            num1 = new Random().nextInt(9);
                                            text1.setText(""+num1);
                                        }
                                    });
                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    Runnable runnable2 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(!bgthread2.isInterrupted()){
                                    Thread.sleep(speed);
                                    text2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            num2 = new Random().nextInt(9);
                                            text2.setText(""+num2);
                                        }
                                    });
                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    Runnable runnable3 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(!bgthread3.isInterrupted()){
                                    Thread.sleep(speed);
                                    text3.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            num3 = new Random().nextInt(9);
                                            text3.setText(""+num3);
                                        }
                                    });
                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    bgthread1 = new Thread(runnable1);
                    bgthread2 = new Thread(runnable2);
                    bgthread3 = new Thread(runnable3);
                    bgthread1.start();
                    bgthread2.start();
                    bgthread3.start();
                }
                break;
        }
    }
}