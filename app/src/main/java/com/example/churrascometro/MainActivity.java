package com.example.churrascometro;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{

    private EditText adults;
    private EditText adultsWhoDoNotDrink;
    private EditText children;
    private EditText time;
    private TextView meatValue;
    private TextView beerValue;
    private TextView sodaValue;
    private Button buttonCalculate;
    private ImageView signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adults = findViewById(R.id.adults);
        adultsWhoDoNotDrink = findViewById(R.id.adultsWhoDoNotDrink);
        children = findViewById(R.id.children);
        time = findViewById(R.id.time);
        meatValue = findViewById(R.id.meatValue);
        beerValue = findViewById(R.id.beerValue);
        sodaValue = findViewById(R.id.sodaValue);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        signOut = findViewById(R.id.signOut);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

    }


    private void calculate()
    {
        if(validation())
        {
            try {

                double AdultsWhoDrink = parseDouble(adults.getText().toString());
                double AdultsWhoNotDrink = parseDouble(adultsWhoDoNotDrink.getText().toString());
                double Childrens =  parseDouble(children.getText().toString());
                double Time = parseDouble(time.getText().toString());
                double Adults = AdultsWhoDrink + AdultsWhoNotDrink;

                Double totalMeat = (meat(Time) * Adults + (meat(Time) / 2 * Childrens)) / 1000;
                Double totalBeer = (beer(Time) * AdultsWhoDrink) / 355;
                Double totalSoda = (soda(Time) * AdultsWhoNotDrink + (soda(Time) / 2 * Childrens)) / 2000;


                DecimalFormat fmt = new DecimalFormat("0.00");
                meatValue.setText(fmt.format(totalMeat));
                DecimalFormat fmt2 = new DecimalFormat("0");
                Double totalBeerFinal = Math.ceil(totalBeer);
                beerValue.setText(fmt2.format(totalBeerFinal));
                Double totalsodaFinal = Math.ceil(totalSoda);
                sodaValue.setText(fmt2.format(totalsodaFinal));

            }catch (NumberFormatException nfe)
            {
                Toast.makeText(this, "Informe valores validos", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int meat(double time)
    {
        if(time >= 6)
        {
            return 550;
        }
        else
        {
            return 400;
        }
    }

    private int beer(double time)
    {
        if(time >= 6)
        {
            return 2000;
        }
        else
        {
            return 1200;
        }
    }

    private int soda(double time)
    {
        if(time >= 6)
        {
            return 1500;
        }
        else
        {
            return 1000;
        }
    }

    private Boolean validation()
    {
        return (adults.getText().toString() != "" && adultsWhoDoNotDrink.getText().toString() != ""
                && children.getText().toString() != "" && time.getText().toString() != "" && time.getText().toString() != "0");
    }
}