import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

public class Practise {

  private static String removeSpecialCharacters(String rawString) {
		
		rawString = rawString.replace("\n", "")
				.replaceAll("\\p{Cntrl}", " ");
		
		CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
		utf8Decoder.onMalformedInput(CodingErrorAction.IGNORE);
		utf8Decoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(rawString);
		CharBuffer parsed;
		try {
			parsed = utf8Decoder.decode(buffer);
			rawString = parsed.toString().replaceAll("[^\\u0000-\\uFFFF]", "")
																.replaceAll("[^\\x20-\\x7e]", "").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "")
																.replaceAll("[^\\p{ASCII}]", "");
		} catch (CharacterCodingException e) {
			System.err.println("\n\nUnparsed String being returned");
			e.printStackTrace();
		}
		
		return rawString;
	}

  private static void main(String[] args) {
    String str = "";
    removeSpecialCharacters(str);
  }

}
