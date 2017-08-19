package br.com.hisao.realmdb.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vinicius on 19/08/17.
 */

public class Person extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String mobile;

    public Person() {

    }

    public Person(long id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
