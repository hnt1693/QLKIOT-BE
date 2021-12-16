//package com.fil.authentication.config;
//
//
//import com.fil.authentication.commons.ResponseAPI;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
///**
// * healthy translator that converts exceptions into {@link OAuth2Exception}s. The output matches the OAuth 2.0
// * specification in terms of error response format and HTTP status code.
// */
//
//@Component
//public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
//
//    @Override
//    public ResponseEntity<ResponseAPI> translate(Exception exception) throws Exception {
//        if(exception instanceof OAuth2Exception){
//            ResponseAPI responseAPI = new ResponseAPI(HttpStatus.UNAUTHORIZED, "Xác thực không thành công", null, Collections.singletonList("Thông tin xác thực không đúng"));
//            return ResponseEntity
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .body(responseAPI);
//        }else{
//            ResponseAPI responseAPI = new ResponseAPI(HttpStatus.UNAUTHORIZED, "Xác thực không thành công", null, Collections.singletonList("Truy cập bị chặn"));
//            return ResponseEntity
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .body(responseAPI);
//        }
//
//    }
//}
//
