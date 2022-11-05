package com.cruisecompany.util.recaptcha;

import com.cruisecompany.exception.RecaptchaException;
import com.cruisecompany.util.password.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RecaptchaVerifier {
    final static Logger logger = LogManager.getLogger(RecaptchaVerifier.class);
    private static final String USER_AGENT = "Chrome/51.0.2704.103";

    public static void verify(String token, String url, String secretKey) throws RecaptchaException {
        if (token == null || "".equals(token)) {
            throw new RecaptchaException("User not verified by recaptcha!");
        }
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secretKey + "&response="
                    + token;

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            if (!jsonObject.getBoolean("success")) throw new RecaptchaException("User not verified by recaptcha!");
        } catch (IOException e) {
            logger.error("Recaptcha error", e);
            throw new RecaptchaException("Verification went wrong!", e);
        }
    }
}
