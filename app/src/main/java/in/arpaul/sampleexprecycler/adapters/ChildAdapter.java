package in.arpaul.sampleexprecycler.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.arpaul.sampleexprecycler.R;
import in.arpaul.sampleexprecycler.listeners.OnDeliveryInteractListener;
import in.arpaul.sampleexprecycler.models.ParcelDO;


public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    private String TAG = "ChildAdapter";
    private List<ParcelDO> mValues;
    private final OnDeliveryInteractListener mListener;
    private Context context;

    public ChildAdapter(Context context, List<ParcelDO> items, OnDeliveryInteractListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
    }

    public void refresh(List<ParcelDO> items) {
        this.mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        if(!TextUtils.isEmpty(holder.mItem.getCustName()))
            holder.tvCustName.setText(holder.mItem.getCustName());

        if(!TextUtils.isEmpty(holder.mItem.getCustNumber()))
            holder.tvCustDDN.setText(holder.mItem.getCustNumber());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDeliveryInteract(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues != null)
            return mValues.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final ImageView ivDeliveryStat;
        public final TextView tvCustName;
        public final TextView tvCustDDN;

        public ParcelDO mItem;

        public ViewHolder(View view) {
            super(view);
            mView               = view;
            ivDeliveryStat      = (ImageView) view.findViewById(R.id.ivDeliveryStat);

            tvCustName          = (TextView) view.findViewById(R.id.tvCustName);
            tvCustDDN           = (TextView) view.findViewById(R.id.tvCustDDN);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvCustName.getText() + "'";
        }
    }
}
