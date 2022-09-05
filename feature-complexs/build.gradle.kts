plugins {
    id("android-app-convention")
}


dependencies {
    implementation(Libs.Accompanist.ViewPager)
    implementation(Libs.Accompanist.Indicators)
    implementation(Libs.RxKotlin.RUNTIME)
    implementation(Libs.Coil.BASE)

    implementation(project(Modules.Base.CORE_MODEL))
    implementation(project(Modules.Base.CORE_DATA))
    api(project(Modules.Base.CORE))

}