# PageLoginExample


[![](https://jitpack.io/v/maliasgharm/PageLoginExample.svg)](https://jitpack.io/#maliasgharm/PageLoginExample)


You Must Decleare layout on Activity 

```JAVA
Login login = findViewById(R.id.loginpage);
```

For Create New Login Class :
``` JAVA
Login login = new Login(context);
```

For Set Type Input UserName : 
```JAVA
login.inputUsername(type as Int);
```

| Type | description |
| :-- | :-- |
| Login.TYPE_INNPUT_USERNAME_EMAIL | Set type Of Input equal Email| 
| Login.TYPE_INNPUT_USERNAME_MOBILE  | Set type Of Input equal Mobile |
| Login.TYPE_INNPUT_USERNAME_USERNAME   | Set type Of Input Any text except Email and Mobile |
| Login.TYPE_INNPUT_USERNAME_USERNAME_EMAIL   | Set type Of Input equal Any text and Email except Mobile  |
| Login.TYPE_INNPUT_USERNAME_USERNAME_MOBILE    | Set type Of Input equal Any text and Mobile except Email |
| Login.TYPE_INNPUT_USERNAME_MOBILE_EMAIL    | Set type Of Input equal Mobile and Email |
| Login.TYPE_INNPUT_USERNAME_MOBILE_EMAIL_USERNAME    | Set type Of Input equal Any text |



For Displayed Login Page : 
```JAVA
login.showLogin(type as Int);
```

For Displayed Register Page : 
```JAVA
login.showRegister(type as Int);
```


UserName type : 

| attr | description |
| :-- | :-- |
| none | show only login page | 
| with_skip | will be displayed login and skip login |




for Listening Login / register / skip clicked  write Below code on Activity and function OnCreated : 

```JAVA
        login.setOnLoginChangeListener(new Login.Companion.OnLoginChangeListener() {
            @Override
            public void onLogined(String username, String password) {
                Log.w("LoginActivity","onLogined : "+username+"_"+password);

            }

            @Override
            public void onRegistered(String username, String password) {
                Log.w("LoginActivity","onRegistered : "+username+"_"+password);
            }

            @Override
            public void onSkiped() {
                Log.w("LoginActivity","skiped");
            }
        });
```

for Draw layout file write this code on Below of code : 

```xml
    <org.noandish.library.loginpage.Login
        android:id="@+id/login"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:type_login_page="none"
        app:type_username_field="mobile_email"
        />
```

type_login_page : 

| attr | description |
| :-- | :-- |
| none | show only login page | 
| with_skip | will be displayed login and skip login |
