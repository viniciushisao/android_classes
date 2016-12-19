package br.com.hisao.mysqlitesample.MySql;

/**
 * Created by vinicius
 */

public class Person {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return cpf;
    }

    public void setTitle(String cpf) {
        this.cpf = cpf;
    }

    public String getAuthor() {
        return name;
    }

    public void setAuthor(String name) {
        this.name = name;
    }

    private String cpf;
    private String name;

    public Person(){}

    public Person(String cpf, String name) {
        super();
        this.cpf = cpf;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", cpf=" + cpf + ", name=" + name
                + "]";
    }
}

