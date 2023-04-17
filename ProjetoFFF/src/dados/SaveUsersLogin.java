package dados;

import negocio.beans.Usuario;

import java.io.Serializable;


public class SaveUsersLogin implements Serializable {

    String fileName;

    Usuario ultimoUsuario;

    public SaveUsersLogin(String fileName){
        this.fileName = fileName;
        Object listaElementos = RepositorioFileUtil.lerDoArquivo(this.fileName);
        if (listaElementos != null && listaElementos instanceof Usuario){
            this.ultimoUsuario = (Usuario) listaElementos;
        }
    }

    public Usuario getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(Usuario ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
        save();
    }


    public void save(){
        RepositorioFileUtil.salvarArquivo(ultimoUsuario, fileName);
    }
}
