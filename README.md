# PageLoginExample


[![](https://jitpack.io/v/maliasgharm/PageLoginExample.svg)](https://jitpack.io/#maliasgharm/PageLoginExample)

for Listener Login or register or skip clicked write above code  : 

```kotlin
  Login login = findViewById(R.id.loginpage);
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

for layout file write this code : 

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
| none | If set none , show page with only login page | 
| with_skip | If set to with_skip will be displayed on page login |
