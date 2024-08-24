plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    id("kotlin-parcelize") // parcelize是kotlin的一个插件，用于自动生成Parcelable实现
    id("kotlin-kapt")
}

android {
    namespace = "com.jpc.musicdelightapplication"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jpc.musicdelightapplication"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters.apply {
                add("armeabi-v7a")
                add("arm64-v8a")
            }
        }

        applicationVariants.all {
            outputs.all {
                if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                    this.outputFileName = "musicdelightapplication-$versionName.apk"
                }
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true // 是否混淆代码
            isShrinkResources = true // 是否移除无用的资源文件
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.java.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.java.get())
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}
ksp {
    arg("moduleName", project.name)
    // crouter 默认 scheme
    arg("defaultScheme", "app")
    // crouter 默认 host
    arg("defaultHost", "music")
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(project(":lib_base"))
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.swiperefreshlayout)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(libs.androidx.appcompat)

    // Room for database
    ksp(libs.room.compiler)
    implementation(libs.room)
    // Hilt for dependency injection
    ksp(libs.hilt.compiler)
    implementation(libs.hilt)

    // Media3 for music player
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.datasource.okhttp)
    implementation(libs.media3.session)
    implementation(libs.media3.ui)

    implementation(libs.retrofit)

    implementation(libs.preference)
    implementation(libs.flexbox)
    // 开源库
    //implementation(libs.lrcview)
    implementation(libs.banner)
    implementation(libs.shapeView)
    implementation(libs.baseRecyclerViewAdapterHelper)
}