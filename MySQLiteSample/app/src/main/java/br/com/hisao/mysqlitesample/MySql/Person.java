package br.com.hisao.mysqlitesample.MySql;

/**
 * Created by vinicius
 */

public class Person {

    private int id;
    private String cpf;
    private String name;


    public Person(String cpf, String name) {
        super();
        this.cpf = cpf;
        this.name = name;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", cpf=" + cpf + ", name=" + name
                + "]";
    }
}

