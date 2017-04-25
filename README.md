# Sherlock
[![Build Status](https://travis-ci.org/ajitsing/Sherlock.svg?branch=master)](https://travis-ci.org/ajitsing/Sherlock)
[![LICENSE](https://img.shields.io/badge/license-Apache-blue.svg)](https://github.com/ajitsing/Sherlock/blob/master/LICENSE.txt)

Sherlock reports any crash that occurres in your application as a notification. You just need to initialize Sherlock at the start of your application and it will take care of the rest.

## Motivation
The motivation behind creating Sherlock is to make the life of Developer and tester easier. Whenever a tester is testing
the app and he/she encounters a crash, most of the time they don't have enough details in their hand to enable developer to
start fixing the crash immediately. Now with the help of Sherlock, any tester will have enough info to report the crash.


![Alt text](https://github.com/ajitsing/ScreenShots/blob/master/sherlock/sherlock_demo.gif)

## Installation
```groovy
android {
    dataBinding {
      enabled = true
    }
}

compile('com.github.ajitsing:sherlock:1.0.0@aar') {
    transitive = true
}
```

Sherlock usages data binding, so make sure that you have enabled data binding for your app.

## Usage
To start using Sherlock just add the below line in the ```onCreate()``` method of Application class.

```java
public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Sherlock.init(this); //Intilializing Sherlock
  }
}
```

Once you add ```Sherlock.init(this)``` Sherlock will take care of reporting all the crashes to you.

### See All Crashes
You can view all the crashes by starting the ```CrashListActivity```.

### Get All Crashes Captured By Sherlock
Sherlock also provides you a way to get all the crashes which are reported by Sherlock in form of java object. You can get them
using below line of code.
```java
Sherlock.getInstance().getAllCrashes()
```
Before accessing ```getInstance()``` make sure that you have initialized Sherlock, otherwise it will throw ```SherlockNotInitializedException```.

### App Info
By default Sherlock only captures the version of your application and shows that as part of crash details. If you want to
provide some extra details regarding app, you can do so by providing an AppInfoProvider to the Sherlock.

```java
Sherlock.setAppInfoProvider(new AppInfoProvider() {
  @Override
  public AppInfo getAppInfo() {
    return new AppInfo.Builder()
               .with("Version", "2.21") //You can get the actual version using "AppInfoUtil.getAppVersion(context)"
               .with("BuildNumber", "221B")
               .build();
  }
});
```

## Contributing
You can contribute to Sherlock by forking the repo and creating pull requests. You can also contribute by reporting bugs/issues.
If you want to see a new feature in Sherlock, just add that as an issue with enough details.

LICENSE
-------

```LICENSE
Copyright (C) 2017 Ajit Singh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
