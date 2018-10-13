package com.masumdroid.photos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.masumdroid.photos.R;
import com.masumdroid.photos.adapter.UserAdapter;
import com.masumdroid.photos.api.ApiInterface;
import com.masumdroid.photos.api.RetrofitClient;
import com.masumdroid.photos.appConstant.AppConstant;
import com.masumdroid.photos.listener.OnItemClickListener;
import com.masumdroid.photos.model.User;
import com.masumdroid.photos.utility.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager lytManagerWord;
    private Activity mActivity;
    private ArrayList<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        initView();
        initLoader();
        getDataFromServer();
        initListener();
    }

    private void initVariable() {
        mActivity = MainActivity.this;
        userList = new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_user);
        recyclerView.setHasFixedSize(true);
        lytManagerWord = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(lytManagerWord);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, lytManagerWord.VERTICAL, 16));
        userAdapter = new UserAdapter(userList, mActivity);
        recyclerView.setAdapter(userAdapter);
    }

    public void getDataFromServer() {
        ApiInterface api = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<User>> call = api.getUser(1);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!userList.isEmpty()) {
                    userList.clear();
                }
                userList.addAll(response.body());
                hideLoader();
                if (userList.isEmpty()) {
                    showEmptyView();
                }
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if (userList.isEmpty()) {
                    showEmptyView();
                }
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener() {
        userAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_title:
                        sendDataViaIntent(position);
                        break;
                    case R.id.m_layout:
                        sendDataViaIntent(position);
                        break;

                    default:
                        sendDataViaIntent(position);
                        break;
                }

            }
        });
    }

    private void sendDataViaIntent(int position) {
        Intent intent = new Intent(mActivity, AlbumActivity.class);
        intent.putExtra(AppConstant.INTENT_KEY, userList.get(position).getId());
        startActivity(intent);
    }
}
