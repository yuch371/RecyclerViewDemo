package cn.demo.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDataList;
    private StaggeredAdapter mStaggeredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        mStaggeredAdapter = new StaggeredAdapter(this, mDataList);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mStaggeredAdapter);
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initEvent();

    }

    private void initEvent() {
        mStaggeredAdapter.setOnItemClickLitener(new StaggeredAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        mDataList = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDataList.add("" + (char) i);
        }
    }

    public void buttonClick(View v){
        switch (v.getId()){
            case R.id.button21:
                mStaggeredAdapter.addData(1);
                break;
            case R.id.button22:
                mStaggeredAdapter.removeData(1);
                break;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.id_action_add:
//                mStaggeredHomeAdapter.addData(1);
//                break;
//            case R.id.id_action_delete:
//                mStaggeredHomeAdapter.removeData(1);
//                break;
//        }
//        return true;
//    }
}