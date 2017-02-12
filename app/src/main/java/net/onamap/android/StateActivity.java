package net.onamap.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import net.onamap.android.model.StateData;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        StateData stateData = (StateData) getIntent().getSerializableExtra("state");
        getSupportActionBar().setTitle(stateData.abbv);
        AppCompatImageView imageView = (AppCompatImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.text);

        imageView.setImageResource(stateData.icon);
        textView.setText(stateData.fullName);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
