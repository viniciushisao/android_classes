package br.com.hisao.realmdb.dbhelper;

import br.com.hisao.realmdb.model.Person;
import io.realm.Realm;

/**
 * Created by vinicius on 19/08/17.
 */

public class PersonHelper {

    public static long getCurrentLastId(Realm realm) {
        try {
            Number num = realm.where(Person.class).max("id");
            long nextID;
            if (num == null) {
                nextID = 1;
            } else {
                nextID = num.intValue();
            }
            return nextID;
        } catch (Exception e) {
            return 0;
        }
    }
}

