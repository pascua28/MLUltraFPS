package com.sammy.spoofer;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Spoofer implements IXposedHookLoadPackage {
    private static final Map<String, String> FAKE_VARS = new HashMap<>();

    static {
        FAKE_VARS.put("ro.product.manufacturer", "Xiaomi");
        FAKE_VARS.put("ro.product.brand", "Xiaomi");
        FAKE_VARS.put("ro.product.name", "Xiaomi");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        try {
            XposedBridge.hookAllMethods(XposedHelpers.findClass("android.os.SystemProperties", lpparam.classLoader),
                    "get", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (FAKE_VARS.containsKey(param.args[0].toString())) {
                                param.setResult(FAKE_VARS.get(param.args[0].toString()));
                            }
                        }
                    });

            XposedBridge.hookAllMethods(XposedHelpers.findClass("android.os.SystemProperties", lpparam.classLoader),
                    "native_get", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            if (FAKE_VARS.containsKey(param.args[0].toString())) {
                                param.setResult(FAKE_VARS.get(param.args[0].toString()));
                            }
                        }
                    });


            XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", "M2102J20SG");
            XposedHelpers.setStaticObjectField(android.os.Build.class, "MANUFACTURER", "Xiaomi");
            XposedHelpers.setStaticObjectField(android.os.Build.class, "PRODUCT", "vayu_global");
            XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", "vayu");

            Log.d("Sammy: ", "build");

        } catch (Throwable throwable) {
            Log.d("Sammy: ", "error");
        }
    }
}
