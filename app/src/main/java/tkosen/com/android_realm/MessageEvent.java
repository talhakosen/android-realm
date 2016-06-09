package tkosen.com.android_realm;

import io.realm.RealmResults;

/**
 * Created by tctkosen on 09/06/16.
 */
public class MessageEvent {
    private RealmResults<Country> country;

    public MessageEvent(RealmResults<Country>  country) {
        this.country = country;
    }

    public RealmResults<Country>  getCountry() {
        return country;
    }

    public void setCountry(RealmResults<Country>  country) {
        this.country = country;
    }
}
