package app.tralkan.colorpickerdialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements ColorChooserFragment.DialogListener{

    private ColorChooserFragment fragment;
    private FragmentManager fm = getSupportFragmentManager();
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.main_layout);
    }

    public void loadDialog(View v){
        fragment = new ColorChooserFragment();
        fragment.show(fm, ColorChooserFragment.TAG);
    }

    @Override
    public void onSelected(ColorChooserFragment fragment) {
        int color = fragment.getColor();
        Toast.makeText(getApplicationContext(),  "Color selected: #"+Integer.toHexString(color), Toast.LENGTH_SHORT).show();

        layout.setBackgroundColor(color);
    }
}