package home.carol;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class NFCItemFragment extends Fragment implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private NfcAdapter nfcAdapter;

    // TODO: Rename and change types of parameters
    public static NFCItemFragment newInstance(String param1, String param2) {
        NFCItemFragment fragment = new NFCItemFragment();
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
    public NFCItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        PackageManager pm = this.getActivity().getPackageManager();
        // Check whether NFC is available on device
        if (!pm.hasSystemFeature(PackageManager.FEATURE_NFC)) {
            // NFC is not available on the device.
            Toast.makeText(this.getActivity(), "The device does not has NFC hardware.",
                    Toast.LENGTH_SHORT).show();
        }
        // Check whether device is running Android 4.1 or higher
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            // Android Beam feature is not supported.
            Toast.makeText(this.getActivity(), "Android Beam is not supported.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            // NFC and Android Beam file transfer is supported.
            Toast.makeText(this.getActivity(), "Android Beam is supported on your device.",
                    Toast.LENGTH_SHORT).show();
        }




        final View view = inflater.inflate(R.layout.fragment_nfc_item, container, false);


        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("chk", "id" + checkedId);
                RadioButton radBtn = (RadioButton) view.findViewById(checkedId);


                String msg = "";
                switch(checkedId){
                    default:
                    case R.id.radioOne:
                        msg = "Door";
                        break;
                    case R.id.radioTwo:
                        msg = "Engine";
                        break;
                    /*
                    case R.id.radioThree:
                        break;
                    */

                }


                sendFile(group,msg//radBtn.getText().toString()
                );

            }

        });






       /* // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);*/




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

    public void sendFile(View view, final String message) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this.getActivity());

        // Check whether NFC is enabled on device
        if(!nfcAdapter.isEnabled()){
            // NFC is disabled, show the settings UI
            // to enable NFC
            Toast.makeText(this.getActivity(), "Please enable NFC.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        }
        // Check whether Android Beam feature is enabled on device
        else {
            if (!nfcAdapter.isNdefPushEnabled()) {
                // Android Beam is disabled, show the settings UI
                // to enable Android Beam
                Toast.makeText(this.getActivity(), "Please enable Android Beam.",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
            } else {
                // NFC and Android Beam both are enabled

                // File to be transferred
                // For the sake of this tutorial I've placed an image named 'wallpaper.png'
                // in the 'Pictures' directory
            /*String fileName = "wallpaper.png";

            // Retrieve the path to the user's public pictures directory
            File fileDirectory = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            // Create a new file using the specified directory and name
            File fileToTransfer = new File(fileDirectory, fileName);
            fileToTransfer.setReadable(true, false);

            nfcAdapter.setBeamPushUris(new Uri[]{Uri.fromFile(fileToTransfer)}, this.getActivity());*/

                NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this.getActivity());
                nfc.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {
                    /*
                     * (non-Javadoc)
                     * @see android.nfc.NfcAdapter.CreateNdefMessageCallback#createNdefMessage(android.nfc.NfcEvent)
                     */
                    @Override
                    public NdefMessage createNdefMessage(NfcEvent event) {

                        String temp = "C4R0L78541";

                        /*Toast.makeText(NFCItemFragment.this.getActivity(), "Please enable NFC.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));*/

                        NdefRecord uriRecord = NdefRecord.createUri(Uri.encode(message));
                        return new NdefMessage(new NdefRecord[]{uriRecord});
                    }




                }, this.getActivity(), this.getActivity());
            }
        }
    }

}
