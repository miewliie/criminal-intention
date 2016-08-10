package com.augmentis.ayp.crimin;


import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.List;

public class CrimeListActivity extends SingleFragementActivity implements CrimeListFragment.Callbacks,
        CrimeFragment.Callbacks{

    @Override
    protected Fragment onCreateFragement() {
        return new CrimeListFragment();

    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if(findViewById(R.id.detail_fragment_container) == null){
            //if it equal null it mean single pane
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        }else {

            Fragment newDetailFragment = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetailFragment)
                    .commit();
        }

    }

    @Override
    public void onOpenSelectFirst() {
        if(findViewById(R.id.detail_fragment_container) != null){
            List<Crime> crimeList = CrimeLab.getInstance(this).getCrimes();

            if(crimeList != null && crimeList.size() > 0){
                Crime crime = crimeList.get(0);

                Fragment newDetailFragment = CrimeFragment.newInstance(crime.getId());
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_fragment_container, newDetailFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        listFragment.updateUI();
    }

    @Override
    public void onCrimeDelete(){
        CrimeListFragment listFragment = (CrimeListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        CrimeFragment detailFragment = (CrimeFragment)
                getSupportFragmentManager().findFragmentById(R.id.detail_fragment_container);

        listFragment.updateUI();

        //clear
        getSupportFragmentManager()
                .beginTransaction()
                .detach(detailFragment)
                .commit();
    }
}
