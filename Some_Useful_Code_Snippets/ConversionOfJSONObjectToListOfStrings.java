// Add all the necessary maven dependecies for JSON parsing and manipulation in the pom.xml file
public class Practise {

  // This method is responsible for converting a JSONObject string into a list of
	// strings through recursion
	// It first checks whether the value is an instance of JSONObject or not, if it
	// is then the function is called again
	// If the encountered value is an instance of JSONArray then values in the
	// JSONArray are traveresed and the function is called individually for each
	// value
	// If neither of the above conditions satisfy then the key value are added as
	// string into the list
	// The code blocks in the if statements are enclosed within try-catch to output
	// the string in case the string is causing an error

	public List<String> getIndividualPairs(JSONObject jsonObj) {
		List<String> keyValueList = new ArrayList<>();
		for (Object key : jsonObj.keySet()) {
			Object value = jsonObj.get(key.toString());

			if (value instanceof JSONObject)
				try {
					keyValueList.addAll(getIndividualPairs((JSONObject) value));
				} catch (Exception e) {
					logger.error("\n\nValue (instanceOf JSONObject) causing problem:" + value);
					e.printStackTrace();
				}
			else if (value instanceof JSONArray)
				try {
					((JSONArray) value).forEach(obj -> {
						keyValueList.addAll(getIndividualPairs((JSONObject) obj));
					});
				} catch (Exception e) {
					logger.error("\n\nValue (instanceOf JSONArray) causing problem:" + value);
					e.printStackTrace();
				}
			else
				keyValueList.add(key + ": " + value);
		}
		return keyValueList;
	}

  
  public static void main(String[] args) {
  }

}
