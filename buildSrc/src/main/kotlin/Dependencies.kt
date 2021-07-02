
import org.gradle.api.artifacts.dsl.RepositoryHandler

object AndroidSdk {
    const val java = 1.8
    const val minSdk = 26
    const val compile = 30
    const val targetSdk = 30
    const val tools = "30.0.2"
    const val versionCode = 1
    const val versionName = "0.0.1"
}

object Dependencies {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Version.Implementation.AndroidX.appcompat}"

        const val autofill = "androidx.autofill:autofill:${Version.Implementation.AndroidX.autofill}"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.Implementation.AndroidX.constraintLayout}"

        const val fragment = "androidx.fragment:fragment-ktx:${Version.Implementation.AndroidX.fragment}"

        const val ktxCore = "androidx.core:core-ktx:${Version.Implementation.AndroidX.core}"

        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Implementation.AndroidX.lifecycle}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Implementation.AndroidX.lifecycle}"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.Implementation.AndroidX.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.Implementation.AndroidX.navigation}"

        const val room = "androidx.room:room-runtime:${Version.Implementation.AndroidX.room}"
        const val roomKtx = "androidx.room:room-ktx:${Version.Implementation.AndroidX.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.Implementation.AndroidX.room}"
    }

    object Bumptech {
        const val glide = "com.github.bumptech.glide:glide:${Version.Implementation.Bumptech.glide}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Version.Implementation.Google.material}"
    }

    object Gradle {
        const val android = "com.android.tools.build:gradle:${Version.Implementation.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Implementation.kotlin}"
    }

    object Kotlin {
        const val core = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.Implementation.kotlin}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Implementation.kotlinCoroutines}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.Implementation.kotlinCoroutines}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Version.Implementation.koin}"
    }

    object Square {
        const val moshi = "com.squareup.moshi:moshi:${Version.Implementation.Square.moshi}"

        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.Implementation.Square.okHttpLogging}"

        const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Version.Implementation.Square.retrofit}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.Implementation.Square.retrofit}"
    }

    object Test {
        const val androidxCore = "androidx.test:core:${Version.Test.AndroidX.core}"
        const val androidxCoreArch = "androidx.arch.core:core-testing:${Version.Test.AndroidX.coreArch}"
        const val androidxJunit = "androidx.test.ext:junit:${Version.Test.AndroidX.junit}"
        const val androidxRunner = "androidx.test:runner:${Version.Test.AndroidX.core}"
        const val androidxRules = "androidx.test:rules:${Version.Test.AndroidX.core}"
        const val fragment = "androidx.fragment:fragment-testing:${Version.Test.AndroidX.fragment}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.Test.AndroidX.espressoCore}"
        const val junit = "junit:junit:${Version.Test.junit}"
        const val junitKtx = "androidx.test.ext:junit-ktx:${Version.Test.AndroidX.junit}"
        const val mockk = "io.mockk:mockk:${Version.Test.mockk}"
        const val mockkAndroid = "io.mockk:mockk-android:${Version.Test.mockk}"
        const val objenesis = "org.objenesis:objenesis:${Version.Test.objenesis}"
    }

    fun addRepos(handler: RepositoryHandler) {
        handler.google()
        handler.mavenCentral()
    }
}

object Version {
    object Implementation {

        object AndroidX {
            const val appcompat = "1.3.0"
            const val autofill = "1.1.0"
            const val core = "1.5.0"
            const val fragment = "1.3.5"
            const val constraintLayout = "2.0.4"
            const val lifecycle = "2.3.1"
            const val navigation = "2.3.5"
            const val room = "2.3.0"
        }

        object Bumptech {
            const val glide = "4.12.0"
        }

        const val gradle = "4.2.2"
        const val kotlin = "1.5.20"
        const val koin = "3.1.0"
        const val kotlinCoroutines = "1.5.0"

        object Square {
            const val moshi = "1.9.2"
            const val okHttpLogging = "3.9.0"
            const val retrofit = "2.6.2"
        }

        object Google {
            const val material = "1.3.0"
        }
    }

    object Test {
        object AndroidX {
            const val core = "1.1.0"
            const val coreArch = "2.0.1"
            const val junit = "1.1.1"
            const val espressoCore = "3.2.0"
            const val fragment = "1.1.0"
        }

        const val jacoco = "0.8.5"
        const val junit = "4.12"
        const val mockk = "1.9.3"
        const val objenesis = "2.6"
    }
}
