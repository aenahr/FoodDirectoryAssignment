package food.csulb.fooddir;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //initialize
    ListView simpleListView;
    String[] foodName = {"Taco", "Fries", "Burger", "Milkshake", "Burrito"};
    int[] thumbImg={R.drawable.thumb0,R.drawable.thumb1,R.drawable.thumb2,R.drawable.thumb3,R.drawable.thumb4};//animal images array
    int[] foodImg={R.drawable.food0,R.drawable.food1,R.drawable.food2,R.drawable.food3,R.drawable.food4};//animal images array
    String[] foodDescription = {"Tacos are crisp-fried corn tortillas filled with seasoned ground beef, cheese, lettuce, and sometimes tomato, onion, salsa, sour cream, and avocado or guacamole.",
            "French fries are served hot, either soft or crispy, and are generally eaten as part of lunch or dinner or by themselves as a snack.",
            "A Burger is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun.",
            "A milkshake is a sweet, cold beverage which is usually made from milk, ice cream, or iced milk, and flavorings or sweeteners such as butterscotch or caramel sauce.",
            "Burrito fillings may include a combination of ingredients such as Mexican-style rice or plain rice, beans or refried beans, lettuce, salsa, meat, guacamole or cheese."};

    boolean show = false;
    public View getView(){
        return simpleListView;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleListView=(ListView)findViewById(R.id.simpleListView);

        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        for (int i=0;i<foodName.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
            hashMap.put("name",foodName[i]);
            hashMap.put("image",thumbImg[i]+"");
            arrayList.add(hashMap);//add the hashmap into arrayList
        }
        String[] from={"name","image"};//string array
        int[] to={R.id.textView,R.id.imageView};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.food_list,from,to);//Create object and set the parameters for simpleAdapter
        simpleListView.setAdapter(simpleAdapter);

        //perform listView item click event

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                show = false;
                //check if Burrito
                if(foodName[i].equals("Burrito")){ //alert user
                    createAndShowAlertDialog();
                }
                else{
                    show = true;
                }

                if(show == true) {
                    // creating Dialog fragment
                    FragmentManager fm = getFragmentManager();
                    FoodDetail dialogFragment = new FoodDetail();
                    dialogFragment.show(getSupportFragmentManager(), "Sample");

                    dialogFragment.getInformation(foodName[i], foodDescription[i], foodImg[i]);
                }
            }
        });

    }

    private void createAndShowAlertDialog() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        FragmentManager fm = getFragmentManager();
                        FoodDetail dialogFragment = new FoodDetail();
                        dialogFragment.show(getSupportFragmentManager(), "Sample");

                        dialogFragment.getInformation(foodName[4], foodDescription[4], foodImg[4]);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        show = false;
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("THIS  PRODUCT IS SPICY! Would you still like to see?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.info:
            //add the function to perform here
            startActivity(new Intent(MainActivity.this, FoodInfo.class));
            return(true);
        case R.id.uni:
            //add the function to perform here
            Uri packageURI = Uri.parse("package:food.csulb.fooddir");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(uninstallIntent);
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }


}
