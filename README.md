# 导入ByteTrack Android SDK
导入 ByteTrack 到你的Android app项目中，ByteTrack支持Android API 21及以上，建议使用最新可用的Android Sdk版本

### 第一步 - 导入ByteTrack
#### 导入ByteTrack
在你的app `build.gradle`文件中添加如下依赖：


```
dependencies {
    implementation ''
}
```
#### Maven central

```
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```
#### Permissions
ByteTrack SDK默认包含了 [INTERNET](https://developer.android.com/reference/android/Manifest.permission.html#INTERNET) 权限，用来进行网络请求：

```
<uses-permission android:name="android.permission.INTERNET"/>
```
如果需要访问图片库或者文件信息，则需要包含 [READ_EXTERNAL_STORAGE](https://developer.android.com/reference/android/Manifest.permission.html#READ_EXTERNAL_STORAGE) 权限：

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```
#### Transitive Dependencies
ByteTrack Android SDK 依赖了 Gson、Coil、Okio、Okhttp和Retrofit最新版本，如果你的App依赖了这些库，它们应该与ByteTrack SDK使用的版本相同，当构建时有两个版本的库时，Gradle会自动选择较新的版本。这意味着如果您当前使用Okhttp 2.x,您的应用引用ByteTrack后会自动获取Okhttp 3.x.

### 第二步 - 初始化ByteTrack

首先你需要获取ByteTrack **APP_ID** 和 **APP_KEY**，这些信息可以在Web中台中获取，如下图所示：
![](http://gitlab.byteslink.com/vuepress/picgo/-/raw/master/pictures/2022/08/31_11_14_44_2FB3F4D4-F244-415b-9604-898ADBB1B053.png)

然后，在你的application class的`onCreate()`方法中初始化BytesTrack:

```
ByteTrack.instance.initMessager(this, "your app id", "your app key", "your user id")
```
**注意：**如果你没有自定义application,你需要先自定义一个，类似这样：

```
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ByteTrack.instance.initMessager(this, "your app id", "your app key", "your user id")
    }
}
```
然后你需要更新manifest的name信息:

```
<application
    android:name=".CustomApplication">
</application>
```

#### 重要提示
ByteTrack必须在application 中 `onCreate()` 方法中初始化，在其它任何一个地方初始化将导致ByteTrack无法正常运行，甚至可能导致应用程序崩溃。
### 第三步 - 创建用户
最后，您需要创建一个用户，如下所示：

```
ByteTrack.instance.userLogin("user id")
```
你也可以在初始化方法中设置用户信息，设置用户信息后就可以关联用户信息了。
#### 访客
当你没有设置过用户信息，此时使用ByteTrack后不会关联用户信息，这时清除手机应用数据或者应用卸载重装后，之前的活动记录将不会存在。
#### 退出登录
当需要退出当前用户账户时，需要调用退出方法：

```
ByteTrack.instance.userLogout()
```
### 其它
ByteTrack默认初始化后会在每个页面显示启动浮窗，如果你不需要的话，可以隐藏：

```
ByteTrack.instance.hideFloatView()
```
此时需要进入BytesTrack,可以通过如下方式：

```
ByteTrack.instance.enterBytesTrack()
```




