package com.rtovehicleinformation.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.rtovehicleinformation.Adapter.MileageListAdapter;
import com.rtovehicleinformation.Database.OpenSQLite;
import com.rtovehicleinformation.Model.MileageModel;
import com.rtovehicleinformation.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MileageActivity extends AppCompatActivity {

    Activity activity = MileageActivity.this;
    @BindView(R.id.addmileage)
    ImageView ivaddmileage;

    @BindView(R.id.emptyLayout)
    LinearLayout emptyLayout;

    @BindView(R.id.my_recycler_view)
    RecyclerView rvmileage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_back)
    ImageView ivback;

    MileageListAdapter mileageListAdapter;
    ArrayList<MileageModel> mileageModels = new ArrayList();
    OpenSQLite openSQLite;
    AdRequest adRequest;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mileage);
        ButterKnife.bind(this);
        this.openSQLite = new OpenSQLite(this.getApplicationContext());
        this.mileageModels = this.openSQLite.listmileage();
        this.mileageListAdapter = new MileageListAdapter(this.mileageModels, this, new MileageListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int n) {
                if (!MileageActivity.this.isFinishing()) {
                    final AlertDialog.Builder aleartdialog = new AlertDialog.Builder(MileageActivity.this);
                    aleartdialog.setMessage("Do you want to delete item ?");
                    aleartdialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int i) {
                            if (MileageActivity.this.openSQLite.dltmileage(MileageActivity.this.mileageModels.get(n).getId()) > 0) {
                                MileageActivity.this.mileageModels.remove(n);
                            }
                            if (MileageActivity.this.mileageModels != null && !MileageActivity.this.mileageModels.isEmpty()) {
                                MileageActivity.this.emptyLayout.setVisibility(View.GONE);
                                MileageActivity.this.rvmileage.setVisibility(View.VISIBLE);
                            } else {
                                MileageActivity.this.rvmileage.setVisibility(View.GONE);
                                MileageActivity.this.emptyLayout.setVisibility(View.VISIBLE);
                            }
                            MileageActivity.this.rvmileage.setLayoutManager(new GridLayoutManager(MileageActivity.this, 1));
                            MileageActivity.this.rvmileage.setAdapter(MileageActivity.this.mileageListAdapter);
                        }
                    });
                    aleartdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    if (!MileageActivity.this.isFinishing()) {
                        aleartdialog.show();
                    }
                }
            }
        });
        this.rvmileage.setLayoutManager(new GridLayoutManager(this, 1));
        this.rvmileage.setAdapter(this.mileageListAdapter);
        final ArrayList<MileageModel> mileageModels = this.mileageModels;
        if (mileageModels != null && !mileageModels.isEmpty()) {
            this.emptyLayout.setVisibility(View.GONE);
            this.rvmileage.setVisibility(View.VISIBLE);
        } else {
            this.rvmileage.setVisibility(View.GONE);
            this.emptyLayout.setVisibility(View.VISIBLE);
        }
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.ivaddmileage.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                Intent intent = new Intent(MileageActivity.this, MileageEntryActivity.class);
                intent.putExtra("KM_UNIT", MileageActivity.this.getResources().getString(R.string.KM_UNIT));
                intent.putExtra("FULL_UNIT", MileageActivity.this.getResources().getString(R.string.FULL_UNIT));
                intent.putExtra("COST_UNIT", MileageActivity.this.getResources().getString(R.string.COST_UNIT));
                MileageActivity.this.startActivity(intent);
                MileageActivity.this.finish();
                MileageActivity.this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            }
        });
        loadBannerAd();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    private void loadBannerAd() {
        adView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    public void onBackPressed() {
        super.onBackPressed();
    }
}
