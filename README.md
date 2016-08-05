# Go Ubiquitous
A wearable watch face for sunshine app to run on Android Wear Devices

Works on both Round and Square face watches

Displays High and Low temperature on Watch face 

Also displays a graphic summarizes the day’s weather 

Created as a part of [Udacity Android Developer Nanodegree](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801)

## API used
Open Weather Map API 

__NOTE:__ Add your API key to ``` Buil.gradle ``` file as specified below in order to run the app.
```
 
  buildTypes.each {
        it.buildConfigField 'String', 'OPEN_WEATHER_MAP_API_KEY', paste_key_here
    }
    
```