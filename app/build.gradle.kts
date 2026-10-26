plugins {
    alias(libs.plugins.android.application)
    //1.Agregar el plugin de kotlin serialization
    kotlin("plugin.serialization") version "2.3.20" // asegurate de tener la vevrsión acorde a tu kotlin

}

android {
    namespace = "com.example.app_cursos"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.app_cursos"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    //codigo para el funcionamiento del Retrofit
    //2. libreria principal de kotlinx serialization Json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    //3. convertidor oficial de Retrofit para kotlinx serialization
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")
    //Retrofit & OKHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //para imagenes
    implementation("com.github.bumbtech.glide:glide:4.16.0")

}