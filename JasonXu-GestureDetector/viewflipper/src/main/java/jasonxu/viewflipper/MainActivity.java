package jasonxu.viewflipper;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnFirst,btnSecond;
    RelativeLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = (RelativeLayout)this.findViewById(R.id.main);
        btnFirst = (Button)this.findViewById(R.id.first);
        btnSecond = (Button)this.findViewById(R.id.second);
        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()){
            case R.id.first:
                fragment = new FirstFragment();
                break;
            case R.id.second:
                fragment = new SecondFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main,fragment)
                .addToBackStack(null)
                .commit();
    }
}
