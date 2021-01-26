plugins {
    id("com.android.application")
    kotlin("android")
}


val materialVersion = "1.2.1"
val appCompatVersion = "1.2.0"
val constraintLayoutVersion = "2.0.4"
val recyclerViewVersion = "1.1.0"
val swipeRefreshVersion = "1.1.0"
val cardViewVersion = "1.0.0"
val coroutinesAndroidVersion = "1.1.0"
val coreKtxVersion = "1.3.2"

dependencies {
    implementation(project(":shared"))

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")

    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshVersion")
    implementation("androidx.cardview:cardview:$cardViewVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion")



}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.pixolus.kmmapplication.KMMAndroidApp"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}