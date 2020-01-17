package org.tickets.germes.app.infra.util;

import com.google.common.hash.Hashing;
import java.nio.charset.Charset;

public class SecurityUtil {
    private SecurityUtil() {
    }

    public static String encryptSHA(String source) {
        return Hashing.sha256().hashString(source, Charset.forName("UTF-8")).toString();
    }
}
