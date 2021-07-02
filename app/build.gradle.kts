plugins {
    id(BuildPlugin.androidApplication)
    id(BuildPlugin.kotlinAndroid)
    kotlin(BuildPlugin.kotlinKapt)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion(AndroidSdk.tools)
    defaultConfig {
        applicationId = BuildPlugin.applicationId
        minSdkVersion(AndroidSdk.minSdk)
        targetSdkVersion(AndroidSdk.targetSdk)

        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName

        testInstrumentationRunner = BuildPlugin.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        getByName("androidTest") {
            java.srcDirs("src/sharedTest/java")
        }
        getByName("test") {
            java.srcDirs("src/sharedTest/java")
        }
    }

    testOptions {
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
        execution = "ANDROID_TEST_ORCHESTRATOR"
    }
}

dependencies {
    //Android
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.autofill)
    implementation(Dependencies.AndroidX.fragment)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.ktxCore)
    implementation(Dependencies.AndroidX.viewModel)
    implementation(Dependencies.AndroidX.livedata)
    implementation(Dependencies.AndroidX.navigationFragment)
    implementation(Dependencies.AndroidX.navigationUi)

    //Kotlin
    implementation(Dependencies.Kotlin.core)
    implementation(Dependencies.Kotlin.coroutines)

    //Room
    implementation(Dependencies.AndroidX.room)
    implementation(Dependencies.AndroidX.roomKtx)
    kapt(Dependencies.AndroidX.roomCompiler)

    //Google
    implementation(Dependencies.Google.material)

    //Glide
    implementation(Dependencies.Bumptech.glide)

    //Koin
    implementation(Dependencies.Koin.android)

    //Square
    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Square.retrofitConverter)
    implementation(Dependencies.Square.moshi)
    implementation(Dependencies.Square.okHttpLogging)

    //Test)
    debugImplementation(Dependencies.Test.fragment) {
        exclude("androidx.test", "monitor")
        exclude("androidx.test", "core")
    }

    testImplementation(Dependencies.Test.androidxCore)
    testImplementation(Dependencies.Test.androidxCoreArch)
    testImplementation(Dependencies.Test.androidxRunner)
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Kotlin.coroutinesTest)

}