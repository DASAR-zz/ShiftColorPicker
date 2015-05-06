##Simple vertical and horizontal color picker

![Alt text](/screenshot.png?raw=true)

##Usage

Grab project from this repository
(Maven/Gradle support coming soon)

Add view to your activity's layout and specify orientation:
```xml
   <uz.shift.colorpicker.LineColorPicker
        xmlns:app="http://schemas.android.com/apk/res/uz.shift.colorpicker"
        android:id="@+id/picker"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        app:orientation="horizontal" />
```

Setup view
```java
colorPicker = (LineColorPicker) findViewById(R.id.picker);

// set color palette
colorPicker.setColors(new int[] {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW});

// set selected color [optional]
colorPicker.setSelectedColor(Color.RED);

// set on change listener
colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
			@Override
			public void onColorChanged(int c) {
				Log.d(TAG, "Selectede color " + Integer.toHexString(c));
			}
		});

// get selected color
int color = colorPicker.getColor();
```

##Downlaod
[Releases](https://github.com/DASAR/ShiftColorPicker/releases)
or grab latest version from repository.

##Requirements

Android 2.3 (Gingerbread) and later.

##Todo
* Maven/Gradle support
* Expand/collapse animations
* More color pickers in different styles

##License

The MIT License (MIT)

Copyright (c) 2015 Bogdasarov Bogdan