package cn.doumi.mvpdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseGeneralRecyclerAdapter;
import cn.doumi.mvpdemo.bean.About;
import cn.doumi.mvpdemo.bean.Author;
import cn.doumi.mvpdemo.bean.Tweet;
import cn.doumi.mvpdemo.widget.IdentityView;
import cn.doumi.mvpdemo.widget.TweetPicturesLayout;


/**
 * Created by
 * thanatos on 16/8/17.
 */
@SuppressWarnings("all")
public class UserTweetAdapter extends BaseGeneralRecyclerAdapter<Tweet> implements View.OnClickListener {
    private Bitmap mRecordBitmap;
    private View.OnClickListener mOnLikeClickListener;
    private boolean isShowIdentityView;

    public UserTweetAdapter(Callback callback) {
        super(callback, ONLY_FOOTER);
        isShowIdentityView = true;
        initListener();
    }

    public void setShowIdentityView(boolean showIdentityView) {
        isShowIdentityView = showIdentityView;
    }

    private void initListener() {
        mOnLikeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (!AccountHelper.isLogin()) {
                    UIHelper.showLoginActivity(mContext);
                    return;
                }
                final int position = Integer.valueOf(v.getTag().toString());
                Tweet tweet = getItem(position);
                if (tweet == null) return;
                OSChinaApi.reverseTweetLike(tweet.getId(), new TweetLikedHandler(position));*/
            }
        };
    }

    private void initRecordImg(Context cxt) {
       /* mRecordBitmap = BitmapFactory.decodeResource(cxt.getResources(), R.mipmap.audio3);
        mRecordBitmap = ImageUtils.zoomBitmap(mRecordBitmap,
                (int) TDevice.dipToPx(cxt.getResources(), 20f), (int) TDevice.dipToPx(cxt.getResources(), 20f));*/
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_tweet_improve, parent, false));
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder h, final Tweet item, int position) {
        ViewHolder holder = (ViewHolder) h;

        final Author author = item.getAuthor();

        if (author == null) {
            holder.mViewName.setText("匿名用户");
        } else {

            holder.mViewName.setText(author.getName());
        }
        if (isShowIdentityView) {
            holder.mIdentityView.setVisibility(View.VISIBLE);
            holder.mIdentityView.setup(author);
        } else {
            holder.mIdentityView.setVisibility(View.GONE);
        }


        holder.mViewTime.setText("4个月前");
        //PlatfromUtil.setPlatFromString(holder.mViewPlatform, item.getAppClient());

        if (!TextUtils.isEmpty(item.getContent())) {
            String content = item.getContent().replaceAll("[\n\\s]+", " ");

        }

        /* - @hide - */
        /*if (item.getAudio() != null) {
            if (mRecordBitmap == null) {
                initRecordImg(mContext);
            }
            ImageSpan recordImg = new ImageSpan(mContext, mRecordBitmap);
            SpannableString str = new SpannableString("c");
            str.setSpan(recordImg, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.mViewContent.setText(str);
            holder.mViewContent.append(spannable);
        } else {
            holder.mViewContent.setText(spannable);
        }*/

        holder.mViewLikeState.setImageResource(
                item.isLiked()
                        ? R.mipmap.ic_thumbup_actived
                        : R.mipmap.ic_thumb_normal);
        holder.mViewLikeState.setTag(position);
        holder.mViewLikeState.setOnClickListener(mOnLikeClickListener);

        holder.mViewLikeCount.setTag(position);
        holder.mViewLikeCount.setOnClickListener(mOnLikeClickListener);

        Tweet.Image[] images = item.getImages();
        holder.mLayoutFlow.setImage(images);

        /* - statistics - */
        if (item.getStatistics() != null) {
            holder.mViewLikeCount.setText(String.valueOf(item.getStatistics().getLike()));
            holder.mViewCmmCount.setText(String.valueOf(item.getStatistics().getComment()));
            int mDispatchCount = item.getStatistics().getTransmit();
            if (mDispatchCount <= 0) {
                //holder.mViewDispatchCount.setVisibility(View.GONE);
                holder.mViewDispatchCount.setText("转发");
            } else {
                holder.mViewDispatchCount.setVisibility(View.VISIBLE);
                holder.mViewDispatchCount.setText(String.valueOf(item.getStatistics().getTransmit()));
            }
        } else {
            holder.mViewLikeCount.setText(String.valueOf(item.getLikeCount()));
            holder.mViewCmmCount.setText(String.valueOf(item.getCommentCount()));
            holder.mViewDispatchCount.setVisibility(View.GONE);
        }
        String textCount = holder.mViewLikeCount.getText().toString();
        holder.mViewLikeCount.setText("0".equals(textCount) ? "赞" : textCount);

        String textComCount = holder.mViewCmmCount.getText().toString();
        holder.mViewCmmCount.setText("0".equals(textComCount) ? "评论" : textComCount);

        /* - about - */
        if (item.getAbout() != null) {
            holder.mLayoutRef.setVisibility(View.VISIBLE);
            holder.mLayoutRef.setTag(position);
            holder.mLayoutRef.setOnClickListener(this);

            About about = item.getAbout();
            holder.mLayoutRefImages.setImage(about.getImages());

            if (!About.check(about)) {
                holder.mViewRefTitle.setVisibility(View.VISIBLE);
                holder.mViewRefTitle.setText("不存在或已删除的内容");
                holder.mViewRefContent.setText("抱歉，该内容不存在或已被删除");
            } else {
               /* if (about.getType() == OSChinaApi.COMMENT_TWEET) {
                    holder.mViewRefTitle.setVisibility(View.GONE);
                    String aname = "@" + about.getTitle();
                    String cnt = about.getContent();
                    Spannable spannable = AssimilateUtils.assimilate(mContext, cnt);
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append(aname + ": ");
                    builder.append(spannable);
                    ForegroundColorSpan span = new ForegroundColorSpan(
                            mContext.getResources().getColor(R.color.day_colorPrimary));
                    builder.setSpan(span, 0, aname.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    holder.mViewRefContent.setMaxLines(Integer.MAX_VALUE);
                    holder.mViewRefContent.setText(builder);
                } else {
                    holder.mViewRefTitle.setVisibility(View.VISIBLE);
                    holder.mViewRefTitle.setText(about.getTitle());
                    holder.mViewRefContent.setMaxLines(3);
                    holder.mViewRefContent.setEllipsize(TextUtils.TruncateAt.END);
                    holder.mViewRefContent.setText(about.getContent());
                }*/
            }
        } else {
            holder.mLayoutRef.setVisibility(View.GONE);
        }
    }

    /**
     * 点击引用时触发
     *
     * @param v Ref View
     */
    @Override
    public void onClick(View v) {
        int position = Integer.valueOf(v.getTag().toString());
        Tweet tweet = getItem(position);
        if (tweet == null) return;
        About about = tweet.getAbout();
        if (about == null) return;
       // UIHelper.showDetail(mContext, about.getType(), about.getId(), about.getHref());
    }

    /**
     * Tweet Item View Holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tweet_face)
        ImageView mViewPortrait;
        @BindView(R.id.identityView)
        IdentityView mIdentityView;
        @BindView(R.id.tv_tweet_name)
        TextView mViewName;
        @BindView(R.id.tv_tweet_time)
        TextView mViewTime;
        @BindView(R.id.tv_tweet_platform)
        TextView mViewPlatform;
        @BindView(R.id.tv_tweet_like_count)
        TextView mViewLikeCount;
        @BindView(R.id.tv_tweet_comment_count)
        TextView mViewCmmCount;

        @BindView(R.id.iv_like_state)
        ImageView mViewLikeState;
        @BindView(R.id.fl_image)
        TweetPicturesLayout mLayoutFlow;
        @BindView(R.id.tv_ref_title)
        TextView mViewRefTitle;
        @BindView(R.id.tv_ref_content)
        TextView mViewRefContent;
        @BindView(R.id.layout_ref_images)
        TweetPicturesLayout mLayoutRefImages;
        @BindView(R.id.iv_dispatch)
        ImageView mViewDispatch;
        @BindView(R.id.tv_dispatch_count)
        TextView mViewDispatchCount;
        @BindView(R.id.layout_ref)
        LinearLayout mLayoutRef;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
