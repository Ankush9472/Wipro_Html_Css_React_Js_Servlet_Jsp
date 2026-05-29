
Servlet container plays an important here just like you jvm in core java

doGET vs doPost  -- HTTP Methods

doGet -->  Fetch data  -- > for eg : searchProducts , fetch dashboard data , pagination APIs

let's say you have passed the data in two textboxes (mail & password) and in form action you have mentioned method ="get"

It is visible on Browser (in url) and the data will be exposed ( which is not acceptable ) which is not secured
// URL( querystring)
http:/localhost:8080/LoginSystem/login?mail=abc@gmail.com&pass=admin

can be cached

doPost  --> Submit data ---> for eg : login , payment , registration

Data sent inside request body(hidden) which is safe

Not cached 

use for submit / modify the data 

https://docs.oracle.com/javaee/7/api/javax/servlet/Servlet.html 

(To follow an api)

RequestDispatcher ( is an interface that gives two methods forward(request , response) / include ()

 It is used to transfer the control inside server 

 
 forward()
 
 syntax : request.getRequestParameter("dashboard.jsp").forward(request,response);
  use it for server side transfer
 same request + response object
 URL does not change 
 for navigation  -- MVC ( architecture)
 
 include() 
 
 request.getRequestParameter("error.jsp").include(request,response);
  
 It includes the content of another resource
 response gets appended
 
 Reusable components (like header & footer)
 

sendRedirect()

response.sendRedirect("products.jsp")
client-side redirect
Browser makes a new request
URL changes


ServletConfig & ServletContext

We have different types of scopes

ServletConfig scope : Per Servlet

api key , db config for one servlet , policy for one servlet

define in web.xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.example.MyServlet</servlet-class>
    
    <!-- Servlet-specific parameter (ServletConfig) -->
    <init-param>
        <param-name>admin_email</param-name>
        <param-value>admin@example.com</param-value>
    </init-param>
</servlet>

ServletContext : Entire Application 

global userid or password or a globalkey 
logging 
<!-- Application-wide parameter (ServletContext) -->
<context-param>
    <param-name>db_name</param-name>
    <param-value>ProductionDB</param-value>
</context-param>


