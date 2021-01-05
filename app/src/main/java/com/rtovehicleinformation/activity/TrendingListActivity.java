package com.rtovehicleinformation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.rtovehicleinformation.Adapter.TrendingListAdapter;
import com.rtovehicleinformation.R;
import com.rtovehicleinformation.kprogresshud.KProgressHUD;
import com.rtovehicleinformation.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingListActivity extends AppCompatActivity {

    Activity activity = TrendingListActivity.this;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.trending_recycler)
    RecyclerView rvtrending;

    @BindView(R.id.trendy_title)
    TextView tvtrendytitle;

    @BindView(R.id.iv_back)
    ImageView ivback;

    String catName;
    TrendingListAdapter trendingListAdapter;
    AdRequest adRequest;
    AdView adView;
    InterstitialAd mInterstitialAd;
    int TrandingPositiion;
    public KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_list);
        ButterKnife.bind(this);
        catName = getIntent().getStringExtra("categoryname");
        tvtrendytitle.setText(this.catName);
        loadBannerAd();
        loadInterstitialAd();
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (this.catName.equals("Mr.Perfect")) {
            tvtrendytitle.setText("Mr.Perfect");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.mrperfectsnum, Utils.mrperfectsname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.mrperfectsnum[i]);
                    }
                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        } else if (this.catName.equals("Dancers")) {
            tvtrendytitle.setText("Dancers");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.dancersnum, Utils.dancersname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.dancersnum[i]);
                    }
                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        } else if (this.catName.equals("Singers")) {
            tvtrendytitle.setText("Singers");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.singersnum, Utils.singersname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.singersnum[i]);
                    }

                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        } else if (this.catName.equals("Actors")) {
            tvtrendytitle.setText("Actors");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.actorsnum, Utils.actorsname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.actorsnum[i]);
                    }
                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        } else if (this.catName.equals("Politicians")) {
            tvtrendytitle.setText("Politicians");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.politiciansnum, Utils.politiciansname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.politiciansnum[i]);
                    }
                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        } else if (this.catName.equals("Sports Person")) {
            tvtrendytitle.setText("Sports Person");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.sportspersonsnum, Utils.sportspersonsname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.sportspersonsnum[i]);
                    }
                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        } else if (this.catName.equals("Actresses")) {
            tvtrendytitle.setText("Actresses");
            this.rvtrending.setLayoutManager(new GridLayoutManager(this, 1));
            this.trendingListAdapter = new TrendingListAdapter(this, Utils.actressesnum, Utils.actressesname, new TrendingListAdapter.OnItemClickListener() {
                public void onItemClick(View view, final int i) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        try {
                            hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("Showing Ads")
                                    .setDetailsLabel("Please Wait...");
                            hud.show();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e2) {
                            e2.printStackTrace();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hud.dismiss();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();

                                } catch (NullPointerException e2) {
                                    e2.printStackTrace();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                                    TrandingPositiion = i;
                                    mInterstitialAd.show();
                                }
                            }
                        }, 2000);
                    } else {
                        TrendingListActivity.this.substring(Utils.actressesnum[i]);
                    }
                }
            });
            this.rvtrending.setAdapter(this.trendingListAdapter);
        }
    }

    private void AdsDialog(int i, String ArraName) {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            try {
                hud = KProgressHUD.create(activity).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Showing Ads")
                        .setDetailsLabel("Please Wait...");
                hud.show();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        hud.dismiss();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();

                    } catch (NullPointerException e2) {
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                }
            }, 2000);
        } else {

        }
    }

    private void TrandingItemClick(int i) {
        if (this.catName.equals("Mr.Perfect")) {
            TrendingListActivity.this.substring(Utils.mrperfectsnum[i]);

        } else if (this.catName.equals("Dancers")) {
            TrendingListActivity.this.substring(Utils.dancersnum[i]);

        } else if (this.catName.equals("Singers")) {
            TrendingListActivity.this.substring(Utils.singersnum[i]);

        } else if (this.catName.equals("Actors")) {
            TrendingListActivity.this.substring(Utils.actorsnum[i]);

        } else if (this.catName.equals("Politicians")) {
            TrendingListActivity.this.substring(Utils.politiciansnum[i]);

        } else if (this.catName.equals("Sports Person")) {
            TrendingListActivity.this.substring(Utils.sportspersonsnum[i]);

        } else if (this.catName.equals("Actresses")) {
            TrendingListActivity.this.substring(Utils.actressesnum[i]);
        }
    }

    private void loadBannerAd() {
        adView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                RequestInterstitial();
                TrandingItemClick(TrandingPositiion);

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);

            }
        });
        RequestInterstitial();
    }

    public void RequestInterstitial() {
        try {
            if (mInterstitialAd != null) {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            } else {
                mInterstitialAd = new InterstitialAd(activity);
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String Input1;
    String Input2;
    String Input3;

    public void substring(String str) {
        String substring;
        if (Character.isDigit(str.charAt(4))) {
            substring = str.substring(0, 4);
            str = str.substring(4);
            Input1 = substring.substring(0, 2);
            Input2 = substring.substring(2, 4);
            Input3 = "";
        } else if (Character.isDigit(str.charAt(5))) {
            substring = str.substring(0, 5);
            str = str.substring(5);
            Input1 = substring.substring(0, 2);
            Input2 = substring.substring(2, 4);
            Input3 = substring.substring(4, 5);
        } else if (Character.isDigit(str.charAt(6))) {
            substring = str.substring(0, 6);
            str = str.substring(6);
            Input1 = substring.substring(0, 2);
            Input2 = substring.substring(2, 4);
            Input3 = substring.substring(4, 6);
        } else {
            substring = str.substring(0, 7);
            str = str.substring(7);
        }

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("first", Input1);
        intent.putExtra("second", Input2);
        intent.putExtra("third", Input3);
        intent.putExtra("fourth", str);
        startActivity(intent);
//        Intent intent = new Intent(this, NumberPlateActivity.class);
//        intent.putExtra("firstPart", substring);
//        intent.putExtra("lastPart", str);
//        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
