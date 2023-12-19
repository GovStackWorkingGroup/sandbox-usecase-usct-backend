package global.govstack.usct.util;

import com.nimbusds.jwt.JWTParser;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Slf4j
public class JwtHttpMessageConverter
    extends AbstractGenericHttpMessageConverter<Map<String, Object>> {

  private final JwtDecoder decoder;

  public JwtHttpMessageConverter(JwtDecoder decoder) {
    super(MediaType.valueOf("application/jwt"));
    this.decoder = decoder;
  }

  @Override
  protected Map<String, Object> readInternal(
      Class<? extends Map<String, Object>> clazz, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {
    return decodeMessage(inputMessage);
  }

  @Override
  protected void writeInternal(
      Map<String, Object> stringObjectMap, Type type, HttpOutputMessage outputMessage) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Map<String, Object> read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {
    return decodeMessage(inputMessage);
  }

  @Override
  public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
    return false;
  }

  private Map<String, Object> decodeMessage(HttpInputMessage inputMessage) throws IOException {
    final String token = new String(inputMessage.getBody().readNBytes(100_000));
    log.info("Got token {}", token);
    try {
      return decoder.decode(token).getClaims();
    } catch (BadJwtException e) {
      log.warn("Validation failed for token {} : {}", token, e.getMessage());
      try {
        return JWTParser.parse(token).getJWTClaimsSet().getClaims();
      } catch (ParseException ex) {
        throw new IOException(e);
      }
    }
  }
}
