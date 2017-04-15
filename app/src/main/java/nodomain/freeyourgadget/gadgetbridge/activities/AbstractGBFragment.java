/*  Copyright (C) 2015-2017 Andreas Shimokawa, Carsten Pfeiffer

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package nodomain.freeyourgadget.gadgetbridge.activities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Abstract base class for fragments. Provides hooks that are called when
 * the fragment is made visible and invisible in the activity. also allows
 * the fragment to define the title to be shown in the activity.
 *
 * @see AbstractGBFragmentActivity
 */
public abstract class AbstractGBFragment extends Fragment {
    private boolean mVisibleInactivity;

    /**
     * Called when this fragment has been fully scrolled into the activity.
     *
     * @see #isVisibleInActivity()
     * @see #onMadeInvisibleInActivity()
     */
    protected void onMadeVisibleInActivity() {
        updateActivityTitle();
    }

    /**
     * Called when this fragment has been scrolled out of the activity.
     *
     * @see #isVisibleInActivity()
     * @see #onMadeVisibleInActivity()
     */
    protected void onMadeInvisibleInActivity() {
        mVisibleInactivity = false;
    }

    /**
     * Returns true if this fragment is currently visible in the hosting
     * activity, not taking into account whether the screen is enabled at all.
     */
    public boolean isVisibleInActivity() {
        return mVisibleInactivity;
    }

    protected void updateActivityTitle() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            if (getTitle() != null) {
                activity.setTitle(getTitle());
            }
        }
    }

    @Nullable
    protected abstract CharSequence getTitle();

    /**
     * Internal
     *
     * @hide
     */
    public void onMadeVisibleInActivityInternal() {
        mVisibleInactivity = true;
        if (isVisible()) {
            onMadeVisibleInActivity();
        }
    }
}
