package machado.maria.gabriela.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() { //quando o usuario clicar no botao, acontece o que esta descrito abaixo
            @Override
            public void onClick(View v) {
                EditText etEmail = findViewById(R.id.etEmail);
                String email = etEmail.getText().toString(); //obtendo email digitado pelo usuario

                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString(); //obtendo assunto digitado pelo usuario

                EditText etTexto = findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString(); //obtendo texto digitado pelo usuario

                Intent i = new Intent(Intent.ACTION_SENDTO); //criando uma intent implicita
                i.setData(Uri.parse("mailto:"));

                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails); //envia os enderecos de email junto com a intent
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); //envia o assunto junto com a intent
                i.putExtra(Intent.EXTRA_TEXT, texto); //envia o texto junto com a intent

                try{ //a intent é executada
                    startActivity(Intent.createChooser(i, "Escolha o App")); //aparece ao usuario um menu para escolher o app
                }
                catch (ActivityNotFoundException e) { //caso o try nao seja possivel, aparece a mensagem abaixo
                    Toast.makeText(MainActivity.this, "Não há nenhum App que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}