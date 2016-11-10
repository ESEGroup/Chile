package services.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * Created by ericreis on 9/29/16.
 */
public class HashService
{
    public String generateSecureHash(String original)
    {
        return BCrypt.hashpw(original, BCrypt.gensalt(12));
    }

    public boolean checkSecureHashMatch(String original, String hash)
    {
        return BCrypt.checkpw(original, hash);
    }
}
