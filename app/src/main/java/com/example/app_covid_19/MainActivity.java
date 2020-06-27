package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable{

    private Fragment fragmentActual = null;
    private int menuActual = R.menu.menu_principal;
    private Menu menu;
    private Perfil perfil = null;
    private Registo registo = null;
    private Teste teste = null;

    /*static final String MENU_ATUAL = "menuActual";
    static final String PERFIL = "perfil";
    static final String REGISTO = "registo";
    static final String TESTE = "teste";
    private static final String FRAGMENTO_ATUAL = "fragmentActual";*/

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {//não funciona
        /*saveInstanceState.putSerializable(FRAGMENTO_ATUAL, (Serializable) fragmentActual);
        saveInstanceState.putInt(MENU_ATUAL,menuActual);
        saveInstanceState.putSerializable(PERFIL, perfil);
        saveInstanceState.putSerializable(REGISTO, registo);
        saveInstanceState.putSerializable(TESTE, teste);*/

        super.onSaveInstanceState(saveInstanceState);
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Registo getRegisto() {
        return registo;
    }

    public Teste getTest() {
    return teste;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*if (savedInstanceState != null){
            savedInstanceState.putSerializable(FRAGMENTO_ATUAL, (Serializable) fragmentActual);
            menuActual = savedInstanceState.getInt(MENU_ATUAL);
            savedInstanceState.putSerializable(PERFIL, perfil);
            savedInstanceState.putSerializable(REGISTO, registo);
            savedInstanceState.putSerializable(TESTE, teste);
        }else{
            fragmentActual = null;
            menuActual = R.menu.menu_principal;
            perfil = null;
            registo = null;
            teste = null;

        }*/

    }

    public void perfilAlterado(Perfil perfil){

        this.perfil = perfil;

        boolean mostraEdeitarEliminarMaisInfoPerfil = (perfil != null);

        menu.findItem(R.id.action_editar_perfil).setVisible(mostraEdeitarEliminarMaisInfoPerfil);
        menu.findItem(R.id.selecionar_para_eliminar_perfil).setVisible(mostraEdeitarEliminarMaisInfoPerfil);
        menu.findItem(R.id.action_mais_info).setVisible(mostraEdeitarEliminarMaisInfoPerfil);
    }

    public void registoAlterado(Registo registo){

        this.registo = registo;

        boolean mostraEliminarRegisto = (registo != null);
        menu.findItem(R.id.elimina_registo).setVisible(mostraEliminarRegisto);

    }

    public void testeAlterado(Teste teste){

        this.teste = teste;

        boolean mostraEliminarTeste = (teste != null);
        menu.findItem(R.id.elimina_teste).setVisible(mostraEliminarTeste);
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
        if (menuActual == R.menu.menu_principal) {
            if(gereOpcoesMenuPrincipal(id)) return true;
        }
         else if (menuActual == R.menu.menu_selecionar_perfil) {
            if(gereOpcoesMenuSelecionaPerfil(id)) return true;
        } else if (menuActual == R.menu.menu_inserir_perfil) {
            if(gereOpcoesMenuInserirPerfil(id)) return true;
        }else if (menuActual == R.menu.menu_alterar_perfil) {
            if(gereOpcoesMenuAlteraPerfil(id)) return true;
        }else if (menuActual == R.menu.menu_eliminar_perfil) {
            if(gereOpcoesMenuEliminarPerfil(id)) return true;
        }else if (menuActual == R.menu.menu_inserir_registo_diario) {
            if(gereOpcoesMenuInsereRegisto(id)) return true;
        }else if (menuActual == R.menu.menu_eliminar_registo) {
            if(gereOpcoesMenuEliminarRegisto(id)) return true;
        }else if (menuActual == R.menu.menu_inserir_teste) {
            if(gereOpcoesMenuInserirTeste(id)) return true;
        }else if (menuActual == R.menu.menu_eliminar_teste) {
            if(gereOpcoesMenuEliminarTeste(id)) return true;
        }else if (menuActual == R.menu.menu_historico_registos) {
            if(gereOpcoesMenuTabelaRegisto(id)) return true;
        }else if (menuActual == R.menu.menu_tabela_testes) {
            if(gereOpcoesMenuTabelaTeste(id)) return true;
        }else if (menuActual == R.menu.menu_vazio) {
             return true;
        }else if (menuActual == R.menu.menu_voltar) {
            if(gereOpcoesMenuvoltar(id)) return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private boolean gereOpcoesMenuvoltar(int id) {
        fragment_editar_menu_perfis fragment_editar_menu_perfis = (fragment_editar_menu_perfis) fragmentActual;
        if (id == R.id.action_voltar_menu_anterior) {
            fragment_editar_menu_perfis.selecionar_perfil();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuTabelaTeste(int id) {
        fragment_tabela_resultado_testes fragmentTabelaResultadoTestes = (fragment_tabela_resultado_testes) fragmentActual;
        if (id == R.id.elimina_teste) {
            fragmentTabelaResultadoTestes.EliminarTeste();
            return true;
        } else if (id == R.id.reverteTeste) {
            fragmentTabelaResultadoTestes.historicoTestes();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuTabelaRegisto(int id) {
        fragment_tabela_registos_diarios fragment_tabela_registos_diarios = (com.example.app_covid_19.fragment_tabela_registos_diarios) fragmentActual;
        if (id == R.id.elimina_registo) {
            fragment_tabela_registos_diarios.EliminarRegisto();
            return true;
        } else if (id == R.id.reverte) {
            fragment_tabela_registos_diarios.historico();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuPrincipal(int id) {
        fragment_menu_principal fragment_menu_principal = (com.example.app_covid_19.fragment_menu_principal)fragmentActual;

        if(id == R.id.go_conf) {
            fragment_menu_principal.configuracoes();
            return true;
        } else if(id == R.id.go_info){
            fragment_menu_principal.sobre();
            return true;
        }

        return false;
    }

    private boolean gereOpcoesMenuEliminarTeste(int id) {
        fragment_eliminar_testes fragment_eliminar_testes = (com.example.app_covid_19.fragment_eliminar_testes) fragmentActual;
        if(id == R.id.canselar_eliminar_teste) {
            fragment_eliminar_testes.cancelarEliminarTeste();
            return true;
        } else if(id == R.id.action_eliminar_teste){
            fragment_eliminar_testes.eliminarTeste();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuInserirTeste(int id) {
        fragment_inserir_teste fragment_inserir_teste = (fragment_inserir_teste) fragmentActual;

        if(id == R.id.action_guardar_teste){
            fragment_inserir_teste.guardarNovoTeste();
            return true;
        } else if(id == R.id.action_cancelar_testes){
            fragment_inserir_teste.cancelarInserirTeste();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuEliminarRegisto(int id) {
        fragment_elimina_registo fragment_elimina_registo = (fragment_elimina_registo) fragmentActual;
        if (id == R.id.action_eliminar_registo) {
            fragment_elimina_registo.eliminarRegisto();
            return true;
        } else if (id == R.id.canselar_eliminar_registo) {
            fragment_elimina_registo.cancelarEliminarRegisto();
            return true;
        }
        return false;
    }

    private boolean gereOpcoesMenuInsereRegisto(int id) {
        fragment_insere_registo_diario fragment_insere_registo_diario = (fragment_insere_registo_diario) fragmentActual;

        if(id == R.id.action_guardar_registo){
            fragment_insere_registo_diario.guardar_novo_registo();
            return true;
        } else if(id == R.id.action_cancelar_registo){
            fragment_insere_registo_diario.cancelarRegistoDiario();
            return true;
        }
        return false;
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
        fragment_inserir_perfil fragment_dados_pessoais = (fragment_inserir_perfil) fragmentActual;
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
        fragment_alterar_perfil fragment_altera_dados_pessoais =(fragment_alterar_perfil) fragmentActual;

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
        }else if(id == R.id.action_mais_info){
            fragment_selecionar_perfil.maisInformacao();
            return true;
        }else if(id == R.id.menu_principal){
            fragment_selecionar_perfil.voltarMenuPrincipal();
            return true;
        }
        return false;
    }

}
