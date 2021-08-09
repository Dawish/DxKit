package com.dxkit.library.utils;

import android.view.View;

import androidx.annotation.Keep;

@Keep
public final class SeeViewId {

    private SeeViewId() {

    }

    public static void seeViewIdName(View view) {
        if (view != null
                && view.getContext() != null
                && view.getContext().getResources() != null
                && View.NO_ID != view.getId()) {
            String idName = view.getContext().getResources().getResourceEntryName(view.getId());
            DxLog.d("SeeViewId", view.getClass().getSimpleName() + " id : " + idName);
        }
    }

}
