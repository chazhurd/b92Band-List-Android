package com.example.churdlab2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    ImageView mIv;
    TextView mTv;
    ListView mLv;
    ActionBarDrawerToggle mAbdt;
    DrawerLayout mDl;
    int mPos;
    String[] mArtDeets;

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("cur",mPos);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymainlayout);

        mIv = findViewById(R.id.mainImageView);
        mDl = findViewById(R.id.drawer);
        mLv = findViewById(R.id.nav_view);
        mTv = findViewById(R.id.toolbar);



        final String[] artistnames = getResources().getStringArray(R.array.artistNames);
         mArtDeets = getResources().getStringArray(R.array.artistDetails);

        //#4.1
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.navlistlay,R.id.navListText, artistnames);

        //#4.5
        mLv.setAdapter(myAdapter);
        //#4.8
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                mPos = position;
                mLv.getItemAtPosition(position);
                setImage(position);

            }
        });

        //#5.1 ended up putting the strings in the xml instead
        final String drawTitle = "Choose Wisely";
        final String ogTitle = (String)this.getTitle();
        //#5.2
        mAbdt = new ActionBarDrawerToggle(this,mDl, R.string.OpenDrawer, R.string.CloseDrawer){
            //#5.3
            @Override
            public void onDrawerClosed(View view){
                MainActivity.super.setTitle(R.string.CloseDrawer);
            }
            @Override
            public void onDrawerOpened(View view){
            MainActivity.super.setTitle(R.string.OpenDrawer);
            invalidateOptionsMenu();
            }

        };
        //#5.4
        mDl.addDrawerListener(mAbdt);

        //#5.5
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mAbdt.syncState();

        //#7
        if(savedInstanceState!=null) {
            int pos = savedInstanceState.getInt("cur", 99);
            mPos = pos;
            setImage(pos);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mAbdt.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if(mDl.isDrawerOpen(mLv)){
            MenuItem item = menu.findItem(R.id.AboutMenuItem);
            item.setVisible(false);
        }
        if(!mDl.isDrawerOpen(mLv)){
            MenuItem item = menu.findItem(R.id.AboutMenuItem);
            item.setVisible(true);
        }

        return true;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onAbout(MenuItem item) {
        Toast.makeText(this, "Lab 2, Spring 2020, Chaz C Hurd", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mTv.setText("Position is " + position);
    }
    // end template

    public void setImage(int position){

        if(position == 0){
            mIv.setImageResource(R.drawable.fred);
            mTv.setText(mArtDeets[0]);
        }
        else if(position == 1){
            mIv.setImageResource(R.drawable.kate);
            mTv.setText(mArtDeets[1]);
        }
        else if(position == 2){
            mIv.setImageResource(R.drawable.cindy);
            mTv.setText(mArtDeets[2]);
        }
        else if(position == 3){
            mIv.setImageResource(R.drawable.keith);
            mTv.setText(mArtDeets[3]);
        }
        else if(position == 4){
            mIv.setImageResource(R.drawable.rickey);
            mTv.setText(mArtDeets[4]);
        }
        else{
            mIv.setImageResource(R.drawable.matt);
            mTv.setText(mArtDeets[5]);
        }
    }
}
