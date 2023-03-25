plugins {
   `java-library`
}

repositories {
    mavenCentral()
    flatDir{dirs ("/lib")}
}

dependencies {
    
    implementation ("net.vproject:cimageio:0.8.1")
    implementation ("org.apache.ant:ant:1.10.12")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.2")
}
