package uz.shift.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LineColorPicker extends View {

	int[] colors = Palette.DEFAULT;

	private Paint paint;
	private Rect rect = new Rect();

	// indicate if nothing selected
	boolean isColorSelected = false;

	private int selectedColor = colors[0];

	private OnColorChangedListener onColorChanged;

	private int cellSize;

	public LineColorPicker(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint = new Paint();
		paint.setStyle(Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		rect.left = 0;
		rect.top = 0;
		rect.right = 0;
		rect.bottom = canvas.getHeight();

		// 8%
		int margin = Math.round(canvas.getHeight() * 0.08f);

		for (int i = 0; i < colors.length; i++) {

			paint.setColor(colors[i]);

			rect.left = rect.right;
			rect.right += cellSize;

			if (isColorSelected && colors[i] == selectedColor) {
				rect.top = 0;
				rect.bottom = canvas.getHeight();
			} else {
				rect.top = margin;
				rect.bottom = canvas.getHeight() - margin;
			}

			canvas.drawRect(rect, paint);
		}

	}

	private void onColorChanged(int color) {
		if (onColorChanged != null) {
			onColorChanged.onColorChanged(color);
		}
	}

	private boolean isClick = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int actionId = event.getAction();

		int newColor;

		switch (actionId) {
		case MotionEvent.ACTION_DOWN:
			isClick = true;
			break;
		case MotionEvent.ACTION_UP:
			newColor = getColorAtXY(event.getRawX(), event.getRawY());

			setSelectedColor(newColor);

			if (isClick) {
				performClick();
			}

			break;

		case MotionEvent.ACTION_MOVE:
			newColor = getColorAtXY(event.getRawX(), event.getRawY());

			setSelectedColor(newColor);

			break;
		case MotionEvent.ACTION_CANCEL:
			isClick = false;
			break;

		case MotionEvent.ACTION_OUTSIDE:
			isClick = false;
			break;

		default:
			break;
		}

		return true;
	}

	/**
	 * Return color at x,y coordinate of view.
	 */
	private int getColorAtXY(float x, float y) {

		// FIXME: colors.length == 0 -> devision by ZERO.
		int cellSize = Math.round(getWidth() / (colors.length * 1f));

		int left = 0;
		int right = 0;

		for (int i = 0; i < colors.length; i++) {
			left = right;
			right += cellSize;

			if (left <= x && right >= x) {
				return colors[i];
			}
		}

		return selectedColor;
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		// begin boilerplate code that allows parent classes to save state
		Parcelable superState = super.onSaveInstanceState();

		SavedState ss = new SavedState(superState);
		// end

		ss.selectedColor = this.selectedColor;
		ss.isColorSelected = this.isColorSelected;

		return ss;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		// begin boilerplate code so parent classes can restore state
		if (!(state instanceof SavedState)) {
			super.onRestoreInstanceState(state);
			return;
		}

		SavedState ss = (SavedState) state;
		super.onRestoreInstanceState(ss.getSuperState());
		// end

		this.selectedColor = ss.selectedColor;
		this.isColorSelected = ss.isColorSelected;
	}

	static class SavedState extends BaseSavedState {
		int selectedColor;
		boolean isColorSelected;

		SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			this.selectedColor = in.readInt();
			this.isColorSelected = in.readInt() == 1;
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeInt(this.selectedColor);
			out.writeInt(this.isColorSelected ? 1 : 0);
		}

		// required field that makes Parcelables from a Parcel
		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		cellSize = Math.round(getWidth() / (colors.length * 1f));
	}

	/**
	 * Return currently selected color.
	 */
	public int getColor() {
		return selectedColor;
	}

	/**
	 * Set selected color as color value from palette.
	 */
	public void setSelectedColor(int color) {

		// not from current palette
		if (!containsColor(colors, color)) {
			return;
		}

		// do we need to re-draw view?
		if (!isColorSelected || selectedColor != color) {
			this.selectedColor = color;

			isColorSelected = true;

			invalidate();

			onColorChanged(color);
		}
	}

	/**
	 * Set selected color as index from palete
	 */
	public void setSelectedColorPosition(int position) {
		setSelectedColor(colors[position]);
	}

	/**
	 * Set picker palette
	 */
	public void setColors(int[] colors) {
		// TODO: selected color can be NOT in set of colors
		// FIXME: colors can be null
		this.colors = colors;

		if (!containsColor(colors, selectedColor)) {
			selectedColor = colors[0];
		}

		cellSize = Math.round(getWidth() / (colors.length * 1f));

		invalidate();
	}

	/**
	 * Return current picker palete
	 */
	public int[] getColors() {
		return colors;
	}

	private boolean containsColor(int[] colors, int c) {
		for (int i = 0; i < colors.length; i++) {
			if (colors[i] == c)
				return true;

		}

		return false;
	}

	public void setOnColorChangedListener(OnColorChangedListener l) {
		this.onColorChanged = l;
	}
}
