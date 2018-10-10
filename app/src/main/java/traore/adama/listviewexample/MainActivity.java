package traore.adama.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //region Properties
    private EditText edtName;
    private List<String> names;
    private ArrayAdapter<String> adapter;
    private ListView lstMain;
    //endregion

    //region Cycle de vie
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //valoriser les proprietés
        edtName = findViewById(R.id.edtName);
        lstMain = findViewById(R.id.lstMain);
        names = new ArrayList<>();

        //defini l'adapter (l'adpter c'est un pont entre la liste et la source de donnée -> la liste)
        //elle va permettre de mettre la donnée dans chaque cellule de la liste en piochant dans la
        //liste qu'on lui passe en parametre de son constructeur (ici on lui passe names)
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        //On lie la listview (le composant graphique) a l'adapter pour permettre à la listview de
        //s'afficher avec les données passés dans l'adapter
        lstMain.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //On fais l'evenement de click dans la fonction onStart() puisque ce n'est pas une valorisation
        //de propriété
        lstMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = names.get(position);
                Toast.makeText(MainActivity.this, "Name : "+name, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //endregion


    //region Click events (Les evenements de click peuvent etre fait comme ça aussi)
    public void addClicked(View view){
        //recuperation de la valeur du champs de texte
        String input = edtName.getText().toString();

        //Si le texte est vide ou null alors on quitte la fonction
        if(TextUtils.isEmpty(input))
            return;

        //ajoute le texte dans la liste
        names.add(input);

        //previens la listview que son contenu a été modifié pour qu'elle fasse un refresh
        adapter.notifyDataSetChanged();
    }

    public void clearClicked(View view){
        //vide la liste
        names.clear();

        //previens la listview que son contenu a été modifié pour qu'elle fasse un refresh
        adapter.notifyDataSetChanged();
    }
    //endregion
}
