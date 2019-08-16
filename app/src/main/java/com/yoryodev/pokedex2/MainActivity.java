package com.yoryodev.pokedex2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int MAXPOKEMON = 807;
    TextView pokeNombre, pokeId, pokeAltura;
    Button pokeSearch, padLeft, padRight;
    EditText pokeRequest;
    RequestQueue requestQueue;
    ImageView pokeSprite;


    String request;
    int padnum;
    Pokemon pokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pokeNombre = (TextView) findViewById(R.id.txt_nombre);
        pokeId = (TextView) findViewById(R.id.txt_id);
        pokeAltura = (TextView) findViewById(R.id.txt_altura);
        pokeSprite = (ImageView) findViewById(R.id.PokeSprite);


        pokeSearch = (Button) findViewById(R.id.but_pokeSearch);
        pokeRequest = (EditText) findViewById(R.id.edittx_pokeSearch);
        padLeft = (Button) findViewById(R.id.Pad_left);
        padRight = (Button) findViewById(R.id.Pad_right);


        pokeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarPokemon();
            }
        });

        padRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pad right", "onClick: " + pokemon.getId());
                if(pokemon.getId() == null){
                    return;
                }else{
                    padnum = Integer.parseInt(pokemon.getId())+1;
                    if(padnum > MAXPOKEMON){
                        padnum =1;
                    }
                }
                BuscarPokemon();
                padnum = 0;

            }
        });

        padLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokemon.getId() == null){
                    return;
                }else{
                    padnum = Integer.parseInt(pokemon.getId())-1;
                    if (padnum <= 0 ){
                        padnum = MAXPOKEMON;
                    }
                }
                BuscarPokemon();
                padnum = 0;


            }
        });
        // Instantiate the RequestQueue with the cache and network.
        requestQueue = Volley.newRequestQueue(this);

// Start the queue
        requestQueue.start();


    }

    private void BuscarPokemon() {

        if(pokeRequest.getText() == null || pokeRequest.getText().length() == 0){
            return;
        }

        if(padnum > 0 ){
            request = String.valueOf(padnum);
        }else{
            request = pokeRequest.getText().toString();
        }


        // Creating volley request obj
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, getString(R.string.Pokeapi_pokemon)+request+"/", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArmarPokedex(response);
                     //   textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    private void ArmarPokedex(JSONObject response) {


        //Instancio mi pokemon y extraigo todos los datos de la API.
        pokemon = new Pokemon();
        JSONArray lista = null;
        JSONObject sprites = null;


        try {

            pokemon.setId(response.getString("id"));
            pokemon.setName(response.getString("name"));
            pokemon.setHeight(response.getString("height"));
            pokemon.setWeight(response.getString("weight"));


            sprites = response.getJSONObject("sprites");

            Log.d("json", "ArmarPokedex: ç"+ sprites.getString("front_default"));

            Picasso.get().load(sprites.getString("front_default")).into(pokeSprite);
            pokeSprite.setBackgroundColor(getResources().getColor(R.color.colorBlack));



        } catch (JSONException e) {
            e.printStackTrace();
        }


        //una vez extraido los datos, los formateo para que aparezcan en el Pokedex



        pokeId.setText(pokemon.getId());
        pokeNombre.setText(pokemon.getName());
        pokeAltura.setText(pokemon.getHeight()+ "Kg");

    }
}
