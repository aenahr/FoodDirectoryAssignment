package food.csulb.fooddir;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;


public class FoodDetail extends DialogFragment {

    String foodName ="";
    String foodDescription = "";
    int imgID = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_detail, container, false);

        TextView foodTitle = (TextView) rootView.findViewById(R.id.title);
        ImageView imageV = (ImageView) rootView.findViewById(R.id.image);
        TextView des = (TextView) rootView.findViewById(R.id.description);

        foodTitle.setText(foodName);
        imageV.setImageResource(imgID);
        des.setText(foodDescription);

        Button dismiss = (Button) rootView.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootView;
    }

    // method to make the dialog fullscreen
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public void getInformation(String name, String description, int img){
        foodName = name;
        foodDescription = description;
        imgID = img;



    }


}
