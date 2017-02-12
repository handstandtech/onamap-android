//package net.onamap.android.older;
//
//import net.onamap.android.model.StateData;
//import net.onamap.android.model.States;
//
//import java.io.File;
//import java.io.PrintWriter;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by handstandtech on 8/11/15.
// */
//public class AndroidSvgCreator {
//
//
//    public static void main(String args[]) throws Exception {
//        new AndroidSvgCreator().one();
//    }
//
//    public void one() throws Exception {
//        Map<String, StateData> map = States.getMap();
//        for (String key : map.keySet()) {
//            String value = map.get(key).fullName;
//            String fillColor = "@color/unselected";
//            String strokeColor = "#646464";
//            generateVectorDrawable(key, value, fillColor, strokeColor, "");
//            generateVectorDrawable(key, value, fillColor, strokeColor, "selected");
//        }
//        generateImageViews(map.keySet());
//    }
//
//    public void generateVectorDrawable(String key, String value, String fillColor, String strokeColor, String suffix) throws Exception {
//        File dir = new File("generated");
//        dir.mkdirs();
//
//        if (!suffix.equals("")) {
//            suffix = "_" + suffix;
//        }
//
//        String filename = "state_" + key.toLowerCase() + suffix + ".xml";
//        File file = new File(dir, filename);
//        System.out.println(file.getCanonicalPath());
//        file.createNewFile();
//        PrintWriter out = new PrintWriter(file);
//        int width = 959;
//        int height = 593;
//
//        out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
//                "    android:width=\"" + width + "dp\"\n" +
//                "    android:height=\"" + height + "dp\"\n" +
//                "    android:viewportWidth=\"" + width + "\"\n" +
//                "    android:viewportHeight=\"" + height + "\">\n" +
//                "\n" +
//                "    <path\n" +
//                "        android:name=\"" + key + "\"\n" +
//                "        android:fillColor=\"" + fillColor + "\"\n" +
//                "        android:strokeColor=\"" + strokeColor + "\"\n" +
//                "        android:pathData=\"" + value + "\" />\n" +
//                "</vector>");
//        out.close();
//    }
//
//    public void generateImageViews(Set<String> keys) throws Exception {
//        File dir = new File("generated");
//        dir.mkdirs();
//        String filename = "_imageviews.xml";
//        File file = new File(dir, filename);
//        System.out.println(file.getCanonicalPath());
//        file.createNewFile();
//        PrintWriter out = new PrintWriter(file);
//        for (String key : keys) {
//            String stateName = key.toLowerCase();
//            out.println("<ImageView android:layout_width=\"fill_parent\" android:layout_height=\"fill_parent\" android:src=\"@drawable/state_" + stateName + "\" android:id=\"@+id/" + stateName + "\" />");
//        }
//        out.close();
//    }
//}
