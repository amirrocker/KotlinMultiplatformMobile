Kotlin Multiplatform Mobile (KMM)

developed to learn the features, differences and possibilities of multi-platform programming using
kotlin and java-interop as well as kotlin and objective-C interop.

See here for more information:
https://play.kotlinlang.org/hands-on/Networking%20and%20Data%20Storage%20with%20Kotlin%20Multiplatfrom%20Mobile/03_Adding_dependecies
and
https://blog.jetbrains.com/de/kotlin/2020/09/kotlin-multiplatform-mobile-alpha/
and
https://medium.com/@cafonsomota/set-up-your-first-kotlin-multiplatform-project-for-android-and-ios-e54c2b6574e7

Among other sources the jetbrains blog shall be used as reference since it contains the most stable and
up-to-date information.

Project:

Dependencies:

The project uses a number of dependencies:
ktor - network code, such as request, response ...
serialization - json serialization
coroutines - lightweight threading model
sqldelight - a sqlight library

Note: serialization and sqldelight libraries are also used as plugins so the buildsystem (gradle) has
access and use the libraries.

