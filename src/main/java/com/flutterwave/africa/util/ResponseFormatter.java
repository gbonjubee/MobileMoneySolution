/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flutterwave.africa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import javax.ws.rs.core.Response;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author gbonjubolaamuda
 */
public class ResponseFormatter {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ResponseFormatter.class.getName());
    StringBuilder result = new StringBuilder();

    public Response formatResponse(HttpResponse response) {
        LOG.info(response.getStatusLine().getStatusCode());
        try {
            if (response.getStatusLine().getStatusCode() != 200) {

                if (response.getEntity().getContentLength() > 0) {
                    Header contentType = response.getEntity().getContentType();
                    if (!contentType.getValue().contains("json")) {
                        JSONObject coreresp = new JSONObject();
                        coreresp.put("responsecode", Util.ERROR_RESPONSE_CODE);
                        coreresp.put("responsemessage", Util.ERROR_RESPONSE_CORE);
                        return Response.status(Response.Status.OK).entity(StandardRestResponse.sendErrorMessage(coreresp)).build();

                    }

                    BufferedReader rd = new BufferedReader(
                            new InputStreamReader(response.getEntity().getContent()));

                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    LOG.info(result.toString());
                    return Response.status(Response.Status.OK).entity(StandardRestResponse.sendErrorMessage(new JSONObject(result.toString()))).build();

                } else {

                    if (response.getStatusLine().getStatusCode() == 409) {
                        JSONObject dupresp = new JSONObject();
                        dupresp.put("responsecode", Util.ERROR_RESPONSE_CODE);
                        dupresp.put("responsemessage", Util.ERROR_RESPONSE_DUPLICATE);
                        return Response.status(Response.Status.OK).entity(StandardRestResponse.sendErrorMessage(dupresp)).build();

                    }
                    //Content-Type: application/json
                    JSONObject jsonresp = new JSONObject();
                    jsonresp.put("responsecode", Util.ERROR_RESPONSE_CODE);
                    jsonresp.put("responsemessage", Util.ERROR_RESPONSE_MSG);
                    return Response.status(Response.Status.OK).entity(StandardRestResponse.sendErrorMessage(jsonresp)).build();

                }
            } else {
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                LOG.info(result.toString());
                return Response.status(Response.Status.OK).entity(StandardRestResponse.sendSuccessMessage(new JSONObject(result.toString()))).build();
            }
        } catch (IOException ex) {
            LOG.error(Arrays.toString(ex.getStackTrace()), ex);
        }
        return null;
    }
    
     public String formatJSON(HttpResponse response){
           Header contentTypeHeader = response.getFirstHeader("Content-Type");
            if (contentTypeHeader.getValue().contains("application/json")) {

                BufferedReader rd = null;
               try {
                   rd = new BufferedReader(
                           new InputStreamReader(response.getEntity().getContent()));
                   String line;
                   while ((line = rd.readLine()) != null) {
                       result.append(line);
                   }  LOG.info(result.toString());
                   return result.toString();
               } catch (IOException ex) {
                 LOG.error(Arrays.toString(ex.getStackTrace()), ex);
               } catch (UnsupportedOperationException ex) {
                    LOG.error(Arrays.toString(ex.getStackTrace()), ex);
               } finally {
                   try {
                       rd.close();
                   } catch (IOException ex) {
                       LOG.error(Arrays.toString(ex.getStackTrace()), ex);
                   }
               }
            } else {
                return StandardRestResponse.sendErrorMessage("Invalid response returned from core");
            }
        return null;
    }
}
