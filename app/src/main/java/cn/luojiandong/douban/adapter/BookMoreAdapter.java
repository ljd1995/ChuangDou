package cn.luojiandong.douban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.luojiandong.douban.R;
import cn.luojiandong.douban.bean.BookInfo;
import cn.luojiandong.douban.utils.GlideUtils;
import cn.luojiandong.douban.utils.UIUtils;

/**
 * Created by JackLuo on 2016/10/22.
 *
 * @description
 */

public class BookMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BookInfo> mDatas;
    private LayoutInflater mInflater;
    private BookMoreAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(BookMoreAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public BookMoreAdapter(List<BookInfo> datas) {
        mDatas = datas;
        mInflater = LayoutInflater.from(UIUtils.getContext());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookMoreAdapter.InTheaterViewHolder(mInflater.inflate(R.layout.item_intheaters_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BookMoreAdapter.InTheaterViewHolder movieViewHolder = (BookMoreAdapter.InTheaterViewHolder) holder;
        double rate = mDatas.get(position).getRating().getAverage();
        movieViewHolder.mTitle.setText(mDatas.get(position).getTitle());
        movieViewHolder.mRating.setText("评分：" + rate);
        GlideUtils.loadImageView(UIUtils.getContext(),mDatas.get(position).getImages().getLarge(),movieViewHolder.mInTheaterImage);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size() > 0 ? mDatas.size() : 0;
    }

    class InTheaterViewHolder extends RecyclerView.ViewHolder {

        ImageView mInTheaterImage;
        TextView mTitle;
        TextView mRating;

        public InTheaterViewHolder(View itemView) {
            super(itemView);
            mInTheaterImage = (ImageView) itemView.findViewById(R.id.inTheaters_image);
            mTitle = (TextView) itemView.findViewById(R.id.inTheaters_title);
            mRating = (TextView) itemView.findViewById(R.id.inTheaters_rating);
        }
    }

}
