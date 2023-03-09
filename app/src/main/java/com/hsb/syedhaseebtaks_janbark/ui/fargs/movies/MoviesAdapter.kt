package com.hsb.syedhaseebtaks_janbark.ui.fargs.movies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.hsb.syedhaseebtaks_janbark.R
import com.hsb.syedhaseebtaks_janbark.data.models.Movies
import com.hsb.syedhaseebtaks_janbark.utils.ViewHolder_Simple
import com.hsb.syedhaseebtaks_janbark.utils.ads.populateUnifiedNativeAdView
import com.hsb.syedhaseebtaks_janbark.utils.setImage

class MoviesAdapter(private val ctx: Context, private var mList: ArrayList<Movies>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var nativeAd: NativeAd? = null
    var itemClick: ((Int) -> Unit)? = null
    var updateClick: ((Movies) -> Unit)? = null
    var deleteClick: ((Movies) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ViewHolder_Simple) {
            return ViewHolderSimple(
                LayoutInflater.from(ctx).inflate(R.layout.movieslistrow, parent, false)
            )
        }
        return ViewHolderAd(
            LayoutInflater.from(ctx).inflate(R.layout.adlayout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    override fun getItemViewType(position: Int): Int {
        return mList[position].viewType
    }

    private inner class ViewHolderSimple(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var titletxt: TextView = itemView.findViewById(R.id.titletxt)
        var updateBtn: Button = itemView.findViewById(R.id.updatebtn)
        var deleteBtn: Button = itemView.findViewById(R.id.deletebtn)

        fun bind(position: Int) {
            val recyclerViewModel = mList[position]
            titletxt.text = recyclerViewModel.Title

            imageView.setImage(recyclerViewModel.Poster)

            imageView.setOnClickListener {
                itemClick?.invoke(position)
            }
            updateBtn.setOnClickListener { updateClick?.invoke(recyclerViewModel) }
            deleteBtn.setOnClickListener { deleteClick?.invoke(recyclerViewModel) }
        }
    }

    private inner class ViewHolderAd(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var native: ConstraintLayout? = null
        var shimmerFrameLayout: ShimmerFrameLayout? = null
        var adFrame: FrameLayout? = null

        fun bind() {}

        init {
            native = itemView.findViewById(R.id.nativeAdCard)
            shimmerFrameLayout = native?.findViewById(R.id.shimmerContainerSetting)
            adFrame = native?.findViewById(R.id.adFrame)
        }
    }

    @SuppressLint("InflateParams")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mList[position].viewType == ViewHolder_Simple) {
            (holder as ViewHolderSimple).bind(position)

        } else {
            (holder as ViewHolderAd).bind()
            val adView = LayoutInflater.from(ctx)
                .inflate(R.layout.native_ad_layout_mini, null) as NativeAdView
            nativeAd?.let {
                populateUnifiedNativeAdView(it, adView)
                holder.shimmerFrameLayout?.stopShimmer()
                holder.shimmerFrameLayout?.visibility = GONE
                holder.adFrame?.visibility = VISIBLE
                holder.native?.visibility = VISIBLE
                holder.adFrame?.removeAllViews()
                holder.adFrame?.addView(adView)
            }
        }
    }

    fun setNative(nativeAd: NativeAd) {
        this.nativeAd = nativeAd
        notifyDataSetChanged()
    }
    fun update(list: ArrayList<Movies>) {
        this.mList = list
        notifyDataSetChanged()
    }
}