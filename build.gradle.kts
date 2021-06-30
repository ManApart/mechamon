import com.soywiz.korge.gradle.*

group = "org.rak.manapart"
version = ""

buildscript {
    val korgePluginVersion: String by project

    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
        // https://mvnrepository.com/artifact/com.soywiz.korlibs.kbox2d/kbox2d
//		implementation("com.soywiz.korlibs.kbox2d:kbox2d:2.0.8")

    }
}
apply<KorgeGradlePlugin>()

korge {
    id = "org.rak.manapart"
    targetJvm()
    targetJs()
}