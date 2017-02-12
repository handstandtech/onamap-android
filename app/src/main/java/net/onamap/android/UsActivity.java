package net.onamap.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.onamap.android.dao.MapDao;
import net.onamap.android.model.StateData;
import net.onamap.android.model.States;

public class UsActivity extends BaseActivity {

    private MapDao mapDao;
    private ArrayAdapter<StateData> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usmap);

        adapter = new ArrayAdapter<StateData>(getApplicationContext(), R.layout.list_view_item_row, States.getStates()) {
            public View getView(int position, View row, ViewGroup parent) {
                ViewHolder holder = null;
                if (row == null) {
                    row = LayoutInflater.from(this.getContext())
                            .inflate(R.layout.list_view_item_row, parent, false);

                    holder = new ViewHolder();
                    holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
                    holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);

                    row.setTag(holder);
                } else {
                    holder = (ViewHolder) row.getTag();
                }

                StateData stateData = getItem(position);
                if (stateData != null) {
                    holder.txtTitle.setText(stateData.fullName + " - " + stateData.selected);
                    holder.imgIcon.setImageResource(stateData.drawableResThumb);
                }

                return row;
            }
        };

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                StateData curr = adapter.getItem(position);
                Intent intent = new Intent(UsActivity.this, StateActivity.class);
                intent.putExtra("state", curr);
                startActivity(intent);
            }

        });

        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }


    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }

}
