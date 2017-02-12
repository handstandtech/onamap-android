package net.onamap.android.model;

import net.onamap.android.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class States {

    private static Map<String, StateData> s = new HashMap<>();

    private static void put(String abbv, String name, int drawableResThumb, int drawableRes) {
        StateData stateData = new StateData(abbv, name, drawableResThumb, drawableRes);
        s.put(abbv, stateData);
    }

    static {
        put("AK", "Alaska", R.drawable.state_thumb_ak, R.drawable.ak);
        put("AL", "Alabama", R.drawable.state_thumb_al, R.drawable.al);
        put("AZ", "Arizona", R.drawable.state_thumb_az, R.drawable.az);
        put("AR", "Arkansas", R.drawable.state_thumb_ar, R.drawable.ar);
        put("CA", "California", R.drawable.state_thumb_ca, R.drawable.ca);
        put("CO", "Colorado", R.drawable.state_thumb_co, R.drawable.co);
        put("CT", "Connecticut", R.drawable.state_thumb_ct, R.drawable.ct);
        put("DC", "District of Columbia", R.drawable.state_thumb_dc, R.drawable.dc);
        put("DE", "Delaware", R.drawable.state_thumb_de, R.drawable.de);
        put("FL", "Florida", R.drawable.state_thumb_fl, R.drawable.fl);
        put("GA", "Georgia", R.drawable.state_thumb_ga, R.drawable.ga);
        put("HI", "Hawaii", R.drawable.state_thumb_hi, R.drawable.hi);
        put("ID", "Idaho", R.drawable.state_thumb_id, R.drawable.id);
        put("IL", "Illinois", R.drawable.state_thumb_il, R.drawable.il);
        put("IN", "Indiana", R.drawable.state_thumb_in, R.drawable.in);
        put("IA", "Iowa", R.drawable.state_thumb_ia, R.drawable.ia);
        put("KS", "Kansas", R.drawable.state_thumb_ks, R.drawable.ks);
        put("KY", "Kentucky", R.drawable.state_thumb_ky, R.drawable.ky);
        put("LA", "Louisiana", R.drawable.state_thumb_la, R.drawable.la);
        put("ME", "Maine", R.drawable.state_thumb_me, R.drawable.me);
        put("MD", "Maryland", R.drawable.state_thumb_md, R.drawable.md);
        put("MA", "Massachusetts", R.drawable.state_thumb_ma, R.drawable.ma);
        put("MI", "Michigan", R.drawable.state_thumb_mi, R.drawable.mi);
        put("MN", "Minnesota", R.drawable.state_thumb_mn, R.drawable.mn);
        put("MS", "Mississippi", R.drawable.state_thumb_ms, R.drawable.ms);
        put("MO", "Missouri", R.drawable.state_thumb_mo, R.drawable.mo);
        put("MT", "Montana", R.drawable.state_thumb_mt, R.drawable.mt);
        put("NE", "Nebraska", R.drawable.state_thumb_ne, R.drawable.ne);
        put("NV", "Nevada", R.drawable.state_thumb_nv, R.drawable.nv);
        put("NH", "New Hampshire", R.drawable.state_thumb_nh, R.drawable.nh);
        put("NJ", "New Jersey", R.drawable.state_thumb_nj, R.drawable.nj);
        put("NM", "New Mexico", R.drawable.state_thumb_nm, R.drawable.nm);
        put("NY", "New York", R.drawable.state_thumb_ny, R.drawable.ny);
        put("NC", "North Carolina", R.drawable.state_thumb_nc, R.drawable.nc);
        put("ND", "North Dakota", R.drawable.state_thumb_nd, R.drawable.nd);
        put("OH", "Ohio", R.drawable.state_thumb_oh, R.drawable.oh);
        put("OK", "Oklahoma", R.drawable.state_thumb_ok, R.drawable.ok);
        put("OR", "Oregon", R.drawable.state_thumb_or, R.drawable.or);
        put("PA", "Pennsylvania", R.drawable.state_thumb_pa, R.drawable.pa);
        put("RI", "Rhode Island", R.drawable.state_thumb_ri, R.drawable.ri);
        put("SC", "South Carolina", R.drawable.state_thumb_sc, R.drawable.sc);
        put("SD", "South Dakota", R.drawable.state_thumb_sd, R.drawable.sd);
        put("TN", "Tennessee", R.drawable.state_thumb_tn, R.drawable.tn);
        put("TX", "Texas", R.drawable.state_thumb_tx, R.drawable.tx);
        put("UT", "Utah", R.drawable.state_thumb_ut, R.drawable.ut);
        put("VT", "Vermont", R.drawable.state_thumb_vt, R.drawable.vt);
        put("VA", "Virginia", R.drawable.state_thumb_va, R.drawable.va);
        put("WA", "Washington", R.drawable.state_thumb_wa, R.drawable.wa);
        put("WV", "West Virginia", R.drawable.state_thumb_wv, R.drawable.wv);
        put("WI", "Wisconsin", R.drawable.state_thumb_wi, R.drawable.wi);
        put("WY", "Wyoming", R.drawable.state_thumb_wy, R.drawable.wy);
    }

    public static Map<String, StateData> getMap() {
        return s;
    }


    public static List<StateData> getStates() {
        List<StateData> list = new ArrayList<>(s.values());
        Collections.sort(list, new Comparator<StateData>() {
            @Override
            public int compare(StateData lhs, StateData rhs) {
                return lhs.fullName.compareTo(rhs.fullName);
            }
        });
        return list;
    }


}
