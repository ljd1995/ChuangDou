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
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.adapter
 * 文件名: BookListAdapter
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 14:21
 * 描述：
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.bookListViewHolder> {
    private List<BookInfo> mDatas;
    private LayoutInflater mInflater;
    private BookListAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(BookListAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public BookListAdapter(List<BookInfo> datas) {
        mDatas = datas;
        mInflater = LayoutInflater.from(UIUtils.getContext());
    }

    @Override
    public bookListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new bookListViewHolder(mInflater.inflate(R.layout.item_intheaters_list, parent, false));
    }

    @Override
    public void onBindViewHolder(bookListViewHolder holder, final int position) {
        String largeImageUrl = mDatas.get(position).getImages().getLarge();
        GlideUtils.loadImageView(UIUtils.getContext(), largeImageUrl, holder.mInTheaterImage);
        holder.mTitle.setText(mDatas.get(position).getTitle());
        holder.mRating.setText("评分：" + mDatas.get(position).getRating().getAverage());
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

    class bookListViewHolder extends RecyclerView.ViewHolder {
        ImageView mInTheaterImage;
        TextView mTitle;
        TextView mRating;

        public bookListViewHolder(View itemView) {
            super(itemView);
            mInTheaterImage = (ImageView) itemView.findViewById(R.id.inTheaters_image);
            mTitle = (TextView) itemView.findViewById(R.id.inTheaters_title);
            mRating = (TextView) itemView.findViewById(R.id.inTheaters_rating);
        }
    }
}
