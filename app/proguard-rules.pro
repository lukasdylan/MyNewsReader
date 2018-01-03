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
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Glide
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
 -dontwarn com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
 -dontwarn com.bumptech.glide.load.resource.bitmap.Downsampler
 -dontwarn com.bumptech.glide.load.resource.bitmap.HardwareConfigState
 -keep class com.bumptech.glide.integration.okhttp3.OkHttpGlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 # RX
 -keep class rx.schedulers.Schedulers {
      public static <methods>;
  }
 -keep class rx.schedulers.ImmediateScheduler {
      public <methods>;
  }
 -keep class rx.schedulers.TestScheduler {
      public <methods>;
  }
 -keep class rx.schedulers.Schedulers {
      public static ** test();
  }
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
      long producerIndex;
      long consumerIndex;
  }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
      long producerNode;
      long consumerNode;
  }
 -dontwarn sun.misc.Unsafe

# GSON
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}

# Retrofit
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Android
-keep class android.support.** { *; }
-keep interface android.support.** { *; }
-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
 -keepclassmembers class * implements android.os.Parcelable {
       public static final android.os.Parcelable$Creator *;
  }

# Dagger
-dontwarn com.google.errorprone.annotations.**