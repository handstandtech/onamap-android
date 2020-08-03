package net.onamap.android

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    public override fun onResume() {
        super.onResume()
        //        ImageView iv = (ImageView) findViewById(R.id.logo);
//        VectorDrawable drawable = (VectorDrawable) iv.getDrawable();
//        drawable.
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    companion object {
        fun mergeImage(base: Bitmap, overlay: Bitmap): Bitmap {
            val adWDelta = (base.width - overlay.width) / 2
            val adHDelta = (base.height - overlay.height) / 2
            val mBitmap = Bitmap.createBitmap(base.width, base.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(mBitmap)
            canvas.drawBitmap(base, 0f, 0f, null)
            canvas.drawBitmap(overlay, adWDelta.toFloat(), adHDelta.toFloat(), null)
            return mBitmap
        }
    }
}