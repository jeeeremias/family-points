package com.prototype.familypoints.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.prototype.familypoints.ListAdapters.PlayersCustomListAdapter;
import com.prototype.familypoints.R;
import com.prototype.familypoints.mock.PlayersMock;
import com.prototype.familypoints.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    public static PlayersFragment newInstance() {
        PlayersFragment fragment = new PlayersFragment();
        return fragment;
    }

    private ListView mPlayersListView;
    private List<Player> mPlayersData;
    private PlayersCustomListAdapter mPlayersCustomListAdapter;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);
        mPlayersListView = (ListView) view.findViewById(R.id.playersList);
        mockData();
        mPlayersCustomListAdapter = new PlayersCustomListAdapter(mPlayersData, getActivity());
        mPlayersListView.setAdapter(mPlayersCustomListAdapter);

        return view;
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
        public void onFragmentInteraction(Uri uri);
    }

    private void mockData() {
        mPlayersData = new ArrayList<>();
        Bitmap bitmap;
        RoundedBitmapDrawable roundedBitmapDrawable;
        for(int i = 0; i < PlayersMock.DATA_MOCK_LIST_SIZE; i ++) {
            bitmap = BitmapFactory.decodeResource(getResources(), PlayersMock.PICTURES[i]);
            roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawable.setCornerRadius(Math.min(roundedBitmapDrawable.getMinimumWidth(), roundedBitmapDrawable.getMinimumHeight()));
            mPlayersData.add(new Player(PlayersMock.NAMES[i], i*100, roundedBitmapDrawable));
        }
    }

}
