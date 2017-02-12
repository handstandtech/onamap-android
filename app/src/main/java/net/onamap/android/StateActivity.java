package net.onamap.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.onamap.android.dao.Photo;
import net.onamap.android.dao.PhotoDao;
import net.onamap.android.model.StateData;
import net.onamap.android.util.GsonUtil;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StateActivity extends BaseActivity {

    private List<Photo> statePhotos;

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
        statePhotos = new PhotoDao(getApplicationContext()).getPhotosForState(stateData.fullName);

        AppCompatImageView photoView = (AppCompatImageView) findViewById(R.id.photoView);
        Picasso.with(getApplicationContext()).load(statePhotos.get(0).url_m).into(photoView);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
