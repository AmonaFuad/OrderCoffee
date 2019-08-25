package com.example.android.ordercoffee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayQuantity(int numberOfCoffee) {
        TextView coffeeTextView = (TextView) findViewById(R.id.quantity_text_view);
        coffeeTextView.setText("" + numberOfCoffee);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {
            /**  toast to display a error message on screen
             * when user try to order more than one hundred cups of coffee.
             */
            Toast toast = Toast.makeText(this, "you cant order more than one hundred cups of coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void dencrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            //  toast to display a error message on screen when user try to order less than one coffee.
            Toast toast = Toast.makeText(this, "you cant order less than one cup of coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void makeOrder(View view) {


        int price = 5;
        CheckBox hasChocolet = (CheckBox) findViewById(R.id.checkbox_Chocolate);
        boolean choclet = hasChocolet.isChecked();
        CheckBox haswippedCream = (CheckBox) findViewById(R.id.checkbox_wipedCream);
        boolean wipped = haswippedCream.isChecked();
        EditText nameEditText = (EditText) findViewById(R.id.Name_Edit_Text);
        String custmerName = nameEditText.getText().toString();
        if (choclet) {
            price = price + 1;
        }
        if (wipped) {
            price = price + 2;
        }
        price = price * quantity;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.coffeeOrderTo)+" " +custmerName );
        // when we send parameters we must follow the order as it declared in the method.
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary(price, wipped, choclet, custmerName) );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //to return the quantity to zero and make new order
        quantity=1;
        displayQuantity(quantity);
    }

    public String orderSummary(int price, boolean haswiped, boolean hascocolet, String name) {

        String string;
        string = getString(R.string.CustmerName)+": " + name;
        string += "\n"+getString(R.string.hasWippedCream)+" = " + haswiped;
        string += "\n"+getString(R.string.hasChocolate)+" = " + hascocolet;
        string+="\n"+getString(R.string.quantityApper)+" = "+quantity;
        string += "\n"+getString(R.string.TotalCost)+" = " + price;
        string += "\n" +getString(R.string.Thank_you)+" "+ name;
        return (string);
    }


}
