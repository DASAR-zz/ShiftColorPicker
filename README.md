#ShiftColorPicker

![Alt text](/screenshot.png?raw=true)

#Usage

Add view to your activity's layout
```xml
    <uz.shift.colorpicker.LineColorPicker
        android:id="@+id/picker"
        android:layout_width="match_parent"
        android:layout_height="60dp" />
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

#Downlaod
[Releases](https://github.com/DASAR/ShiftColorPicker/releases)

#TODO
* Vertical version
* Expand/colapse animations
* More color pickers in deferent styles

#LICENSE

The MIT License (MIT)

Copyright (c) 2015 Bogdasarov Bogdan
