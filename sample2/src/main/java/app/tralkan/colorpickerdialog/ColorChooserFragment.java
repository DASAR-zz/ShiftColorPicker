package app.tralkan.colorpickerdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uz.shift.colorpicker.LineColorPicker;


/**
 * A placeholder fragment containing a simple view.
 */
public class ColorChooserFragment extends DialogFragment {

    public static final String TAG = "ColorChooserFragment";
    private LineColorPicker colorPicker;
    private Context mContext;
    private DialogListener mCallback;

    public ColorChooserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();

        try {
            mCallback = (DialogListener) activity;
        } catch (ClassCastException e) {
            /** The activity does not implement the listener. */
            throw new ClassCastException(activity.toString() + " must implement "+ColorChooserFragment.DialogListener.class.getSimpleName());
        }
    }




    private void setupUI(View fragmentView) {
        colorPicker = (LineColorPicker) fragmentView.findViewById(R.id.picker);
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_color_chooser, null, false);
        setupUI(v);
        builder.setView(v);
        builder.setTitle(R.string.msg_select_color);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mCallback.onSelected(ColorChooserFragment.this);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return  builder.create();
    }




    public int getColor(){ return colorPicker.getColor(); }

    public interface DialogListener {
        void onSelected(ColorChooserFragment fragment);
    }

}
