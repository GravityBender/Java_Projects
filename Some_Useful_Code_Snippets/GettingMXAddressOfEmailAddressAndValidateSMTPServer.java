// Add the javax.naming dependency in the pom.xml of the maven project.

public class practise {

  public static void main(String[] args) {
    
    String emailAddress = "garvit@yahoo.com";
    isAddressValid(emailAddress);
      
  }
  
  public static Map<String, Object> isAddressValid(@RequestParam("email") String address) {
		Map<String, Object> returnObj = new HashMap<>();

		if (address.contains("yahoo.co.in")) {
			returnObj.put("msg", "Address is valid");
			returnObj.put("isValid", true);
			return returnObj;
		}
    
		// Find the separator for the domain name
		int pos = address.indexOf('@');

		// If the address does not contain an '@', it's not valid
		if (pos == -1) {
			returnObj.put("msg", "Invalid Email");
			returnObj.put("isValid", false);
			return returnObj;
		}
    
		// Isolate the domain/machine name and get a list of mail exchangers
		String domain = address.substring(++pos);
		List<String> mxList = null;
		try {
			mxList = getMX(domain);
		} catch (NamingException ex) {
			returnObj.put("msg", "Naming exception");
			returnObj.put("isValid", false);
			return returnObj;
		}

		// Just because we can send mail to the domain, doesn't mean that the
		// address is valid, but if we can't, it's a sure sign that it isn't
		if (CollectionUtils.isNullOrEmpty(mxList)) {
			returnObj.put("msg", "No mail exchanger found");
			returnObj.put("isValid", false);
			return returnObj;
		}

		// Now, do the SMTP validation, try each mail exchanger until we get
		// a positive acceptance. It *MAY* be possible for one MX to allow
		// a message [store and forwarder for example] and another [like
		// the actual mail server] to reject it. This is why we REALLY ought
		// to take the preference into account.
		for (int mx = 0; mx < mxList.size(); mx++) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>");
			System.out.println((String) mxList.get(mx));
			System.out.println("<<<<<<<<<<<<<<<<<<<<");
		}
		for (int mx = 0; mx < mxList.size(); mx++) {
			boolean valid = false;
			try {
				int res;
				//
				Socket skt = new Socket((String) mxList.get(mx), 25);
				BufferedReader rdr = new BufferedReader(new InputStreamReader(skt.getInputStream()));
				BufferedWriter wtr = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));
				
				res = hear(rdr);
				System.out.println(res);
				if (res != 220) {
					returnObj.put("msg", "Invalid header");
					returnObj.put("isValid", false);
					return returnObj;
				}
				say(wtr, "EHLO rgagnon.com");

				res = hear(rdr);
				if (res != 250) {
					returnObj.put("msg", "Not ESMTP");
					returnObj.put("isValid", false);
					return returnObj;
				}

				// validate the sender address
				say(wtr, "MAIL FROM: <tim@orbaker.com>");
				res = hear(rdr);
				if (res != 250) {
					returnObj.put("msg", "Sender rejected");
					returnObj.put("isValid", false);
					return returnObj;
				}

				say(wtr, "RCPT TO: <" + address + ">");
				res = hear(rdr);

				// be polite
				say(wtr, "RSET");
				hear(rdr);
				say(wtr, "QUIT");
				hear(rdr);
				if (res != 250) {
					returnObj.put("msg", "Sender rejected");
					returnObj.put("isValid", false);
					return returnObj;
				}
				valid = true;
				rdr.close();
				wtr.close();
				skt.close();
			} catch (Exception ex) {
				// Do nothing but try next host
				ex.printStackTrace();
			} finally {
				if (valid) {
					returnObj.put("msg", "Address is valid");
					returnObj.put("isValid", true);
					return returnObj;
				}
			}
		}
		returnObj.put("msg", "Address not valid");
		returnObj.put("isValid", false);
		return returnObj;
	}
  
  private static List<String> getMX(String hostName) throws NamingException {
		// Perform a DNS lookup for MX records in the domain
		Hashtable env = new Hashtable();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		DirContext ictx = new InitialDirContext(env);
		Attributes attrs = ictx.getAttributes(hostName, new String[] { "MX" });
		Attribute attr = attrs.get("MX");

		// if we don't have an MX record, try the machine itself
		if ((attr == null) || (attr.size() == 0)) {
			attrs = ictx.getAttributes(hostName, new String[] { "A" });
			attr = attrs.get("A");
			if (attr == null)
				throw new NamingException("No match for name '" + hostName + "'");
		}
		// Huzzah! we have machines to try. Return them as an array list
		// NOTE: We SHOULD take the preference into account to be absolutely
		// correct. This is left as an exercise for anyone who cares.
		ArrayList res = new ArrayList();
		NamingEnumeration en = attr.getAll();

		while (en.hasMore()) {
			String mailhost;
			String x = (String) en.next();
			String f[] = x.split(" ");
			// THE fix *************
			if (f.length == 1)
				mailhost = f[0];
			else if (f[1].endsWith("."))
				mailhost = f[1].substring(0, (f[1].length() - 1));
			else
				mailhost = f[1];
			// THE fix *************
			res.add(mailhost);
		}
		return res;
	}
}

}
