import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class LanguageTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Language language = Language.valueOf("ENGLISH");
		Language language2 = Language.valueOf("CHINESE_SIMPLIFIED");
		System.out.println(language);
		System.out.println(language2);
		language = Language.fromString("en");
		language2 = Language.fromString("zh-CHS");
		Translate.setClientId("ianir");
		Translate.setClientSecret("dUap2bXo4ZYJ2JMxiHmQzA/CI54e55lNnGY+Gpx+Lr8=");
		System.out.println(language.getName(language));
		System.out.println(language2.getName(language2));
	}

}
