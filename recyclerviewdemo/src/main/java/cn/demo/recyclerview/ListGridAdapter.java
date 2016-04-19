package cn.demo.recyclerview;

/**
 * Created by yuch on 2016/4/12.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *创建一个RecyclerView的数据适配器，并指定条目的Holder缓存类的类型
 */
class ListGridAdapter extends RecyclerView.Adapter<ListGridAdapter.MyViewHolder>
{
    //数据源和布局填充器
    private List<String> mDataList;
    private LayoutInflater mInflater;



    //条目点击事件的接口和回调
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public ListGridAdapter(Context context, List<String> dataList)
    {
        mInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }



    //创建条目复用的Holder时触发，类似于ListView中的ConvertView的创建,ViewType 用于多种多样式条目时有意义
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //创建一个条目复用
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_home, parent, false));
        return holder;
    }
    //当显示条目时触发，在内部实现复用的条目布局和数据绑定显示
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {

        holder.tv.setText(mDataList.get(position));

        // 如果设置了回调，则设置条目内组件的点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mDataList.size();
    }

    public void addData(int position)
    {
        mDataList.add(position, "Insert One");
        notifyItemInserted(position);
    }


    public void removeData(int position)
    {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);


        }
    }
}