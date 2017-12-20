package in.arpaul.sampleexprecycler;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import in.arpaul.sampleexprecycler.adapters.ParentAdapter;
import in.arpaul.sampleexprecycler.listeners.OnDeliveryInteractListener;
import in.arpaul.sampleexprecycler.models.ParcelDO;

import static in.arpaul.sampleexprecycler.models.ParcelDO.SORTED;
import static in.arpaul.sampleexprecycler.models.ParcelDO.UNSORTED;

public class MainActivity extends AppCompatActivity implements OnDeliveryInteractListener {

    private RecyclerView rvNestParcel;
    private ParentAdapter parentAdapter;
    private LinkedHashMap<String, List<ParcelDO>> hashParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseUI();

        bindControls();
    }

    void bindControls() {

        List<ParcelDO> listParcel = new ArrayList<>();
        hashParcel = new LinkedHashMap<String, List<ParcelDO>>();
        listParcel.add(new ParcelDO("1", "001", "Name1", "9876543210"));
        listParcel.add(new ParcelDO("2", "002", "Name2", "9871543210"));
        hashParcel.put(UNSORTED, listParcel);
        listParcel.clear();
        listParcel.add(new ParcelDO("3", "101", "Name3", "9876512210"));
        listParcel.add(new ParcelDO("4", "102", "Name4", "9871598210"));
        hashParcel.put(SORTED, listParcel);
        parentAdapter.refresh(hashParcel);
    }

    @Override
    public void onDeliveryInteract(ParcelDO objOrderDO) {

    }

    void initialiseUI() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvNestParcel        = (RecyclerView) findViewById(R.id.rvNestParcel);
        parentAdapter = new ParentAdapter(this, new LinkedHashMap<String, List<ParcelDO>>(), this);
        rvNestParcel.setAdapter(parentAdapter);
    }
}
