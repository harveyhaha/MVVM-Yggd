# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping priguardMapping.txtj y
-optimizations !code/simplification/artithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*
#忽略警告
-ignorewarnings

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepnames class * implements java.io.Serializable
-keepattributes Signature
-keep class **.R$* {*;}
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keepclasseswithmembernames class * { # 保持native方法不被混淆
    native <methods>;
}
-keepclassmembers enum * {  # 使用enum类型时需要注意避免以下两个方法混淆，因为enum类的特殊性，以下两个方法会被反射调用，
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-dontwarn androidx.annotation.**
-keep class androidx.** {*;}
-keep interface androidx.** {*;}
-dontwarn androidx.**

# retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

#rxjava
-keep class io.reactivex.** { *; }

#rxpermissions2
-keep class com.tbruyelle.rxpermissions2.** { *; }

#coroutines
-keep class kotlinx.coroutines.** { *; }

#glide
-keep class com.bumptech.glide.** { *; }
#leakcanary
-keep class com.squareup.leakcanary.** { *; }
-keep class leakcanary.** { *; }
#leakcanary
-keep class timber.** { *; }
#dagger2
-keep class dagger.** { *; }