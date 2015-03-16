Name:Dechen Deng								Name:Rucha Katakwar
Email id:dengdechen@gmail.com 					Email id:rucha.b@gmail.com 
Student ID:009877075							Student ID:006761859




How to run the app:
1.Import the project in any of the ide: STS or IntelliJ.
2.Ensure that it has Tomcat or any of the server instances.
3.Ensure that the web.xml in Tomcat has this param:
<init-param>
            <param-name>readonly</param-name>
            <param-value>false</param-value>
</init-param>
By default it is set to true and the DELETE method is disabled.
To enable DELETE method add readonly param and set it to false.
4.Right click on the project and Run as -> Run on Server.
5.http://localhost:8080/cmpe275/homepage


