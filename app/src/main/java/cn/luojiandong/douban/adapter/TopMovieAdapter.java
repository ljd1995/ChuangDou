package cn.luojiandong.douban.adapter;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import cn.luojiandong.douban.R;
import cn.luojiandong.douban.bean.SubjectsInfo;
import cn.luojiandong.douban.utils.GlideUtils;
import cn.luojiandong.douban.utils.UIUtils;

/**
 * Created by JackLuo on 2016/10/18.
 */

public class TopMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SubjectsInfo> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private static final int EMPTY_VIEW = 1;
    private static final int VIEW_PROG = 2;
    private boolean isLoading = false;
    private OnLoadMoreListener onLoadMoreListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public TopMovieAdapter(List<SubjectsInfo> datas, RecyclerView recyclerView) {
        mDatas = datas;
        mInflater = LayoutInflater.from(UIUtils.getContext());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading && recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                        >= recyclerView.computeVerticalScrollRange()) {
                    onLoadMoreListener.onLoadMore();
                }
            }
        });

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            return new EmptyViewHolder(mInflater.inflate(R.layout.empty_view, parent, false));
        } else if (viewType == VIEW_PROG) {
            return new ProgressViewHolder(mInflater.inflate(R.layout.progressbar_item, parent, false));
        } else {
            return new TopMovieViewHolder(mInflater.inflate(R.layout.item_top_list, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TopMovieViewHolder) {
            TopMovieViewHolder movieViewHolder = (TopMovieViewHolder) holder;
            double rate = mDatas.get(position).getRating().getAverage();
            movieViewHolder.title.setText(mDatas.get(position).getTitle());
            movieViewHolder.originalName.setText(mDatas.get(position).getOriginal_title());
            movieViewHolder.topMovieSort.setText("——— "+(position + 1)+" ———");
            movieViewHolder.year.setText("上映时间：" + mDatas.get(position).getYear());
            movieViewHolder.rating.setText("评分：" + rate);
            movieViewHolder.mRatingBar.setProgress((int) rate);
            GlideUtils.loadImageView(UIUtils.getContext(),mDatas.get(position).getImages().getMedium(),movieViewHolder.topMovieImage);
            //兼容低版本RatingBar的progressTint属性
            LayerDrawable ld_stars = (LayerDrawable) movieViewHolder.mRatingBar.getProgressDrawable();
            ld_stars.getDrawable(2).setColorFilter(UIUtils.getColor(R.color.colorYellow), PorterDuff.Mode.SRC_ATOP);
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
        } else if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() > 0 ? mDatas.size() : 1;
    }

    class TopMovieViewHolder extends RecyclerView.ViewHolder {

        ImageView topMovieImage;
        TextView title;
        TextView originalName;
        TextView year;
        TextView rating;
        RatingBar mRatingBar;
        TextView topMovieSort;

        public TopMovieViewHolder(View itemView) {
            super(itemView);
            topMovieImage = (ImageView) itemView.findViewById(R.id.top_movie_image);
            title = (TextView) itemView.findViewById(R.id.top_movie_title);
            originalName = (TextView) itemView.findViewById(R.id.top_movie_original_title);
            year = (TextView) itemView.findViewById(R.id.top_movie_year);
            rating = (TextView) itemView.findViewById(R.id.top_movie_rating);
            topMovieSort = (TextView) itemView.findViewById(R.id.tv_top_movie_sort);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.ratingbar);
        }
    }


    class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public TextView textView;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar_load);
            textView = (TextView) itemView.findViewById(R.id.tv_load);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_empty);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoading() {
        isLoading = true;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() == 0) {
            return EMPTY_VIEW;
        }
        return mDatas.get(position) != null ? super.getItemViewType(position) : VIEW_PROG;
    }
}
