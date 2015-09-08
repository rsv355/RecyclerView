package demopayment.krishna.com.recyclerview;

import android.app.Application;
import android.content.Context;


/**
 * Created by Android on 10-06-2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseHandler handler = new DatabaseHandler(getApplicationContext());
        try{
            handler.createDataBase();
        }catch(Exception e){

        }


    }



}
