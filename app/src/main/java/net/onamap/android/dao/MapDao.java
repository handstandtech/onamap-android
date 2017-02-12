package net.onamap.android.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.onamap.android.StateDataListAdapter;
import net.onamap.android.model.StateData;
import net.onamap.android.model.States;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MapDao {

    private static final String TAG = MapDao.class.getSimpleName();

    private static final String MAP_VALUES = "MAP_VALUES";
    private final SharedPreferences settings;
    private StateDataListAdapter adapter;

    private Context context;

    private List<StateData> values;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public MapDao(Context context) {
        this.context = context;
        settings = context.getSharedPreferences(MAP_VALUES, Context.MODE_PRIVATE);
        loadValues();
    }

    public void setAdapter(StateDataListAdapter adapter) {
        this.adapter = adapter;
    }

    public List<StateData> getValues() {
        return values;
    }

    private void loadValues() {
        if (values == null) {
            //get the sharepref
            String valuesJson = settings.getString(MAP_VALUES, null);
            if (valuesJson != null) {
                values = gson.fromJson(valuesJson, new TypeToken<List<StateData>>() {
                }.getType());
            } else {
                values = defaultValues();
            }
        }
    }

    private void saveValues() {
        //set the sharedpref
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(MAP_VALUES, gson.toJson(values));
        editor.commit();
    }

    private List<StateData> defaultValues() {
        final List<StateData> list = new ArrayList<>();
        for (Map.Entry<String, StateData> entry : States.getMap().entrySet()) {
            list.add(entry.getValue());
        }

        Collections.sort(list, new Comparator<StateData>() {
            @Override
            public int compare(StateData lhs, StateData rhs) {
                return lhs.fullName.compareTo(rhs.fullName);
            }
        });
        return list;
    }

    public StateData getState(String stateAbbv) {
        for (StateData stateData : getValues()) {
            if (stateData.abbv.equalsIgnoreCase(stateAbbv)) {
                return stateData;
            }
        }
        return null;
    }

    public int getIdxOfState(String stateAbbv) {
        for (int i = 0; i < values.size(); i++) {
            StateData stateData = values.get(i);
            if (stateData.abbv.equalsIgnoreCase(stateAbbv)) {
                return i;
            }
        }
        return -1;
    }

    public StateData updateState(StateData pStateData) {
        int index = getIdxOfState(pStateData.abbv);
        values.set(index, pStateData);
        saveValues();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        return pStateData;
    }
}
