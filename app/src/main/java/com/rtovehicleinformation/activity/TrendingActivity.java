package com.rtovehicleinformation.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.rtovehicleinformation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingActivity extends AppCompatActivity {

    Activity activity = TrendingActivity.this;
    @BindView(R.id.actor)
    CardView actor;

    @BindView(R.id.actress)
    CardView actress;

    @BindView(R.id.dancer)
    CardView dancer;

    @BindView(R.id.politician)
    CardView politician;


//    @BindView(R.id.missperfect)
//    CardView missperfect;


    @BindView(R.id.mrperfect)
    CardView mrperfect;

    @BindView(R.id.singer)
    CardView singer;

    @BindView(R.id.sportsperson)
    CardView sportsperson;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_back)
    ImageView ivback;

    AdRequest adRequest;
    AdView adView;
    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        ButterKnife.bind(this);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.mrperfect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Mr.Perfect");
                TrendingActivity.this.startActivity(intent);
                TrendingActivity.this.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        this.dancer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Dancers");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        this.singer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Singers");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        this.actor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Actors");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        this.politician.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Politicians");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        this.sportsperson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Sports Person");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        this.actress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(TrendingActivity.this, TrendingListActivity.class);
                intent.putExtra("categoryname", "Actresses");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        loadBannerAd();
        loadInterstitialAd();
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
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }


}
