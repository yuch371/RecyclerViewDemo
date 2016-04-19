package cn.demo.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    //组件和数据源 4-19:14:34
    RecyclerView mRecyclerView;
    List<String> mDataList;
    private ListGridAdapter mReycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readDataToList();
        initView();
    }

    private  void readDataToList(){
        //模拟读数据列表的源
        mDataList=new ArrayList<String>();
        for(char i='A',j=1;i<'Z';i++,j++){
            mDataList.add(""+i+j);
        }
    }

    private void initView(){
        //找到组件
        mTextView= ((TextView) findViewById(R.id.textView1));
        mRecyclerView= ((RecyclerView) findViewById(R.id.recyclerView1));

        //设置条目的高度为固定尺寸大小
        mRecyclerView.setHasFixedSize(true);
        //设置组件的布局管理器，RecyccleView是通过布局管理器的类型确认是哪种显示风格的，
        // 后续只需要改变布局管理器类型即可调整显示风格，初始为ListView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //参数2,表示布局类型可以有以下几种：
        //  LinearLayoutManager：线性布局
        //  GridLayoutManager：网格布局
        //  StaggeredGridLayoutManager：流式布局
        //参数3：表示是否从最后的Item数据开始显示，ture表示是，false就是正常显示—从开头显示。
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, true));
        //创建适配器的实例
        mReycleAdapter = new ListGridAdapter(MainActivity.this,mDataList);
        //设置组建的适配器
        mRecyclerView.setAdapter(mReycleAdapter);
        //设置ListView条目的分割器
        // mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    public void buttonClick(View v){
        switch (v.getId()){
            case R.id.button11:
                //设置为线性布局结构（列表样式）
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.button12:
                //设置为网格布局结构（网格样式）
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.button13:

                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.button14:
                //跳转到瀑布流模式
                Intent intent=new Intent(MainActivity.this,StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.button21:
                //在指定位置添加一条数据
                mReycleAdapter.addData(1);
                break;
            case R.id.button22:
                //删除指定位置处的一条数据
                mReycleAdapter.removeData(1);
                break;
        }
    }

}

