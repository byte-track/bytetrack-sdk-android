# 开始使用

&nbsp;&nbsp;导入 ByteTrack SDK到您的Android app项目中，ByteTrack支持Android API 21及以上，建议使用最新可用的Android Sdk版本
<br/>
<br/>

## 第一步 - 导入ByteTrack SDK

<br/>

### 导入 SDK

&nbsp;&nbsp;在您的app `build.gradle`文件中添加如下依赖（我们推荐您使用最新发布的版本，具体版本信息请参考：[sdk](https://github.com/byte-track/bytetrack-sdk-android/tags)）：

```
dependencies {
    implementation 'io.github.byte-track:bytetrack-sdk-android:1.0.1'
}
```

<br/>

### Maven central

&nbsp;&nbsp;ByteTrack SDK 托管在 maven central，您需要将如下信息添加到build.gradle文件中

```
allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven { url "https://www.jitpack.io" }
    }
}
```

<br/>

<br/>

### DataBinding

&nbsp;&nbsp;在app build module下引用data binding

```
android {
    ...
    buildFeatures {
        dataBinding = true
    }
}
```

<br/>

### Permissions

&nbsp;&nbsp;ByteTrack SDK默认包含了 [INTERNET](https://developer.android.com/reference/android/Manifest.permission.html#INTERNET) 权限，用来进行网络请求：

```
<uses-permission android:name="android.permission.INTERNET"/>
```

&nbsp;&nbsp;如果需要访问图片库或者文件信息，则需要包含 [READ_EXTERNAL_STORAGE](https://developer.android.com/reference/android/Manifest.permission.html#READ_EXTERNAL_STORAGE) 权限：

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

<br/>

### Transitive Dependencies

&nbsp;&nbsp;ByteTrack Android SDK 依赖了 Gson、Coil、Okio、Okhttp和Retrofit最新版本，如果您的App依赖了这些库，它们应该与ByteTrack SDK使用的版本相同，当构建时有两个版本的库时，Gradle会自动选择较新的版本。这意味着如果您当前使用Okhttp 2.x,您的应用引用ByteTrack后会自动获取Okhttp 3.x.
<br/>
<br/>

## 第二步 - 初始化ByteTrack

<br/>

&nbsp;&nbsp;首先您需要获取ByteTrack的 **APP_ID** 和 **APP_KEY**，这些信息可以登陆商户中台中，从`设置->信使设置->api管理`中获取，如下图所示：
![](http://gitlab.byteslink.com/vuepress/picgo/-/raw/master/pictures/2022/08/31_11_14_44_2FB3F4D4-F244-415b-9604-898ADBB1B053.png)

&nbsp;&nbsp;然后，在您的application class的`onCreate()`方法中初始化BytesTrack:

```
ByteTrack.instance.initMessager(this, "your app id", "your app key", "your user id")
```

<br/>

**注意：** 如果您没有自定义application,您需要先自定义一个，类似这样：

```
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ByteTrack.instance.initMessager(this, "your app id", "your app key", "your user id")
    }
}
```

- `your app id：`表示的是您在ByteTrack注册商户的应用id；
- `your app key：`表示的是您在ByteTrack注册商户的应用密钥；
- `your user id：`表示的是用户在您自己业务系统中，的唯一标识信息，比如：用户名 或者 用户id
  - 如果用户作为游客（`游客`代表用户未登陆您的业务系统），`your user id`参数可以不填；
  - 如果用户作为访客(`访客`代表用户登陆了您的业务系统，在您的业务系统中具备唯一身份标识)，`your user id`参数是用户在您业务系统中具备的唯一身份标识信息；
    <br/>

&nbsp;&nbsp;然后你需要更新manifest的name信息:

```
<application
    android:name=".CustomApplication">
</application>
```

<br/>

### 重要提示:

&nbsp;&nbsp;ByteTrack必须在application 中 `onCreate()` 方法中初始化，在其它任何一个地方初始化将导致ByteTrack无法正常运行，甚至可能导致应用程序崩溃。
<br/>
<br/>

## 第三步 - 创建用户

&nbsp;&nbsp;在ByteTrack的中，将您业务系统中的访问者分为两大类：

- 游客：即访问您业务系统的用户，处于未登陆的状态（比如：未登录 或者 没有账号），无法获取到该访问者在您业务系统中的唯一身份识别信息（账号id 或者 账号名）；
- 访客：即访问您业务系统的用户，处于登陆的状态，您的业务系统可以获取到该访问者的唯一身份识别信息；
  <br/>

&nbsp;&nbsp;针对访客，您可以在该访客登陆成功（或者您的业务系统可以获取该访问者唯一身份信息的时候）时，为该用户在ByteTrack系统中创建一个身份信息，如下所示：

```
ByteTrack.instance.userLogin("your user id")
```

&nbsp;&nbsp;针对游客，则无需为该用户在ByteTrack系统中创建身份信息。

**注意：**

- 你也可以在上述的初始化方法中设置用户信息，设置用户信息后就可以关联用户信息了。
- 当您业务系统中的用户，以游客的身份使用ByteTrack时，此时使用ByteTrack后不会关联用户信息，这时清除手机应用数据或者应用卸载重装后，之前的活动记录将不会存在。
  <br/>
  <br/>

## 第四步 - 退出登录

&nbsp;&nbsp;当需要退出当前用户账户时，需要调用退出方法：

```
ByteTrack.instance.userLogout()
```

&nbsp;&nbsp;用户在您的业务系统中退出登陆之后，任然能够通过游客的身份继续享受ByteTrack的服务。
<br/>
<br/>

## 第五步 - 浮窗控制

&nbsp;&nbsp;ByteTrack默认初始化后会在每个页面显示启动浮窗，如果你不需要的话，可以隐藏：

```
ByteTrack.instance.hideFloatView()
```

&nbsp;&nbsp;此时需要进入BytesTrack,可以通过如下方式：

```
ByteTrack.instance.enterBytesTrack()
```

&nbsp;&nbsp;通过上述步骤，您既可以启动ByteTrack的服务！

<br/>
<br/>

# 技术支持

&nbsp;&nbsp;除此之前，我们通过[使用指南](https://www.yuque.com/books/share/ad2cd6ce-faee-4c99-b6cb-4dc44564952e/zqpon8)，为您提供了更加详细的使用说明。

&nbsp;&nbsp;如果您遇到使用问题，或者你想咨询有经验的开发者，可以加入我们的技术交流群：

- QQ交流群：530947468

