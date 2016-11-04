# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5  
-dontusemixedcaseclassnames  
-dontskipnonpubliclibraryclasses  
-dontpreverify  
-verbose  
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  

-keepattributes *Annotation*  
-keepattributes Signature

-keep public class * extends android.app.Fragment    
-keep public class * extends android.app.Activity  
-keep public class * extends android.app.Application  
-keep public class * extends android.app.Service  
-keep public class * extends android.content.BroadcastReceiver  
-keep public class * extends android.content.ContentProvider  
-keep public class * extends android.app.backup.BackupAgentHelper  
-keep public class * extends android.preference.Preference  
-keep public class * extends android.support.v4.**  
-keep public class com.android.vending.licensing.ILicensingService
-keep class com.android.vending.licensing.ILicensingService  
-keep class android.support.v4.** { *; }
-keep class okhttp3.**

-dontwarn android.support.v4.**
-dontwarn okio.**
-dontwarn com.uk.co.senab.photoview.**
-dontwarn android.webkit.WebView
-keep class com.sobot.** {*;}

-keepclasseswithmembernames class * {  
    native <methods>;  
}  

-keepclasseswithmembernames class * {  
    public <init>(android.content.Context, android.util.AttributeSet);  
}

-keepclasseswithmembernames class * {  
    public <init>(android.content.Context, android.util.AttributeSet, int);  
}

-keepclassmembers enum * {  
    public static **[] values();  
    public static ** valueOf(java.lang.String);  
}

-keep class * implements android.os.Parcelable {  
  public static final android.os.Parcelable$Creator *;  
}

-keepclasseswithmembers class * {  
    public <init>(android.content.Context);  
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}