package com.rtovehicleinformation.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.rtovehicleinformation.Adapter.VehicleExpenseListAdapter;
import com.rtovehicleinformation.Database.OpenSQLite;
import com.rtovehicleinformation.Model.VehicleExpenseModel;
import com.rtovehicleinformation.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehicleExpenseActivity extends AppCompatActivity {
    Activity activity = VehicleExpenseActivity.this;

    @BindView(R.id.addexpense)
    ImageView ivaddexpense;

    @BindView(R.id.emptyLayout)
    LinearLayout emptyLayout;

    @BindView(R.id.ivExp)
    ExpandableListView listView;

    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;

    @BindView(R.id.tvTotal)
    TextView tvTotal;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_back)
    ImageView ivback;

    OpenSQLite openSQLite;
    VehicleExpenseListAdapter expenseListAdapter;
    ArrayList<VehicleExpenseModel> expenseModels = new ArrayList();

    AdRequest adRequest;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_expense);
        ButterKnife.bind(this);
        this.openSQLite = new OpenSQLite(getApplicationContext());
        tAmunt();
        loadBannerAd();
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                VehicleExpenseActivity.this.listView.setIndicatorBounds(VehicleExpenseActivity.this.listView.getMeasuredWidth() - 80, VehicleExpenseActivity.this.listView.getMeasuredWidth());
            }
        });
        this.ivaddexpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(VehicleExpenseActivity.this, VehicleExpenseOptionsActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        });
        this.listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                return false;
            }
        });
        this.listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                try {
                    final VehicleExpenseModel vehicleExpenseModel = VehicleExpenseActivity.this.expenseModels.get(i).getExpenseModels().get(i2);
                    view = ((LayoutInflater) VehicleExpenseActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_menu, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(VehicleExpenseActivity.this);
                    builder.setView(view);
                    builder.setCancelable(true);
                    final AlertDialog create = builder.create();
                    create.show();
                    Button button = view.findViewById(R.id.btnDeleteBill);
                    view.findViewById(R.id.btnEditBill).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            create.dismiss();
                            Intent intent = new Intent(VehicleExpenseActivity.this, ExpenceFormActivity.class);
                            intent.putExtra("BID", vehicleExpenseModel.getI1());
                            intent.putExtra("BPAYEE", vehicleExpenseModel.getStr3());
                            intent.putExtra("BAMOUNT", vehicleExpenseModel.getStr());
                            intent.putExtra("BCATNAME", vehicleExpenseModel.getStr2());
                            intent.putExtra("BCATICON", vehicleExpenseModel.getI());
                            intent.putExtra("BNOTE", vehicleExpenseModel.getStr4());
                            intent.putExtra("BDUEDATE", vehicleExpenseModel.getStr5());
                            intent.putExtra("btn", "UPDATE");
                            VehicleExpenseActivity.this.startActivity(intent);
                            VehicleExpenseActivity.this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        }
                    });
                    button.setOnClickListener(new View.OnClickListener() {


                        public void onClick(View view) {
                            create.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(VehicleExpenseActivity.this);
                            builder.setMessage("Do you want to delete item ?");
                            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (VehicleExpenseActivity.this.openSQLite.dlt(vehicleExpenseModel.getI1()) > 0) {
                                        VehicleExpenseActivity.this.tAmunt();
                                    }
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        }
                    });
                } catch (Exception unused) {
                    unused.printStackTrace();
                }
                return false;
            }
        });

    }

    private void loadBannerAd() {
        adView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onResume() {
        super.onResume();
        tAmunt();
    }

    @SuppressLint({"WrongConstant"})
    private void tAmunt() {
        String rawQuery = this.openSQLite.rawQuery();
        int i = 0;
        if (rawQuery != null) {
            this.emptyLayout.setVisibility(8);
            this.tvTotal.setVisibility(0);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Total Amount: ");
            stringBuilder.append(getString(R.string.unit));
            stringBuilder.append(" ");
            stringBuilder.append(rawQuery);
            tvTotal.setText(stringBuilder.toString());
        } else {
            this.tvTotal.setVisibility(8);
            this.emptyLayout.setVisibility(0);
        }
        this.expenseModels = this.openSQLite.list();
        this.expenseListAdapter = new VehicleExpenseListAdapter(this, this.expenseModels);
        this.listView.setAdapter(this.expenseListAdapter);
        while (i < this.expenseListAdapter.getGroupCount()) {
            try {
                this.listView.expandGroup(i);
                i++;
            } catch (Exception unused) {
                return;
            }
        }
    }
}
