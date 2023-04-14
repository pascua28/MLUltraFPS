package com.sammy.spoofer;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Spoofer implements IXposedHookLoadPackage {

    private static final String TARGET_PACKAGE = "com.mobile.legends";
    private static final String MANUFACTURER = "Xiaomi";
    private static final String BRAND = "Xiaomi";
    private static final String DEVICE_NAME = "Xiaomi";
    private static final String MODEL = "M2102J20SG";
    private static final String PRODUCT = "vayu_global";
    private static final String BOARD = "vayu";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals(TARGET_PACKAGE)) {

            XposedHelpers.setStaticObjectField(android.os.Build.class, "MANUFACTURER", MANUFACTURER);
            XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND", BRAND);
            XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE", DEVICE_NAME);
            XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", MODEL);
            XposedHelpers.setStaticObjectField(android.os.Build.class, "PRODUCT", PRODUCT);
            XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", BOARD);
        }
    }
}
