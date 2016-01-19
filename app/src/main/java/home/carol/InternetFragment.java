package home.carol;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import home.carol.model.Item;
import home.carol.services.RestServices;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class InternetFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RelativeLayout loadingLayout;

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
    public InternetFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        /*ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Lights",false,""));
        items.add(new Item("Aircon",false,""));
        items.add(new Item("Music",false,""));
        items.add(new Item("Engine",false,""));
        items.add(new Item("Door",false,""));

        init(items);*/
    }


    public void init(ArrayList<Item> items){
        mAdapter = new NetItemListAdapter(items);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_item, container, false);

        loadingLayout = (RelativeLayout)view.findViewById(R.id.rel_loading);


        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);


        loadingLayout.setVisibility(View.VISIBLE);
        //getCurrentStatus();
        startPoll();


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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
           /* mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);*/
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
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

            LayoutInflater inflater = InternetFragment.this.getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.fragment_item_net, null);


          /*  Switch switchPower = (Switch) view.findViewById(R.id.switch_power);
            switchPower.setChecked(items.get(position).getOn());

            switchPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    HashMap<String,String> params = new HashMap<String, String>();
                    if(!isChecked) {
                        params.put("args", "off"+(position+1));
                    }else{
                        params.put("args", "on"+(position+1));
                    }

                    new RestAsyncTask(params).execute();



                }


            });*/


            Button btnOn = (Button) view.findViewById(R.id.btn_on);
            Button btnOff = (Button) view.findViewById(R.id.btn_off);

            btnOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put("args", "on"+(position+1));
                    new RestAsyncTask(params).execute();
                }
            });

            btnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put("args", "off"+(position+1));
                    new RestAsyncTask(params).execute();
                }
            });

            ImageView imageConnected = (ImageView) view.findViewById(R.id.iv_connected);
            imageConnected.setImageResource((items.get(position).getOn()?
              R.drawable.green_circle:R.drawable.grey_circle));

            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(items.get(position).getName());

            return view;
        }
    }
    public class RestAsyncTask extends AsyncTask<Void, Void, String> {
        HashMap<String,String> params;
        public RestAsyncTask(HashMap<String,String> params){
            this.params = params;
        }

        @Override
        protected String doInBackground(Void... param) {
            String response = RestServices.getInstance().callRest(InternetFragment.this.getActivity(), params);

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(params.get("args").toString().equals("status") && !s.isEmpty()){


                String results = "";
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    results = jsonObject.getString("return_value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(InternetFragment.this.getActivity(), results, Toast.LENGTH_LONG).show();
                ArrayList<Item> items = new ArrayList<Item>();
                items.add(new Item("Lights",false,""));
                items.add(new Item("Aircon",false,""));
                items.add(new Item("Music",false,""));
                items.add(new Item("Engine",false,""));
                items.add(new Item("Door", false, ""));



                for(int i = 0; i < results.length(); i++){
                    char c = results.charAt(i);
                    //Item item = null;
                    switch(i){
                        case 0:
                            items.get(1).setOn((c == '1'));
                            //item = new Item("Aircon",(c == '1'),"");
                            break;
                        case 1:

                            items.get(2).setOn((c == '1'));
                            //item = new Item("Music",(c == '1'),"");
                            break;
                        case 2:

                            items.get(0).setOn((c == '1'));
                            //item = new Item("Lights",(c == '1'),"");
                            break;
                        case 3:

                            items.get(3).setOn((c == '1'));
                            //item = new Item("Engine",(c == '1'),"");
                            break;
                        case 4:

                            items.get(4).setOn((c == '1'));
                            //item = new Item("Door",(c == '1'),"");
                            break;
                    }

                    //items.add(item);
                }



                loadingLayout.setVisibility(View.GONE);
                init(items);

                //getCurrentStatus();



            }

        }
    }

    public void getCurrentStatus(){
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("args", "status");
        new RestAsyncTask(params).execute();
    }


    @Override
    public void onPause() {
        super.onPause();
        doAsynchronousTask.cancel();
        timer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(doAsynchronousTask != null){
            startPoll();
        }
    }

    TimerTask doAsynchronousTask;
    Timer timer;
    public void startPoll(){

        getCurrentStatus();
        final Handler handler = new Handler();
        timer = new Timer();
        doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                            //Toast.makeText(InternetFragment.this.getActivity(), "", Toast.LENGTH_LONG).show();
                           /* PerformBackgroundTask performBackgroundTask = new PerformBackgroundTask();
                            // PerformBackgroundTask this class is the class that extends AsynchTask
                            performBackgroundTask.execute();*/
                            getCurrentStatus();

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 50000); //execute in every 50000 ms

    }


}
