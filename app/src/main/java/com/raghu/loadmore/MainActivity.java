package com.raghu.loadmore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raghu.loadmorelib.LoadMoreAdapter;
import com.raghu.loadmorelib.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvUser;
    private LoadMoreAdapter loadMoreWrapper;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUser = findViewById(R.id.rvUser);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvUser.setLayoutManager(layoutManager);
        rvUser.setHasFixedSize(true);
        userAdapter = new UserAdapter();
        rvUser.setAdapter(userAdapter);

        loadMoreWrapper = LoadMoreWrapper.with(userAdapter, true)
                .setFooterView(R.layout.base_footer)
                .setShowNoMoreEnabled(true)
                .setNoMoreView(R.layout.base_no_more)
                .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                        Log.d(TAG, "onLoadMore: ");
                        enabled.setLoadMoreEnabled(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                addUser();
                            }
                        }, 1000);
                    }
                }).into(rvUser);


        addUser();
    }

    private void addUser() {
        if (userAdapter.getItemCount() > 30) {
            stopLoadMore();
        } else {
            startLoadMore();
            List<UserModel> userList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                UserModel userModel = new UserModel("Raghu", "9925358171");
                userList.add(userModel);
            }
            userAdapter.setUsersList(userList);
        }
    }

    private void stopLoadMore() {
        try {
            Log.d(TAG, "stopLoadMore: ");
            if (loadMoreWrapper != null) {
                loadMoreWrapper.setLoadMoreEnabled(false);
                loadMoreWrapper.setShowNoMoreEnabled(false);
                userAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLoadMore() {
        try {
            Log.d(TAG, "startLoadMore: ");
            if (loadMoreWrapper != null) {
                loadMoreWrapper.setLoadMoreEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersHolder> {
        private final String TAG = UserAdapter.class.getSimpleName();
        private List<UserModel> usersList = new ArrayList<>();

        public UserAdapter() {

        }

        public void setUsersList(List<UserModel> usersList) {
            if (usersList != null) {
                this.usersList.addAll(usersList);
                notifyDataSetChanged();
            }
        }

        @NonNull
        @Override
        public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UsersHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
            if (usersList == null || usersList.size() < 1 || position < 0)
                return;

            UserModel user = usersList.get(position);

            String strPosition = String.valueOf(position);
            holder.tvPosition.setText(strPosition);
            holder.tvName.setText(user.getName());
            holder.tvMobile.setText(user.getMobileNo());
        }

        @Override
        public int getItemCount() {
            return usersList == null ? 0 : usersList.size();
        }

        protected class UsersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tvName, tvMobile, tvPosition;

            public UsersHolder(@NonNull View itemView) {
                super(itemView);
                tvPosition = itemView.findViewById(R.id.tvPosition);
                tvName = itemView.findViewById(R.id.tvName);
                tvMobile = itemView.findViewById(R.id.tvMobile);

                tvPosition.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (getAdapterPosition() < 0 || usersList == null || usersList.size() == 0)
                    return;

                if (view==tvPosition){
                    usersList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }

            }
        }
    }
}