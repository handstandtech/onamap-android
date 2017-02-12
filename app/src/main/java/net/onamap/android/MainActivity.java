package net.onamap.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewGroup vg = (ViewGroup) findViewById(android.R.id.content);
//        Map<String, String> map = StatesData.getMap();
//
//
////        int imageResourceId1 = getResources()
////                .getIdentifier("state_va", "drawable", getPackageName());
////
////        //ImageView Setup
//        ImageView imageView1 = (ImageView) findViewById(R.id.image);
//        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
//        Bitmap bmap = Bitmap.createBitmap(959, 593, conf); // this creates a MUTABLE bitmap
//
//
//        int count = 0;
//        for (String key : map.keySet()) {
//            if (count == 6) {
//                break;
//            }
//            count++;
//            int imageResourceId = R.drawable.state_ak;
////            imageResourceId = getResources()
////                    .getIdentifier("state_" + key.toLowerCase(), "drawable", getPackageName());
//
//////        //ImageView Setup
////            ImageView imageView = new ImageView(this);
//////        //setting image resource
////            imageView.setImageResource(imageResourceId);
//////        //setting image position
////            imageView.setLayoutParams(new ViewGroup.LayoutParams(
////                    ViewGroup.LayoutParams.MATCH_PARENT,
////                    ViewGroup.LayoutParams.MATCH_PARENT));
//////
////            imageView.buildDrawingCache();
////            Bitmap drawingCache = imageView.getDrawingCache();
//            Bitmap drawingCache = BitmapFactory.decodeResource(getResources(),
//                    imageResourceId);
//
//            VectorDrawable myIcon = (VectorDrawable) getResources().getDrawable( imageResourceId );
//
//
//            Bitmap bmp = Bitmap.createBitmap(myIcon.getIntrinsicWidth(), myIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bmp);
//            myIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//            myIcon.draw(canvas);
//
//            canvas.getBit
//                    Bitmap.createBitmap(canvas.get)
//
//
//
//            bmap = mergeImage(bmap, drawingCache);
//            drawingCache = null;
//
//        }
//        imageView1.setImageBitmap(bmap);
//        vg.addView(imageView1);
    }

    public static Bitmap mergeImage(Bitmap base, Bitmap overlay) {
        int adWDelta = (int) (base.getWidth() - overlay.getWidth()) / 2;
        int adHDelta = (int) (base.getHeight() - overlay.getHeight()) / 2;

        Bitmap mBitmap = Bitmap.createBitmap(base.getWidth(), base.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        canvas.drawBitmap(base, 0, 0, null);
        canvas.drawBitmap(overlay, adWDelta, adHDelta, null);

        return mBitmap;
    }

    public void onResume() {
        super.onResume();
//        ImageView iv = (ImageView) findViewById(R.id.logo);
//        VectorDrawable drawable = (VectorDrawable) iv.getDrawable();
//        drawable.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
