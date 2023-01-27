package br.com.ada.crud.controller.arquivo;

/*
E - Element (usado exclusivamente pelo Java Collections Framework)
K - Key
N - Number
T - Type
V - Value
S,U,V etc. - 2nd, 3rd, 4th types
*/
public interface EscritorArquivo<T> {

    void escrever(T object, String arquivo);

    T apagar(String arquivo);

}
