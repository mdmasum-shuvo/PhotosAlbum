package com.masumdroid.photos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.masumdroid.photos.R;
import com.masumdroid.photos.adapter.AlbumAdapter;
import com.masumdroid.photos.api.ApiInterface;
import com.masumdroid.photos.api.RetrofitClient;
import com.masumdroid.photos.appConstant.AppConstant;
import com.masumdroid.photos.model.Album;
import com.masumdroid.photos.utility.AppUtils;
import com.masumdroid.photos.utility.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager lytManager;
    private Activity mActivity;
    private ArrayList<Album> albumList;
    private AlbumAdapter albumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariable();
        initView();
        initLoader();
        getDataFromServer();

    }

    private void getDataFromServer() {

        ApiInterface api = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<Album>> call = api.getAlbum(getDataFromIntent());

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (!albumList.isEmpty()){
                    albumList.clear();
                }
                albumList.addAll(response.body()) ;
                hideLoader();
                albumAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                if (albumList.isEmpty())
                    showEmptyView();
            }
        });
    }


    private void initVariable() {
        mActivity=AlbumActivity.this;
        albumList=new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_album);
        recyclerView = findViewById(R.id.recycler_album);
        recyclerView.setHasFixedSize(true);
        lytManager = new LinearLayoutManager(mActivity);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, AppUtils.dpToPx(10, mActivity), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        albumAdapter=new AlbumAdapter(albumList,mActivity);
        recyclerView.setAdapter(albumAdapter);
    }

    private int getDataFromIntent(){
        Intent intent=getIntent();
        return  intent.getIntExtra(AppConstant.INTENT_KEY,0);
    }
}
