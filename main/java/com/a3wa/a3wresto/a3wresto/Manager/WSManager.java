package com.a3wa.a3wresto.a3wresto.Manager;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class WSManager {

    private WsListener wsListener;

    public WSManager(WsListener wsListener){
        this.wsListener  = wsListener;
    }

    public void sendRequest(final int idRequest, String target, Map<String, String> params){

        if(params == null){
            params = new HashMap<>();
        }

        String jsonString = new Gson().toJson(params);

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json");


        Fuel.post("http://51.15.254.4:9001"+target).body(jsonString, Charset.forName("UTF-8")).header(header).responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError error) {
                //do something when it is failure

                if(wsListener == null){
                    return;
                }

                wsListener.errorRequest(idRequest);
            }

            @Override
            public void success(Request request, Response response, String data) {
                //do something when it is successful
                if(wsListener == null){
                    return;
                }

                wsListener.successRequest(idRequest, data);
            }
        });

    }

}
