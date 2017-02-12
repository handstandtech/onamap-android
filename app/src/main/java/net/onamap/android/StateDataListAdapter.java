package net.onamap.android;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.onamap.android.dao.MapDao;
import net.onamap.android.model.StateData;

public class StateDataListAdapter extends ArrayAdapter<StateData> {

    private static final String TAG = StateDataListAdapter.class.getSimpleName();

    private final MapDao mapDao;

    Context context;

    int layoutResourceId;

    public StateDataListAdapter(Context context, int layoutResourceId, MapDao mapDao) {
        super(context, layoutResourceId, mapDao.getValues().toArray(new StateData[mapDao.getValues().size()]));
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.mapDao = mapDao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StateData stateData = mapDao.getValues().get(position);

        Log.v(TAG, "State: " + stateData.fullName);
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            Log.v(TAG, "Inflating.");
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            row.setTag(holder);
        } else {
            Log.v(TAG, "Previously Inflated");
            holder = (ViewHolder) row.getTag();
        }

        holder.txtTitle.setText(stateData.fullName + " - " + stateData.selected);
        holder.imgIcon.setImageResource(stateData.icon);

        return row;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}