SimpleCircularProgressbar
=========================

A very basic circular progress bar. Just include it and use as you want.

## Features

1. Supports clockwise and anticlockwise rotations
2. works well in scrollviews
3. Can set colors dynamically and start position of progress

## How to use ?

 Add the repository
```gradle
    maven {
        url 'https://github.com/Kaustubh-Deshmukh/progressbar_repo/raw/master'
    }
```
Add the dependency
```gradle
    compile 'SimpleCircularProgressbar:library:1.1'
```

### Code
Example code for getting Simple circular progress bar
```xml
    <com.kd.progressbar.SimpleCircularProgressbar
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        custom:thickness="10"
        custom:progress="17"
        custom:rotation="anticlockwise"
        custom:progressColor="#FF00FF"
        android:layout_width="200dp"
        android:layout_height="200dp"/>
```

# Developer

Kaustubh Deshmukh (kaustubh@rainingclouds.com)
RainingClouds Technologies Pvt Ltd

# Licence 

The MIT License (MIT)

Copyright (c) 2014 Kaustubh Deshmukh

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

