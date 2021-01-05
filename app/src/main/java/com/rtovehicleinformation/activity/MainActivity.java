package com.rtovehicleinformation.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.rtovehicleinformation.BuildConfig;
import com.rtovehicleinformation.Model.PetrolDieselData;
import com.rtovehicleinformation.R;
import com.rtovehicleinformation.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Activity activity = MainActivity.this;

    //    String city = "Rajkot";
//    List<PetrolDieselData> petroldata;
    String strDate;

//    @BindView(R.id.changeCity)
//    TextView tvchangeCity;
//
//    @BindView(R.id.city)
//    TextView tvcityState;
//
//    @BindView(R.id.diesel)
//    TextView tvdiesel;
//
//    @BindView(R.id.dieselname)
//    TextView tvdieselname;
//
//    @BindView(R.id.errorMsg)
//    TextView tverrorMsg;
//
//    @BindView(R.id.petrol)
//    TextView tvpetrol;
//
//    @BindView(R.id.petrolDiesel)
//    LinearLayout layoutpetrolDiesel;
//
//    @BindView(R.id.petrolname)
//    TextView tvpetrolname;
//
//    @BindView(R.id.progress)
//    ProgressBar progress;
//
//    @BindView(R.id.retry)
//    TextView tvretry;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.cv_owner_info)
    CardView reg;

    @BindView(R.id.cv_trafficSymbols)
    CardView trafficSymbols;

    @BindView(R.id.cv_trending)
    CardView trending;

    @BindView(R.id.cv_vehicleExpense)
    CardView vehicleExpense;

    @BindView(R.id.cv_vehicleMileage)
    CardView vehicleMileage;

//    @BindView(R.id.view1)
//    View view1;
//
//    @BindView(R.id.view2)
//    View view2;

    @BindView(R.id.iv_nav_drawer)
    ImageView ivNavDrawer;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.tv_version)
    TextView tvVersionCode;

    public ActionBarDrawerToggle mDrawerToggle;

    public KProgressHUD hud;
    public InterstitialAd mInterstitialAd;
    private UnifiedNativeAd nativeAd;
    int id;
//    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        apiInterface = APIClient.getClient().create(APIInterface.class);
        interstitialAd();
        CallNativeAds();
        GetAppVersion();
        NavigationView navigationView = findViewById(R.id.nav_view);
        ivNavDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        this.strDate = new SimpleDateFormat("ddMMMyyyy").format(Calendar.getInstance().getTime());
        init();
