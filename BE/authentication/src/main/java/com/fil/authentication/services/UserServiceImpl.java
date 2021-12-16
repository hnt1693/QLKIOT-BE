package com.fil.authentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.commons.Utils;
import com.fil.authentication.constants.Messages;
import com.fil.authentication.exceptions.AuthenticationException;
import com.fil.authentication.models.Account;
import com.fil.authentication.payload.request.AccountResetPWPayload;
import com.fil.authentication.repository.AccountRepository;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private AccountRepository accountRepository;

    @Value("${swagger.authen.url}")
    private String url;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private GmailService gmailService;

    @Override
    public ResponseAPI getDetails(String name) throws Exception {
        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new Exception("Lỗi ngoại lệ"));
        return new ResponseAPI(Messages.getSuccess("thông tin người dùng"), account);
    }

    @Override
    public ResponseAPI login(Map request) throws Exception {
        try {
            String authenUrl = url + "/oauth/token";
            HttpPost post = new HttpPost(authenUrl);
            String username = request.get("username") != null ? request.get("username").toString() : null;
            String password = request.get("password") != null ? request.get("password").toString() : null;
            String authorization = request.get("authorization") != null ? request.get("authorization").toString() : null;
            String grantType = request.get("grant_type") != null ? request.get("grant_type").toString() : null;
            if (username == null || password == null || authorization == null | grantType == null) {
                throw new AuthenticationException("Thông tin đăng nhập không đúng");
            }
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("grant_type", grantType));
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, "UTF-8");
            post.setEntity(ent);
            post.setHeader("Authorization", authorization);
            HttpClient client = new DefaultHttpClient();
            HttpResponse responsePOST = client.execute(post);
            if (responsePOST.getStatusLine().getStatusCode() == 200) {
                String json = EntityUtils.toString(responsePOST.getEntity());
                return new ResponseAPI("Đăng nhập thành công", com.fil.authentication.commons.Utils.getObjectMapper().readValue(json, Map.class));
            } else {
                String json = EntityUtils.toString(responsePOST.getEntity());
                System.out.println(json);
                throw new Exception("21331");
            }
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }

    }

    @Override
    public ResponseAPI revokeToken(String token) throws Exception {
        tokenServices.revokeToken(token);
        return new ResponseAPI("Đăng xuất thành công", null);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseAPI forgotPassword(AccountResetPWPayload accountPayload) throws Exception {
        Account account = accountRepository.findByUsername(accountPayload.getUsername()).orElseThrow(() -> new Exception(Messages.notFound("tài khoản")));
        try {
            Map<String, Object> mapPassword = new HashMap<>();
            mapPassword.put("password", passwordEncoder.encode(accountPayload.getPassword()));
            mapPassword.put("timestamp", new Date().getTime());
            mapPassword.put("key", Utils.randomKey(8));
            ObjectMapper objectMapper = Utils.getObjectMapper();
            account.setForgotPassword(objectMapper.writeValueAsString(mapPassword));
            accountRepository.save(account);
            gmailService.sendResetPasswordMail(account);
            return new ResponseAPI(Messages.createSuccess("key mật khẩu"), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseAPI(Messages.updateFailed("key mật khẩu"), null);
        }
    }

    @Transactional
    @Override
    public ResponseAPI confirmChangeForgotPassword(String username, String key) throws Exception {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new Exception(Messages.notFound("tài khoản")));
        try {
            String mapForgotPW = account.getForgotPassword();
            if (mapForgotPW == null) {
                throw new Exception("Không tồn tại yêu cầu thay đổi mật khẩu");
            }
            Map<String, Object> mapPassword = Utils.getObjectMapper().readValue(mapForgotPW, Map.class);
            ;
            String keyData = mapPassword.get("key").toString();
            Long time = (Long) mapPassword.get("timestamp");
            String password = mapPassword.get("password").toString();

            if (!key.equals(keyData)) {
                throw new Exception("Khóa xác thực không đúng");
            }
            if (new Date().getTime() - time > 300000) {
                throw new Exception("Khóa xác thực quá thời hạn");
            }
            account.setPassword(password);
            account.setForgotPassword(null);
            accountRepository.save(account);
            return new ResponseAPI(Messages.updateSuccess("mật khẩu"), null);
        } catch (Exception e) {
            return new ResponseAPI(e.getMessage(), null);
        }
    }

}
