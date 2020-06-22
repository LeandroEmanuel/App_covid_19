package com.example.app_covid_19;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Fragment fragmentActual = null;
    private int menuActual = R.menu.menu_principal;
    private Menu menu;
    private Perfil perfil = null;

    public Perfil getPerfil() {
        return perfil;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void perfilAlterado(Perfil perfil){

        this.perfil = perfil;

        boolean mostraEdeitarEliminarPerfil = (perfil != null);

        menu.findItem(R.id.action_editar_perfil).setVisible(mostraEdeitarEliminarPerfil);
        menu.findItem(R.id.selecionar_para_eliminar_perfil).setVisible(mostraEdeitarEliminarPerfil);
    }

    public void setFragmentActual(Fragment fragmentActual){
        this.fragmentActual = fragmentActual;
    }

    public void setMenuActual(int menuActual){
        if(menuActual != this.menuActual){
            this.menuActual = menuActual;
            invalidateOptionsMenu();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(menuActual, menu);

        this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (menuActual == R.menu.menu_selecionar_perfil) {
            if(gereOpcoesMenuSelecionaPerfil(id)) return true;

        } else if (menuActual == R.menu.menu_inserir_perfil) {
            if(gereOpcoesMenuInserirPerfil(id)) return true;
        }else if (menuActual == R.menu.menu_alterar_perfil) {
            if(gereOpcoesMenuAlteraPerfil(id)) return true;
        }else if (menuActual == R.menu.menu_eliminar_perfil) {
            if(gereOpcoesMenuEliminarPerfil(id)) return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean gereOpcoesMenuEliminarPerfil(int id) {
        fragment_eliminar_perfil fragment_eliminar_perfil =(fragment_eliminar_perfil) fragmentActual;

        if(id == R.id.canselar_eliminar_perfil) {
            fragment_eliminar_perfil.cancelar();
            return true;
        } else if(id == R.id.action_eliminar_perfil){
            fragment_eliminar_perfil.eliminar();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuInserirPerfil(int id) {
        fragment_insere_dados_pessoais fragment_dados_pessoais = (fragment_insere_dados_pessoais) fragmentActual;
        if(id == R.id.action_guardar){
            fragment_dados_pessoais.guardaNovoPerfil();
            return true;
        } else if(id == R.id.canselar_eliminar_perfil){
            fragment_dados_pessoais.cancelarInserirPerfil();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuAlteraPerfil(int id) {
        fragment_altera_dados_pessoais fragment_altera_dados_pessoais =(com.example.app_covid_19.fragment_altera_dados_pessoais) fragmentActual;

        if(id == R.id.action_guardar){
            fragment_altera_dados_pessoais.guardaAlteraPerfil();
            return true;
        } else if(id == R.id.canselar_eliminar_perfil){
            fragment_altera_dados_pessoais.cancelarAlterarDadosPessoais();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuSelecionaPerfil(int id) {
        fragment_selecionar_perfil fragment_selecionar_perfil = (com.example.app_covid_19.fragment_selecionar_perfil) fragmentActual;

        if(id == R.id.action_novoPerfil){
            fragment_selecionar_perfil.novoPerfil();
            return true;
        }else if(id == R.id.action_editar_perfil){
            fragment_selecionar_perfil.editarPerfil();
            return true;
        } else if(id == R.id.selecionar_para_eliminar_perfil){
            fragment_selecionar_perfil.eliminarPerfil();
            return true;
        }
        return false;
    }
}
