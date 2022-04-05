    package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

    /**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
        private NeighbourApiService mApiService;
        private List<Neighbour> mNeighbours;
        private RecyclerView mRecyclerView2;
        private List<Neighbour> mNeighbourFav;




    public FavoriteFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavoriteFragment.
     */
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_fav_list, container, false);
        Context context = view.getContext();
        mRecyclerView2 = (RecyclerView) view;
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView2.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
}

        private void initList() {
            mNeighbours = mApiService.getFavListNeighbour();
            mRecyclerView2.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
    }
        @Override
        public void onResume() {
            super.onResume();
            initList();
    }
        @Override
        public void onStart() {
            super.onStart();
            EventBus.getDefault().register(this);
        }
        @Override
        public void onStop() {
            super.onStop();
            EventBus.getDefault().unregister(this);
        }
        /**
         * Fired if the user clicks on a delete button
         * @param event
         */
        @Subscribe
        public void onDeleteNeighbour(DeleteNeighbourEvent event) {
            mApiService.deleteNeighbour(event.neighbour);
            initList();
        }

        }

