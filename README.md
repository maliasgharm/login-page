# PageLoginExample


[![](https://jitpack.io/v/maliasgharm/PageLoginExample.svg)](https://jitpack.io/#maliasgharm/PageLoginExample)

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
this
