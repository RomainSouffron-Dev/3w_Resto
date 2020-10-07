package com.a3wa.a3wresto.a3wresto.Manager;

public interface WsListener {
    void errorRequest(int idRequest);
    void successRequest(int idRequest, String data);
}
