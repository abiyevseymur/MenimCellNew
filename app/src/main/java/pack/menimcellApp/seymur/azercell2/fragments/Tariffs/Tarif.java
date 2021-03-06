package pack.menimcellApp.seymur.azercell2.fragments.Tariffs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pack.menimcellApp.seymur.azercell2.ObjectClasses.USSDcodes;
import pack.menimcellApp.seymur.azercell2.R;
import pack.menimcellApp.seymur.azercell2.SendSMSTariff;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tarif.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tarif#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tarif extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tarif() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tarif.
     */
    // TODO: Rename and change types and number of parameters
    public static Tarif newInstance(String param1, String param2) {
        Tarif fragment = new Tarif();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    // NUMBER AND SMS
    String numb = "7575";
    String textSMS = "A";
    //
    Context context;
    Button btnTarrif;
    TextView tittleMessage;
    String tittleMessageString;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tarif, container, false);
      /*  if (((BalanceMenu)getActivity()) != null) {
            ((BalanceMenu)getActivity()).showUpButton();
        }*/
        context = getContext();
        tittleMessage = (TextView)view.findViewById(R.id.thePriceofTariffChange);
        tittleMessageString = tittleMessage.getText().toString();
        btnTarrif = (Button) view.findViewById(R.id.btnAzercelim);
        btnTarrif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                USSDcodes Az = new USSDcodes();
//                Az.sendUssdCode()
            /*    Intent intent = new Intent(getActivity(), SendSMSTariff.class);
                Bundle b = new Bundle();
                b.putString("first", numb);
                b.putString("second",textSMS);
                b.putString("messageTittle",tittleMessageString);
                intent.putExtras(b);
                startActivity(intent);*/
                USSDcodes ussdCode = new USSDcodes();
                Intent intentA = ussdCode.sendUssdCode(getString(R.string.azercellimTarrif),getString(R.string.azercelimTarifQiymeti),getString(R.string.simpleTarrif),getString(R.string.azn),context);
                startActivity(intentA);
            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    final String TAG = "cyrcle";
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "fTarif onDetach");
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
