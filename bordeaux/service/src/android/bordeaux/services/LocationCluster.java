/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.bordeaux.services;

import android.location.Location;
import android.text.format.Time;
import android.util.Log;

import java.lang.Math;
import java.util.ArrayList;

public class LocationCluster extends BaseCluster {
    public static String TAG = "LocationCluster";

    private static double FORGETTING_FACTOR = 0.1;

    private boolean mIsNewCluster;

    private ArrayList<Location> mLocations = new ArrayList<Location>();

    public LocationCluster(Location location, long avgInterval) {
        super(location, avgInterval);
        mIsNewCluster = true;
    }

    public void addSample(Location location) {
        mLocations.add(location);
    }

    public void consolidate(long interval) {
        // TODO: add check on interval
        double[] newCenter = {0f, 0f, 0f};
        long newDuration = 0l;

        // update cluster center
        for (Location location : mLocations) {
            double[] vector = getLocationVector(location);
            long duration = location.getTime();

            newDuration += duration;
            for (int i = 0; i < 3; ++i) {
                newCenter[i] += vector[i] * duration;
            }
        }
        for (int i = 0; i < 3; ++i) {
            newCenter[i] /= newDuration;
        }
        // remove location data
        mLocations.clear();

        if (mIsNewCluster) {
            for (int i = 0; i < 3; ++i) {
                mCenter[i] = newCenter[i];
            }
            mDuration = newDuration;
            mIsNewCluster = false;
        } else {
            // the new center is weight average over latest and existing centers.
            // fine tune the weight of new center
            double newWeight = ((double) newDuration) / (newDuration + mDuration);
            newWeight *= FORGETTING_FACTOR;
            double currWeight = 1f - newWeight;
            double norm = 0;
            for (int i = 0; i < 3; ++i) {
                mCenter[i] = currWeight * mCenter[i] + newWeight * newCenter[i];
                norm += mCenter[i] * mCenter[i];
            }
            // normalize
            for (int i = 0; i < 3; ++i) {
                mCenter[i] /= norm;
            }

            newWeight = FORGETTING_FACTOR;
            currWeight = 1f - newWeight;
            mDuration = (long) (mDuration * currWeight + newDuration * newWeight);
        }
    }

    /*
     * if the new created cluster whose covered area overlaps with any existing
     * cluster move the center away from that cluster till there is no overlap.
     */
    public void moveAwayCluster(LocationCluster cluster, float distance) {
        double[] vector = new double[3];

        double dot = 0f;
        for (int i = 0; i < 3; ++i) {
            dot += mCenter[i] * cluster.mCenter[i];
        }
        double norm = 0f;
        for (int i = 0; i < 3; ++i) {
            vector[i] = mCenter[i] - dot * cluster.mCenter[i];
            norm += vector[i] * vector[i];
        }
        norm = Math.sqrt(norm);

        double radian = distance / EARTH_RADIUS;
        for (int i = 0; i < 3; ++i) {
            mCenter[i] = cluster.mCenter[i] * Math.cos(radian) +
                    (vector[i] / norm) * Math.sin(radian);
        }
    }
}
