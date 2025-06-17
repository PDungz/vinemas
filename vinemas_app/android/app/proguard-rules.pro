# Stripe SDK - Keep Push Provisioning classes
-keep class com.stripe.android.pushProvisioning.** { *; }

# Stripe SDK - Prevent R8 from stripping class/methods
-dontwarn com.stripe.android.pushProvisioning.**
