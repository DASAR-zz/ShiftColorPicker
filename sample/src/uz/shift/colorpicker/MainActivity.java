package uz.shift.colorpicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected static final String TAG = "ShiftPicker";

	private LineColorPicker colorPicker;

	private TextView tvColor;

	String[] pallete = new String[] { "#b8c847", "#67bb43", "#41b691",
			"#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		colorPicker = (LineColorPicker) findViewById(R.id.picker);
		tvColor = (TextView) findViewById(R.id.textViewColor);

		int[] colors = new int[pallete.length];

		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.parseColor(pallete[i]);
		}

		// Set palette
		colorPicker.setColors(colors);

		// Set selected color
		colorPicker.setSelectedColor(colors[0]);

		// Get selected color
		int color = colorPicker.getColor();

		updateColor(color);

		colorPicker.setOnColorChangedListener(new OnColorChangedListener() {

			@Override
			public void onColorChanged(int c) {
				Log.d(TAG, "Selectede color " + Integer.toHexString(c));

				updateColor(c);
			}
		});

	}

	private void updateColor(int color) {
		String hex = Integer.toHexString(color);

		hex = hex.toUpperCase();

		tvColor.setText(String.format("Selected color: #%s", hex));
	}
}
