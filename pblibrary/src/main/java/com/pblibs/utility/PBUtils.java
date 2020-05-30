package com.pblibs.utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.core.content.ContextCompat;
import androidx.core.util.PatternsCompat;


import com.pblibrary.proggyblast.R;
import com.pblibs.base.PBApplication;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UnknownFormatConversionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pblibs.utility.PBConstants.ALGORITHM_MD5;
import static com.pblibs.utility.PBConstants.DATE_FORMAT_2;
import static com.pblibs.utility.PBConstants.EMPTY;
import static com.pblibs.utility.PBConstants.NUM_PATTERN;
import static com.pblibs.utility.PBConstants.ONE;
import static com.pblibs.utility.PBConstants.PSWD_PATTERN;
import static com.pblibs.utility.PBConstants.TEXT_PATTERN;
import static com.pblibs.utility.PBConstants.TWO;
import static com.pblibs.utility.PBConstants.UNKNOWN_ERROR;
import static com.pblibs.utility.PBConstants.ZERO;


public class PBUtils {

    private static PBUtils mInstance;
    private Context mContext;

    public PBUtils() {
        this.mContext = PBApplication.getInstance().getContext();
    }

    public static PBUtils getInstance() {
        if (mInstance == null) {
            synchronized (PBUtils.class) {
                if (mInstance == null) {
                    mInstance = new PBUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * Check if dark theme is enabled or not
     *
     * @param context
     * @return
     */

    public static boolean isDarkTheme(Context context) {
        boolean isDarkMode = false;
        switch (context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                isDarkMode = true;
                break;
        }
        return isDarkMode;
    }

    /**
     * to format UTC time to normal date time
     *
     * @param originalDate
     * @return
     */

    public static String formatUTCTime(String originalDate, String toFormatPattern) {
        String formattedDate = "";
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat(toFormatPattern);
            Date date = inputFormat.parse(originalDate);
            formattedDate = outputFormat.format(date);
            System.out.println(formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    /**
     * Validate Email Address
     *
     * @param email
     * @return
     */

    public String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return mContext.getString(R.string.empty_email);
        } else {
            Matcher lMatcher = PatternsCompat.EMAIL_ADDRESS.matcher(email);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_email);
            }
        }
    }

    /**
     * Validate Name
     *
     * @param name
     * @return
     */

    public String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return mContext.getString(R.string.empty_name);
        } else {
            Matcher lMatcher = Pattern.compile(TEXT_PATTERN).matcher(name);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_name);
            }
        }
    }

    /**
     * Validate First Name
     *
     * @param firstName
     * @return
     */

    public String validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            return mContext.getString(R.string.empty_first_name);
        } else {
            Matcher lMatcher = Pattern.compile(TEXT_PATTERN).matcher(firstName);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_first_name);
            }
        }
    }

    /**
     * Validate Last Name
     *
     * @param lastName
     * @return
     */

    public String validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return mContext.getString(R.string.empty_last_name);
        } else {
            Matcher lMatcher = Pattern.compile(TEXT_PATTERN).matcher(lastName);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_last_name);
            }
        }
    }

    /**
     * Validate the Password with based on length
     *
     * @param password
     * @param length
     * @return
     */

    public String validatePassword(String password, int length) {
        if (password == null || password.isEmpty()) {
            return mContext.getString(R.string.empty_password);
        } else {
            //String pwdPattern = String.format(Locale.US,PSWD_PATTERN, length);
            Matcher lMatcher = Pattern.compile(PSWD_PATTERN).matcher(password);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_pwd);
            }
        }
    }

    /**
     * Validate text is number
     *
     * @param text
     * @return
     */

    public String validateNumber(String text) {
        if (text == null || text.isEmpty()) {
            return mContext.getString(R.string.empty_number);
        } else {
            Matcher lMatcher = Pattern.compile(NUM_PATTERN).matcher(text);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_number);
            }
        }
    }

    /**
     * Validate Username
     *
     * @param userName
     * @param length
     * @return
     */

    public String validateUserName(String userName, int length) {
        if (userName == null || userName.isEmpty()) {
            return mContext.getString(R.string.empty_username);
        } else {
            if (userName.trim().length() >= length) {
                return EMPTY;
            } else {
                return String.format(mContext.getString(R.string.invalid_username), length);
            }
        }
    }

    /**
     * Validate Mobile Number
     *
     * @param mobileNumber
     * @return
     */

    public String validateMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return mContext.getString(R.string.empty_mobile_num);
        } else {
            //PhoneNumberUtils.isGlobalPhoneNumber(mobileNumber)
            Matcher lMatcher = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$").matcher(mobileNumber);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_mobile_num);
            }
        }
    }

    /**
     * Validate URL
     *
     * @param url
     * @return
     */

    public String validateURL(String url) {
        if (url == null || url.isEmpty()) {
            return mContext.getString(R.string.empty_url);
        } else {
            Matcher lMatcher = Patterns.WEB_URL.matcher(url);
            if (lMatcher.matches()) {
                return EMPTY;
            } else {
                return mContext.getString(R.string.invalid_url);
            }
        }
    }

    /**
     * Validate DOB
     *
     * @param dob
     * @param minage
     * @return
     */

    public boolean validateDOB(Calendar dob, int minage) {
        Calendar minAdultAge = Calendar.getInstance();
        minAdultAge.add(Calendar.YEAR, minage);
        return !minAdultAge.before(dob);
    }

    /**
     * Get IMEI Number
     *
     * @param context
     * @return
     */

    public String getImeiNumber(Context context, int simSlot) {
        if (context == null) {
            return EMPTY;
        } else {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return "";
                    }
                    return tm.getImei(simSlot);
                } else {
                    return tm.getDeviceId(simSlot);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return UNKNOWN_ERROR;
            }
        }
    }

    /**
     * Encrypt text into MD5
     *
     * @param text
     * @return
     */

    public String encryptToMD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
            md.update(text.getBytes());
            byte[] digestBytes = md.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digestBytes.length; i++) {
                stringBuilder.append(Integer.toString((digestBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return UNKNOWN_ERROR;
        }
    }

    /**
     * Change Bitmap color
     *
     * @param sourceBitmap
     * @param color
     * @return
     */

    public Bitmap changeImageBitmapColor(Bitmap sourceBitmap, int color) {
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }

    /**
     * To expand view
     *
     * @param view
     * @return
     */

    public int expandView(final View view) {
        try {
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final int targetHeight = view.getMeasuredHeight();
            view.getLayoutParams().height = 1;
            view.setVisibility(View.VISIBLE);
            Animation animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    view.getLayoutParams().height = interpolatedTime == 1
                            ? ViewGroup.LayoutParams.WRAP_CONTENT
                            : (int) (targetHeight * interpolatedTime);
                    view.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            animation.setDuration((int) (targetHeight / view.getContext().getResources().getDisplayMetrics().density));
            view.startAnimation(animation);
            return ONE;
        } catch (Exception e) {
            e.printStackTrace();
            return ZERO;
        }
    }

    /**
     * To collapse View
     *
     * @param view
     * @return
     */

    public int collapseView(final View view) {
        try {
            final int initialHeight = view.getMeasuredHeight();
            Animation animation = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime == 1) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                        view.requestLayout();
                    }
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            animation.setDuration((int) (initialHeight / view.getContext().getResources().getDisplayMetrics().density));
            view.startAnimation(animation);
            return ONE;
        } catch (Exception e) {
            e.printStackTrace();
            return ZERO;
        }
    }

    /**
     * Get Bitmap from URL
     *
     * @param url
     * @return
     */

    public Bitmap getBitmapFromURL(String url) {
        if (url == null) {
            return null;
        }
        try {
            URL lUrl = new URL(url);
            return BitmapFactory.decodeStream(lUrl.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convert String into date from existing format to new format
     *
     * @param existFormat
     * @param newFormat
     * @param dateValue
     * @return
     */

    public String formatDate(String existFormat, String newFormat, String dateValue, Calendar calendar,
                             boolean isFileName) {
        try {
            if (isFileName) {
                newFormat = DATE_FORMAT_2;
            }
            SimpleDateFormat simpleDateFormat;
            Date date;
            if (dateValue != null) {
                simpleDateFormat = new SimpleDateFormat(existFormat, Locale.US);
                date = simpleDateFormat.parse(dateValue);
            } else {
                date = calendar.getTime();
            }
            if (date != null) {
                simpleDateFormat = new SimpleDateFormat(newFormat, Locale.US);
                return simpleDateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UNKNOWN_ERROR;
    }

    /**
     * To check if application is in background or not
     *
     * @return
     */

    public boolean isApplicationInBackground() {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                topActivity = tasks.get(0).topActivity;
            }
            if (!topActivity.getPackageName().equals(mContext.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * To get app current version
     *
     * @return
     */

    public String getAppCurrentVersion() {
        String currentVersion = "";
        try {
            currentVersion = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            Log.d("currentVersion", currentVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVersion;
    }

    /**
     * Open and redirect app in play store
     */

    public void openPlayStore() {
        Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            mContext.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google" +
                    ".com/store/apps/details?id=" + mContext.getPackageName())));
        }
    }

    /**
     * Set locale for app
     *
     * @param language
     */

    public void setLocale(String language) {
        try {
            Locale myLocale = new Locale(language);
            Resources res = mContext.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Display width of device
     *
     * @return
     */

    public int getDisplayWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        }
        return size.x;
    }

    /**
     * Get Display Height of device
     *
     * @return
     */

    public int getDisplayHeight() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        }
        return size.y;
    }

    /**
     * Format single digits to double digits
     *
     * @param num
     * @return
     */

    public String format2Digit(int num) {
        DecimalFormat format = new DecimalFormat("00");
        return format.format(num);
    }

    /**
     * Check service is running in background or not
     *
     * @param serviceClass
     * @return
     */

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Free Garbage Memory
     */

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    /**
     * Generate PDF with array of bitmaps
     *
     * @param bitmaps
     * @param pageWidth
     * @param pageHeight
     * @param bgcolor
     */

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void generatePDF(Bitmap[] bitmaps, int pageWidth, int pageHeight, int bgcolor) {
        PdfDocument document = new PdfDocument();
        for (int i = 0; i < bitmaps.length; i++) {
            Bitmap bitmap = bitmaps[i];
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i + 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.parseColor(ContextCompat.getColor(mContext, bgcolor) + ""));
            canvas.drawPaint(paint);
            bitmap = Bitmap.createScaledBitmap(bitmap, 700, 1100, true);
            canvas.drawBitmap(bitmap, 50f, 50f, null);
            document.finishPage(page);
        }
    }

    /**
     * get common android id
     */

    public String getAndroidId() {
        try {
            return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
            return EMPTY;
        }
    }

    /**
     * To check if device is rooted or not
     */

    public static boolean isDeviceRooted(){
        return checkRootMethod1();
    }

    private static boolean checkRootMethod1(){
        String buildTags=android.os.Build.TAGS;
        return buildTags!=null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2(){
        String paths[]={"/system/app/Superuser.apk","/sbin/su","/system/bin/su","/system/xbin/su","/data/local/xbin" +
                "/su","/data/local/bin/su","/system/sd/bin/su","/system/bin/failsafe/su","/data/local/su","/su/bin/su"};
        return false;
        //return buildTags!=null && buildTags.contains("test-keys");
    }

}
