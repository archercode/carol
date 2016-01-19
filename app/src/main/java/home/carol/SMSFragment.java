package home.carol;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import home.carol.model.Item;
public class SMSFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String restoredPhone;

    SmsManager smsManager;
    SharedPreferences sharedpreferences;
    String keycode = "C@R0L";

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private NetItemListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static InternetFragment newInstance(String param1, String param2) {
        InternetFragment fragment = new InternetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SMSFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = this.getActivity().getSharedPreferences(MainActivity.TAG, Context.MODE_PRIVATE);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item("LIGHTS ON",false,""));
        items.add(new Item("LIGHTS OFF",false,""));
        items.add(new Item("AIRCON ON",false,""));
        items.add(new Item("AIRCON OFF",false,""));
        items.add(new Item("MUSIC ON",false,""));
        items.add(new Item("MUSIC OFF",false,""));
        items.add(new Item("ENGINE START",false,""));
        items.add(new Item("ENGINE STOP",false,""));
        items.add(new Item("ALL OFF",false,""));
        items.add(new Item("LOCK DOORS",false,""));
        items.add(new Item("OPEN DOORS",false,""));
        items.add(new Item("CAR STATUS",false,""));



        mAdapter = new NetItemListAdapter(items);

/*
        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_item, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public class NetItemListAdapter extends BaseAdapter{

        ArrayList<Item> items;

        public NetItemListAdapter(ArrayList<Item> items){
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = SMSFragment.this.getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.fragment_item_sms, null);
            restoredPhone = sharedpreferences.getString("cell_number", "09758005563");
            Button btnName = (Button) view.findViewById(R.id.btn_name);
            btnName.setText(items.get(position).getName());


            btnName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("Button", "click");
                  smsManager = SmsManager.getDefault();
                  smsManager.sendTextMessage(restoredPhone, null, keycode+items.get(position).getName().toString(), null, null);
                    Toast.makeText(getActivity()," Message Sent: " + items.get(position).getName().toString(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }

}
