package uk.org.mattford.scoutlink.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pircbotx.User;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import uk.org.mattford.scoutlink.R;
import uk.org.mattford.scoutlink.adapter.ConversationsPagerAdapter;

public class UserListFragment extends Fragment {
    private OnUserListFragmentInteractionListener mListener;
    private ViewPager pager;
    private ConversationsPagerAdapter adapter;
    private RecyclerView recyclerView;

    public UserListFragment(ViewPager pager) {
        this.pager = pager;
        this.adapter = (ConversationsPagerAdapter)pager.getAdapter();
        this.pager.addOnAdapterChangeListener((viewPager, oldAdapter, newAdapter) -> this.adapter = (ConversationsPagerAdapter)newAdapter);
        this.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                populateUsersForItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                populateUsersForItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void populateUsersForItem(int i) {
        ConversationsPagerAdapter.ConversationInfo info = this.adapter.getItemInfo(i);
        if (info != null) {
            ArrayList<User> users = info.conv.getUsers();
            recyclerView.setAdapter(new UserListRecyclerViewAdapter(users, mListener));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            populateUsersForItem(pager.getCurrentItem());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserListFragmentInteractionListener) {
            mListener = (OnUserListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnUserListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onUserListItemClicked(String username);
    }
}
