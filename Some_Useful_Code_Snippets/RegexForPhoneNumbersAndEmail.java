public class Practise{

  public static void main(String[] args) {
    String str = "";
    
    Set<String> phoneSet = new HashSet<>();
		Set<String> emailSet = new HashSet<>();
		
    Pattern emailPattern = Pattern.compile("[-a-z0-9~!$%^&*_=+\\}\\{\\'?]+(\\.[-a-z0-9~!$%^&*_=+\\}\\{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|in))");
    Matcher emailMatcher = emailPattern.matcher(str);
    while (emailMatcher.find()) {
      emailSet.add(emailMatcher.group(0));
    }

    Pattern phonePattern = Pattern.compile("\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*", Pattern.MULTILINE);
    Matcher phoneMatcher = phonePattern.matcher(str);
    String phone = "";
    while (phoneMatcher.find()) {
      phoneSet.add(phoneMatcher.group(0));
    }
    
  }
}
