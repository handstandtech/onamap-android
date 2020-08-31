package net.onamap.android.util

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object AssetUtils {
    fun doit(context: Context, filename: String?): String? {
        var str: String? = null
        try {
            val buf = StringBuilder()
            var json: InputStream? = null
            json = context.assets.open(filename!!)
            val inputStream = BufferedReader(InputStreamReader(json, "UTF-8"))
            while (inputStream.readLine().also { str = it } != null) {
                buf.append(str)
            }
            inputStream.close()
            str = buf.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return str
    }
}