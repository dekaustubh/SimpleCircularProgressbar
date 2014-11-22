SimpleCircularProgressbar
=========================

A very basic circular progress bar. Just include it and use as you want.

## Features

1. Supports clockwise and anticlockwise roations
2. works well in scrollviews
3. Can set colors dynaliccaly and start position of progress

## How to use ?

1. Download library from repo
2. Add the dependency in your gradle file
```gradle
    compile project(':library')
```
3. in settings.gradle 
```include ':yourapp', ':library'```

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

### Supporting mathods 
``` SimpleCircularProgressbar bar = (SimpleCircularProgressbar) findViewById(R.id.view);
    bar.setProgress(35);
    bar.setMaxProgress(100);
    bar.setThickness(8);
    bar.setRotation(SimpleCircularProgressbar.ANTICLOCKWISE); // supports SimpleCircularProgressbar.ANTICLOCKWISE also
    bar.startFrom(SimpleCircularProgressbar.RIGHT); // Supports LEFT, TOP, BOTTOM also
    bar.setProgressColor(Color.RED);
    bar.setSecondaryColor(Color.YELLOW);
````

### known issues
``` right now does not support rotation changes ```

# Developer

Kaustubh Deshmukh (kaustubh@rainingclouds.com)
RainingClouds Technologies Pvt Ltd

# Licence 

The MIT License (MIT)

Copyright (c) 2014 Kaustubh Deshmukh

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

