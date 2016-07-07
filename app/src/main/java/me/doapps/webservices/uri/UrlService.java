package me.doapps.webservices.uri;

/**
 * Created by frank on 30/06/16.
 */
public class UrlService {
    //Post https://sheetsu.com/apis/v1.0/020b2c0f
    public static final String IngresarDataURL="https://sheetsu.com/apis/v1.0/020b2c0f";
    //Put https://sheetsu.com/apis/v1.0/020b2c0f/:column/:value
    //https://sheetsu.com/apis/v1.0/020b2c0f/name/Lois
    public static final String ActualizarDataURL="https://sheetsu.com/apis/v1.0/020b2c0f/id/";
    //GET https://sheetsu.com/apis/v1.0/020b2c0f/search
    //https://sheetsu.com/apis/v1.0/020b2c0f/search?score=43&name=Peter
    public static final String BuscarDataURL="https://sheetsu.com/apis/v1.0/020b2c0f/search?";
    //Delete https://sheetsu.com/apis/v1.0/020b2c0f/:column/:value
    public static final String EliminarDataURL="https://sheetsu.com/apis/v1.0/020b2c0f";
    //GET https://sheetsu.com/apis/v1.0/020b2c0f
    public static final String ListarDataURL="https://sheetsu.com/apis/v1.0/020b2c0f";
}
