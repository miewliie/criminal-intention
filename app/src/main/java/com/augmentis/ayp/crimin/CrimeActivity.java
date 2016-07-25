package com.augmentis.ayp.crimin;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragementActivity {

    protected static final String CRIME_ID = "crimeActivity.crimeID";
    protected static final String CRIME_POSITION = "crimeActivity.crimePos";

    public static Intent newIntent(Context context, UUID id, int _position){
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(CRIME_ID, id);
        intent.putExtra(CRIME_POSITION, _position);
        return intent;
    }

    protected Fragment onCreateFragement(){
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CRIME_ID);
        int position = (int) getIntent().getExtras().get(CRIME_POSITION);
        Fragment fragment = CrimeFragment.newInstance(crimeId, position);
        return fragment;

    }
}
