package sda.testing.robolectric;

import android.content.res.Resources;

import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowResources;

import sda.testing.R;

@Implements(Resources.class)
public class Zadanie3CustomShadowResources extends ShadowResources {

    @Override
    public String getQuantityString(int resId, int quantity) throws Resources.NotFoundException {
        if (resId == R.plurals.numberOfSongsAvailable) {
            return "Some String";
        }
        return super.getQuantityString(resId, quantity);
    }
}
