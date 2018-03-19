package com.example.user.parsejsonwithretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.parsejsonwithretrofit.model.AddressParameter;
import com.example.user.parsejsonwithretrofit.model.Device;
import com.example.user.parsejsonwithretrofit.model.ParameterObject;
import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.tavendo.autobahn.WebSocket;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketOptions;

public class MainActivity extends AppCompatActivity {

    private Button btnConnect;
    private Button btnDisConnect;

    private WebSocketConnection mWebSocketConnection = new WebSocketConnection();

    private static List<ParameterObject> parameterObjectList;
    private static List<AddressParameter> addressParameterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initOnClick();

        parameterObjectList = new ArrayList<>();
        addressParameterList = new ArrayList<>();

        addressParameterList.add(new AddressParameter("2000","float",4));
        parameterObjectList.add(new ParameterObject("0.0.0.254",addressParameterList));
        addressParameterList.add(new AddressParameter("2002","float",4));
        parameterObjectList.add(new ParameterObject("0.0.0.254",addressParameterList));
        addressParameterList.add(new AddressParameter("2004","float",4));
        parameterObjectList.add(new ParameterObject("0.0.0.254",addressParameterList));
        addressParameterList.add(new AddressParameter("2006","float",4));
        parameterObjectList.add(new ParameterObject("0.0.0.254",addressParameterList));
        addressParameterList.add(new AddressParameter("2007","float",4));
        parameterObjectList.add(new ParameterObject("0.0.0.254",addressParameterList));
        addressParameterList.add(new AddressParameter("2008","float",4));
        parameterObjectList.add(new ParameterObject("0.0.0.254",addressParameterList));

    }

    private void initOnClick() {

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToWebsocket("");
            }
        });

        btnDisConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disConnectToWebsocket();
            }
        });
    }

    private void disConnectToWebsocket() {

        if (mWebSocketConnection.isConnected()) {
            mWebSocketConnection.disconnect();
        }
        mWebSocketConnection = null;
    }

    private String getRealTimeStringRequest(){
        String result = "";
        Device device = new Device();
        device.setObjects(parameterObjectList);
        device.setUpdateTime(5);
        Gson gson = new Gson();
        result = gson.toJson(device);
        return result;
    }

    WebSocket.WebSocketConnectionObserver connectionObserver;
    private void connectToWebsocket(String wsURL) {
        if(mWebSocketConnection == null){
            mWebSocketConnection = new WebSocketConnection();
        }
        String webSocketRealTimeDataUri = "wss://dataengine.globiots.com:443/data-engine/mobile/realtime";
        final String realTimeStringRequest = getRealTimeStringRequest();
        try {

            connectionObserver =
                    new WebSocket.WebSocketConnectionObserver() {
                        @Override
                        public void onOpen() {
                            mWebSocketConnection.sendTextMessage(realTimeStringRequest);
                        }

                        @Override
                        public void onClose(WebSocketCloseNotification code, String reason) {
                            Log.d("REALTIME_ON_CLOSE", reason);
                        }

                        @Override
                        public void onTextMessage(String payload) {
                            Log.d("REALTIME", payload);
                        }

                        @Override
                        public void onRawTextMessage(byte[] payload) {

                        }

                        @Override
                        public void onBinaryMessage(byte[] payload) {

                        }
                    };

            WebSocketOptions websocketOptions = new WebSocketOptions();
            websocketOptions.setSocketConnectTimeout(15000);//ms ~ 15 s
            websocketOptions.setSocketReceiveTimeout(15000);//ms ~ 15 s

            mWebSocketConnection.connect(
                    new URI(webSocketRealTimeDataUri),
                    connectionObserver,
                    websocketOptions);

        }catch (Exception e){
            Log.w("ERROR",e.toString());
        }
    }

    private void initView() {

        btnConnect = findViewById(R.id.btnConnect);
        btnDisConnect = findViewById(R.id.btnDisConnect);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebSocketConnection.isConnected()) {
            mWebSocketConnection.disconnect();
        }
        mWebSocketConnection = null;
    }
}
