package fr.utt.tweetit;

import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import fr.utt.tweetit.OnFragmentInteractionListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link NewMessageFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link NewMessageFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class NewMessageFragment extends Fragment {
	//Tag de classe, permettant de r�cup�rer les fragments de cette classe
	// ex :NewMessageFragment test = (NewMessageFragment) fragManager.findFragmentByTag(NewMessageFragment.TAG)
	public static final String TAG = "newMessageFragment";
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	
	private EditText message;
	private Button btn_envoi;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment NewMessageFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static NewMessageFragment newInstance(String param1, String param2) {
		NewMessageFragment fragment = new NewMessageFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public NewMessageFragment() {
		// Required empty public constructor
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
		// Inflate the layout for this fragment
		
		View rootView = inflater.inflate(R.layout.fragment_new_message, container, false);
		
		this.message = (EditText) rootView.findViewById(R.id.text_message);
		this.btn_envoi = (Button) rootView.findViewById(R.id.btn_envoi);
		
		this.btn_envoi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//envoi de la requete
				JsonHttpRequest request = new JsonHttpRequest(new JsonHttpCallback() {
					
					@Override
					public Object call(JSONObject jsonResponse) {
						// TODO Auto-generated method stub
						return null;
					}
				});
				String token = ((TweetItActivity) getActivity()).getToken();
				request.execute("http://train.sandbox.eutech-ssii.com/messenger/message.php?token");
			}
		});
		
		return rootView;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
}