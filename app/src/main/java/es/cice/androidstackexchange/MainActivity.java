package es.cice.androidstackexchange;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String MODEL_FRAGMENT_TAG="ModelFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        QuestionListFragment qlf=new QuestionListFragment();
        ft.add(R.id.fragmentContainer,qlf,null);
        ModelFragment mf=new ModelFragment();
        ft.add(mf,MODEL_FRAGMENT_TAG);
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();
    }
}
