package in.arpaul.sampleexprecycler.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import in.arpaul.sampleexprecycler.R;
import in.arpaul.sampleexprecycler.listeners.OnDeliveryInteractListener;
import in.arpaul.sampleexprecycler.models.ParcelDO;

import static in.arpaul.sampleexprecycler.models.ParcelDO.SORTED;
import static in.arpaul.sampleexprecycler.models.ParcelDO.UNSORTED;


public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    private String TAG = "ChildAdapter";
//    private List<ParcelDO> mValues;
    private LinkedHashMap<String, List<ParcelDO>> hashParcels;
//    private ChildAdapter adapterChild;
    private final OnDeliveryInteractListener mListener;
    private Context context;
    public static int selectedGroup = 0;
    private RecyclerView.RecycledViewPool viewPool;

    public ParentAdapter(Context context, LinkedHashMap<String, List<ParcelDO>> hashParcels, OnDeliveryInteractListener listener) {
        this.context = context;
        this.hashParcels = hashParcels;
        mListener = listener;

//        adapterChild = new ChildAdapter(context, new ArrayList<ParcelDO>(), listener);

        viewPool = new RecyclerView.RecycledViewPool();
    }

    public void refresh(LinkedHashMap<String, List<ParcelDO>> hashParcels) {
        this.hashParcels = hashParcels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_parent, parent, false);
        ViewHolder holder = new ViewHolder(view);;
        holder.rvCellNestParcel.setRecycledViewPool(viewPool);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(position == 0) {
            holder.mItem = hashParcels.get(UNSORTED);
            holder.tvParcelList.setText("First (" + holder.mItem.size() + ")");
        } else if(position == 1) {
            holder.mItem = hashParcels.get(SORTED);
            holder.tvParcelList.setText("Second (" + holder.mItem.size() + ")");
        } else
            return;

        if(position == selectedGroup) {
            holder.ivGroupExp.setImageResource(R.drawable.ic_rgt_arw);
            holder.childAdapter.refresh(holder.mItem);
        } else {
            holder.ivGroupExp.setImageResource(R.drawable.ic_dwn_arw);
            holder.childAdapter.refresh(new ArrayList<ParcelDO>());
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGroup = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(hashParcels != null)
            return hashParcels.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final ImageView ivGroupExp;
        public final TextView tvParcelList;
        public final RecyclerView rvCellNestParcel;

        public List<ParcelDO> mItem;
        public ChildAdapter childAdapter;

        public ViewHolder(View view) {
            super(view);
            mView               = view;
            ivGroupExp          = (ImageView) view.findViewById(R.id.ivGroupExp);
            tvParcelList        = (TextView) view.findViewById(R.id.tvParcelList);
            rvCellNestParcel    = (RecyclerView) view.findViewById(R.id.rvCellNestParcel);
            childAdapter = new ChildAdapter(context, new ArrayList<ParcelDO>(), mListener);
            rvCellNestParcel.setAdapter(childAdapter);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvParcelList.getText() + "'";
        }
    }
}
