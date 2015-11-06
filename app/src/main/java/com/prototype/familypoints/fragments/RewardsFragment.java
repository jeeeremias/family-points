package com.prototype.familypoints.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.prototype.familypoints.ListAdapters.RewardsCustomListAdapter;
import com.prototype.familypoints.R;
import com.prototype.familypoints.activities.NewPlayerActivity;
import com.prototype.familypoints.activities.NewRewardActivity;
import com.prototype.familypoints.mock.Mock;
import com.prototype.familypoints.model.Reward;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RewardsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RewardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private ListView mRewardsListView;
    private List<Reward> mRewardsData;
    private RewardsCustomListAdapter mRewardsCustomListAdapter;

    public static RewardsFragment newInstance() {
        RewardsFragment fragment = new RewardsFragment();
        return fragment;
    }

    public RewardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_rewards, container, false);
        mRewardsListView = (ListView) mView.findViewById(R.id.rewardsLV);
        mockData();
        RewardsCustomListAdapter mRewardsCustomListAdapter = new RewardsCustomListAdapter(mRewardsData, getActivity());
        mRewardsListView.setAdapter(mRewardsCustomListAdapter);

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_add).setEnabled(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(getActivity(), NewRewardActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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
        mRewardsData = new ArrayList<>();
        for(int i = 0; i < Mock.REWARDS_DATA_MOCK_LIST_SIZE; i ++) {
            mRewardsData.add(new Reward(Mock.REWARDS_NAMES[i], i*100, R.drawable.gift));
        }
    }

}
