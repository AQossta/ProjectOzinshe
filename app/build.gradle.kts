plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.navigationSafeArgs)

}

android {
    namespace = "com.example.ozinsheexample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ozinsheexample"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val nav_version = "2.8.5"
    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    implementation ("com.tbuonomo:dotsindicator:4.2")
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    // Coroutines to make the HTTP requests asynchronous(In the background thread)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Okhttp3 for the POST requests
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.1")

    // Gson (To convert raw JSON to pretty JSON)
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    implementation ("com.github.bosphere.android-fadingedgelayout:fadingedgelayout:1.0.0")

    implementation ("com.github.chrisbanes:PhotoView:2.3.0")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    implementation ("com.kyleduo.switchbutton:library:2.1.0")
}