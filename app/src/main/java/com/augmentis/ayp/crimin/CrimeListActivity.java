package com.augmentis.ayp.crimin;


import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragementActivity {

    @Override
    protected Fragment onCreateFragement() {
        return new CrimeListFragment();

    }


}
