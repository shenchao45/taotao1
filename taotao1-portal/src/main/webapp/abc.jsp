<%--
  Created by IntelliJ IDEA.
  User: shenchao
  Date: 2016/12/19
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="./js/jquery-1.6.4.js"></script>
    <script>
        var url = "http://localhost:8081/category.json"
        function callback(data){
            var data2 = data['data'];
            console.log(data2.length);
        }
    </script>
    <script src="http://localhost:8081/category.json"></script>
</head>
<body>

</body>
</html>
