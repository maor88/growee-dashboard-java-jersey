/**
 * 
 */
package com.growee.startgrowee;



/**
 * @author Maor
 *
 */
	public enum MassagesFromServer {
		False("false"), True("true"), Admin("admin"),User("user"),LogOut("logOut"),LogIn("logIn"),UserNameInUse("userNameInUse");

		private String massage;

		MassagesFromServer(String massage) {
			this.massage = massage;
		}

		public String getMassage() {
			return massage;
		}
	}
	

