package pack.menimcellApp.seymur.azercell2.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import pack.menimcellApp.seymur.azercell2.BalanceMenu;
import pack.menimcellApp.seymur.azercell2.ObjectClasses.USSDcodes;
import pack.menimcellApp.seymur.azercell2.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BalanceNew.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BalanceNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BalanceNew extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BalanceNew() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BalanceNew.
     */
    // TODO: Rename and change types and number of parameters
    public static BalanceNew newInstance(String param1, String param2) {
        BalanceNew fragment = new BalanceNew();
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
        ftransfer = new Transfer();
        fcredit = new Credit();

    }

    Context context;
    ImageButton getBalanceImageBtn;
    ImageButton refillImageBtn;
    ImageButton getCreditImageBtn;
    ImageButton getTransferImageBtn;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance_new, container, false);
        context = getContext();
        if (((BalanceMenu)getActivity()) != null) {
            ((BalanceMenu)getActivity()).hideUpButton();
        }
        //recieve balance
        getBalanceImageBtn = (ImageButton) view.findViewById(R.id.getBalance);
        getBalanceImageBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("IMAGE", "motion event: " + motionEvent.toString());
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        getBalanceImageBtn.setImageResource(R.drawable.balance_white);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        getBalanceImageBtn.setImageResource(R.drawable.getbalance);
                        break;
                    }
                }
                return false;
            }
        });
        getBalanceImageBtn.setOnClickListener(this);
        refillImageBtn = (ImageButton) view.findViewById(R.id.refill);
        refillImageBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("IMAGE", "motion event: " + motionEvent.toString());
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        refillImageBtn.setImageResource(R.drawable.refill_white);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        refillImageBtn.setImageResource(R.drawable.gettariff);
                        break;
                    }
                }
                return false;
            }
        });
        view.findViewById(R.id.refill).setOnClickListener(this);
        getCreditImageBtn = (ImageButton) view.findViewById(R.id.getCredit);
        getCreditImageBtn.setOnClickListener(this);
        getCreditImageBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("IMAGE", "motion event: " + motionEvent.toString());
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        getCreditImageBtn.setImageResource(R.drawable.credit_white);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        getCreditImageBtn.setImageResource(R.drawable.getcredit);
                        break;
                    }
                }
                return false;
            }
        });
        getTransferImageBtn = (ImageButton)view.findViewById(R.id.getTransfer);
        getTransferImageBtn.setOnClickListener(this);
        getTransferImageBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("IMAGE", "motion event: " + motionEvent.toString());
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        getTransferImageBtn.setImageResource(R.drawable.transfer_white);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        getTransferImageBtn.setImageResource(R.drawable.transfer);
                        break;
                    }
                }
                return false;
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


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    Transfer ftransfer;
    Credit fcredit;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.getBalance:
                USSDcodes ussdCode = new USSDcodes();
                Intent intentA = ussdCode.sendUssdCode(getString(R.string.balanceUSSDcode),getString(R.string.theBalanceWillSend),context);
                startActivity(intentA);
            break;

            case  R.id.refill:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://azercell.3dsecure.az/"));
                if(getActivity() != null)
                getActivity().startActivity(i);
            break;

            case R.id.getCredit:
                FragmentTransaction ft = null;
                if (this.getFragmentManager() != null) {
                    ft = this.getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container,fcredit,"back");
                    ft.addToBackStack("back");
                    ft.commit();
                    break;
                }

            case R.id.getTransfer:
                FragmentTransaction t = null;
                if (this.getFragmentManager() != null) {
                    t = this.getFragmentManager().beginTransaction();
                    t.replace(R.id.fragment_container,ftransfer );
                    t.addToBackStack("e");
                    t.commit();
                    break;

                }

        }

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