//        initLocation();
    }

    private void init() {
//        this.tvretry.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                if (Utils.checkConnectivity(activity, false)) {
//                    tverrorMsg.setVisibility(View.GONE);
//                    tvretry.setVisibility(View.GONE);
//                    initLocation();
//                } else {
//                    Toast.makeText(MainActivity.this, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        vehicleExpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
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
                                id = 101;
                                mInterstitialAd.show();
                            }
                        }
                    }, 2000);
                } else {
                    VehicelExpanse();
                }

            }
        });
        vehicleMileage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
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
                                id = 102;
                                mInterstitialAd.show();
                            }
                        }
                    }, 2000);
                } else {
                    VehicelMileage();
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
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
                                id = 100;
                                mInterstitialAd.show();
                            }
                        }
                    }, 2000);
                } else {
                    OwnerInfo();
                }


            }
        });
        trafficSymbols.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
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
                                id = 103;
                                mInterstitialAd.show();
                            }
                        }
                    }, 2000);
                } else {
                    TrafficSymbole();
                }
            }
        });
        trending.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
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
                                id = 104;
                                mInterstitialAd.show();
                            }
                        }
                    }, 2000);
                } else {
                    Tranding();
                }
            }
        });
    }

    private void GetAppVersion() {
        PackageInfo info = null;
        PackageManager manager = activity.getPackageManager();
        try {
            info = manager.getPackageInfo(activity.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            tvVersionCode.setText("v" + info.versionName);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        SelectedNavItem(menuItem.getItemId());
        return true;
    }

    private void SelectedNavItem(int itemId) {
        switch (itemId) {
            case R.id.nav_rate_us:
                RateApp();
                break;
            case R.id.nav_invite:
                ShareApp();
                break;
//            case R.id.nav_more:
//                startActivity(new Intent(activity,));
//                break;
            case R.id.nav_feddback:
                Feedback();
                break;
            case R.id.nav_privacy:
                Intent intent1 = new Intent("android.intent.action.VIEW");
                intent1.setData(Uri.parse(getResources().getString(R.string.privacy_link)));
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void Feedback() {
        Intent intent2 = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", getResources().getString(R.string.feedback_email), null));
        intent2.putExtra("android.intent.extra.SUBJECT", "Feedback");
        intent2.putExtra("android.intent.extra.TEXT", "Write your feedback here");
        startActivity(Intent.createChooser(intent2, "Send email..."));
    }

    private void RateApp() {
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }

    private void ShareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Wavy Music");
            String shareMessage = "\nGet free RTO Vehicle Information at here:";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + activity.getPackageName() + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void OwnerInfo() {
        startActivity(new Intent(activity, HomeActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void VehicelExpanse() {
        startActivity(new Intent(activity, VehicleExpenseActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void VehicelMileage() {
        startActivity(new Intent(activity, MileageActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void TrafficSymbole() {
        startActivity(new Intent(activity, TrafficSymbolsActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void Tranding() {
        startActivity(new Intent(activity, TrendingActivity.class));
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void CallNativeAds() {
        AdLoader.Builder builder = new AdLoader.Builder(this, getResources().getString(R.string.admob_native));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = unifiedNativeAd;
                FrameLayout frameLayout = findViewById(R.id.fl_adplaceholder);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.layout_native_advance_small, null);
                populateNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void interstitialAd() {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                RequestInterstitial();
                switch (id) {
                    case 100:
                        OwnerInfo();
                        break;
                    case 101:
                        VehicelExpanse();
                        break;
                    case 102:
                        VehicelMileage();
                        break;
                    case 103:
                        TrafficSymbole();
                        break;
                    case 104:
                        Tranding();
                        break;
                }
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

    private void populateNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView unifiedNativeAdView) {
        MediaView mediaView = unifiedNativeAdView.findViewById(R.id.ad_media);
        unifiedNativeAdView.setMediaView(mediaView);
        unifiedNativeAdView.setHeadlineView(unifiedNativeAdView.findViewById(R.id.ad_headline));
        unifiedNativeAdView.setBodyView(unifiedNativeAdView.findViewById(R.id.ad_body));
        unifiedNativeAdView.setCallToActionView(unifiedNativeAdView.findViewById(R.id.ad_call_to_action));
        unifiedNativeAdView.setStarRatingView(unifiedNativeAdView.findViewById(R.id.ad_stars));
        unifiedNativeAdView.setStoreView(unifiedNativeAdView.findViewById(R.id.ad_store));
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(nativeAd.getHeadline());
        if (nativeAd.getBody() == null) {
            unifiedNativeAdView.getBodyView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) unifiedNativeAdView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getStore() == null) {
            unifiedNativeAdView.findViewById(R.id.appinstall_store_icon).setVisibility(View.GONE);
            unifiedNativeAdView.getStoreView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.findViewById(R.id.appinstall_store_icon).setVisibility(View.VISIBLE);
            unifiedNativeAdView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getStoreView()).setText(nativeAd.getStore());
        }
        if (nativeAd.getStarRating() == null) {
            unifiedNativeAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) unifiedNativeAdView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            unifiedNativeAdView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        unifiedNativeAdView.setNativeAd(nativeAd);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homescreen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        } else if (itemId == R.id.share) {
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "RTO Information");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\nLet me recommend you this application\n\n");
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(BuildConfig.APPLICATION_ID);
                stringBuilder.append("\n\n");
                intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
                startActivity(Intent.createChooser(intent, "choose one"));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            } catch (Exception unused) {
                unused.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }


//    private void GetFuleProice() {
//        petroldata = new ArrayList<>();
//        Pref.setStringValue(MainActivity.this, Const.PREF_LAST_CITY, MainActivity.this.city);
//        Call<JsonObject> call = apiInterface.GetAllTheme(strDate);
//        call.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        JSONObject jsonObj = new JSONObject(new Gson().toJson(response.body()));
//                        JSONArray pirceArray = jsonObj.getJSONArray("prices");
//                        for (int i = 0; i < pirceArray.length(); i++) {
//                            JSONObject pricejsonObject = pirceArray.getJSONObject(i);
//                            PetrolDieselData petrolDieselData = new PetrolDieselData();
//                            petrolDieselData.setPriceId(pricejsonObject.getInt("priceId"));
//                            petrolDieselData.setCityId(pricejsonObject.getInt("cityId"));
//                            petrolDieselData.setCityName(pricejsonObject.getString("cityName"));
//                            petrolDieselData.setStateName(pricejsonObject.getString("stateName"));
//                            petrolDieselData.setPPrice(pricejsonObject.getDouble("pPrice"));
//                            petrolDieselData.setDPrice(pricejsonObject.getDouble("dPrice"));
//                            petrolDieselData.setPriceDate(pricejsonObject.getString("priceDate"));
//                            petroldata.add(petrolDieselData);
//                            Log.e("CityName", "" + petrolDieselData.getCityName());
//                            if (petrolDieselData.getCityName().toLowerCase().equals(Pref.getStringValue(MainActivity.this, Const.PREF_LAST_CITY, "").toLowerCase())) {
//                                Log.e("CityNameMatch", "" + petrolDieselData.getCityName());
//                                StringBuilder stringBuilder = new StringBuilder();
//                                stringBuilder.append("₹ ");
//                                stringBuilder.append(petrolDieselData.getPPrice());
//                                tvpetrol.setText(stringBuilder.toString());
//                                stringBuilder = new StringBuilder();
//                                stringBuilder.append("₹ ");
//                                stringBuilder.append(petrolDieselData.getDPrice());
//                                tvdiesel.setText(stringBuilder.toString());
//                                stringBuilder = new StringBuilder();
//                                stringBuilder.append(petrolDieselData.getCityName());
//                                stringBuilder.append(", ");
//                                stringBuilder.append(petrolDieselData.getStateName());
//                                tvcityState.setText(stringBuilder.toString());
//                            }
//                        }
//                        tvchangeCity.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(MainActivity.this, CityListActivity.class);
//                                intent.putExtra("data", (Serializable) MainActivity.this.petroldata);
//                                startActivityForResult(intent, 1);
//                                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }


//    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
//        super.onActivityResult(i, i2, intent);
//        if (i == 1 && i2 == -1) {
//            PetrolDieselData petrolDieselData = (PetrolDieselData) intent.getSerializableExtra("data");
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(petrolDieselData.getCityName());
//            stringBuilder.append(", ");
//            stringBuilder.append(petrolDieselData.getStateName());
//            tvcityState.setText(stringBuilder.toString());
//            stringBuilder = new StringBuilder();
//            stringBuilder.append("₹ ");
//            stringBuilder.append(petrolDieselData.getPPrice());
//            tvpetrol.setText(stringBuilder.toString());
//            stringBuilder = new StringBuilder();
//            stringBuilder.append("₹ ");
//            stringBuilder.append(petrolDieselData.getDPrice());
//            tvdiesel.setText(stringBuilder.toString());
//        }
//    }


//    public void initLocation() {
//        GetFuleProice();
//    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this, ExitAppctivity.class));
        finish();
    }
}
