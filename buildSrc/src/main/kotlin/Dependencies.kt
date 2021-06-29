
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

        const val fragment = "androidx.fragment:fragment-ktx:${Version.Implementation.AndroidX.fragment}"

        const val ktxCore = "androidx.core:core-ktx:${Version.Implementation.AndroidX.core}"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.Implementation.AndroidX.constraintLayout}"

        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Implementation.AndroidX.lifecycle}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Implementation.AndroidX.lifecycle}"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.Implementation.AndroidX.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.Implementation.AndroidX.navigation}"
    }

    object Bumptech {
        const val glide = "com.github.bumptech.glide:glide:${Version.Implementation.Bumptech.glide}"
    }

    object Facebook {
        const val stetho = "com.facebook.stetho:stetho:${Version.Implementation.Facebook.stetho}"
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
    }
    object Square {
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
        handler.jcenter()
    }
}

object Version {
    object Implementation {

        object AndroidX {
            const val appcompat = "1.3.0"
            const val core = "1.5.0"
            const val fragment = "1.3.5"
            const val constraintLayout = "2.0.4"
            const val lifecycle = "2.3.1"
            const val navigation = "2.3.5"
        }

        object Bumptech {
            const val glide = "4.12.0"
        }

        const val gradle = "4.2.1"
        const val kotlin = "1.5.20"
        const val kotlinCoroutines = "1.5.0"

        object Square {
            const val retrofit = "2.6.2"
        }

        object Google {
            const val material = "1.3.0"
        }

        object Facebook {
            const val stetho = "1.5.1"
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

        const val junit = "4.12"
        const val mockk = "1.9.3"
        const val objenesis = "2.6"
    }
}
