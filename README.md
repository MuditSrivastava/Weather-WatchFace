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
## Concepts Learned

- Understanding the fundamentals of Android Wear.
- Designing for multiple watch form factors.
- Communicating between a mobile device and a wearable device.

## Screenshots

<img width="30%" src="https://github.com/MuditSri-2908/Go-Ubiquitous/blob/master/Screenshots/device-2016-09-06-012957.png"/>
<img width="40%" src="https://github.com/MuditSri-2908/Go-Ubiquitous/blob/master/Screenshots/device-2016-09-06-013714.png"/>

<img width="30%" src="https://github.com/MuditSri-2908/Go-Ubiquitous/blob/master/Screenshots/device-2016-09-06-012811.png"/>


## General Project Guideline
App conforms to common standards found in the [Android Nanodegree General Project Guidelines](http://udacity.github.io/android-nanodegree-guidelines/core.html)

## License

```
Copyright 2016 Mudit Srivastava

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
