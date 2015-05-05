package uz.shift.colorpicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected static final String TAG = "ShiftPicker";

	private LineColorPicker horizontalPicker, verticalPicker;

	private TextView tvColor;

	String[] pallete = new String[] { "#b8c847", "#67bb43", "#41b691",
			"#4182b6", "#4149b6", "#7641b6", "#b741a7", "#c54657", "#d1694a" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		horizontalPicker = (LineColorPicker) findViewById(R.id.picker);

		verticalPicker = (LineColorPicker) findViewById(R.id.picker2);

		tvColor = (TextView) findViewById(R.id.textViewColor);

		verticalPicker.setColors(generateColors(Color.BLUE, Color.RED, 10));

		// Create palette from HEX values
		int[] colors = new int[pallete.length];

		for (int i = 0; i < colors.length; i++) {
			colors[i] = Color.parseColor(pallete[i]);
		}

		// Set palette
		horizontalPicker.setColors(colors);

		// Set selected color [optional]
		horizontalPicker.setSelectedColor(colors[0]);

		// Get selected color
		int color = horizontalPicker.getColor();

		updateColor(color);

		OnColorChangedListener onChangeListener = new OnColorChangedListener() {

			@Override
			public void onColorChanged(int c) {
				Log.d(TAG, "Selectede color " + Integer.toHexString(c));

				updateColor(c);
			}
		};

		horizontalPicker.setOnColorChangedListener(onChangeListener);
		verticalPicker.setOnColorChangedListener(onChangeListener);

	}

	/**
	 * Generate palette of count colors
	 */
	private int[] generateColors(int from, int to, int count) {
		int[] palette = new int[count];

		float[] hsvFrom = new float[3];
		float[] hsvTo = new float[3];

		Color.colorToHSV(from, hsvFrom);
		Color.colorToHSV(to, hsvTo);

		float step = (hsvTo[0] - hsvFrom[0]) / count;

		for (int i = 0; i < count; i++) {
			palette[i] = Color.HSVToColor(hsvFrom);

			hsvFrom[0] += step;
		}

		return palette;
	}

	/**
	 * Display selected color
	 */
	private void updateColor(int color) {
		String hex = Integer.toHexString(color);

		hex = hex.toUpperCase();

		tvColor.setText(String.format("Selected color: #%s", hex));
	}
}
