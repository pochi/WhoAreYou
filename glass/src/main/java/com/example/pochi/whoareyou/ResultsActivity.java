package com.example.pochi.whoareyou;

import java.io.Serializable;

/**
 * Created by pochi on 2015/01/05.
 */
public class ResultsActivity implements Serializable {
    private int sample;

    public int getPerson() {
        return sample;
    }

    public void setPerson(int number) {
        sample = number;
    }
}
