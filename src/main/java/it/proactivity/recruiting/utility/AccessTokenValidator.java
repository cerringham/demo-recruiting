package it.proactivity.recruiting.utility;

import it.proactivity.recruiting.model.AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenValidator {

    private static final String SEPARATOR = ".";
    @Autowired
    AccountUtility accountUtility;

    public Boolean validateToken(AccessToken token) {
        if (token == null) {
            return false;
        }
        String username = token.getAccount().getUsername();

        String base64Username = accountUtility.encodeString(username);

        String tokenValueFirst12 = token.getValue().substring(0, 12);

        String firstSeparator = token.getValue().substring(12, 13);

        String criptedUsername = token.getValue().substring(13, 53);

        String secondSeparator = token.getValue().substring(53, 54);

        String tokenValueLast13 = token.getValue().substring(54);

        return StringUtils.isAlpha(tokenValueFirst12) && firstSeparator.equals(SEPARATOR) &&
                criptedUsername.equals(base64Username) && secondSeparator.equals(SEPARATOR) && StringUtils.isNumeric(tokenValueLast13);
    }
}
