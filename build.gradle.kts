buildscript {
    val agp_version by extra("7.4.2")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.22" apply false
    id("com.google.devtools.ksp") version "1.7.22-1.0.8" apply false
    id("androidx.navigation.safeargs") version "2.5.3" apply false
}
