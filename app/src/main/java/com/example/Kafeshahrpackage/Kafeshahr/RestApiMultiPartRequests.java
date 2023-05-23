package com.example.Kafeshahrpackage.Kafeshahr;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.beloo.widget.chipslayoutmanager.util.log.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.HttpMultipartMode;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;

import static android.net.sip.SipErrorCode.SERVER_ERROR;

/**
 * Created by Maliheh on 10/04/2018.
 */

public class RestApiMultiPartRequests<T> extends Request<T> {

    private final Map<String, String> mStringParts;
    private final Map<String, File> mFileParts;
    private MultipartEntityBuilder mBuilder;
    private final Response.Listener<T> mListener;

    public RestApiMultiPartRequests(String url,
                                    Map<String, String> stringParts,
                                    Map<String, File> fileParts,
                                    Response.Listener<T> listener,
                                    Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        mListener = listener;
        mStringParts = stringParts;
        mFileParts = fileParts;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
        if (mBuilder != null) {
            mBuilder = null;
        }
        mBuilder = MultipartEntityBuilder.create();
        mBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        mBuilder.setBoundary("_____" + Long.toString(System.currentTimeMillis()) + "_____");
        mBuilder.setCharset(Consts.UTF_8);
        if (mStringParts != null) {
            for (Map.Entry<String, String> entry : mStringParts.entrySet()) {
                mBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create("text/plain", Charset.forName("UTF-8")));
            }
        }

        Log.e("Size", "Size: " + mFileParts.size());
        for (Map.Entry<String, File> entry : mFileParts.entrySet()) {
            ContentType imageContentType = ContentType.create("image/*");//MULTIPART_FORM_DATA;
            Log.d("", "Key " + entry.getKey());
            Log.d("", "Value " + entry.getValue());
            Log.d("", "Name " + entry.getValue().getName());
            //"userfile"
            mBuilder.addBinaryBody(entry.getKey(), entry.getValue(), imageContentType, entry.getValue().getName());
        }
    }

    @Override
    public String getBodyContentType() {
        return mBuilder.build().getContentType().getValue();
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            mBuilder.build().writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }


    public HttpEntity getEntity() {
        return mBuilder.build();
    }


    @SuppressWarnings("unchecked")
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>) Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);

    }










}
